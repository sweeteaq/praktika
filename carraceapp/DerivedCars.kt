package com.example.carraceapp

class SUV(
    brand: String,
    model: String,
    year: Int,
    drive: String,
    val enginePower: Int
) : Car(brand, model, year, drive) {
    override fun getPerformanceScore(): Int {
        // Возвращаем оценку на основе мощности двигателя
        return enginePower
    }
}

class Sedan(
    brand: String,
    model: String,
    year: Int,
    drive: String,
    val luxuryLevel: Int
) : Car(brand, model, year, drive) {
    override fun getPerformanceScore(): Int {
        // Возвращаем оценку на основе уровня роскоши
        return luxuryLevel * 10
    }
}

class Truck(
    brand: String,
    model: String,
    year: Int,
    drive: String,
    val payloadCapacity: Int
) : Car(brand, model, year, drive) {
    override fun getPerformanceScore(): Int {
        // Возвращаем оценку на основе грузоподъемности
        return payloadCapacity / 100
    }
}

class Coupe(
    brand: String,
    model: String,
    year: Int,
    drive: String,
    val sportiness: Int
) : Car(brand, model, year, drive) {
    override fun getPerformanceScore(): Int {
        // Возвращаем оценку на основе спортивности
        return sportiness * 20
    }
}
