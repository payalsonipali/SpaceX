package com.payals.spacex;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(User... users);

    @Query("Select * from user where id = :id")
    List<User> findById(String id);
    @Delete
    void delete(User user);

    @Query("Delete from user")
    void deleteAll();

}