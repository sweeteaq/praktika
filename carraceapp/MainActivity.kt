package com.example.carraceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.util.Log
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val numberOfCarsEditText = findViewById<EditText>(R.id.numberOfCarsEditText)
        val startRaceButton = findViewById<Button>(R.id.startRaceButton)
        resultTextView = findViewById(R.id.resultTextView)

        startRaceButton.setOnClickListener {
            val numberOfCarsStr = numberOfCarsEditText.text.toString()
            if (numberOfCarsStr.isNotEmpty()) {
                val numberOfCars = numberOfCarsStr.toIntOrNull()
                if (numberOfCars != null && numberOfCars > 0) {
                    val cars = createRandomCars(numberOfCars)
                    conductRaces(cars)
                } else {
                    showToast("Please enter a valid number of cars")
                }
            } else {
                showToast("Please enter a number of cars")
            }
        }
    }

    private fun createRandomCars(count: Int): List<Car> {
        val brands = listOf("Toyota", "Honda", "Ford", "BMW")
        val models = listOf("ModelA", "ModelB", "ModelC", "ModelD")
        val drives = listOf("FWD", "RWD", "AWD")
        val years = (2000..2023).toList()

        val cars = List(count) {
            when (Random.nextInt(4)) {
                0 -> SUV(
                    brand = brands.random(),
                    model = models.random(),
                    year = years.random(),
                    drive = drives.random(),
                    enginePower = Random.nextInt(100, 300)
                )
                1 -> Sedan(
                    brand = brands.random(),
                    model = models.random(),
                    year = years.random(),
                    drive = drives.random(),
                    luxuryLevel = Random.nextInt(1, 10)
                )
                2 -> Truck(
                    brand = brands.random(),
                    model = models.random(),
                    year = years.random(),
                    drive = drives.random(),
                    payloadCapacity = Random.nextInt(500, 5000)
                )
                else -> Coupe(
                    brand = brands.random(),
                    model = models.random(),
                    year = years.random(),
                    drive = drives.random(),
                    sportiness = Random.nextInt(1, 10)
                )
            }
        }

        // Debug output for generated cars
        Log.d("CarsList", "Generated cars: $cars")
        return cars
    }

    private fun conductRaces(cars: List<Car>) {
        var raceCars = cars.shuffled().toMutableList()
        val raceResults = StringBuilder()

        while (raceCars.size > 1) {
            val winners = mutableListOf<Car>()
            Log.d("Race", "Starting a new round with ${raceCars.size} cars")

            for (i in raceCars.indices step 2) {
                if (i + 1 < raceCars.size) {
                    val car1 = raceCars[i]
                    val car2 = raceCars[i + 1]
                    val winner = race(listOf(car1, car2))
                    winners.add(winner)
                    raceResults.append("Race between ${car1.brand} ${car1.model} and ${car2.brand} ${car2.model}. Winner: ${winner.brand} ${winner.model}\n")
                } else {
                    Log.d("Race", "${raceCars[i].brand} ${raceCars[i].model} has no pair, advancing to the next round")
                    raceResults.append("${raceCars[i].brand} ${raceCars[i].model} has no pair, advancing to the next round\n")
                    winners.add(raceCars[i])
                }
            }
            raceCars = winners
        }

        val overallWinner = raceCars.first()
        raceResults.append("Overall winner: ${overallWinner.brand} ${overallWinner.model}")
        Log.d("Race", raceResults.toString())
        showToast(raceResults.toString())
        resultTextView.text = raceResults.toString()
    }

    private fun race(cars: List<Car>): Car {
        val car1 = cars[0]
        val car2 = cars[1]
        val winner = if (car1.getPerformanceScore() > car2.getPerformanceScore()) car1 else car2
        Log.d("Race", "Race between ${car1.brand} ${car1.model} and ${car2.brand} ${car2.model}. Winner: ${winner.brand} ${winner.model}")
        return winner
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
