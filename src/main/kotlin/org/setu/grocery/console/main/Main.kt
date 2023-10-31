package org.setu.grocery.console.main

import mu.KotlinLogging
import org.setu.grocery.console.models.GroceryModel
import org.setu.grocery.console.models.GroceryMemStore
import org.setu.grocery.console.views.GroceryView

private val logger = KotlinLogging.logger {}

val groceryView = GroceryView()
val groceries = GroceryMemStore()

fun main(args: Array<String>){
    logger.info { "Launching Grocery Console App" }
    println("Grocery Kotlin App Version 1.0")

    var input: Int

    do {
        input = groceryView.menu()
        when(input) {
            1 -> addGrocery()
            2 -> updateGrocery()
            3 -> groceryView.listGroceries(groceries)
            //4 -> listFruits()
            //5 -> listVegetables()
            6 -> searchGrocery()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Grocery Console App" }
}

fun addGrocery(){
    var aGrocery = GroceryModel()

    if (groceryView.addGroceryData(aGrocery))
        groceries.create(aGrocery)
    else
        logger.info("Grocery not added.")
}

fun updateGrocery() {

    groceryView.listGroceries(groceries)
    var searchId = groceryView.getId()
    val aGrocery = search(searchId)

    if(aGrocery != null) {
        if(groceryView.updateGroceryData(aGrocery)) {
            groceries.update(aGrocery)
            groceryView.showGrocery(aGrocery)
            logger.info("Grocery Updated : [ $aGrocery ]")
        }
        else
            logger.info("Grocery Not Updated")
    }
    else
        println("Grocery Not Updated...")
}

fun searchGrocery() {
    val aGrocery = search(groceryView.getId())!!
    groceryView.showGrocery(aGrocery)
}


fun search(id: Long) : GroceryModel? {
    var foundGrocery = groceries.findOne(id)
    return foundGrocery
}