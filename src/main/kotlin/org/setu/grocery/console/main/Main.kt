package org.setu.grocery.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
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
    println("You Chose Add Grocery")
}

fun updateGrocery() {
    println("You Chose Update Grocery")
}

fun listGroceries() {
    println("You Chose List All Groceries")
}

fun listFruits(){
    println("You Chose List All Fruits")
}

fun listVegetables(){
    println("You Chose List All Vegetables")
}