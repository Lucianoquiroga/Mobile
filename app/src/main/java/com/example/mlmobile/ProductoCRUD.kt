package com.example.mlmobile

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class ProductoCRUD(context: Context) {

    private var helper:DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun newProducto(item:Producto) {
        val db: SQLiteDatabase = helper?.writableDatabase!!

        val values = ContentValues()
        values.put(ProductoContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_TITLE, item.title)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_PRICE, item.price)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_CONDITION, item.condition)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_THUMBNAIL, item.thumbnail)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_AVAILABLE_QUANTITY, item.availableQuantity)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_SOLD_QUANTITY, item.soldQuantity)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_PERMA_LINK, item.permalink)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_STATE_NAME, item.addressStateName)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_CITY_NAME, item.addressCityName)

        val newRowId = db.insert(ProductoContract.Companion.Entrada.NOMBRE_TABLA, null, values)

        db.close()
    }

    fun getProductos():ArrayList<Producto> {

        val items:ArrayList<Producto> = ArrayList()

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(ProductoContract.Companion.Entrada.COLUMNA_ID,
            ProductoContract.Companion.Entrada.COLUMNA_TITLE,
            ProductoContract.Companion.Entrada.COLUMNA_PRICE,
            ProductoContract.Companion.Entrada.COLUMNA_CONDITION,
            ProductoContract.Companion.Entrada.COLUMNA_THUMBNAIL,
            ProductoContract.Companion.Entrada.COLUMNA_AVAILABLE_QUANTITY,
            ProductoContract.Companion.Entrada.COLUMNA_SOLD_QUANTITY,
            ProductoContract.Companion.Entrada.COLUMNA_PERMA_LINK,
            ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_STATE_NAME,
            ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_CITY_NAME)

        val c: Cursor = db.query(
            ProductoContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null
        )

        while (c.moveToNext()){
            items.add(Producto(
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_ID)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_TITLE)),
                c.getInt(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_PRICE)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_CONDITION)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_THUMBNAIL)),
                c.getInt(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_AVAILABLE_QUANTITY)),
                c.getInt(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_SOLD_QUANTITY)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_PERMA_LINK)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_STATE_NAME)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_CITY_NAME))
            ))
        }

        db.close()

        return items
    }

    fun getProducto(id: String): Producto{

        var item:Producto? = null

        val db: SQLiteDatabase = helper?.readableDatabase!!

        val columnas = arrayOf(ProductoContract.Companion.Entrada.COLUMNA_ID,
            ProductoContract.Companion.Entrada.COLUMNA_TITLE,
            ProductoContract.Companion.Entrada.COLUMNA_PRICE,
            ProductoContract.Companion.Entrada.COLUMNA_CONDITION,
            ProductoContract.Companion.Entrada.COLUMNA_THUMBNAIL,
            ProductoContract.Companion.Entrada.COLUMNA_AVAILABLE_QUANTITY,
            ProductoContract.Companion.Entrada.COLUMNA_SOLD_QUANTITY,
            ProductoContract.Companion.Entrada.COLUMNA_PERMA_LINK,
            ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_STATE_NAME,
            ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_CITY_NAME
            )

        val c:Cursor = db.query(
            ProductoContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            "id = ?",
            arrayOf(id),
            null,
            null,
            null
        )

        while (c.moveToNext()) {
            item = Producto(
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_ID)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_TITLE)),
                c.getInt(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_PRICE)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_CONDITION)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_THUMBNAIL)),
                c.getInt(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_AVAILABLE_QUANTITY)),
                c.getInt(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_SOLD_QUANTITY)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_PERMA_LINK)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_STATE_NAME)),
                c.getString(c.getColumnIndexOrThrow(ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_CITY_NAME))
                )
        }
        
        db.close()

        return item!!
    }
    
    fun updateProducto(item:Producto) {
        
        val db: SQLiteDatabase = helper?.writableDatabase!!
        
        val values = ContentValues()
        values.put(ProductoContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_TITLE, item.title)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_PRICE, item.price)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_CONDITION, item.condition)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_THUMBNAIL, item.thumbnail)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_AVAILABLE_QUANTITY, item.availableQuantity)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_SOLD_QUANTITY, item.soldQuantity)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_PERMA_LINK, item.permalink)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_STATE_NAME, item.addressStateName)
        values.put(ProductoContract.Companion.Entrada.COLUMNA_ADDRESS_CITY_NAME, item.addressCityName)

        db.update(
            ProductoContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            "id = ?",
            arrayOf(item.id)
        )

        db.close()
    }

    fun deleteProducto(item:Producto){

        val db: SQLiteDatabase = helper?.writableDatabase!!

        db.delete(
            ProductoContract.Companion.Entrada.NOMBRE_TABLA,
            "id = ?",
            arrayOf(item.id)
        )

        db.close()
    }

    fun deleteProductos() {
        val db: SQLiteDatabase = helper?.writableDatabase!!

        db.delete(
            ProductoContract.Companion.Entrada.NOMBRE_TABLA,
            null,
            null
        )

        db.close()
    }

}