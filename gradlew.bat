<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout
    android:background="#222"
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <de.hdodenhof.circleimageview.CircleImageView
        android:layout_width="30dp"
        android:layout_height="30dp"
        android:layout_margin="10dp"
        android:src="@drawable/dog"
        android:elevation="1dp"
        android:id="@+id/story"
        />
    <TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Name Here"
        android:textColor="#fff"
        android:elevation="5dp"
        android:fontFamily="@font/quickmedium"
        android:textStyle="bold"
        android:layout_toRightOf="@id/story"
        android:layout_alignBottom="@id/story"
        android:layout_alignTop="@id/story"
        android:gravity="center"/>

    <ImageView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:src="@drawable/dog"
        android:layout_marginBottom="55dp"
        android:scaleType="centerCrop"
        />
    <ImageButton
        android:layout_width="40dp"
        android:layout_height="40dp"
        android:src="@drawable/ic_baseline_more_vert_24"
        android:layout_alignParentRight="true"
        android:background="@null"
        android:layout_alignBottom="@id/story"
        android:layout_alignTop="@id/story"/>


    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Send message"
        android:textColorHint="#fff"
        android:fontFamily="@font/quickmedium"
        android:textColor="#fff"
        android:layout_alignParentBottom="true"
        android:background="@drawable/backroundwhite"
        android:layout_toLeftOf="@id/send"
        android:paddingHorizontal="15dp"
        android:paddingVertical="10dp"
        android:id="@+id/ed"
        android:layout_marginBottom="5dp"
        android:layout_marginHorizontal="5dp"
        
        />


    <ImageButton
        android:id="@+id/send"
        android:layout_width="40dp"
        android:layout_height="40dp"
        and