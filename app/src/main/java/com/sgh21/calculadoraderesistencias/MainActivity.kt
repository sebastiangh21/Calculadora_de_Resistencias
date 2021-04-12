package com.sgh21.calculadoraderesistencias

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.sgh21.calculadoraderesistencias.databinding.ActivityMainBinding

const val EMPTY = ""

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.selectionBandSpinner.setSelection(2)
        val adapterMultiplicer = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.multiplier_list))
        val adapterTolerance = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.tolerance_list))
        val adapterBand3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.band23_list))
        val adapterTemperatureCoefficient = ArrayAdapter(this, android.R.layout.simple_spinner_item, resources.getStringArray(R.array.temperature_coefficient_list))
        var selectionBand = mainBinding.selectionBandSpinner.selectedItemPosition.toString()

        mainBinding.selectionBandSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                selectionBand = mainBinding.selectionBandSpinner.selectedItemPosition.toString()
                if (position == 0) {
                    mainBinding.band3Spinner.adapter = adapterMultiplicer
                    mainBinding.band4Spinner.adapter = adapterTolerance
                    mainBinding.band3TextView.text = getString(R.string.multiplier)
                    mainBinding.band4TextView.text = getString(R.string.tolerance)
                    mainBinding.band5TextView.visibility = View.INVISIBLE
                    mainBinding.band6TextView.visibility = View.INVISIBLE
                    mainBinding.band5Spinner.visibility = View.INVISIBLE
                    mainBinding.band6Spinner.visibility = View.INVISIBLE
                    mainBinding.color3ImageView.setBackgroundColor(0xFFd2b48c.toInt())
                    mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFFd2b48c.toInt())
                } else {
                    if (position == 1) {
                        mainBinding.band3Spinner.adapter = adapterBand3
                        mainBinding.band4Spinner.adapter = adapterMultiplicer
                        mainBinding.band5Spinner.adapter = adapterTolerance
                        mainBinding.band3TextView.text = getString(R.string.band_3)
                        mainBinding.band4TextView.text = getString(R.string.multiplier)
                        mainBinding.band5TextView.text = getString(R.string.tolerance)
                        mainBinding.band5TextView.visibility = View.VISIBLE
                        mainBinding.band6TextView.visibility = View.INVISIBLE
                        mainBinding.band5Spinner.visibility = View.VISIBLE
                        mainBinding.band6Spinner.visibility = View.INVISIBLE
                        mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFFd2b48c.toInt())
                    } else {
                        mainBinding.band3Spinner.adapter = adapterBand3
                        mainBinding.band4Spinner.adapter = adapterMultiplicer
                        mainBinding.band5Spinner.adapter = adapterTolerance
                        mainBinding.band6Spinner.adapter = adapterTemperatureCoefficient
                        mainBinding.band3TextView.text = getString(R.string.band_3)
                        mainBinding.band4TextView.text = getString(R.string.multiplier)
                        mainBinding.band5TextView.text = getString(R.string.tolerance)
                        mainBinding.band6TextView.text = getString(R.string.temperature_coefficient)
                        mainBinding.band5TextView.visibility = View.VISIBLE
                        mainBinding.band6TextView.visibility = View.VISIBLE
                        mainBinding.band5Spinner.visibility = View.VISIBLE
                        mainBinding.band6Spinner.visibility = View.VISIBLE
                    }
                }
                calculateResistance(selectionBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

        }

        mainBinding.band1Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> mainBinding.color1ImageView.setBackgroundColor(0xFF8b4513.toInt())
                    1 -> mainBinding.color1ImageView.setBackgroundColor(0xFFff0000.toInt())
                    2 -> mainBinding.color1ImageView.setBackgroundColor(0xFFff4500.toInt())
                    3 -> mainBinding.color1ImageView.setBackgroundColor(0xFFffff00.toInt())
                    4 -> mainBinding.color1ImageView.setBackgroundColor(0xFF008000.toInt())
                    5 -> mainBinding.color1ImageView.setBackgroundColor(0xFF0000ff.toInt())
                    6 -> mainBinding.color1ImageView.setBackgroundColor(0xFF800080.toInt())
                    7 -> mainBinding.color1ImageView.setBackgroundColor(0xFF808080.toInt())
                    8 -> mainBinding.color1ImageView.setBackgroundColor(0xFFFFFFFF.toInt())
                    else -> mainBinding.color1ImageView.setBackgroundColor(0xFFd2b48c.toInt())
                }
                calculateResistance(selectionBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        mainBinding.band2Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> mainBinding.color2ImageView.setBackgroundColor(0xFF000000.toInt())
                    1 -> mainBinding.color2ImageView.setBackgroundColor(0xFF8b4513.toInt())
                    2 -> mainBinding.color2ImageView.setBackgroundColor(0xFFff0000.toInt())
                    3 -> mainBinding.color2ImageView.setBackgroundColor(0xFFff4500.toInt())
                    4 -> mainBinding.color2ImageView.setBackgroundColor(0xFFffff00.toInt())
                    5 -> mainBinding.color2ImageView.setBackgroundColor(0xFF008000.toInt())
                    6 -> mainBinding.color2ImageView.setBackgroundColor(0xFF0000ff.toInt())
                    7 -> mainBinding.color2ImageView.setBackgroundColor(0xFF800080.toInt())
                    8 -> mainBinding.color2ImageView.setBackgroundColor(0xFF808080.toInt())
                    9 -> mainBinding.color2ImageView.setBackgroundColor(0xFFFFFFFF.toInt())
                    else -> mainBinding.color2ImageView.setBackgroundColor(0xFFd2b48c.toInt())
                }
                calculateResistance(selectionBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        mainBinding.band3Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (selectionBand == "0") {
                    when (position) {
                        0 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF000000.toInt())
                        1 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF8b4513.toInt())
                        2 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFff0000.toInt())
                        3 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFff4500.toInt())
                        4 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFffff00.toInt())
                        5 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF008000.toInt())
                        6 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF0000ff.toInt())
                        7 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF800080.toInt())
                        8 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF808080.toInt())
                        9 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFFFFFFF.toInt())
                        10 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFffd700.toInt())
                        11 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFc0c0c0.toInt())
                        else -> mainBinding.multiplierImageView.setBackgroundColor(0xFFd2b48c.toInt())
                    }
                } else {
                    when (position) {
                        0 -> mainBinding.color3ImageView.setBackgroundColor(0xFF000000.toInt())
                        1 -> mainBinding.color3ImageView.setBackgroundColor(0xFF8b4513.toInt())
                        2 -> mainBinding.color3ImageView.setBackgroundColor(0xFFff0000.toInt())
                        3 -> mainBinding.color3ImageView.setBackgroundColor(0xFFff4500.toInt())
                        4 -> mainBinding.color3ImageView.setBackgroundColor(0xFFffff00.toInt())
                        5 -> mainBinding.color3ImageView.setBackgroundColor(0xFF008000.toInt())
                        6 -> mainBinding.color3ImageView.setBackgroundColor(0xFF0000ff.toInt())
                        7 -> mainBinding.color3ImageView.setBackgroundColor(0xFF800080.toInt())
                        8 -> mainBinding.color3ImageView.setBackgroundColor(0xFF808080.toInt())
                        9 -> mainBinding.color3ImageView.setBackgroundColor(0xFFFFFFFF.toInt())
                        else -> mainBinding.color3ImageView.setBackgroundColor(0xFFd2b48c.toInt())
                    }
                }
                calculateResistance(selectionBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        mainBinding.band4Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (selectionBand == "0") {
                    when (position) {
                        0 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF8b4513.toInt())
                        1 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFff0000.toInt())
                        2 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFff4500.toInt())
                        3 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFffff00.toInt())
                        4 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF008000.toInt())
                        5 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF0000ff.toInt())
                        6 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF800080.toInt())
                        7 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF808080.toInt())
                        8 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFffd700.toInt())
                        9 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFc0c0c0.toInt())
                        10 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFd2b48c.toInt())
                        else -> mainBinding.toleranceImageView.setBackgroundColor(0xFFd2b48c.toInt())
                    }
                } else {
                    when (position) {
                        0 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF000000.toInt())
                        1 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF8b4513.toInt())
                        2 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFff0000.toInt())
                        3 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFff4500.toInt())
                        4 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFffff00.toInt())
                        5 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF008000.toInt())
                        6 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF0000ff.toInt())
                        7 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF800080.toInt())
                        8 -> mainBinding.multiplierImageView.setBackgroundColor(0xFF808080.toInt())
                        9 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFFFFFFF.toInt())
                        10 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFffd700.toInt())
                        11 -> mainBinding.multiplierImageView.setBackgroundColor(0xFFc0c0c0.toInt())
                        else -> mainBinding.multiplierImageView.setBackgroundColor(0xFFd2b48c.toInt())
                    }
                }
                calculateResistance(selectionBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        mainBinding.band5Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF8b4513.toInt())
                    1 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFff0000.toInt())
                    2 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFff4500.toInt())
                    3 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFffff00.toInt())
                    4 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF008000.toInt())
                    5 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF0000ff.toInt())
                    6 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF800080.toInt())
                    7 -> mainBinding.toleranceImageView.setBackgroundColor(0xFF808080.toInt())
                    8 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFffd700.toInt())
                    9 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFc0c0c0.toInt())
                    10 -> mainBinding.toleranceImageView.setBackgroundColor(0xFFd2b48c.toInt())
                    else -> mainBinding.toleranceImageView.setBackgroundColor(0xFFd2b48c.toInt())
                }
                calculateResistance(selectionBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        mainBinding.band6Spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position) {
                    0 -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFF000000.toInt())
                    1 -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFF8b4513.toInt())
                    2 -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFFff0000.toInt())
                    3 -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFFff4500.toInt())
                    4 -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFFffff00.toInt())
                    5 -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFF008000.toInt())
                    6 -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFF0000ff.toInt())
                    7 -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFF800080.toInt())
                    8 -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFF808080.toInt())
                    else -> mainBinding.temperatureCoefficientImageView.setBackgroundColor(0xFFd2b48c.toInt())
                }
                calculateResistance(selectionBand)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    private fun calculateResistance(selectionBand: String){
        val band1 = mainBinding.band1Spinner.selectedItemPosition
        val band2 = mainBinding.band2Spinner.selectedItemPosition.toString()
        val band3 = mainBinding.band3Spinner.selectedItemPosition.toString()
        val band4 = mainBinding.band4Spinner.selectedItemPosition.toString()
        val band5 = mainBinding.band5Spinner.selectedItemPosition.toString()
        val band6 = mainBinding.band6Spinner.selectedItemPosition.toString()
        val aux = band1 + 1
        var result = aux.toString()

        if (selectionBand == "0") {
            when (band3) {
                "0" -> result += band2
                "1" -> result += band2 + "0"
                "2" -> result += "," + band2 + "k"
                "3" -> result += band2 + "k"
                "4" -> result += band2 + "0k"
                "5" -> result += "," + band2 + "M"
                "6" -> result += band2 + "M"
                "7" -> result += band2 + "0M"
                "8" -> result += "," + band2 + "G"
                "9" -> result += band2 + "G"
                "10" -> result += ",$band2"
                "11" -> result = "0,$aux$band2"
                else -> result = EMPTY
            }

            result += "\u2126\u00B1"

            when (band4) {
                "0" -> result += "1%"
                "1" -> result += "2%"
                "2" -> result += "0.05%"
                "3" -> result += "0.02%"
                "4" -> result += "0.5%"
                "5" -> result += "0.25%"
                "6" -> result += "0.1%"
                "7" -> result += "0.01%"
                "8" -> result += "5%"
                "9" -> result += "10%"
                "10" -> result += "20%"
                else -> result = EMPTY
            }
        } else {
            when (band4) {
                "0" -> result += "$band2$band3"
                "1" -> result += ",$band2$band3" + "k"
                "2" -> result += band2 + "," + band3 + "k"
                "3" -> result += band2 + band3 + "k"
                "4" -> result += "," + band2 + band3 + "M"
                "5" -> result += band2 + "," + band3 + "M"
                "6" -> result += band2 + band3 + "M"
                "7" -> result += "," + band2 + band3 + "G"
                "8" -> result += band2 + "," + band3 + "G"
                "9" -> result += band2 + band3 + "G"
                "10" -> result += "$band2,$band3"
                "11" -> result += ",$band2$band3"
                else -> result = EMPTY
            }
            result += "\u2126\u00B1"

            when (band5) {
                "0" -> result += "1%"
                "1" -> result += "2%"
                "2" -> result += "0.05%"
                "3" -> result += "0.02%"
                "4" -> result += "0.5%"
                "5" -> result += "0.25%"
                "6" -> result += "0.1%"
                "7" -> result += "0.01%"
                "8" -> result += "5%"
                "9" -> result += "10%"
                "10" -> result += "20%"
                else -> result = EMPTY
            }
            if (selectionBand == "2") {
                result += "\u0040"

                when (band6) {
                    "0" -> result += "250"
                    "1" -> result += "100"
                    "2" -> result += "50"
                    "3" -> result += "15"
                    "4" -> result += "25"
                    "5" -> result += "20"
                    "6" -> result += "10"
                    "7" -> result += "5"
                    "8" -> result += "1"
                    else -> result = EMPTY
                }
                result += "ppm/\u2103"
            }
        }
        mainBinding.resultTextView.text = result
    }
}