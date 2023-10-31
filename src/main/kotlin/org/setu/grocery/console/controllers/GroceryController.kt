package org.setu.grocery.console.controllers

import mu.KotlinLogging
import org.setu.grocery.console.models.GroceryJSONStore

import org.setu.grocery.console.models.GroceryModel
import org.setu.grocery.console.views.GroceryView
class GroceryController {
    val groceryView = GroceryView()
    //val groceries = GroceryMemStore()
    val groceries = GroceryJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Grocery Console App" }
        println("Grocery Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Grocery Console App" }
    }

    fun menu() :Int { return groceryView.menu() }

    fun add(){
        var aGrocery = GroceryModel()

        if (groceryView.addGroceryData(aGrocery))
            groceries.create(aGrocery)
        else
            logger.info("Grocery Not Added")
    }

    fun list() {
        groceryView.listGroceries(groceries)
    }

    fun update() {

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

    fun search() {
        val aGrocery = search(groceryView.getId())!!
        groceryView.showGrocery(aGrocery)
    }


    fun search(id: Long) : GroceryModel? {
        var foundGrocery = groceries.findOne(id)
        return foundGrocery
    }
}