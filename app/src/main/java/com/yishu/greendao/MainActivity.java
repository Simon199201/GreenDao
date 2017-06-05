package com.yishu.greendao;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yishu.greendao.db.UserDao;
import com.yishu.greendao.entitys.User;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDao = GreenDaoApplication.getInstances().getDaoSession().getUserDao();
        //query
        findViewById(R.id.getAllData).setOnClickListener(this);
        //insert
        findViewById(R.id.insert).setOnClickListener(this);
        //update
        findViewById(R.id.update).setOnClickListener(this);
        //delete
        findViewById(R.id.delete).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.getAllData:
                query();
                break;
            case R.id.insert:
                insert();
                break;
            case R.id.update:
                update();
                break;
            case R.id.delete:
                delete();
                break;
        }
    }

    private void delete() {
        userDao.deleteByKey((long) 1);
    }

    private void update() {
        User user = userDao.load((long) 2);
        if (user != null) {
            user.setName("simon updated" + System.currentTimeMillis());
        }
        userDao.update(user);
    }

    private void insert() {
        User user = new User();
        user.setName("simon" + System.currentTimeMillis());
        userDao.insert(user);
    }

    private void query() {
        List<User> list = userDao.loadAll();
        for (User user : list) {
            Log.e(TAG, "query: " + user.getId()+"\t" + user.getName());
        }
    }
}
