package com.example.android.android_me.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cfung on 4/23/18.
 */

public class BodyPartFragment extends Fragment {

    private List<Integer> mImageIds;
    private int mlistIndex;

    // completed (3) Create final Strings to store state information about the list of images and list index
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    private static final String TAG = "BodyPartFragment";


    public BodyPartFragment() {
    }

    // completed (1) Create a setter method and class variable to set and store of a list of image resources
    public void setImageIds(List<Integer> ids){
        mImageIds = ids;
    }

    // completed (2) Create another setter method and variable to track and set the index of the list item to display
    public void setListIndex(int idx){
        mlistIndex = idx;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // completed (5) Only create new fragments when there is no previously saved state
        if(savedInstanceState != null){

            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mlistIndex = savedInstanceState.getInt(LIST_INDEX);
        }

        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // display the first image - will update later
        //imageView.setImageResource(AndroidImageAssets.getHeads().get());

        // completed (3) If a list of image ids exists, set the image resource to the correct item in that list
        // Otherwise, create a Log statement that indicates that the list was not found

        if (mImageIds != null){
            imageView.setImageResource(mImageIds.get(mlistIndex));

            // completed (1) Set a click listener on the image view and on a click increment the list index and set the image resource
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (mlistIndex < mImageIds.size() - 1){
                        mlistIndex++;
                    }
                    // completed (2) If you reach the end of a list of images, set the list index back to 0 (the first item in the list)
                    imageView.setImageResource(mImageIds.get(mlistIndex));
                }
            });


        } else {
            Log.v(TAG, "imageId is null...");
        }


        return rootView;

    }

    // completed (4) Override onSaveInstanceState and save the current state of this fragment
    @Override
    public void onSaveInstanceState(Bundle currentState) {
        currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        currentState.putInt(LIST_INDEX, mlistIndex);
    }

}
