package com.example.zhangshun.activityandfragmentlifecycle;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentFragment extends Fragment {


    public ContentFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("ContentFragment", "onAttach Called");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ContentFragment", "onCreate Called");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        Log.d("ContentFragment", "onCreateView Called");

        return inflater.inflate(R.layout.fragment_content, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("ContentFragment", "onActivityCreated Called");
    }


    @Override
    public void onStart() {
        super.onStart();
        Log.d("ContentFragment", "onStart Called");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("ContentFragment", "onResume Called");
    }


    @Override
    public void onPause() {
        super.onPause();
        Log.d("ContentFragment", "onPause Called");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("ContentFragment", "onStop Called");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("ContentFragment", "onDestroyView Called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("ContentFragment", "onDetach Called");
    }
}
