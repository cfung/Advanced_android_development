package com.example.android.android_me.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * Created by cfung on 5/8/18.
 */

public class MasterListFragment extends Fragment{

    // completed (1) Define a new interface OnImageClickListener that triggers a callback in the host activity
    // The callback is a method named onImageSelected(int position) that contains information about
    // which position on the grid of images a user has clicked
    OnImageClickListener mCallback;

    public interface OnImageClickListener{
        void onImageSelected(int position);
    }
    // completed (2) Override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{

            mCallback = (OnImageClickListener) context;

        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnImageClickListener");
        }
    }

    public MasterListFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        MasterListAdapter adapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(adapter);

        // TODO (3) Set a click listener on the gridView and trigger the callback onImageSelected when an item is clicked


        return rootView;
    }


}
