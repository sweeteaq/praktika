package com.example.carraceapp

open class Car(
    val brand: String,
    val model: String,
    val year: Int,
    val drive: String
) {
    open fun getPerformanceScore(): Int {

        return 0
    }

    fun printInfo() {
        println("Brand: $brand, Model: $model, Year: $year, Drive: $drive")
    }
}
