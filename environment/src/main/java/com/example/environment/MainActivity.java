package com.example.environment;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;


/**
 * Environment 是一个提供访问环境变量的类。
 *
 * SDcard/Android/data/
 * Android app在运行过程中,一般会缓存数据到sdcard中,大部分的应用都是直接在sdcard下新建一个目录,然后保存数据.
 * 但是这样当应用卸载后会留下了垃圾数据.如果想要app卸载后与该应用相关的数据也清除掉怎么办?就是使用这个目录
 *
 */
public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Environment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void test(View view) {

        getCacheDir();// app内部缓存 /data/data/com.example.environment/cache
        getExternalCacheDir();// app外部缓存/storage/emulated/0/Android/data/com.example.environment/cache
        //File[] files = getExternalCacheDirs();//这个获取的也是 getExternalCacheDir()


        getExternalFilesDir("httpDir");///storage/emulated/0/Android/data/com.example.environment/files/httpDir
        // getExternalFilesDirs() 和上面一样


        //获得android data的目录 api24
        //File file = getDataDir();

        getDir("aaa",MODE_PRIVATE);///data/data/com.example.mypackage/app_aaa
    }

    public void environment(View view) {

        String sdcardState = Environment.getExternalStorageState();//mounted  这个函数用来获取SD卡的挂载状态，如果传入参数path则是获取该路径的的挂载状态
        Log.i(TAG, "environment: sdcard state="+sdcardState);

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
            Toast.makeText(this,"挂在正常",Toast.LENGTH_SHORT).show();
        }

        File path = Environment.getExternalStorageDirectory();//该函数用来返回SD卡的根目录，即/storage/emulated/0/  强烈建立创建一个子目录去操作，要不然会污染SD卡的主目录
        System.out.println("getExternalStorageDirectory="+path.getPath());

        String dcim = Environment.DIRECTORY_DOWNLOADS;
        Log.i(TAG, "environment: "+dcim);//Download



        //如果您需要往sdcard中保存特定类型的内容，可以考虑使用Environment.getExternalStoragePublicDirectory(String type)函数，该函数可以返回特定类型的目录，目前支持如下类型：
        File temp = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Log.i(TAG, "environment: temp="+temp);
        //        public static String
//                DIRECTORY_ALARMS
//        系统提醒铃声存放的标准目录。
//        public static String
//                DIRECTORY_DCIM
//        相机拍摄照片和视频的标准目录。
//        public static String
//                DIRECTORY_DOWNLOADS
//        下载的标准目录。
//        public static String
//                DIRECTORY_MOVIES
//        电影存放的标准目录。
//        public static String
//                DIRECTORY_MUSIC
//        音乐存放的标准目录。
//        public static String
//                DIRECTORY_NOTIFICATIONS
//        系统通知铃声存放的标准目录。
//        public static String
//                DIRECTORY_PICTURES
//        图片存放的标准目录。
//        public static String
//                DIRECTORY_PODCASTS
//        系统广播存放的标准目录。
//        public static String
//                DIRECTORY_RINGTONES
//        系统铃声存放的标准目录。

    }
}
