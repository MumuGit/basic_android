package com.ybslux.android.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ybslux.android.R;
import com.ybslux.android.util.SaveBitmapUtil;

import java.io.FileNotFoundException;

/**
 * Created by Administrator on 2017/4/24 0024.
 */

public class ImageTransferActivity extends Activity implements OnClickListener {
    private ImageView mSourceImgIv;
    private ImageView mCreatedImgIv;
    private TextView mTakePhotoTv;
    private TextView mChooseImageTv;
    private RadioGroup mImageFormatRg;
    private RadioButton mJpgRb;
    private RadioButton mPngRb;
    private RadioButton mWebpRb;
    private TextView mSaveImageTv;
    private TextView mShowImageTv;
    private SaveBitmapUtil.SaveBitmapCompressFormat mFormat = SaveBitmapUtil.SaveBitmapCompressFormat.WEBP;
    private Bitmap sourceBitmap;
    private Bitmap savedBitmap;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String s= (String) msg.obj;
            Toast.makeText(ImageTransferActivity.this, s, Toast.LENGTH_SHORT).show();
            return false;
        }
    });

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        switch (requestCode) {
            case 0:
                sourceBitmap= (Bitmap) data.getExtras().get("data");
                mSourceImgIv.setImageBitmap(sourceBitmap);
                break;
            case 1:
                Uri uri = data.getData();
                ContentResolver cr = this.getContentResolver();
                try {
                    sourceBitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    mSourceImgIv.setImageBitmap(sourceBitmap);
                } catch (FileNotFoundException e) {
                    Log.e("Exception", e.getMessage(), e);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_transfer);
        mSourceImgIv = (ImageView) findViewById(R.id.source_img_iv);
        mCreatedImgIv = (ImageView) findViewById(R.id.created_img_iv);
        mTakePhotoTv = (TextView) findViewById(R.id.take_photo_tv);
        mChooseImageTv = (TextView) findViewById(R.id.choose_image_tv);
        mImageFormatRg = (RadioGroup) findViewById(R.id.image_format_rg);
        mJpgRb = (RadioButton) findViewById(R.id.jpg_rb);
        mPngRb = (RadioButton) findViewById(R.id.png_rb);
        mWebpRb = (RadioButton) findViewById(R.id.webp_rb);
        mSaveImageTv = (TextView) findViewById(R.id.save_image_tv);
        mShowImageTv = (TextView) findViewById(R.id.show_image_tv);
        mTakePhotoTv.setOnClickListener(this);
        mChooseImageTv.setOnClickListener(this);
        mSaveImageTv.setOnClickListener(this);
        mShowImageTv.setOnClickListener(this);
        mImageFormatRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.jpg_rb:
                        mFormat = SaveBitmapUtil.SaveBitmapCompressFormat.JPEG;
                        break;
                    case R.id.png_rb:
                        mFormat = SaveBitmapUtil.SaveBitmapCompressFormat.PNG;
                        break;
                    case R.id.webp_rb:
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.take_photo_tv:
                String state = Environment.getExternalStorageState(); //拿到sdcard是否可用的状态码
                if (state.equals(Environment.MEDIA_MOUNTED)) {   //如果可用
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, 0);
                } else {
                    Toast.makeText(ImageTransferActivity.this, "sdcard不可用", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.choose_image_tv:
                String state2 = Environment.getExternalStorageState(); //拿到sdcard是否可用的状态码
                if (state2.equals(Environment.MEDIA_MOUNTED)) {   //如果可用
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/*");
                    startActivityForResult(intent, 1);
                } else {
                    Toast.makeText(ImageTransferActivity.this, "sdcard不可用", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.save_image_tv:
                String[] permissions = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                        , android.Manifest.permission.READ_PHONE_STATE
                        , android.Manifest.permission.CAMERA};
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(permissions, 0);
                }
                SaveBitmapUtil.getInstance(ImageTransferActivity.this).save(sourceBitmap, mFormat, new SaveBitmapUtil.saveListener() {
                    @Override
                    public void result(Bitmap bitmap, String msg) {
                        savedBitmap = bitmap;
                        Message message=new Message();
                        message.obj=msg;
                        handler.sendMessage(message);
                    }
                });
                break;
            case R.id.show_image_tv:
                if (savedBitmap == null) return;
                mCreatedImgIv.setImageBitmap(savedBitmap);
                break;
            default:
                break;
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
