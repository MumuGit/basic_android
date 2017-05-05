package com.yb.demo.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yb.demo.R;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;

/**
 * Created by Administrator on 2017/4/20 0020.
 */

public class FirstFragment extends Fragment {
    private LineChartView mLineChart;
    private ColumnChartView mColumnChart;
    private LineChartData data;
    private List<Line> lines = new ArrayList<>();
    private ColumnChartData cData;
    private List<Column> columns = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_first, container, false);
        mLineChart = (LineChartView) v.findViewById(R.id.line_chart);
        mColumnChart = (ColumnChartView) v.findViewById(R.id.column_chart);
        mLineChart.setBackgroundColor(Color.CYAN);
        generateData();
        return v;
    }

    private void generateData() {
        List<PointValue> points = new ArrayList<>();
        List<SubcolumnValue> cValues = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            float x=i*1f;
            float y=(float) (Math.random() * 100);
            PointValue point = new PointValue(x,y);
            points.add(point);
            SubcolumnValue cValue=new SubcolumnValue(y, Color.CYAN);
            cValues.add(cValue);
        }
        Line line = new Line(points);
        line.setColor(Color.WHITE);
        lines.clear();
        lines.add(line);
        data = new LineChartData(lines);
        mLineChart.setLineChartData(data);
        Column column=new Column(cValues);
        columns.clear();
        columns.add(column);
        cData=new ColumnChartData(columns);
        mColumnChart.setColumnChartData(cData);
    }
}
