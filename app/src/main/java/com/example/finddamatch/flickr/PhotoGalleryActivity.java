/*
Description: makes the fragment for the flickr photos, taken from the book source code
 */
package com.example.finddamatch.flickr;


import androidx.fragment.app.Fragment;

public class PhotoGalleryActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() {
        return PhotoGalleryFragment.newInstance();
    }
}
