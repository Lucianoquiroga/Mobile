package com.example.mlmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ProductActivity : AppCompatActivity(), ProductoFragment.OnListFragmentInteractionListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)


        val cantidad: String? = intent.getStringExtra("cantidad")

        this.title = "$cantidad resultados"

    }

    override fun onListFragmentInteraction(item: Producto) {
        val id: String? = item.id
        val nextIntent = Intent(this, ProductDescriptionActivity::class.java).apply {
            putExtra("producto_id",  id)
        }

        startActivity(nextIntent)
    }
}



