package com.pszemek.betterfy.backend.models.simplified;

/**
 * Created by Ciemek on 12/06/16.
 */
public class Image {

    /**
     * height of the fetched image in px
     *      (height in px should be the same as width)
     *      usual sizes:
     *          640px
     *          300px
     *          60px
     *          (NULL) - if image is 'Discover Weekly' or similar (fetched from u.scdn.com)
     */
    private int height;

    /**
     *  external (open) url for image resource
     *      (should be always available, without OAuth)
     *      usual sources:
     *          mosaic.scdn.co - for mosaic images
     *          u.scdn.co - image of owner with overlay of 'Discover Weekly' stuff
     *          i.scdn.co - standard image assets
     */
    private String url;

    /**
     * width of the fetched image in px.
     *      (width in px should be the same as height)
     *      usual sizes:
     *          640px
     *          300px
     *          60px
     *          (NULL) - if image is 'Discover Weekly' or similar (fetched from u.scdn.com)
     */
    private int width;

    public int getHeight() {
        return height;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }
}
