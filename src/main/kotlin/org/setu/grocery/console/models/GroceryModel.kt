package org.setu.grocery.console.models

data class GroceryModel(
    var title: String = "",
    var price: Double = .0,
    var type: Int = 0,
    var groceryType: String = ""
    )