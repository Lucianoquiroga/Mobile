package com.example.mlmobile

import android.graphics.Color
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mlmobile.ProductoFragment.OnListFragmentInteractionListener
import com.squareup.picasso.Picasso
import java.text.DecimalFormat

class MyProductoRecyclerViewAdapter(
    private val mListener: OnListFragmentInteractionListener?,
    mProductos: ArrayList<Producto>
) : RecyclerView.Adapter<MyProductoRecyclerViewAdapter.ViewHolder>() {

    var mProductos: ArrayList<Producto>? = null
    private val mOnClickListener: View.OnClickListener

    init {
        this.mProductos = mProductos
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Producto
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_producto, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val producto = mProductos?.get(position)
        val mYellow = ForegroundColorSpan(Color.rgb(255, 196, 0))
        val mGreen = ForegroundColorSpan(Color.rgb(46, 125, 50))
        val mRed = ForegroundColorSpan(Color.RED)
        val df = DecimalFormat("###,###.##")
        var price = df.format(mProductos?.get(position)?.price).toString()
        price = price.replace(oldValue = ",", newValue = ".")
        var condition: String

        if (mProductos?.get(position)?.condition.equals("new")) {
            condition = "Estado: Nuevo"

            val mSpannableString = SpannableString(condition)
            mSpannableString.setSpan(mGreen, 8, 13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.mIdViewCondition.text = mSpannableString
        } else if (mProductos?.get(position)?.condition.equals("used")) {
            condition = "Estado: Usado"

            val mSpannableString = SpannableString(condition)
            mSpannableString.setSpan(mYellow, 8, 13,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.mIdViewCondition.text = mSpannableString
        } else {
            condition = "Estado: Sin definir"

            val mSpannableString = SpannableString(condition)
            mSpannableString.setSpan(mRed, 8, 19,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            holder.mIdViewCondition.text = mSpannableString
        }

        holder.mIdViewTitle.text = mProductos?.get(position)?.title
        holder.mIdViewPrice.text = "$ $price"
        Picasso.get().load(mProductos?.get(position)?.thumbnail).resize(250, 250).centerCrop().into(holder.mIdViewPhoto)

        with(holder.mView) {
            tag = producto
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mProductos?.size!!

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mIdViewTitle: TextView = mView.findViewById(R.id.textViewTitle)
        val mIdViewCondition: TextView = mView.findViewById(R.id.textViewCondition)
        val mIdViewPhoto: ImageView = mView.findViewById(R.id.imageViewPhoto)
        val mIdViewPrice: TextView = mView.findViewById(R.id.textViewPrice)
    }
}
