package com.example.helloworld;

import android.os.Parcel;
import android.os.Parcelable;

public class MatchItem implements Parcelable {

        private String imageUrl;
        private String name;
        private boolean liked;
        private String uid;
        private String occupation;
        private String description;

        public MatchItem() {

        }

        protected MatchItem(Parcel in) {
            imageUrl = in.readString();
            name = in.readString();
            liked = in.readByte() != 0;
            uid = in.readString();
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

        public String getImageUrl() { return imageUrl; }
        public String getName() {
            return name;
        }
        public boolean isLiked() {
            return liked;
        }
        public void setLiked(boolean liked) {
            this.liked = liked;
        }
        public String getUid() {
            return uid;
        }
        public void setUid(String uid) {
        this.uid = uid;
    }
        public String getOccupation() {
            return occupation;
        }
        public String getDescription() {
            return description;
        }

        @Override
        public int describeContents() { return 0; }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(imageUrl);
            parcel.writeString(name);
            parcel.writeString(description);
            parcel.writeByte((byte) (liked ? 1 : 0));
        }

}