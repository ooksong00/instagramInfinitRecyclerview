package com.young.instaapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.young.instaapi.data.model.MediaItem;

import java.util.List;

public class DetailedInstaShopRcvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    List<MediaItem> items;
    private String TAG = "DetailedInstaShopRcvAdapter";
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM= 1;
    private String instaId;

    //construct
    public DetailedInstaShopRcvAdapter(Context context, List<MediaItem> items, String instaId) {
        mContext = context;
        this.items = items;
        this.instaId = instaId;
        Log.d(TAG, "DetailedInstaShopRcvAdapter");
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            Uri uri = Uri.parse(items.get(position).user.getProfilePicture());
            SimpleDraweeView draweeView = headerViewHolder.profileImg;
            draweeView.setImageURI(uri);
            headerViewHolder.userId.setText(items.get(position).user.getUserName());
        } else {
            ViewHolder  viewHolder = (ViewHolder) holder;
            Log.d(TAG, "onBindViewHolder");
            Uri uri = Uri.parse(items.get(position).images.low_resolution.url);
            SimpleDraweeView draweeView = viewHolder.background;
            draweeView.setImageURI(uri);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(TYPE_HEADER == viewType) {
            View itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.detailed_insta_shop_recyclerview_header_item, parent, false);

            return new HeaderViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(mContext)
                    .inflate(R.layout.detailed_insta_shop_recyclerview_item, parent, false);

            return new ViewHolder(itemView);
        }
    }


    @Override
    public int getItemCount() {
        return items.size();
    }
    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(isPositionHeader(position))
            return TYPE_HEADER;
        return TYPE_ITEM;
    }

    //viewHolder body
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        public SimpleDraweeView background;

        public ViewHolder(View view) {
            super(view);
            Log.d(TAG, "ViewHolder");
            background = (SimpleDraweeView) view.findViewById(R.id.background);
            view.setOnClickListener(this);
        }
        public void onClick(View view) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(items.get(getPosition()).link));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.getApplicationContext().startActivity(intent);
        }

    }

    public class HeaderViewHolder extends  RecyclerView.ViewHolder implements  View.OnClickListener {
        SimpleDraweeView profileImg;
        TextView userId;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            profileImg = (SimpleDraweeView) itemView.findViewById(R.id.profile_img);
            userId = (TextView) itemView.findViewById(R.id.user_id);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(instaId == null) {
                return;
            }
            String instagramWebsite= "https://www.instagram.com/" + instaId;
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(instagramWebsite));
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.getApplicationContext().startActivity(intent);
        }
    }

}
