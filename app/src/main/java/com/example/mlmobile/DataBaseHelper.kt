package com.example.mlmobile

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataBaseHelper(context: Context): SQLiteOpenHelper(context, ProductoContract.Companion.Entrada.NOMBRE_TABLA, null, ProductoContract.Companion.VERSION) {

    companion object{
        val CREATE_TABLE = "CREATE TABLE " + ProductoContract.Companion.Entrada.NOMBRE_TABLA +
                " (" + ProductoContract.Companion.Entrada.COLUMNA_ID + " TEXT PRIMARY KEY, " +
                ProductoContract.Companion.Entrada.COLUMNA_TITLE + " TEXT," +
                ProductoContract.Companion.Entrada.COLUMNA_PRICE + " INTEGER," +
                ProductoContract.Companion.Entrada.COLUMNA_CONDITION + " TEXT," +
                ProductoContract.Companion.Entrada.COLUMNA_THUMBNAIL + " TEXT, " +
                ProductoContract.Companion.Entrada.COLUMNA_AVAILABLE_QUANTITY + " INTEGER, " +
                ProductoContract.Companion.Entrada.COLUMNA_SOLD_QUANTITY + " INTEGER, " +
                ProductoContract.Companion.Entrada.COLUMNA_PERMA_LINK + " TEXT, " +
                ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_STATE_NAME + " TEXT, " +
                ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_CITY_NAME + " TEXT )"

        val REMOVE_TABLE = "DROP TABLE IF EXISTS" + ProductoContract.Companion.Entrada.NOMBRE_TABLA
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, i: Int, i2: Int) {
        db?.execSQL(REMOVE_TABLE)
        onCreate(db)
    }

}