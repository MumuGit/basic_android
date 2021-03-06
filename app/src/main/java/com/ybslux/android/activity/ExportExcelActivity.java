package com.ybslux.android.activity;

import android.Manifest;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.ybslux.android.R;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * 导出excel
 */

public class ExportExcelActivity extends Activity {
    TextView create_tv;
    Thread mThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel);
        create_tv = (TextView) findViewById(R.id.create_tv);
        create_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mThread.start();
            }
        });
        //excel的读写均可使用此工具，此处未做内存优化处理
        mThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, 0);
                }
                File file = new File(Environment.getExternalStorageDirectory() + "/download/" + "yb_" + Calendar.getInstance(Locale.CHINA).getTimeInMillis() + ".xls");
                if (file.exists()) {
                    file.delete();
                }
                WritableWorkbook workbook = null;
                try {
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
