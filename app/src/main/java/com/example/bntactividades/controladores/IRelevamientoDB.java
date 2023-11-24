package com.example.bntactividades.controladores;

import com.example.bntactividades.modelos.Relevamiento;

import java.util.List;

//Interface de la vase de datos de los relevamientos
public interface IRelevamientoDB {

    //esta interface tiene estos metodos

    Relevamiento elemento(int id );
    Relevamiento elemento(String title);

    List<Relevamiento> lista();

    void agregar(Relevamiento obj);
    void actualizar(int id, Relevamiento obj);

    void eliminar(int id);

}
