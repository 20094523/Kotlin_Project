package org.setu.grocery.console.models

interface GroceryStore {
    fun findAll(): List<GroceryModel>
    fun findOne(id: Long): GroceryModel?
    fun create(placemark: GroceryModel)
    fun update(placemark: GroceryModel)
}