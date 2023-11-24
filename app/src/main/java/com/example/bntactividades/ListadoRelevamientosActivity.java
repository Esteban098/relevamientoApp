package com.example.bntactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.bntactividades.controladores.RelevamientoDB;
import com.example.bntactividades.controladores.SelectListener;
import com.example.bntactividades.modelos.Relevamiento;


import java.util.ArrayList;
import java.util.List;

public class ListadoRelevamientosActivity extends AppCompatActivity implements SelectListener {

    ListView listView;
    ArrayList<String> nombresRelevamientos;
    ArrayList<Integer> idRelevamientos;
    RelevamientoDB relevamientoDB;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_relevamientos);
        init();
    }

    private void init(){
        context = this.getApplicationContext();
        relevamientoDB = new RelevamientoDB(context, "RelevamientoDB.db", null, 1);
        listView = findViewById(R.id.listaRelevamientos);
        llenarListView();
    }

    private void llenarListView() {
        nombresRelevamientos = new ArrayList<String>();
        idRelevamientos = new ArrayList<Integer>();

        List<Relevamiento> listaRelevamientos = relevamientoDB.lista();
        for (int i = 0; i<listaRelevamientos.size(); i++){
            Relevamiento r = listaRelevamientos.get(i);
            nombresRelevamientos.add(r.getNombre());
            idRelevamientos.add(r.getId());
        }
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        context,
                        android.R.layout.simple_list_item_1,
                        nombresRelevamientos
                );
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long r) {
                Relevamiento relevamiento = relevamientoDB.elemento(idRelevamientos.get(i));
                Bundle bolsa = new Bundle();
                bolsa.putInt("id", relevamiento.getId());
                bolsa.putString("nombre", relevamiento.getNombre());
                bolsa.putInt("tipoPoste", relevamiento.getTipoPoste());
                bolsa.putInt("tipoHerraje", relevamiento.getTipoHerraje());
                bolsa.putInt("tipoObstruccion", relevamiento.getTipoObstruccion());
                bolsa.putInt("tipoInfraestructura", relevamiento.getTipoInfraestructura());
                bolsa.putDouble("latitud", relevamiento.getLatitud());
                bolsa.putDouble("altitud", relevamiento.getAltitud());
                bolsa.putDouble("longitud", relevamiento.getLongitud());
                bolsa.putString("observaciones", relevamiento.getObservaciones());

                Intent intent = new Intent(context, GestionarRelevamientosActivity.class);
                intent.putExtras( bolsa );
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemClick(String nombre) {

    }
}