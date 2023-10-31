package org.setu.grocery.console.main

import mu.KotlinLogging
import org.setu.grocery.console.models.GroceryModel

private val logger = KotlinLogging.logger {}
var grocery = GroceryModel()
val groceries = ArrayList<GroceryModel>()

fun main(args: Array<String>){
    logger.info { "Launching Grocery Console App" }
    println("Grocery Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addGrocery()
            2 -> updateGrocery()
            3 -> listGroceries()
            4 -> listFruits()
            5 -> listVegetables()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Grocery Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add Grocery")
    println(" 2. Update Grocery")
    println(" 3. List All Groceries")
    println(" 4. List All Fruits")
    println(" 5. List All Vegetables")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readln()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addGrocery(){

    println("Add Grocery")
    println()
    print("Enter a Title : ")
    grocery.title = readln()!!
    println("You entered "+ grocery.title +" for title")
    print("Enter a Price : ")
    grocery.price = readLine()?.toDoubleOrNull() ?: error("You need to enter a double")!!
    println("You entered " + grocery.price + " for price")
    print("Enter a type (1=fruit 2=vegetable) : ")
    grocery.type = readLine()?.toIntOrNull() ?: error("You need to enter an int")!!
    if (grocery.type==1){
        grocery.groceryType = "Fruit"
    }
    else{
        grocery.groceryType = "Vegetable"
    }

    println("You entered "+ grocery.groceryType +" for type")

    if (grocery.title.isNotEmpty()) {
        groceries.add(grocery.copy())
        logger.info("Grocery Added : [ $grocery ]")
    }
    else
        logger.info("Grocery Not Added")
}

fun updateGrocery() {
    println("Update Grocery")
    println("What do you want to update?")
    grocery.title = readln()!!
    print("Enter a new Title for [" + grocery.title + "] : ")
    grocery.title = readln()!!
    print("Enter a new price for [" + grocery.title + "] : ")
    grocery.price = readLine()?.toDoubleOrNull() ?: error("You need to enter a double")!!
    print("Enter a new type for [" + grocery.title + "] (1=fruit 2=vegetable) : ")
    grocery.type = readLine()?.toIntOrNull() ?: error("You need to enter an int")!!
    if (grocery.type==1){
        grocery.groceryType = "Fruit"
    }
    else{
        grocery.groceryType = "Vegetable"
    }
    println()
    println("You've updated " + grocery.title + " with the price [ " +  grocery.price + " ] and type [ "+ grocery.groceryType +" ]")

}

fun listGroceries() {
    println("You Chose List All Groceries")
    println()
    groceries.forEach { logger.info("${it}") }
}

fun listFruits(){
    println("You Chose List All Fruits")

    println()
    groceries.forEach { logger.info("${it}") }
}

fun listVegetables(){
    println("You Chose List All Vegetables")

    println()
    groceries.forEach { logger.info("${it}") }
}