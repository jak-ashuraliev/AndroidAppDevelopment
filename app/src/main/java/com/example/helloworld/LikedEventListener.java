package com.example.helloworld;

public interface LikedEventListener<T> {
    void updateLiked(MatchItem item);
    void onSuccess(T dataResponse);
    void onFailure();
}