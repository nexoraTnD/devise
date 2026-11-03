package com.example.currencyconverter

import android.os.Bundle
import android.view.View
import android.graphics.Paint
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyconverter.databinding.ActivityMainBinding
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val currencies = listOf("USD", "EUR", "GBP", "XOF")
    // Nombre d'unités de chaque devise pour un dollar américain. Taux fixes pédagogiques.
    private val rates = mapOf("USD" to 1.0, "EUR" to 0.92, "GBP" to 0.79, "XOF" to 610.0)
    private val input = StringBuilder("0")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) // View Binding remplace findViewById.
        setContentView(binding.root)
        // Souligner montant et résultat comme dans la maquette du TP.
        binding.tvMontant.paintFlags = binding.tvMontant.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        binding.tvResultat.paintFlags = binding.tvResultat.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        setupSpinners()
        setupKeypad()
        binding.btnInverser.setOnClickListener {
            val source = binding.spDeviseSource.selectedItemPosition
            val target = binding.spDeviseCible.selectedItemPosition
            binding.spDeviseSource.setSelection(target)
            binding.spDeviseCible.setSelection(source)
            binding.tvResultat.text = ""
        }
        binding.btnConvertir.setOnClickListener { convertAmount() }
    }

    private fun setupSpinners() {
        // Un ArrayAdapter alimente chacun des deux menus de devises.
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies).also {
            it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
        binding.spDeviseSource.adapter = adapter
        binding.spDeviseCible.adapter = adapter
        binding.spDeviseCible.setSelection(1)
    }

    private fun setupKeypad() {
        // Les boutons chiffres ajoutent un caractère à la chaîne affichée.
        val digitIds = listOf(binding.key0, binding.key1, binding.key2, binding.key3, binding.key4, binding.key5, binding.key6, binding.key7, binding.key8, binding.key9)
        digitIds.forEach { button -> button.setOnClickListener { appendDigit((it as Button).text.toString()) } }
        binding.keyDecimal.setOnClickListener {
            if (!input.contains('.')) { if (input.isEmpty()) input.append('0'); input.append('.') }
            refreshAmount()
        }
        binding.keyClear.setOnClickListener { input.clear(); input.append('0'); refreshAmount(); binding.tvResultat.text = "" }
    }

    private fun appendDigit(digit: String) {
        if (input.toString() == "0") input.clear()
        if (input.length < 12) input.append(digit)
        refreshAmount()
    }

    private fun refreshAmount() { binding.tvMontant.text = input.toString() }

    private fun convertAmount() {
        val amount = input.toString().toDoubleOrNull()
        if (amount == null || !amount.isFinite() || amount <= 0.0 || amount > 1_000_000.0) {
            Toast.makeText(this, R.string.invalid_amount, Toast.LENGTH_SHORT).show()
            return
        }
        val source = currencies[binding.spDeviseSource.selectedItemPosition]
        val target = currencies[binding.spDeviseCible.selectedItemPosition]
        val result = amount / rates.getValue(source) * rates.getValue(target)
        // Deux décimales et séparateur décimal point pour un affichage prévisible.
        val symbols = DecimalFormatSymbols(Locale.US)
        binding.tvResultat.text = DecimalFormat("#,##0.00", symbols).format(result)
    }
}
