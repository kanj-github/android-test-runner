package com.kanj.apps.androidtestrunner.notes;

import java.io.Serializable;

/**
 * Created by Kanj Narayan on 06/04/17.
 */

public class PhotoUrls implements Serializable{
    public String url;
    public String thumbnailUrl;
    public int sequenceNumber;
    public boolean deleted;

    public PhotoUrls(AssetUrl assetUrl) {
        this.url = assetUrl.url;
        this.thumbnailUrl = assetUrl.thumbnailUrl;
        this.sequenceNumber = assetUrl.sequenceNumber;
        this.deleted = false;
    }
}
