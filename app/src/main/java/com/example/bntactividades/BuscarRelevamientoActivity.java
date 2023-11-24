package com.example.bntactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bntactividades.controladores.RelevamientoDB;
import com.example.bntactividades.modelos.Relevamiento;

public class BuscarRelevamientoActivity extends AppCompatActivity implements View.OnClickListener{

    Context context;
    EditText txtnombre;
    Button btnbuscar;
    RelevamientoDB relevamientoDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_relevamiento);
        init();

    }

    private void init(){
        context = getApplicationContext();
        txtnombre = findViewById(R.id.bus_txtnombre);
        btnbuscar = findViewById(R.id.bus_btnbuscar);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bus_btnbuscar){
            String nombre = txtnombre.getText().toString();
            Relevamiento relevamiento = buscarRelevamiento( nombre );
            if (relevamiento != null ) {
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

                Intent i = new Intent(context, GestionarRelevamientosActivity.class);
                i.putExtras( bolsa );
                startActivity(i);
            } else {
                Toast.makeText(context, "No se encontro un Relevamiento con ese nombre.", Toast.LENGTH_LONG).show();
            }
        }
    }

    private Relevamiento buscarRelevamiento(String nombre) {
        relevamientoDB = new RelevamientoDB(context, "RelevamientosDB.db", null, 1);
        Relevamiento relevamiento = relevamientoDB.elemento(nombre);
        return relevamiento;
    }
}//