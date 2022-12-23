package com.juanjiga.crud_db;

public class Estructura_db {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Estructura_db() {}

    /* Inner class that defines the table contents */
    //public static class FeedEntry implements BaseColumns {

    //Nombres de la tabla y de los campos (columnas)
    public static final String TABLE_NAME = "datosDatos";
    public static final String _ID = "id";
    public static final String C_NAME = "Nombre";
    public static final String C_USER = "Usuario";
    public static final String C_PASSWORD = "Contraseña";

    //}

    //Instrucción para crear la tabla
    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    Estructura_db._ID + " INTEGER PRIMARY KEY," +
                    Estructura_db.C_NAME + " TEXT NOT NULL," +
                    Estructura_db.C_USER + " TEXT," +
                    Estructura_db.C_PASSWORD + " TEXT)";

    //Instrucción para borrar la tabla
    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + Estructura_db.TABLE_NAME;




}
