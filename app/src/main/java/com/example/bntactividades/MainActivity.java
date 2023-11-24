package com.example.bntactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    Button btnListar, btnRegistrar, btnBuscar, btnExportar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        context = getApplicationContext();
        btnRegistrar = findViewById(R.id.btnregistrar);
        btnListar = findViewById(R.id.btnlistar);
        btnBuscar = findViewById(R.id.btnbuscar);
        btnExportar = findViewById(R.id.btnexportar);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnregistrar) {
            Toast.makeText(context, "registrar", Toast.LENGTH_LONG).show();
            Intent i = new Intent(context, GestionarRelevamientosActivity.class);
            Bundle bolsa = new Bundle();
            bolsa.putInt("id", 0);
            i.putExtras(bolsa);
            startActivity(i);
        } else if (view.getId() == R.id.btnlistar) {
            Toast.makeText(context, "listar", Toast.LENGTH_LONG).show();
            Intent i2 = new Intent(context, ListadoRelevamientosActivity.class);
            startActivity(i2);
        } else if (view.getId() == R.id.btnbuscar) {
            Toast.makeText(context, "buscar", Toast.LENGTH_LONG).show();
            Intent i3 = new Intent(context, BuscarRelevamientoActivity.class);
            startActivity(i3);
        }
    }


}