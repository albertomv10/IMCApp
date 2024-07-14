package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class MainActivity : AppCompatActivity() {
    // prueba de pull request
    private var isMaleSelected = true
    private var isFemaleSelected = false
    private var currentHeight = 130
    private var currentWeight: Int = 70
    private var currentAge: Int = 25

    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var txtHeight: TextView
    private lateinit var range: RangeSlider
    private lateinit var btnSumWeight: FloatingActionButton
    private lateinit var btnResWeight: FloatingActionButton
    private lateinit var txtWeight: TextView
    private lateinit var btnSumAge: FloatingActionButton
    private lateinit var btnResAge: FloatingActionButton
    private lateinit var txtAge: TextView
    private lateinit var btnCalculate: Button

    companion object{
        const val IMC_KEY = "IMC_Result"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()
        initListeners()
        initUi()
    }

    private fun initComponents() {
        viewMale = findViewById(R.id.cardView1)
        viewFemale = findViewById(R.id.cardView2)
        txtHeight = findViewById(R.id.height)
        range = findViewById(R.id.range)
        btnSumWeight = findViewById(R.id.btnSumWeight)
        btnResWeight = findViewById(R.id.btnResWeight)
        txtWeight = findViewById(R.id.textWeight)
        btnSumAge = findViewById(R.id.btnSumAge)
        btnResAge = findViewById(R.id.btnResAge)
        txtAge = findViewById(R.id.textAge)
        btnCalculate = findViewById(R.id.button)
    }
    private fun initUi() {
        updateButtonColors()
        txtWeight.text = currentWeight.toString()
        txtAge.text = currentAge.toString()
    }

    private fun initListeners() {
        viewMale.setOnClickListener { selectGender(isMale = true) }
        viewFemale.setOnClickListener { selectGender(isMale = false) }
        range.addOnChangeListener { _, value, _ ->
            currentHeight = value.toInt()
            val txtAltura = "$currentHeight cm"
            txtHeight.text = txtAltura
        }
        btnSumWeight.setOnClickListener {
            currentWeight++
            txtWeight.text = currentWeight.toString()
        }
        btnResWeight.setOnClickListener {
            if (currentWeight>0) {
                currentWeight--
                txtWeight.text = currentWeight.toString()
            }
        }
        btnSumAge.setOnClickListener {
            currentAge++
            txtAge.text = currentAge.toString()
        }
        btnResAge.setOnClickListener {
            if (currentAge>0) {
                currentAge--
                txtAge.text = currentAge.toString()
            }
        }
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }

    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra("IMC_Result", result)
        startActivity(intent)
    }

    private fun selectGender(isMale: Boolean) {
        isMaleSelected = isMale
        isFemaleSelected = !isMale
        updateButtonColors()
    }

    private fun updateButtonColors() {
        viewMale.setCardBackgroundColor(getCardColor(isMaleSelected))
        viewFemale.setCardBackgroundColor(getCardColor(isFemaleSelected))
    }

    private fun getCardColor(isSelected: Boolean): Int {
        val colorReference = if (isSelected) {
            R.color.selected_button
        } else {
            R.color.button
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun calculateIMC():Double{
        return currentWeight/(currentHeight*currentHeight*0.0001)
    }
}



