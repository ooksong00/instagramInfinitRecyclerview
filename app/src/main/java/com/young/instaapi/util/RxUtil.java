package com.young.instaapi.util;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public class RxUtil {

    public static void unsubscribe(Subscription subscription) {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
    public static void unSubscriptionsSubscribe(CompositeSubscription subscriptions) {
        if (subscriptions != null && !subscriptions.isUnsubscribed()) {
            subscriptions.unsubscribe();
        }
    }
}