package com.kanj.apps.androidtestrunner.notes;

import android.os.Parcel;
import android.support.test.runner.AndroidJUnit4;
import com.kanj.apps.androidtestrunner.notes.AssetUrl;
import com.kanj.apps.androidtestrunner.notes.NoteItem;
import com.kanj.apps.androidtestrunner.notes.PhotoUrls;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by Kanj Narayan on 06/04/17.
 */

@RunWith(AndroidJUnit4.class)
public class NoteItemParcelableTest {
    private static final String PHOTO = "PHOTO";
    private static final String URL_1 = "URL_1";
    private static final String URL_2 = "URL_2";
    private static final String THUMBNAIL_URL_1 = "THUMBNAIL_URL_1";
    private static final String THUMBNAIL_URL_2 = "THUMBNAIL_URL_2";

    private NoteItem note;

    @Before
    public void createNote() {
        AssetUrl assetUrl1 = new AssetUrl();
        assetUrl1.sequenceNumber = 1;
        assetUrl1.url = URL_1;
        assetUrl1.thumbnailUrl = THUMBNAIL_URL_1;
        assetUrl1.type = PHOTO;

        AssetUrl assetUrl2 = new AssetUrl();
        assetUrl2.sequenceNumber = 2;
        assetUrl2.url = URL_2;
        assetUrl2.thumbnailUrl = THUMBNAIL_URL_2;
        assetUrl2.type = PHOTO;

        PhotoUrls uls1 = new PhotoUrls(assetUrl1);
        PhotoUrls uls2 = new PhotoUrls(assetUrl2);

        ArrayList<PhotoUrls> photoUrls = new ArrayList<>();
        photoUrls.add(uls1);
        photoUrls.add(uls2);

        note = new NoteItem("assetId", "circleId", "text", "name", "propertyId", photoUrls, "timestamp", true);
    }
    @Test
    public void testNoteItemParcelableImplementation() {
        Parcel parcel = Parcel.obtain();
        note.writeToParcel(parcel, 0);
        parcel.setDataPosition(0);

        NoteItem parcelledNote = NoteItem.CREATOR.createFromParcel(parcel);

        assertThat(URL_1, is(parcelledNote.photos.get(0).url));
        assertThat(URL_2, is(parcelledNote.photos.get(1).url));

        assertThat(THUMBNAIL_URL_1, is(parcelledNote.photos.get(0).thumbnailUrl));
        assertThat(THUMBNAIL_URL_2, is(parcelledNote.photos.get(1).thumbnailUrl));

        assertThat("assetId", is(parcelledNote.assetId));
        assertThat("circleId", is(parcelledNote.circleId));
        assertThat("text", is(parcelledNote.text));
        assertThat("name", is(parcelledNote.authorFirstName));
        assertThat("propertyId", is(parcelledNote.propertyId));
        assertThat("timestamp", is(parcelledNote.timestamp));
        assertThat(true, is(parcelledNote.isUserAuthor));
    }
}
