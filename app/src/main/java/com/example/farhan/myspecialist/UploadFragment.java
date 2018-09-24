package com.example.farhan.myspecialist;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public class UploadFragment extends Fragment {

    public UploadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Intent myIntent = new Intent(getActivity(), UploadActivity.class);
        getActivity().startActivity(myIntent);

        return inflater.inflate(R.layout.fragment_upload, container, false);
    }
}