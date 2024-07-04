package com.example.carraceapp

class CarBuilder(private val brand: String) {
    private var model: String = ""
    private var year: Int = 0
    private var drive: String = ""

    fun setModel(model: String) = apply { this.model = model }
    fun setYear(year: Int) = apply { this.year = year }
    fun setDrive(drive: String) = apply { this.drive = drive }

    fun build(): Car {
        return Car(brand, model, year, drive)
    }
}
