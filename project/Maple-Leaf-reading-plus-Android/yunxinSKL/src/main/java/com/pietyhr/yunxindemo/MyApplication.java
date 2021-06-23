package com.pietyhr.yunxindemo;

import android.app.Application;
import android.util.Log;

import com.netease.nim.uikit.api.NimUIKit;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.util.NIMUtil;

import java.io.File;

/**
 * @method com.pietyhr.yunxindemo
 * @author: 我心飞扬不飘荡
 * @date: 2019/12/25 0025
 * @description 描述一下方法的作用
 */
public class MyApplication extends Application {

    private static String path = "/sdcard/loverReader/";//sd路径


    @Override
    public void onCreate() {
        super.onCreate();
        NIMClient.init(this, null, null);
        if (NIMUtil.isMainProcess(this)) {
            // 初始化
//            Log.e("初始化NimUiKit","开始");
            NimUIKit.init(this);
        }


        File fileDir = new File(path);
        if (!fileDir.exists()&&fileDir.isDirectory()){
            fileDir.mkdir();
        }

    }
}