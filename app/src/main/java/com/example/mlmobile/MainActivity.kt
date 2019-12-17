package com.example.mlmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject
import java.lang.Exception
import java.net.URLEncoder
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private var listaProductosDB: ArrayList<Producto>? = null
    private var crud: ProductoCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val orientation = this.resources.configuration.orientation
        if (orientation == 1) {
            setContentView(R.layout.activity_main)
        } else if (orientation == 2) {
            setContentView(R.layout.activity_main_landscape)
        }
        val buttonSearch: Button = findViewById(R.id.buttonSearch)
        val inputSearch: EditText = findViewById(R.id.editTextSearch)

        buttonSearch.setSafeOnClickListener  {

            if(Network.redOn(this)) {

                if (!inputSearch.text.isEmpty()) {
                    var textSearch: String = inputSearch.text.toString()

                    textSearch = URLEncoder.encode(textSearch, "UTF-8")
                    textSearch = textSearch.replace(oldValue = "+", newValue = "%20")

                    var urlML: String = "https://api.mercadolibre.com/sites/MLA/search?q=$textSearch"

                    solicitudHTTPVolley(urlML)

                } else {
                    Toast.makeText(this, "Debe ingresar al menos un carácter", Toast.LENGTH_SHORT).show()
                }

            } else {
                Toast.makeText(this, "no hay conexión de internet", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun solicitudHTTPVolley(url: String){
        val progressBarClass = CustomProgressBar()
        val queue = Volley.newRequestQueue(this)

        progressBarClass.show(this,"Buscando...")

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String> {
                response ->
            try {

                var json = JSONObject(response)
                val results = json.getJSONArray("results")

                listaProductosDB = ArrayList()
                crud = ProductoCRUD(this)

                if (crud?.getProductos()?.size!! > 0) {
                    crud?.deleteProductos()
                }

                for (i in 0..results.length() - 1) {

                    val id: String = i.toString()
                    val title: String = results.getJSONObject(i).getString("title")
                    val price: Int = results.getJSONObject(i).getInt("price")
                    val condition: String = results.getJSONObject(i).getString("condition")
                    val thumbnail: String = results.getJSONObject(i).getString("thumbnail")
                    val availableQuantity: Int = results.getJSONObject(i).getInt("available_quantity")
                    val soldQuantity: Int = results.getJSONObject(i).getInt("sold_quantity")
                    val permalink: String = results.getJSONObject(i).getString("permalink")

                    val address: JSONObject = results.getJSONObject(i).get("address") as JSONObject
                    val addressStateName: String = address.getString("state_name")
                    val addressCityName: String = address.getString("city_name")

                    crud?.newProducto(Producto(id, title, price, condition, thumbnail, availableQuantity, soldQuantity, permalink, addressStateName, addressCityName))

                }

                val cantidad = results.length().toString()
                val nextIntent = Intent(this, ProductActivity::class.java).apply {
                    putExtra("cantidad",  cantidad)
                }

                progressBarClass.dialog.dismiss()
                startActivity(nextIntent)

            } catch (e: Exception){

            }
        }, Response.ErrorListener{})

        queue.add(request)
    }

    private fun View.setSafeOnClickListener(onSafeClick: (View) -> Unit) {
        val safeClickListener = SafeClickListener {
            onSafeClick(it)
        }
        setOnClickListener(safeClickListener)
    }
}
