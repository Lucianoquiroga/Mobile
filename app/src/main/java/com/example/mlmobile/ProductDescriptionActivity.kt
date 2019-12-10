package com.example.mlmobile

import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.text.DecimalFormat


class ProductDescriptionActivity : AppCompatActivity() {

    private var crud: ProductoCRUD? = null
    private var producto: Producto? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_description)

        val id: String = intent.getStringExtra("producto_id")
        this.setTitle("Producto")

        val orientation = this.resources.configuration.orientation

        if (orientation == 1) {
            setContentView(R.layout.activity_product_description)
        } else if (orientation == 2) {
            setContentView(R.layout.activity_product_description_landscape)
        }

        crud = ProductoCRUD(this)
        producto = crud?.getProducto(id)

        val textViewTitle: TextView = findViewById(R.id.textViewTitle)
        val textViewAvailableQuantity: TextView = findViewById(R.id.textViewAvailableQuantity)
        val textViewSoldQuantity: TextView = findViewById(R.id.textViewSoldQuantity)
        val textViewPrice: TextView = findViewById(R.id.textViewPrice)
        var imageViewThumbnail: ImageView = findViewById(R.id.imageViewThumbnail)
        val buttonPermalink: Button = findViewById(R.id.buttonPermalink)
        val textViewCondition: TextView = findViewById(R.id.textViewCondition)
        val textViewAddress: TextView = findViewById(R.id.textViewAddress)
        val condition: String
        val df = DecimalFormat("###,###.##")
        var price = df.format(producto?.price).toString()
        val mYellow = ForegroundColorSpan(Color.rgb(255, 196, 0))
        val mGreen = ForegroundColorSpan(Color.rgb(46, 125, 50))
        val mRed = ForegroundColorSpan(Color.RED)

        textViewTitle.text = producto?.title
        textViewAvailableQuantity.text = "Stock disponible: " + producto?.availableQuantity.toString()
        textViewSoldQuantity.text = "Vendidos: " + producto?.soldQuantity.toString()
        price = price.replace(oldValue = ",", newValue = ".")
        textViewPrice.text = "Precio de venta: $ " + price
        Picasso.get().load(producto?.thumbnail).resize(250, 250).centerCrop().into(imageViewThumbnail)
        textViewAddress.text = producto?.addressCityName + ", " + producto?.addressStateName

        if (producto?.condition.equals("new")) {
            condition = "Estado: Nuevo"

            val mSpannableString = SpannableString(condition)
            mSpannableString.setSpan(mGreen, 8, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            textViewCondition.text = mSpannableString
        } else if (producto?.condition.equals("used")) {
            condition = "Estado: Usado"

            val mSpannableString = SpannableString(condition)
            mSpannableString.setSpan(mYellow, 8, 13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            textViewCondition.text = mSpannableString
        } else {
            condition = "Estado: Sin definir"

            val mSpannableString = SpannableString(condition)
            mSpannableString.setSpan(mRed, 8, 19,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            textViewCondition.text = mSpannableString
        }

        buttonPermalink.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(producto?.permalink)
            startActivity(openURL)
        }

    }
}
