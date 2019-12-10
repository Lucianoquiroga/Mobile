package com.example.mlmobile


class Producto (
    id: String, title: String, price: Int, condition: String,
    thumbnail: String, availableQuantity: Int,soldQuantity: Int,
    permalink: String, addressStateName: String, addressCityName: String) {

    var id: String? = ""
    var title: String = ""
    var price: Int = 0
    var condition: String = ""
    var thumbnail: String = ""
    var availableQuantity: Int = 0
    var soldQuantity: Int = 0
    var permalink: String = ""
    var addressStateName: String = ""
    var addressCityName: String = ""

    init {
        this.id = id
        this.title = title
        this.price = price
        this.condition = condition
        this.thumbnail = thumbnail
        this.availableQuantity = availableQuantity
        this.soldQuantity = soldQuantity
        this.permalink = permalink
        this.addressStateName = addressStateName
        this.addressCityName = addressCityName
    }

}