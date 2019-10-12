package com.alick.objectboxdemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alick.objectboxdemo.R;
import com.alick.objectboxdemo.bean.User;

import java.util.List;

/**
 * @author 崔兴旺
 * @createTime 2019/10/12 15:21
 * @description
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<User> users;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new UserViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_user, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder viewHolder, int i) {
        User user = users.get(i);
        viewHolder.tv_username.setText(user.getUsername());
        viewHolder.tv_sex.setText(user.isSex() ? "男" : "女");
        viewHolder.tv_age.setText(String.valueOf(user.getAge()));
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return users != null ? users.size() : 0;
    }


    public class UserViewHolder extends RecyclerView.ViewHolder {
        TextView tv_username;
        TextView tv_sex;
        TextView tv_age;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_username = itemView.findViewById(R.id.tv_username);
            tv_sex = itemView.findViewById(R.id.tv_sex);
            tv_age = itemView.findViewById(R.id.tv_age);
        }

    }


}
