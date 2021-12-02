package com.example.practica1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ChangeUser extends AppCompatActivity {

    public UserRoomDatabase db;
    public UserDao mUserDao;
    private List<User> userList;
    private User removableUser;

    private TextView removeText;
    private Button removeButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user);

        removeText = (TextView) findViewById(R.id.removeText);
        removeButton = (Button) findViewById(R.id.confirmRemove);
        cancelButton = (Button) findViewById(R.id.cancelRemove);

        removeText.setVisibility(View.GONE);
        removeButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);

        db = UserRoomDatabase.getDatabase(getApplicationContext());
        mUserDao = db.userDao();
        userList = mUserDao.getAll();
        init();
    }

    public void init(){
        ListAdapter listAdapter = new ListAdapter(userList, this);
        RecyclerView recyclerView = findViewById(R.id.listRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listAdapter);
    }

    public void goAddUser(View view){
        Intent next = new Intent(this, AddUser.class);
        startActivity(next);
    }

    public void goBack(View view){
        Intent next = new Intent(this, Menu.class);
        startActivity(next);
    }

    public void select(View view){
        mUserDao.setAllInactive(false);
        mUserDao.setActiveUser(true, view.getContentDescription().toString());
        goBack(view);
    }

    public void edit(View view){
        Intent next = new Intent(this, EditUser.class);
        next.putExtra("username", view.getContentDescription().toString());
        startActivity(next);
    }

    public void remove(View view){
        removeText.setVisibility(View.VISIBLE);
        removeButton.setVisibility(View.VISIBLE);
        cancelButton.setVisibility(View.VISIBLE);
        removableUser = mUserDao.getSpecificUser(view.getContentDescription().toString());
    }

    public void confirmRemove(View view){
        mUserDao.delete(removableUser);
        removeText.setVisibility(View.GONE);
        removeButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
        userList = mUserDao.getAll();
        init();
    }

    public void cancelRemove(View view){
        removeText.setVisibility(View.GONE);
        removeButton.setVisibility(View.GONE);
        cancelButton.setVisibility(View.GONE);
    }

    public void deleteAll(View view){
        mUserDao.deleteAll();
    }

}