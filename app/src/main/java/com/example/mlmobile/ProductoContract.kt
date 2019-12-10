package com.example.mlmobile

import android.provider.BaseColumns

class ProductoContract {

    companion object {

        val VERSION = 1
        class Entrada: BaseColumns{
            companion object{
                val NOMBRE_TABLA = "productos"

                val COLUMNA_ID = "id"
                val COLUMNA_TITLE = "title"
                val COLUMNA_PRICE = "price"
                val COLUMNA_CONDITION = "condition"
                val COLUMNA_THUMBNAIL = "thumbnail"
                val COLUMNA_AVAILABLE_QUANTITY = "availableQuantity"
                val COLUMNA_SOLD_QUANTITY = "soldQuantity"
                val COLUMNA_PERMA_LINK = "permalink"
                val COLUMNA_ADDRESS_STATE_NAME = "addressStateName"
                val COLUMNA_ADDRESS_CITY_NAME = "addressCityName"
            }
        }

    }

}