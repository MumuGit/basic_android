package com.yb.demo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yb.demo.R;
import com.yb.demo.activity.ChartActivity;
import com.yb.demo.activity.ExportExcelActivity;
import com.yb.demo.activity.FileActivity;
import com.yb.demo.activity.ImageTransferActivity;
import com.yb.demo.activity.LineChartActivity1;
import com.yb.demo.activity.LocationActivity;
import com.yb.demo.activity.QRCodeGenerateActivity;
import com.yb.demo.activity.QRCodeScanActivity;
import com.yb.demo.activity.RestRouteShowActivity;
import com.yb.demo.adapter.ContentAdapter;

import java.util.ArrayList;
import java.util.List;

public class IndexFragment extends Fragment {
    private RecyclerView content_rv;
    private List<Class> list = new ArrayList<>();
    private ContentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_index, container, false);
        content_rv = (RecyclerView) v.findViewById(R.id.content_rv);
        list.clear();
        list.add(QRCodeScanActivity.class);
        list.add(QRCodeGenerateActivity.class);
        list.add(ImageTransferActivity.class);
        list.add(ExportExcelActivity.class);
        list.add(LocationActivity.class);
        list.add(FileActivity.class);
        list.add(RestRouteShowActivity.class);
        adapter = new ContentAdapter(list);
        content_rv.setLayoutManager(new LinearLayoutManager(getContext()));
        content_rv.setAdapter(adapter);
        return v;
    }

}
