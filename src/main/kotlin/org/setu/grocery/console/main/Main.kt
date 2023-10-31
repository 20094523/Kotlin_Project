package org.setu.grocery.console.main

import mu.KotlinLogging
import org.setu.grocery.console.models.GroceryModel

private val logger = KotlinLogging.logger {}
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
            6 -> searchGrocery()
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
    println(" 6. Search for Vegetable")
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
    var aGrocery = GroceryModel()

    println("Add Grocery")
    println()
    print("Enter a Title : ")
    aGrocery.title = readln()!!
    println("You entered "+ aGrocery.title +" for title")
    print("Enter a Price : ")
    aGrocery.price = readLine()?.toDoubleOrNull() ?: error("You need to enter a double")!!
    println("You entered " + aGrocery.price + " for price")
    print("Enter a type (1=fruit 2=vegetable) : ")
    aGrocery.type = readLine()?.toIntOrNull() ?: error("You need to enter an int")!!
    if (aGrocery.type==1){
        aGrocery.groceryType = "Fruit"
    }
    else{
        aGrocery.groceryType = "Vegetable"
    }
    aGrocery.id++
    println("You entered "+ aGrocery.groceryType +" for type")

    if (aGrocery.title.isNotEmpty()) {
        aGrocery.id = groceries.size.toLong()
        groceries.add(aGrocery.copy())
        logger.info("Grocery Added : [ $aGrocery ]")
    }
    else
        logger.info("Grocery Not Added")
}

fun updateGrocery() {

    println("Update Grocery")
    println()
    listGroceries()
    var searchId = getId()
    val aGrocery = search(searchId)


    if(aGrocery != null) {
        println("What do you want to update?")
        aGrocery.title = readln()!!
        print("Enter a new Title for [" + aGrocery.title + "] : ")
        aGrocery.title = readln()!!
        print("Enter a new price for [" + aGrocery.title + "] : ")
        aGrocery.price = readLine()?.toDoubleOrNull() ?: error("You need to enter a double")!!
        print("Enter a new type for [" + aGrocery.title + "] (1=fruit 2=vegetable) : ")
        aGrocery.type = readLine()?.toIntOrNull() ?: error("You need to enter an int")!!
        if (aGrocery.type == 1) {
            aGrocery.groceryType = "Fruit"
        } else {
            aGrocery.groceryType = "Vegetable"
        }
        println()
        println("You've updated " + aGrocery.title + " with the price [ " + aGrocery.price + " ] and type [ " + aGrocery.groceryType + " ]")
    }
    else
        println("Grocery not updated.")
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

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readln()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : GroceryModel? {
    var foundGrocery: GroceryModel? = groceries.find { p -> p.id == id }
    return foundGrocery
}

fun searchGrocery() {

    var searchId = getId()
    val aGrocery = search(searchId)

    if(aGrocery != null)
        println("Grocery Details [ $aGrocery ]")
    else
        println("Grocery Not Found...")
}