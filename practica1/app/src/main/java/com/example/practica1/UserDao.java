package com.example.practica1;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM user_table")
    List<User> getAll();

    @Delete
    void delete(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT username FROM user_table")
    List<String> getAllUsernames();

    @Query("SELECT * FROM user_table WHERE isActive")
    User getActiveUser();

    @Query("SELECT * FROM user_table WHERE username =:name")
    User getSpecificUser(String name);

    @Query("UPDATE user_table SET isActive =:b")
    void setAllInactive(boolean b);

    @Query("UPDATE user_table SET isActive =:b WHERE username =:name")
    void setActiveUser(boolean b, String name);

    @Query("UPDATE user_table SET avatar =:img WHERE username =:name")
    void changeImage(int img, String name);
}

