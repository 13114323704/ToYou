package com.hq.toyou;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.dk.view.patheffect.PathTextView;
import com.hq.toyou.heartview.HeartView;
import com.linroid.filtermenu.library.FilterMenu;
import com.linroid.filtermenu.library.FilterMenuLayout;

public class MainActivity extends AppCompatActivity {

    private HeartView heartView;
    private Handler handler=new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        final PathTextView mPathTextView = (PathTextView) findViewById(R.id.path);
        mPathTextView.setPaintType(PathTextView.Type.SINGLE);
        //没有效果，原因未知
        mPathTextView.setDuration(5000);
        mPathTextView.setTextSize(70f);
        mPathTextView.setTextWeight(13);
        mPathTextView.setTextColor(Color.parseColor("#00BFFF"));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPathTextView.init("To HRX");
            }
        },5000);

        heartView=(HeartView)this.findViewById(R.id.heartView);

        FilterMenuLayout layout = (FilterMenuLayout) findViewById(R.id.filter_menu);
        FilterMenu menu = new FilterMenu.Builder(this)
                .addItem(R.mipmap.emoji1)
                .addItem(R.mipmap.emoji2)
                .addItem(R.mipmap.emoji3)
                .addItem(R.mipmap.emoji4)
        //.inflate(R.menu....)//inflate  menu resource
        .attach(layout)
                .withListener(new FilterMenu.OnMenuChangeListener() {
                    @Override
                    public void onMenuItemClick(View view, int position) {
                        switch (position){
                            case 0:
                                mPathTextView.init("To HRX");
                                try{
                                    heartView.reDraw();
                                }catch (Exception e){
                                    Toast.makeText(MainActivity.this,"老婆，别急呀，先让花花绽放完毕嘛",Toast.LENGTH_LONG).show();
                                }
                                break;

                            case 1:
                                mPathTextView.init("Love You");
                                break;

                            case 2:
                                mPathTextView.init("For");
                                break;

                            case 3:
                                mPathTextView.init("Every Day");
                                break;

                            default:
                                break;
                        }
                    }
                    @Override
                    public void onMenuCollapse() {

                    }
                    @Override
                    public void onMenuExpand() {

                    }
                })
                .build();


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK)
        {
            if(heartView.isDrawing){
                Toast.makeText(MainActivity.this,"老婆，别急呀，先让花花绽放完毕嘛！",Toast.LENGTH_LONG).show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }


}
