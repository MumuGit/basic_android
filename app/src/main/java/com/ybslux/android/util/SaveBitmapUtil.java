package com.ybslux.android.util;

import android.Manifest;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

import static com.ybslux.android.util.SaveBitmapUtil.SaveBitmapCompressFormat.WEBP;

/**
 * Created by 范伟强 on 2017/4/26 0026.
 * 将bitmap转换为jpg，png，webp格式并保存到本地
 */

public class SaveBitmapUtil {

    static SaveBitmapUtil sInstance;
    Activity mActivity;

    private SaveBitmapUtil(Activity activity) {
        mActivity = activity;
    }

    public interface saveListener {
        void result(Bitmap bitmap, String msg);
    }

    public enum SaveBitmapCompressFormat {
        JPEG(".jpg"),
        PNG(".png"),
        WEBP(".webp");
        final String nativeString;

        SaveBitmapCompressFormat(String nativeString) {
            this.nativeString = nativeString;
        }
    }

    public static SaveBitmapUtil getInstance(Activity activity) {
        sInstance = new SaveBitmapUtil(activity);
        return sInstance;
    }

    public void save(Bitmap bitmap, saveListener listener) {
        save(bitmap, null, null, listener);
    }

    public void save(Bitmap bitmap, String name, saveListener listener) {
        save(bitmap, name, null, listener);
    }

    public void save(Bitmap bitmap, SaveBitmapCompressFormat type, saveListener listener) {
        save(bitmap, null, type, listener);
    }

    public void save(final Bitmap bitmap, String name, SaveBitmapCompressFormat type, final saveListener listener) {
        if (bitmap == null) {
            listener.result(null, "请传入bitmap");
            return;
        }
        if (TextUtils.isEmpty(name)) {
            name = "yibang_" + Calendar.getInstance(Locale.CHINA).getTimeInMillis();
        }
        if (type == null) {
            type = WEBP;
        }
        final Bitmap.CompressFormat bct;
        switch (type) {
            case JPEG:
                bct = Bitmap.CompressFormat.JPEG;
                break;
            case PNG:
                bct = Bitmap.CompressFormat.PNG;
                break;
            default:
                bct = Bitmap.CompressFormat.WEBP;
                break;
        }
        final String finalName = name;
        final SaveBitmapCompressFormat finalType = type;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
                try {
                    requestPermissions();
                    File file = new File(Environment.getExternalStorageDirectory() + "/download/" + finalName + finalType.nativeString);
                    if (file.exists()) {
                        file.delete();
                    }
                    FileOutputStream out;
                    out = new FileOutputStream(file);
                    bitmap.compress(bct, 100, out);
                    out.flush();
                    out.close();
                    listener.result(BitmapFactory.decodeFile(file.getAbsolutePath()), "文件已保存到:" + file.getPath());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void requestPermissions() {
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_PHONE_STATE
                , Manifest.permission.CAMERA};
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.requestPermissions(permissions, 0);
        }
    }

}
