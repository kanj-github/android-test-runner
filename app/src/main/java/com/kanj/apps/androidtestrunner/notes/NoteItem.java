package com.kanj.apps.androidtestrunner.notes;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;

/**
 * Created by Kanj Narayan on 06/04/17.
 */

public class NoteItem implements Parcelable{
    public String assetId;
    public String circleId;
    public String text;
    public String authorFirstName;
    public String propertyId;
    public ArrayList<PhotoUrls> photos;
    public String timestamp;
    public boolean isUserAuthor;

    // Use this constructor to create this object from server response
    public NoteItem(String assetId, String circleId, String text, String authorFirstName,
        String propertyId, ArrayList<PhotoUrls> photos, String timestamp, boolean isUserAuthor) {
        this.assetId = assetId;
        this.circleId = circleId;
        this.text = text;
        this.authorFirstName = authorFirstName;
        this.propertyId = propertyId;
        this.photos = photos;
        this.timestamp = timestamp;
        this.isUserAuthor = isUserAuthor;
    }

    public NoteItem(Parcel in) {
        String[] array = new String[6];
        in.readStringArray(array);

        this.assetId = array[0];
        this.circleId = array[1];
        this.text = array[2];
        this.authorFirstName = array[3];
        this.propertyId = array[4];
        this.timestamp = array[5];

        this.photos = (ArrayList<PhotoUrls>) in.readSerializable();

        boolean[] booleans = new boolean[1];
        in.readBooleanArray(booleans);
        this.isUserAuthor = booleans[0];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        String[] array = new String[] {
            this.assetId,
            this.circleId,
            this.text,
            this.authorFirstName,
            this.propertyId,
            this.timestamp
        };
        parcel.writeStringArray(array);

        parcel.writeSerializable(photos);

        parcel.writeBooleanArray(new boolean[] {this.isUserAuthor});
    }

    public static final Parcelable.Creator<NoteItem> CREATOR = new Parcelable.Creator<NoteItem>() {
        @Override
        public NoteItem createFromParcel(Parcel parcel) {
            return new NoteItem(parcel);
        }

        @Override
        public NoteItem[] newArray(int i) {
            return new NoteItem[i];
        }
    };
}
