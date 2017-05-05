package com.yb.demo.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.yb.demo.BuildConfig;
import com.yb.demo.R;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Locale;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * Created by Administrator on 2017/4/27 0027.
 */

public class ExportExcelActivity extends Activity {
    TextView create_tv;
    Thread mThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_second);
        create_tv = (TextView) findViewById(R.id.create_tv);
        create_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThread.start();
            }
        });
        Log.e("ExportExcelActivity", "permission");
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, 0);
                }
                Log.e("ExportExcelActivity", "permission");
                File file = new File(Environment.getExternalStorageDirectory() + "/download/" + "yb_" + Calendar.getInstance(Locale.CHINA).getTimeInMillis() + ".xls");
                if (file.exists()) {
                    file.delete();
                }
                WritableWorkbook workbook = null;
                try {
                    Log.e("ExportExcelActivity", "create");
                    workbook = Workbook.createWorkbook(file);
                    WritableSheet sheet = workbook.createSheet("意帮", 0);
                    for (int i = 0; i < 20; i++) {
                        for (int j = 0; j < 5; j++) {
                            Log.e("run: ", j + ":" + i);
                            Label label = new Label(j, i, j + ":" + i);
                            sheet.addCell(label);
                        }
                    }
                    workbook.write();
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (RowsExceededException e) {
                    e.printStackTrace();
                } catch (WriteException e) {
                    e.printStackTrace();
                } finally {

                }
            }
        });

    }


}
