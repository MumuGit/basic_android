package com.ybslux.android.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.ybslux.android.util.RxBarCode;
import com.ybslux.android.util.SaveBitmapUtil;
import com.ybslux.android.R;

import cn.bingoogolapple.qrcode.core.BGAQRCodeUtil;
import cn.bingoogolapple.qrcode.zxing.QRCodeDecoder;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

/**
 * 生成二维码
 */
public class QRCodeGenerateActivity extends AppCompatActivity {
    private ImageView mChineseIv;
    private ImageView mEnglishIv;
    private ImageView mChineseLogoIv;
    private ImageView mEnglishLogoIv;
    private ImageView mIvNumberBar;
    private ImageView mIvEnglishBar;
    private EditText mEtNumber;
    private EditText mEtEnglish;

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            String s = (String) msg.obj;
            Toast.makeText(QRCodeGenerateActivity.this, s, Toast.LENGTH_SHORT).show();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_generate);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));

        initView();
        createQRCode();
    }

    private void initView() {
        mChineseIv = (ImageView) findViewById(R.id.iv_chinese);
        mChineseLogoIv = (ImageView) findViewById(R.id.iv_chinese_logo);
        mEnglishIv = (ImageView) findViewById(R.id.iv_english);
        mEnglishLogoIv = (ImageView) findViewById(R.id.iv_english_logo);
        mIvNumberBar = (ImageView) findViewById(R.id.iv_number_bar);
        mIvEnglishBar = (ImageView) findViewById(R.id.iv_english_bar);
        mEtNumber = (EditText) findViewById(R.id.et_number);
        mEtEnglish = (EditText) findViewById(R.id.et_english);
    }

    private void createQRCode() {
        createChineseQRCode();
        createEnglishQRCode();
        createChineseQRCodeWithLogo();
        createEnglishQRCodeWithLogo();
        createNumberBar();
        createEnglishBar();
    }

    private void createChineseQRCode() {
        /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode("意帮", BGAQRCodeUtil.dp2px(QRCodeGenerateActivity.this, 150));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    mChineseIv.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(QRCodeGenerateActivity.this, "生成中文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void createEnglishQRCode() {
        /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                return QRCodeEncoder.syncEncodeQRCode("yibang", BGAQRCodeUtil.dp2px(QRCodeGenerateActivity.this, 150), Color.parseColor("#ff0000"));
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    mEnglishIv.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(QRCodeGenerateActivity.this, "生成英文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void createChineseQRCodeWithLogo() {
        /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                Bitmap logoBitmap = BitmapFactory.decodeResource(QRCodeGenerateActivity.this.getResources(), R.mipmap.ic_launcher);
                return QRCodeEncoder.syncEncodeQRCode("意帮", BGAQRCodeUtil.dp2px(QRCodeGenerateActivity.this, 150), Color.parseColor("#ff0000"), logoBitmap);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    mChineseLogoIv.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(QRCodeGenerateActivity.this, "生成带logo的中文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void createEnglishQRCodeWithLogo() {
        /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(Void... params) {
                Bitmap logoBitmap = BitmapFactory.decodeResource(QRCodeGenerateActivity.this.getResources(), R.mipmap.ic_launcher);
                return QRCodeEncoder.syncEncodeQRCode("yibang", BGAQRCodeUtil.dp2px(QRCodeGenerateActivity.this, 150), Color.BLACK, Color.WHITE, logoBitmap);
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    mEnglishLogoIv.setImageBitmap(bitmap);
                } else {
                    Toast.makeText(QRCodeGenerateActivity.this, "生成带logo的英文二维码失败", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void createNumberBar() {
        if (TextUtils.isEmpty(mEtNumber.getText().toString())) return;
        RxBarCode.builder(mEtNumber.getText().toString()).
                backColor(Color.TRANSPARENT).
                codeColor(Color.BLACK).
                codeWidth(1000).
                codeHeight(300).
                into(mIvNumberBar);
    }

    private void createEnglishBar() {
        if (TextUtils.isEmpty(mEtEnglish.getText().toString())) return;
        RxBarCode.builder(mEtEnglish.getText().toString()).
                backColor(Color.TRANSPARENT).
                codeColor(Color.BLACK).
                codeWidth(1000).
                codeHeight(300).
                into(mIvEnglishBar);
    }

    public void decodeChinese(View v) {
        mChineseIv.setDrawingCacheEnabled(true);
        Bitmap bitmap = mChineseIv.getDrawingCache();
        decode(bitmap, "解析中文二维码失败");
    }

    public void decodeEnglish(View v) {
        mEnglishIv.setDrawingCacheEnabled(true);
        Bitmap bitmap = mEnglishIv.getDrawingCache();
        decode(bitmap, "解析英文二维码失败");
    }

    public void decodeChineseWithLogo(View v) {
        mChineseLogoIv.setDrawingCacheEnabled(true);
        Bitmap bitmap = mChineseLogoIv.getDrawingCache();
        decode(bitmap, "解析带logo的中文二维码失败");
    }

    public void decodeEnglishWithLogo(View v) {
        mEnglishLogoIv.setDrawingCacheEnabled(true);
        Bitmap bitmap = mEnglishLogoIv.getDrawingCache();
        decode(bitmap, "解析带logo的英文二维码失败");
    }

    public void generateNumberBar(View v) {
        createNumberBar();
    }

    public void generateEnglishBar(View v) {
        createEnglishBar();
    }

    public void saveChinese(View v) {
        mChineseIv.setDrawingCacheEnabled(true);
        Bitmap bitmap = mChineseIv.getDrawingCache();
        save(bitmap);
    }

    public void saveEnglish(View v) {
        mEnglishIv.setDrawingCacheEnabled(true);
        Bitmap bitmap = mEnglishIv.getDrawingCache();
        save(bitmap);
    }

    public void saveChineseWithLogo(View v) {
        mChineseLogoIv.setDrawingCacheEnabled(true);
        Bitmap bitmap = mChineseLogoIv.getDrawingCache();
        save(bitmap);
    }

    public void saveEnglishWithLogo(View v) {
        mEnglishLogoIv.setDrawingCacheEnabled(true);
        Bitmap bitmap = mEnglishLogoIv.getDrawingCache();
        save(bitmap);
    }

    public void saveNumberBar(View v) {
        mIvNumberBar.setDrawingCacheEnabled(true);
        Bitmap bitmap = mIvNumberBar.getDrawingCache();
        save(bitmap);
    }

    public void saveEnglishBar(View v) {
        mIvEnglishBar.setDrawingCacheEnabled(true);
        Bitmap bitmap = mIvEnglishBar.getDrawingCache();
        save(bitmap);
    }

    private void save(Bitmap bitmap) {
        SaveBitmapUtil.getInstance(this).save(bitmap, new SaveBitmapUtil.saveListener() {
            @Override
            public void result(Bitmap bitmap, String msg) {
                Message message = new Message();
                message.obj = msg;
                handler.sendMessage(message);
            }
        });

    }

    public void decodeIsbn(View v) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.test_isbn);
        decode(bitmap, "解析ISBN失败");
    }

    private void decode(final Bitmap bitmap, final String errorTip) {
        /*
        这里为了偷懒，就没有处理匿名 AsyncTask 内部类导致 Activity 泄漏的问题
        请开发在使用时自行处理匿名内部类导致Activity内存泄漏的问题，处理方式可参考 https://github.com/GeniusVJR/LearningNotes/blob/master/Part1/Android/Android%E5%86%85%E5%AD%98%E6%B3%84%E6%BC%8F%E6%80%BB%E7%BB%93.md
         */
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return QRCodeDecoder.syncDecodeQRCode(bitmap);
            }

            @Override
            protected void onPostExecute(String result) {
                if (TextUtils.isEmpty(result)) {
                    Toast.makeText(QRCodeGenerateActivity.this, errorTip, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(QRCodeGenerateActivity.this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }
}