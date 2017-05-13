package com.ybslux.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ybslux.android.fragment.FirstFragment;
import com.ybslux.android.fragment.IndexFragment;
import com.ybslux.android.fragment.SecondFragment;


public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private FrameLayout contenerFl;
    private LinearLayout oneLl;
    private LinearLayout twoLl;
    private LinearLayout threeLl;
    private LinearLayout fourLl;

    private Fragment firstFragment = new FirstFragment();
    private Fragment secondFragment = new SecondFragment();
    private Fragment indexFragment = new IndexFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contenerFl = (FrameLayout) findViewById(R.id.contener_fl);
        oneLl = (LinearLayout) findViewById(R.id.one_ll);
        twoLl = (LinearLayout) findViewById(R.id.two_ll);
        threeLl = (LinearLayout) findViewById(R.id.three_ll);
        fourLl = (LinearLayout) findViewById(R.id.four_ll);
        oneLl.setOnClickListener(this);
        twoLl.setOnClickListener(this);
        threeLl.setOnClickListener(this);
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ljl);
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            @Override
//            public void onGenerated(Palette palette) {
//                oneLl.setBackgroundColor(palette.getDominantSwatch().getRgb());
//                twoLl.setBackgroundColor(palette.getMutedSwatch().getRgb());
//                threeLl.setBackgroundColor(palette.getVibrantSwatch().getRgb());
//                fourLl.setBackgroundColor(palette.getDarkMutedSwatch().getRgb());
//                contenerFl.setBackgroundColor(palette.getLightMutedSwatch().getRgb());
//            }
//        });
        getSupportFragmentManager().beginTransaction().replace(R.id.contener_fl, indexFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one_ll:
                getSupportFragmentManager().beginTransaction().replace(R.id.contener_fl, indexFragment).commit();
                break;
            case R.id.two_ll:
                getSupportFragmentManager().beginTransaction().replace(R.id.contener_fl, secondFragment).commit();
                break;
            case R.id.three_ll:
                getSupportFragmentManager().beginTransaction().replace(R.id.contener_fl, firstFragment).commit();
                break;
            default:
                break;
        }
    }
}
