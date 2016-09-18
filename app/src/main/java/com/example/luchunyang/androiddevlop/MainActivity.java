package com.example.luchunyang.androiddevlop;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et;
    private ImageView floatImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = (EditText) findViewById(R.id.et);
    }

    /**
     * 整个Android的窗口机制是基于一个叫做 WindowManager，这个接口可以添加view到屏幕，也可以从屏幕删除view。它面向的对象一端是屏幕，另一端就是View
     * 其实我们的Activity或者Diolog底层的实现也是通过WindowManager，这个 WindowManager是全局的
     * Window是android中的窗口，表示顶级窗口的意思，也就是主窗口，它有两个实现类，PhoneWindow和MidWindow,我们一般的 activity对应的主要是PhoneWindow，在activity中经常使用的setContentView等方法也是在这个里面实现的。
     */
    private void createFloatImge() {
        WindowManager manager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.flags = layoutParams.FLAG_NOT_TOUCH_MODAL | layoutParams.FLAG_NOT_FOCUSABLE;
        layoutParams.x = 900-64;
        layoutParams.y = 1400-64;

        layoutParams.width = 64;
        layoutParams.height = 64;

        floatImg = new ImageView(this);
        floatImg.setImageResource(R.drawable.research_64);
        floatImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"点击了",Toast.LENGTH_SHORT).show();
            }
        });
        layoutParams.gravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
        manager.addView(floatImg,layoutParams);

    }

    //设置透明度（这是窗体本身的透明度，非背景） 0表示完全不透明 黑色的
    public void lightOff(View view) {
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.alpha = 0.5f;
        getWindow().setAttributes(layoutParams);
    }

    public void start2Activity(View view) {
        startActivity(new Intent(this,Main2Activity.class));
    }

    public void addFloatImage(View view) {
        createFloatImge();
    }

    public void removeFloatImg(View view){
        //移除底部float
        WindowManager manager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
        manager.removeViewImmediate(floatImg);
    }

    public void addFloatButton(View view) {
        WindowManager mWm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        Button button = new Button(this);
        button.clearFocus();
        button.setText("window manager test!");
        WindowManager.LayoutParams mParams = new WindowManager.LayoutParams();
        mParams.x = 900-200;
        mParams.y = 1400-64;

        mParams.width = 200;
        mParams.height = 64;

        mWm.addView(button, mParams);
    }

    public void addEditImage(View view) {
        Drawable[] drawables = et.getCompoundDrawables();
        if(drawables == null)
            System.out.println("drawable is null");
        else if(drawables.length == 0)
            System.out.println("drawable length is 0");

        if(drawables[2] == null) {
            System.out.println("drawables[2] == null");
            drawables[2] = ContextCompat.getDrawable(this,R.drawable.delete);
            drawables[2].setBounds(0,0,drawables[2].getIntrinsicWidth(),drawables[2].getIntrinsicHeight());
            et.setCompoundDrawables(drawables[0],drawables[1],drawables[2],drawables[3]);
        }
    }


}
