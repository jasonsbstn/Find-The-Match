/*
Description: makes the fragment for the flickr photos, taken from the book source code
 */
package com.example.finddamatch.flickr;


import androidx.fragment.app.Fragment;

public class PhotoGalleryActivity extends Single_Fragment_Activity {

    @Override
    protected Fragment createFragment() {
        return Photo_Gallery_Fragment.newInstance();
    }
}
