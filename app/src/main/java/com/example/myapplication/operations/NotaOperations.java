package com.example.myapplication.operations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.Spannable;

import com.example.myapplication.Database.SQLHelper;
import com.example.myapplication.models.NotaModel;

import java.util.ArrayList;

public class NotaOperations {
    private String DBNAME="appnotasmyapp.db";
    private int VERSION =2;
    public final Context context;
    private SQLiteDatabase database;
    private SQLHelper helper;
    private NotaModel model;
    private ArrayList<String> list;


    public NotaOperations(Context context) {
        this.context = context;
        helper = new SQLHelper(context,DBNAME, null, VERSION);
    }

    public void  openRead(){
        database = helper.getReadableDatabase();
    }

    public void openWrite(){
        database = helper.getReadableDatabase();
    }

    public  void  close(){
        database.close();
    }

    public  int insert(NotaModel model){


        ContentValues content = new ContentValues();
        content.put("titulo", model.getTitulo());
        content.put("contenido", model.getContenido());
        openWrite();
        long id = database.insert("nota",null,content);
        close();
        return (int)id;

    }
    public ArrayList<String> List(){
        list= new ArrayList<>();
        openRead();

        Cursor cursor = database.query("nota", null, null, null, null, null, null);
        if (cursor.getCount()>0) {
            cursor.moveToFirst();
            String titulo, contenido, consolidado;
            int id;
            do {
                id= cursor.getInt(cursor.getColumnIndex("id"));
                titulo = cursor.getString(cursor.getColumnIndex("titulo"));
                contenido = cursor.getString(cursor.getColumnIndex("contenido"));

                consolidado = String.valueOf(id) + " - " + titulo;
                list.add(consolidado);



            } while (cursor.moveToNext());

        }

        close();
        return list;

    }



}
