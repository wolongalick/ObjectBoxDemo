package com.alick.objectboxdemo;

import android.content.Context;
import android.util.Log;

import com.alick.objectboxdemo.bean.MyObjectBox;
import com.alick.objectboxdemo.bean.User;

import io.objectbox.Box;
import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

/**
 * @author 崔兴旺
 * @createTime 2019/9/29 18:08
 * @description
 */
public class DataManager {
    private static DataManager instance = null;

    public BoxStore  boxStore;
    public Box<User> userBox;

    private DataManager() {

    }

    public static DataManager getInstance() {
        synchronized (DataManager.class) {
            if (instance == null) {
                instance = new DataManager();
            }
        }
        return instance;
    }

    public void init(Context context) {
        //第一次没运行之前，MyObjectBox默认会有报错提示，可以忽略。创建实体类， make之后报错就会不提示
        boxStore = MyObjectBox.builder().androidContext(context).build();
        if (BuildConfig.DEBUG) {//开启浏览器访问ObjectBox
            boolean started = new AndroidObjectBrowser(boxStore).start(context);
            Log.i("ObjectBrowser", "Started:"+started);
        }
        initUserEntityBox();
    }

    private void initUserEntityBox() {
        //对应操作对应表的类
        userBox = boxStore.boxFor(User.class);
    }


}
