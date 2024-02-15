package com.example.temperatureconverter

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val celsius = findViewById<SeekBar>(R.id.celsiusSeekbar)
        val fahrenheit = findViewById<SeekBar>(R.id.fahrenheitSeekbar)
        val message = findViewById<TextView>(R.id.interestingMessage)

        fahrenheit.progress = 32
        message.text = "I wish it were warmer."

        val fahrenheitLabel = findViewById<TextView>(R.id.fahrenheitView)
        val celsiusLabel = findViewById<TextView>(R.id.celsiusView)

        var celsiusAdjust = false
        var fahrenheitAdjust = false

        celsius.max = 100
        fahrenheit.max = 212

        celsius.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (!fahrenheitAdjust) {
                    if (celsius.progress < 20) {
                        message.text = "I wish it were warmer."
                    } else {
                        message.text = "I wish it were colder."
                    }
                    val convertedTemp = (progress * 9.0 / 5) + 32
                    fahrenheitLabel.text = "Fahrenheit: $convertedTemp"
                    celsiusLabel.text = "Celsius: ${progress.toDouble()}"
                    celsiusAdjust = true
                    fahrenheit.progress = convertedTemp.toInt()
                    celsiusAdjust = false

                    fahrenheit.progress = convertedTemp.toInt()
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        fahrenheit.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (!celsiusAdjust) {
                    if (progress < 32) {
                        // Snap back to 32°F
                        fahrenheit.progress = 32
                    } else {
                        val convertedTemp = (progress - 32) * 5.0 / 9
                        fahrenheitLabel.text = "Fahrenheit: ${progress.toDouble()}"
                        celsiusLabel.text = "Celsius: $convertedTemp"
                        fahrenheitAdjust = true
                        celsius.progress = convertedTemp.toInt()
                        if (celsius.progress < 20) {
                            message.text = "I wish it were warmer."
                        } else {
                            message.text = "I wish it were colder."
                        }
                        fahrenheitAdjust = false
                    }
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}