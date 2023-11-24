package com.example.bntactividades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bntactividades.controladores.RelevamientoDB;
import com.example.bntactividades.modelos.Relevamiento;

public class GestionarRelevamientosActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txtnombre, txttipoposte, txttipoherraje, txttipoobstruccion, txttipoinfraestructura, txtlatitud, txtaltitud, txtlongitud, txtobservaciones;
    int id;
    Button btnguardar,btnactualizar, btnborrar;
    RelevamientoDB relevamientoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestionar_relevamientos);
        init();
    }


    private void init() {
        context = getApplicationContext();
        txtnombre = findViewById(R.id.ges_txtnombre);
        txttipoposte = findViewById(R.id.ges_txttipoposte);
        txttipoherraje = findViewById(R.id.ges_txttipoherraje);
        txttipoinfraestructura = findViewById(R.id.ges_txttipoinfraestructura);
        txttipoobstruccion = findViewById(R.id.ges_txttipoobstruccion);
        txtlatitud = findViewById(R.id.ges_txtlatitud);
        txtaltitud = findViewById(R.id.ges_txtaltitud);
        txtlongitud = findViewById(R.id.ges_txtlongitud);
        txtobservaciones = findViewById(R.id.ges_txtobservaciones);

        btnguardar = findViewById(R.id.ges_btnguardar);
        btnactualizar = findViewById(R.id.ges_btnactualizar);
        btnborrar = findViewById(R.id.ges_btnborrar);

        Intent i = getIntent();
        Bundle bolsa = i.getExtras();
        id = bolsa.getInt("id");
        if (id != 0) {
            txtnombre.setText(bolsa.getString("nombre"));
            txttipoposte.setText(bolsa.getInt("tipoPoste")+ "");
            txttipoherraje.setText(bolsa.getInt("tipoHerraje")+ "");
            txttipoobstruccion.setText(bolsa.getInt("tipoObstruccion")+ "");
            txttipoinfraestructura.setText(bolsa.getInt("tipoInfraestructura")+ "");
            txtlatitud.setText(bolsa.getDouble("latitud") + "");
            txtaltitud.setText(bolsa.getDouble("altitud") + "");
            txtlongitud.setText(bolsa.getDouble("longitud") + "");
            txtobservaciones.setText(bolsa.getString("observaciones"));
        }
    }

    private void limpiarCampos(){
        id = 0;
        txtnombre.setText("");
        txttipoposte.setText("");
        txttipoherraje.setText("");
        txttipoobstruccion.setText("");
        txttipoinfraestructura.setText("");
        txtlatitud.setText("");
        txtaltitud.setText("");
        txtlongitud.setText("");
        txtobservaciones.setText("");


    }

    private Relevamiento llenarDatos() {
       Relevamiento relevamiento = new Relevamiento();
       String n = txtnombre .getText().toString();
       String poste = txttipoposte .getText().toString();
       String herraje = txttipoherraje .getText().toString();
       String obstruccion = txttipoobstruccion .getText().toString();
       String infraestructura = txttipoinfraestructura .getText().toString();
       String latitud = txtlatitud .getText().toString();
       String altitud = txtaltitud .getText().toString();
       String longitud = txtlongitud .getText().toString();
       String observaciones = txtobservaciones .getText().toString();

       relevamiento.setId( id );
       relevamiento.setNombre( n );
       relevamiento.setTipoPoste( Integer.parseInt(poste) );
       relevamiento.setTipoHerraje( Integer.parseInt(herraje) );
       relevamiento.setTipoObstruccion(Integer.parseInt(obstruccion) );
       relevamiento.setTipoInfraestructura( Integer.parseInt(infraestructura) );
       relevamiento.setLatitud( Double.parseDouble(latitud) );
       relevamiento.setAltitud( Double.parseDouble(altitud));
       relevamiento.setLongitud( Double.parseDouble(longitud) );
       relevamiento.setObservaciones( observaciones );

       return relevamiento;
    }

    private void guardar(){
        relevamientoDB=new RelevamientoDB(context, "RelevamientosDB.db", null, 1);
        Relevamiento relevamiento = llenarDatos();
        if (id == 0 ){
            relevamientoDB.agregar(relevamiento);
            Toast.makeText(context, "Se ha guardado con exito",  Toast.LENGTH_LONG).show();

        }else {
            relevamientoDB.actualizar(id, relevamiento);
            Toast.makeText(context, "se ha actualizado con exito", Toast.LENGTH_LONG).show();
        }
    }

    private void eliminar(){
        relevamientoDB=new RelevamientoDB(context, "RelevamientosDB.db", null, 1);
        Relevamiento relevamiento = llenarDatos();
        if (id == 0 ){
            Toast.makeText(context, "No es posible borrar ",  Toast.LENGTH_LONG).show();

        }else {
            relevamientoDB.eliminar(id);
            limpiarCampos();
            Toast.makeText(context, "Se borro el registro", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == R.id.ges_btnguardar || viewId == R.id.ges_btnactualizar) {
            guardar();
        } else if (viewId == R.id.ges_btnborrar) {
            eliminar();
        }
    }


}