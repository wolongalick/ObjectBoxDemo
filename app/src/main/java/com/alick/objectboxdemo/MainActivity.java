package com.alick.objectboxdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.alick.objectboxdemo.adapter.UserAdapter;
import com.alick.objectboxdemo.bean.User;
import com.alick.objectboxdemo.bean.User_;
import com.alick.objectboxdemo.utils.RecyclerUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<User>   allUsers    = new ArrayList<>();
    private UserAdapter  userAdapter = new UserAdapter(allUsers);
    private RecyclerView rv_user;
    private EditText     et_username;
    private RadioButton  rb_men;
    private RadioButton  rb_women;
    private EditText     et_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_user = findViewById(R.id.rv_user);
        et_username = findViewById(R.id.et_username);
        rb_men = findViewById(R.id.rb_men);
        rb_women = findViewById(R.id.rb_women);
        et_age = findViewById(R.id.et_age);

        rv_user.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        rv_user.setAdapter(userAdapter);

        query(null);
    }

    /**
     * 添加用户
     *
     * @param view
     */
    public void add(View view) {
        String username = et_username.getText().toString().trim();
        String age      = et_age.getText().toString().trim();

        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请填写姓名", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "请填写年龄", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = new User(username, rb_men.isChecked(), Integer.valueOf(age));
        DataManager.getInstance().userBox.put(user);
        RecyclerUtils.insertRefresh(userAdapter, allUsers, user, allUsers.size());
        Toast.makeText(this, "添加成功", Toast.LENGTH_SHORT).show();
    }

    /**
     * 按年龄删除
     *
     * @param view
     */
    public void deleteByAge(View view) {
        String age = et_age.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
            return;
        }

        List<User> users = DataManager.getInstance().userBox.query().equal(User_.age, Integer.valueOf(age)).build().find();
        if (users.isEmpty()) {
            Toast.makeText(this, "删除0条", Toast.LENGTH_SHORT).show();
            return;
        }
        DataManager.getInstance().userBox.remove(users);
        Toast.makeText(this, "删除" + users.size() + "条", Toast.LENGTH_SHORT).show();
        query(null);
    }

    /**
     * 修改用户
     *
     * @param view
     */
    public void updateByAge(View view) {


        List<User> userList = DataManager.getInstance().userBox.query().greater(User_.age, 50).or().equal(User_.age, 50).build().find();
        if (userList.isEmpty()) {
            Toast.makeText(this, "没有年龄大于或等于" + 50 + "的用户", Toast.LENGTH_SHORT).show();
            return;
        }
        for (User user : userList) {
            user.setUsername("老年人");
        }
        Toast.makeText(this, "修改了"+userList.size()+"个用户", Toast.LENGTH_SHORT).show();
        DataManager.getInstance().userBox.put(userList);
        query(null);
    }

    /**
     * 查询用户
     *
     * @param view
     */
    public void query(View view) {
        List<User> users = DataManager.getInstance().userBox.query().build().find();
        allUsers.clear();
        allUsers.addAll(users);
        userAdapter.notifyDataSetChanged();
    }

    /**
     * 删除全部
     *
     * @param view
     */
    public void deleteAll(View view) {
        DataManager.getInstance().userBox.removeAll();
        allUsers.clear();
        userAdapter.notifyDataSetChanged();
    }
}
