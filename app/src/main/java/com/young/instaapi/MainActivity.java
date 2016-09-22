package com.young.instaapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.young.instaapi.data.model.MediaItem;
import com.young.instaapi.data.remote.DetailedInstaCatApi;
import com.young.instaapi.data.response.InstaMediaResponse;
import com.young.instaapi.util.EndlessRecyclerViewScrollListener;
import com.young.instaapi.util.NetworkModule;
import com.young.instaapi.util.RxUtil;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public RecyclerView mRecyclerview;
    public DetailedInstaShopRcvAdapter mDetailedInstaCatRcvAdapter;
    private Subscription mSubscription;
    private String TAG = "DetailedInstaShop";
    private String website;
    List<MediaItem> items;
    private int current_page = 1;
    private String maxId = "0";
    private int addMaxId = 19;
    private boolean moreAvailable;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate");
        setContentView(R.layout.activity_main);
        String instaId = "hyeri_0609";
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        setRecyclerview(instaId);

    }
    private void setRecyclerview(final String instaId){
        mRecyclerview = (RecyclerView) findViewById(R.id.detailed_insta_cat_recyclerview);

        GridLayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override public int getSpanSize(int position) {
                return (position == 0) ? 3 : 1;
            }
        });
        mRecyclerview.addOnScrollListener(new EndlessRecyclerViewScrollListener(mLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {

                if(moreAvailable == true) {
                    current_page += 1;
                    maxId = items.get(addMaxId).id;
                    getData(instaId);
                    addMaxId += 20;
                }
            }
        });
        mRecyclerview.setLayoutManager(mLayoutManager);
        getData(instaId);

    }



    private void getData(final String instaId) {
        Log.d(TAG, "setRecyclerview");
        DetailedInstaCatApi instaCatApi = NetworkModule.createService(DetailedInstaCatApi.class);
        Observable<InstaMediaResponse> call = instaCatApi.getInstaCatMedia(instaId, maxId);
        mSubscription = call
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<InstaMediaResponse>() {
                    @Override
                    public void onCompleted() {
                        progressBar.setVisibility(View.GONE);
                        Log.d("AAAA", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("AAAA", e.toString());
                    }

                    @Override
                    public void onNext(InstaMediaResponse instaMediaResponse) {
                        moreAvailable= instaMediaResponse.moreAvailable;
                        if(current_page <=1) {
                            items = instaMediaResponse.items;
                            mDetailedInstaCatRcvAdapter = new DetailedInstaShopRcvAdapter(getApplicationContext(),
                                    instaMediaResponse.items, instaId);
                            mRecyclerview.setAdapter(mDetailedInstaCatRcvAdapter);
                        }else {
                            //Load data
                            items.addAll(instaMediaResponse.items);
                            mDetailedInstaCatRcvAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    @Override public void onDestroy() {
        super.onDestroy();
        RxUtil.unsubscribe(mSubscription);
    }
}
