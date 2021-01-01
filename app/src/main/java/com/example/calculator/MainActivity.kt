package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var lastNumeric: Boolean = false

    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun onDigit(view: View) {
        findViewById<TextView>(R.id.tvInput).append((view as Button).text)

        lastNumeric = true
    }

    /**
     * Append . to the TextView
     */
    fun onDecimalPoint(view: View) {

        if (lastNumeric && !lastDot) {
            findViewById<TextView>(R.id.tvInput).append(".")
            lastNumeric = false // Update the flag
            lastDot = true // Update the flag
        }
    }


    fun onOperator(view: View) {
        if (lastNumeric && !isOperatorAdded(findViewById<TextView>(R.id.tvInput).text.toString())) {
            findViewById<TextView>(R.id.tvInput).append((view as Button).text)
            lastNumeric = false
            lastDot = false
        }
    }


    fun onClear(view: View) {
        findViewById<TextView>(R.id.tvInput).text = ""
        lastNumeric = false
        lastDot = false
    }


    fun onEqual(view: View) {
        if (lastNumeric) {
            var value = findViewById<TextView>(R.id.tvInput).text.toString()
            var prefix = ""
            try {

                if (value.startsWith("-")) {
                    prefix = "-"
                    value = value.substring(1);
                }

                if (value.contains("/")) {
                    val splitedValue = value.split("/")

                    var one = splitedValue[0]
                    val two = splitedValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    findViewById<TextView>(R.id.tvInput).text = removeZeroAfterDot((one.toDouble() / two.toDouble()).toString())
                } else if (value.contains("*")) {

                    val splitedValue = value.split("*")

                    var one = splitedValue[0]
                    val two = splitedValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    findViewById<TextView>(R.id.tvInput).text = removeZeroAfterDot((one.toDouble() * two.toDouble()).toString())
                } else if (value.contains("-")) {


                    val splitedValue = value.split("-")

                    var one = splitedValue[0]
                    val two = splitedValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }


                    findViewById<TextView>(R.id.tvInput).text = removeZeroAfterDot((one.toDouble() - two.toDouble()).toString())
                } else if (value.contains("+")) {

                    val splitedValue = value.split("+")

                    var one = splitedValue[0]
                    val two = splitedValue[1]

                    if (!prefix.isEmpty()) {
                        one = prefix + one
                    }

                    findViewById<TextView>(R.id.tvInput).text = removeZeroAfterDot((one.toDouble() + two.toDouble()).toString())
                }
            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }


    private fun isOperatorAdded(value: String): Boolean {

        return if (value.startsWith("-")) {
            false
        } else {
            (value.contains("/")
                    || value.contains("*")
                    || value.contains("-")
                    || value.contains("+"))
        }
    }


    private fun removeZeroAfterDot(result: String): String {

        var value = result

        if (result.contains(".0")) {
            value = result.substring(0, result.length - 2)
        }

        return value
    }
}