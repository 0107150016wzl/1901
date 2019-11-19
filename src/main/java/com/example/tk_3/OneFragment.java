package com.example.tk_3;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {


    private SmartRefreshLayout smartRefrsesh;
    private RecyclerView recycleerview;
    private OnfragmentAdapter adapter;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_one, container, false);
        initView(inflate);
        return inflate;
    }

    private void initView(View inflate) {
        smartRefrsesh =  smartRefrsesh.findViewById(R.id.smartRefrsesh);
        recycleerview =  recycleerview.findViewById(R.id.recycleerview);
        adapter = new OnfragmentAdapter(getActivity());
        recycleerview.setAdapter(adapter);
    }
}
