package com.payals.spacex;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    public String id = "";

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "agency")
    public String agency;

    @ColumnInfo(name = "image")
    public String image;

    @ColumnInfo(name = "wikipedia")
    public String wikipedia;

    @ColumnInfo(name = "status")
    public String status;
}
