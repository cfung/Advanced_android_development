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

import java.util.List;

/**
 * Created by cfung on 4/23/18.
 */

public class BodyPartFragment extends Fragment {

    private List<Integer> mImageIds;
    private int mlistIndex;
    public final static String TAG = "MyActivity";


    public BodyPartFragment() {
    }

    public void setImageIds(List<Integer> ids){
        mImageIds = ids;
    }

    public void setImageIdsIndex(int idx){
        mlistIndex = idx;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        // display the first image - will update later
        //imageView.setImageResource(AndroidImageAssets.getHeads().get());
        if (mImageIds != null){
            imageView.setImageResource(mImageIds.get(mlistIndex));
        } else {
            Log.v(TAG, "imageId is null...");
        }


        return rootView;

    }


}
