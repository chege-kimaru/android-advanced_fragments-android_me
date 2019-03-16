package com.example.android.fragmentsadvanced.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.fragmentsadvanced.R;
import com.example.android.fragmentsadvanced.data.AndroidImageAssets;

/**
 * Created by Kevin Kimaru Chege on 6/25/2017.
 */

public class MasterListFragment extends Fragment {

    //Define a new interface onImageclickListener to trigger a callback in the host activity
    OnImageClickListener mCallback;

    //onImageclickListener interface triggers a method in the host activity named onImageSelected
    public interface OnImageClickListener {
        void OnImageSelected(int position);
    }

    //override onAttach to make sure that the container activity has implemented the callback
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //This makes sure that the host activity has implemented the callback interface if not it throws
        //an exception
        try {
            mCallback = (OnImageClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnImageClickListener");
        }

    }

    //Mandatory empty constructor
    public  MasterListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);

        GridView gridView = (GridView) rootView.findViewById(R.id.images_grid_view);

        //create the adapter
        MasterListAdapter mAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());

        //set the adapter on the gridview
        gridView.setAdapter(mAdapter);

        //set a click listener  on the gridview and trigger the callback onImageSelected when an item is clicked
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //trigger the calback method and pass in the position that was clicked
                mCallback.OnImageSelected(position);
            }
        });



        return rootView;

    }
}
