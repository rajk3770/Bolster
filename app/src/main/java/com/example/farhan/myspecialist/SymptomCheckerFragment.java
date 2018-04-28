package com.example.farhan.myspecialist;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class SymptomCheckerFragment extends Fragment {


    public SymptomCheckerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Intent myIntent = new Intent(getActivity(), SymptomCheckerActivity.class);
        getActivity().startActivity(myIntent);

        return inflater.inflate(R.layout.fragment_symptom_checker, container, false);
    }

}
