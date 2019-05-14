package com.example.adroit.myapplication.resumo;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;

import java.io.Serializable;
import java.util.Date;

public class Note implements Serializable {

    private long mDateTime;
    private String mTitle;
    private String mCategoria;
    private String mContent;

    public Note(long mDateTime, String mTitle, String mCategoria, String mContent) {
        this.mDateTime = mDateTime;
        this.mTitle = mTitle;
        this.mCategoria = mCategoria;
        this.mContent = mContent;
    }

    public void setContent(String content) {
        mContent = mContent;
    }

    public void setDateTime(long dateTime) {
        mDateTime = mDateTime;
    }

    public void setTitle(String title) {
        mTitle = mTitle;
    }

    public void setCategoria(String categoria) {
        mCategoria = mCategoria;
    }

    public long getDateTime() {
        return mDateTime;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getCategoria() {
        return mCategoria;
    }

    public String getContent() {
        return mContent;
    }

    public String getDateTimeFormat(Context context) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"
                , context.getResources().getConfiguration().locale);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(new Date(mDateTime));
    }

}