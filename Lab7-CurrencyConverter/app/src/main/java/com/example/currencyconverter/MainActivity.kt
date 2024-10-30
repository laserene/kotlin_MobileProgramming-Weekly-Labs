package com.example.currencyconverter

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

class MainActivity : AppCompatActivity() {
    private lateinit var sourceAmount: EditText
    private lateinit var destinationAmount: EditText
    private lateinit var sourceCurrency: Spinner
    private lateinit var destinationCurrency: Spinner
    // Conversion rates relative to USD
    private val currencyRates = mapOf("USD" to 1.0, "VND" to 25355.0, "SGD" to 1.32, "EUR" to 0.92, "JPY" to 153.26)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sourceAmount = findViewById(R.id.sourceAmount)
        destinationAmount = findViewById(R.id.destinationAmount)
        sourceCurrency = findViewById(R.id.sourceCurrency)
        destinationCurrency = findViewById(R.id.destinationCurrency)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.currency_array,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        sourceCurrency.adapter = adapter
        destinationCurrency.adapter = adapter

        sourceCurrency.setSelection(adapter.getPosition("VND"))
        destinationCurrency.setSelection(adapter.getPosition("USD"))

        sourceAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertCurrency()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        sourceCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                handleCurrencySwap()
                convertCurrency()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        destinationCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: android.view.View?, position: Int, id: Long) {
                handleCurrencySwap()
                convertCurrency()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
    private fun convertCurrency() {
        val sourceText = sourceAmount.text.toString()
        val sourceValue = if (sourceText.isEmpty()) 0.0 else sourceText.toDoubleOrNull() ?: 0.0

        val sourceRate = currencyRates[sourceCurrency.selectedItem.toString()] ?: 1.0
        val destinationRate = currencyRates[destinationCurrency.selectedItem.toString()] ?: 1.0

        val rawConvertedValue = (sourceValue / sourceRate) * destinationRate

        var formattedValue = BigDecimal(rawConvertedValue)
            .setScale(10, RoundingMode.HALF_UP)
            .stripTrailingZeros()

        if (formattedValue.precision() > 16) {
            formattedValue = formattedValue.round(MathContext(16, RoundingMode.HALF_UP))
        }

        destinationAmount.setText(formattedValue.toPlainString())
    }
    private fun handleCurrencySwap() {
        val selectedSource = sourceCurrency.selectedItem.toString()
        val selectedDestination = destinationCurrency.selectedItem.toString()

        if (selectedSource == selectedDestination) {

            destinationCurrency.setSelection(getPreviousSourcePosition(selectedSource))
        }
    }
    private fun getPreviousSourcePosition(selectedSource: String): Int {

        for (i in 0 until sourceCurrency.adapter.count) {
            if (sourceCurrency.adapter.getItem(i) != selectedSource) {
                return i
            }
        }
        return 0;
    }
}