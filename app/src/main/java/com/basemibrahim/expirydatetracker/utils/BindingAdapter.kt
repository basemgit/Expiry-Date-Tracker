package com.basemibrahim.expirydatetracker.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.basemibrahim.expirydatetracker.R
import com.basemibrahim.expirydatetracker.data.ExpiredProduct
import com.basemibrahim.expirydatetracker.data.Product
import com.basemibrahim.expirydatetracker.ui.ExpiredProductsAdapter
import com.basemibrahim.expirydatetracker.ui.ProductsAdapter
import java.util.*

@BindingAdapter("list")
fun bindRecyclerView(recyclerView: RecyclerView, list:List<Product>?) {
    val adapter = recyclerView.adapter as ProductsAdapter
    adapter.submitList(list)
}

@BindingAdapter("expiredList")
fun bindExpiredRecyclerView(recyclerView: RecyclerView, expiredList:List<ExpiredProduct>?) {
    val adapter = recyclerView.adapter as ExpiredProductsAdapter
    adapter.submitList(expiredList)
}
@BindingAdapter("productName","productType","expiryDate", requireAll = false)
fun bindDetails(textView: TextView, productName: String?, productType: String?,expiryDate: Date? ) {
    productName.let {
        textView.text = textView.context.resources.getString(R.string.product_name).plus(" : ").
        plus(it)
    }
    productType?.let {
        textView.text = textView.context.resources.getString(R.string.product_type).plus(" : ").
        plus(it)
    }
    expiryDate?.let {
        textView.text = textView.context.resources.getString(R.string.expiry_date).plus(" : ").
        plus(Utils.dateToString(it,"dd-MM-yyyy"))
    }

}