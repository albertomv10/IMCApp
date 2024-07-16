package com.example.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.imcapp.MainActivity.Companion.IMC_KEY
import java.text.DecimalFormat

class ResultIMCActivity : AppCompatActivity() {
    // esto es otra prueba para el pull request
    lateinit var txtIMC:TextView
    lateinit var txtIndicator: TextView
    lateinit var txtExplain: TextView
    lateinit var image:ImageView
    lateinit var btnBack: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result_imcactivity)
        initComponents()
        initiListeners()
    }

    private fun updateResult(imc:Double) {
        val decimalFormat = DecimalFormat("#.00")
        val result = decimalFormat.format(imc)
        txtIMC.text = result
        calculateHealth(imc)
    }

    private fun initComponents() {
        txtIMC = findViewById(R.id.imcText)
        txtIndicator = findViewById(R.id.indicatorText)
        txtExplain = findViewById(R.id.explainText)
        image = findViewById(R.id.image)
        btnBack = findViewById(R.id.buttonVolver)
        val imc = intent.extras?.getDouble(IMC_KEY) ?: 0.0
        updateResult(imc)
    }

    private fun initiListeners(){
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun calculateHealth(result:Double){
        when(result){
            in 0.0..18.49 ->{
                txtIndicator.text = "BAJO PESO"
                txtIndicator.setTextColor(ContextCompat.getColor(this, R.color.peso_bajo))
                txtExplain.text = "Estas en los huesos, pide unos cuantos knoweats que están muy buenos"
                image.setImageResource(R.drawable.xokasesqueleto)
            }
            in 18.50..24.99 -> {
                txtIndicator.text = "PESO NORMAL"
                txtIndicator.setTextColor(ContextCompat.getColor(this, R.color.peso_normal))
                txtExplain.text = "Buen peso bro pero no te confies, shhh y te callas."

                image.setImageResource(R.drawable.xokasnormal)
            }
            in 25.00..29.99 ->{
                txtIndicator.text = "SOBREPESO"
                txtIndicator.setTextColor(ContextCompat.getColor(this, R.color.sobrepeso))
                txtExplain.text = "Estas modo El Fokas, comprate una casa de 2 millones de euros con un gimnasio privado y entrenador personal"
                image.setImageResource(R.drawable.xokassobrepeso2)
            }
            in 30.00..100.00 -> {
                txtIndicator.text = "OBESIDAD"
                txtIndicator.setTextColor(ContextCompat.getColor(this, R.color.obesidad))
                txtExplain.text = "Estás gordito, ¿y que?"
                image.setImageResource(R.drawable.xokasobesidad)
            }
        }

    }
}