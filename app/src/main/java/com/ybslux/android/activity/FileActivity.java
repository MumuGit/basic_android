package com.ybslux.android.activity;

import android.Manifest;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import com.ybslux.android.R;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import main.java.com.UpYun;


public class FileActivity extends AppCompatActivity {
    //空间名
    public static String SPACE = "jinlaisandbox-images";
    //操作员
    public static String OPERATER = "jinlaisandbox";
    //密码
    public static String PASSWORD = "jinlaisandbox";
    private TextView info_tv;
    UpYun upYun;
    File temp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        info_tv = (TextView) findViewById(R.id.info_tv);
        upYun = new UpYun(SPACE, OPERATER, PASSWORD);
        upYun.setDebug(true);
        upYun.setTimeout(10);
        try {
            temp = getTempFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] permissions = {Manifest.permission.INTERNET};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, 0);
        }
    }

    /**
     *
     * @return
     * @throws IOException
     */
    private File getTempFile() throws IOException {
        File temp = File.createTempFile("upyun", "test");
        temp.deleteOnExit();
        OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(temp));
        outputStream.write("just for test !".getBytes());
        outputStream.close();
        return temp;
    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            info_tv.setText(msg.obj.toString());
            return false;
        }
    });

    /**
     * 文件上传
     *
     * @param v
     */
    public void upload(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg = new Message();
                try {
                    boolean result = upYun.writeFile("/up/dd.jpg", temp);
                    if (result) {
                        msg.obj = "文件上传成功！";
                    } else {
                        msg.obj = "文件上传失败！";
                    }
                    mHandler.sendMessage(msg);
                } catch (IOException e) {
                    msg.obj = "文件上传失败！";
                    mHandler.sendMessage(msg);
                    e.printStackTrace();
                    Log.e("error", "upload: ", e);
                }
            }
        }).start();

    }

    /**
     * 获取文件目录
     *
     * @param v
     */
    public void files(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<UpYun.FolderItem> items = upYun.readDir("");
                StringBuilder sb = new StringBuilder();
                for (UpYun.FolderItem item : items) {
                    sb.append(item.name + "\t");
                    sb.append(item.date + "\t");
                    sb.append(item.size + "\n");
                }
                Message msg = new Message();
                msg.obj = sb;
                mHandler.sendMessage(msg);
            }
        }).start();
    }

    /**
     * 获取文件信息
     *
     * @param v
     */
    public void fileInfo(View v) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> items = upYun.getFileInfo("test22.png");
                StringBuilder sb = new StringBuilder();
                for (String i : items.keySet()) {
                    sb.append(i + ":" + items.get(i) + "\n");
                }
                Message msg = new Message();
                msg.obj = sb;
                mHandler.sendMessage(msg);
            }
        }).start();
    }
}
