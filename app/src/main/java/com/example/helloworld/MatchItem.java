package com.example.helloworld;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.firebase.firestore.Exclude;
import java.util.HashMap;
import java.util.Map;

public class MatchItem implements Parcelable {

    public String uid;
    public String name;
    public String imageUrl;
    public boolean liked ;
    public String occupation;
    public String description;

    public MatchItem() {}

    public MatchItem(String name, String imageUrl, String description, String occupation, boolean liked) {
        this.name = name;
        this.imageUrl = imageUrl;
        this.liked = liked;
        this.occupation = occupation;
        this.description = description;
    }

    public MatchItem(Parcel in) {
        name = in.readString();
        imageUrl = in.readString();
        liked = in.readByte() != 0;
        occupation = in.readString();
        description = in.readString();
    }

    public static final Creator<MatchItem> CREATOR = new Creator<MatchItem>() {
        @Override
        public MatchItem createFromParcel(Parcel in) {
            return new MatchItem(in);
        }

        @Override
        public MatchItem[] newArray(int size) {
            return new MatchItem[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put(Constants.collection_uid, uid);
        result.put(Constants.collection_name, name);
        result.put(Constants.collection_imageUrl, imageUrl);
        result.put(Constants.collection_liked, liked);
        result.put(Constants.collection_occupation, occupation);
        result.put(Constants.collection_description, description);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(imageUrl);
        parcel.writeByte((byte) (liked ? 1 : 0));
        parcel.writeString(occupation);
        parcel.writeString(description);
    }
}
