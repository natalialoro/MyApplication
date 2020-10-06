package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.TokenWatcher;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.models.NotaModel;
import com.example.myapplication.operations.NotaOperations;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private NotaModel model;
    private NotaOperations operations;
    private TextView uno;
    private ArrayList<String> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uno= findViewById(R.id.uno);
        String titulo ="Nuevo Titulo de la nota";
        String contenido ="Contenido Nuevo de la nota que quiero guardar en la base de datos";

        operations = new NotaOperations(this);

        model = new NotaModel(titulo, contenido);
       int r = operations.insert(model);
       if(r>0) {
           Toast.makeText(this, "Guardado correctamente", Toast.LENGTH_LONG).show();
           uno.setText(String.valueOf(r));
       }else{
           Toast.makeText(this, "No se guardo correctamente", Toast.LENGTH_LONG).show();
       }
       String consolidadoMostrar = "";
        list = operations.list();


       for (int i = 0; i < list.size(); i++){
           consolidadoMostrar += list.get(i) + "\n ---------------- \n\n";

       }

       uno.setText(consolidadoMostrar);
       uno.setText(model.toString());

    }
}