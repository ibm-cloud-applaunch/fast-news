package com.ibm.examples.yashsoni.applaunchdemo.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by yashsoni on 22/01/18.
 */
public class NewsFeedModel implements Parcelable {
    public static final Creator<NewsFeedModel> CREATOR = new Creator<NewsFeedModel>() {
        @Override
        public NewsFeedModel createFromParcel(Parcel in) {
            return new NewsFeedModel(in);
        }

        @Override
        public NewsFeedModel[] newArray(int size) {
            return new NewsFeedModel[size];
        }
    };
    public String title;
    public String desc;
    public String author;
    public String imageUrl;
    public boolean isAudioAvailable;

    public NewsFeedModel(){}

    public NewsFeedModel(Parcel in) {
        title = in.readString();
        desc = in.readString();
        author = in.readString();
        imageUrl = in.readString();
        isAudioAvailable = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(desc);
        parcel.writeString(author);
        parcel.writeString(imageUrl);
        parcel.writeByte((byte) (isAudioAvailable ? 1 : 0));
    }
}
