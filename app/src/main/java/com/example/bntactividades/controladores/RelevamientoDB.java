package com.example.bntactividades.controladores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.bntactividades.modelos.Relevamiento;

import java.util.ArrayList;
import java.util.List;

//Definimos la estructura de acceso a los datos
public class RelevamientoDB extends SQLiteOpenHelper implements IRelevamientoDB {
    Context contexto;
    private List<Relevamiento> relevamientosList = new ArrayList<>();

    public RelevamientoDB(@Nullable Context context, @Nullable String name,
                          @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(context, name, factory, version);
        this.contexto = context;
    };

    //El sig metodo permite definir la estructura de la base de datos.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE relevamientos (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " nombre TEXT, " +
                " tipoPoste INTEGER, " +
                " tipoHeraje INTEGER, " +
                " tipoObstruccion INTEGER, " +
                " tipoInfraestructura INTEGER, " +
                " latitud REAL, " +
                " altitud REAL, " +
                " longitud REAL, " +
                " observaciones TEXT )";
        sqLiteDatabase.execSQL(sql);

        //Funcion para llenar el registri de una tabla
        String insert = "INSERT INTO relevamientos VALUES (null, " +
                //Nombre
                "'Proyecto 1', " +
                //tipoPOste
                "1, " +
                //tipoHerraje
                "3, " +
                //tipoObstruccion
                "1, " +
                //tipoInfraestructura
                "2, " +
                //Latitud
                "1431234, " +
                //Altitud
                "4234234, " +
                //longitud
                "9723874, " +
                //observaciones
                "'Comentarios hechos por el usuario')";
        sqLiteDatabase.execSQL( insert );
        insert = "INSERT INTO relevamientos VALUES (null, " +
                //Nombre
                "'Proyecto 2', " +
                //tipoPOste
                "2, " +
                //tipoHerraje
                "1, " +
                //tipoObstruccion
                "3, " +
                //tipoInfraestructura
                "1, " +
                //Latitud
                "100, " +
                //Altitud
                "120, " +
                //longitud
                "140, " +
                //observaciones
                "'comentario proyecto 2')";
        sqLiteDatabase.execSQL( insert );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Metodo para retornar un elemento segun el id de tipo lectura, sin modificar.
    @Override
    public Relevamiento elemento(int id) {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM relevamientos WHERE _id=" +id;
        Cursor cursor  = database.rawQuery( sql, null);
        try {
            if(cursor.moveToNext())
                return extraerRelevamiento(cursor);
            else
                return null;
        } catch (Exception e) {
            Log.d("TAG", "Error elemnto(id) RevelamientoDB" + e.getMessage());
            throw e;
        } finally {
            if (cursor != null ) cursor.close();
        }

    }

    private Relevamiento extraerRelevamiento(Cursor cursor) {
        Relevamiento relevamiento = new Relevamiento();
        relevamiento.setId(cursor.getInt(0));
        relevamiento.setNombre(cursor.getString(1));
        relevamiento.setTipoPoste(cursor.getInt(2));
        relevamiento.setTipoHerraje(cursor.getInt(3));
        relevamiento.setTipoObstruccion(cursor.getInt(4));
        relevamiento.setTipoInfraestructura(cursor.getInt(5));
        relevamiento.setLatitud(cursor.getDouble(6));
        relevamiento.setAltitud(cursor.getDouble(7));
        relevamiento.setLongitud(cursor.getDouble(8));
        relevamiento.setObservaciones(cursor.getString(9));
        return relevamiento;
    }

    @Override
    public Relevamiento elemento(String title) {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM relevamientos WHERE nombre='" + title + "'" ;
        Cursor cursor  = database.rawQuery( sql, null);
        try {
            if(cursor.moveToNext())
                return extraerRelevamiento(cursor);
            else
                return null;
        } catch (Exception e) {
            Log.d("TAG", "Error elemnto(title) RevelamientoDB" + e.getMessage());
            throw e;
        } finally {
            if (cursor != null ) cursor.close();
        }
    }

    @Override
    public List<Relevamiento> lista() {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM relevamientos ORDER BY nombre ASC";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                relevamientosList.add(
                        new Relevamiento( cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getInt(2),
                                cursor.getInt(3),
                                cursor.getInt(4),
                                cursor.getInt(5),
                                cursor.getDouble(6),
                                cursor.getDouble(7),
                                cursor.getDouble(8),
                                cursor.getString(9))
                );
        } while (cursor.moveToNext());
        }
        cursor.close();
        return relevamientosList;
    }

    @Override
    public void agregar(Relevamiento obj) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("nombre", obj.getNombre());
        values.put("tipoPoste", obj.getTipoPoste());
        values.put("tipoHerraje", obj.getTipoHerraje());
        values.put("tipoObstruccion", obj.getTipoObstruccion());
        values.put("tipoInfraestructura", obj.getTipoInfraestructura());
        values.put("latitud", obj.getLatitud());
        values.put("altitud", obj.getAltitud());
        values.put("longitud", obj.getLongitud());
        values.put("observaciones", obj.getObservaciones());
        database.insert("relevamientos", null, values);
    }

    @Override
    public void actualizar(int id, Relevamiento obj) {
        SQLiteDatabase database = getWritableDatabase();
        String [] parametros = { String.valueOf(id) };

        ContentValues values = new ContentValues();
        values.put("nombre", obj.getNombre());
        values.put("tipoPoste", obj.getTipoPoste());
        values.put("tipoHerraje", obj.getTipoHerraje());
        values.put("tipoObstruccion", obj.getTipoObstruccion());
        values.put("tipoInfraestructura", obj.getTipoInfraestructura());
        values.put("latitud", obj.getLatitud());
        values.put("altitud", obj.getAltitud());
        values.put("longitud", obj.getLongitud());
        values.put("observaciones", obj.getObservaciones());

        database.update("relevamientos", values, "_id=?", parametros);
    }

    @Override
    public void eliminar(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String [] parametros = { String.valueOf(id) };

        database.delete("relevamientos", "_id=?", parametros );
    }
}//relevamientoDB
