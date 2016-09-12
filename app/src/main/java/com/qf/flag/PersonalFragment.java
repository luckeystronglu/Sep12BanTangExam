package com.qf.flag;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.sep12bantangexam.R;


/**
 * Created by lenovo on 2016/9/12.
 */
public class PersonalFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View coview = inflater.inflate(R.layout.flagment_personal,null);
        return coview;
    }
}
