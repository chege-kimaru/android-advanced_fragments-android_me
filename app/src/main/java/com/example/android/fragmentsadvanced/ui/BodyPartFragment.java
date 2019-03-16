package com.example.android.fragmentsadvanced.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.fragmentsadvanced.R;
import com.example.android.fragmentsadvanced.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin Kimaru Chege on 6/25/2017.
 */

public class BodyPartFragment extends Fragment {

    //final strings to store state information about the list of images and list indexes
    public static final String IMAGE_ID_LIST = "image_ids";
    public static final String LIST_INDEX = "list_index";

    private static final String TAG = "BodyPartFragment";

    //variables to store a list of images and index of the image that this fragment displays
    private List<Integer> mImageIds;
    private int mListIndex;

    //Mandatory for instsnciating the fragment
    public BodyPartFragment() {
    }

    //inflates the fragment layout and sets any image resources
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //load the saved state (list of images and indexes) if there is one
        if (savedInstanceState != null) {
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_INDEX);
        }


        //inflate the android-me fragment layout
        View rootView = inflater.inflate(R.layout.fragment_body_part, container, false);

        //Get a reference to the image view in the fragment layout
        final ImageView imageView = (ImageView) rootView.findViewById(R.id.body_part_image_view);

        if (mImageIds != null) {
            //set the image resource to display
            imageView.setImageResource(mImageIds.get(mListIndex));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListIndex < mImageIds.size()-1) {
                        mListIndex++;
                    } else {
                        mListIndex = 0;
                    }
                    //set the new image resource to display
                    imageView.setImageResource(mImageIds.get(mListIndex));
                }
            });
        } else {
            Log.v(TAG, "This fragment has a null list of image ids");
        }

        return rootView;
    }

    //setter methods for keeping track of the list images this fragment can display and which
    //image in th list is currently being displayed
    public void setImageIds(List<Integer> imageIds) {
        mImageIds = imageIds;
    }

    public void setListIndex(int listIndex) {
        mListIndex = listIndex;
    }

    //save the current state of this fragment
    @Override
    public void onSaveInstanceState(Bundle currentState) {
            currentState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
            currentState.putInt(LIST_INDEX, mListIndex);
    }
}
