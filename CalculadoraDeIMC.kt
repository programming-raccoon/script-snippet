package com.example.appimcimm

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CalculadoraDeIMC :  AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculadora_de_imc)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Dotamos de funcionalidad al btn de calcular
        btnCalcular.setOnClickListener() {

            // Declaración de constantes que vamos a usar
            val masa = editTextMasa.text.toString().toDouble()
            val estatura = editTextEstatura.text.toString().toDouble()
            val imc = calcularIMC(masa, estatura)

            // Mtd para redondear el numero Double en 2 decimales
            val imcFormateado = DecimalFormat("#.##").format(imc)

            // Notficación Toast para darle un feedback al usuario con la acción de clickar
            val toast = Toast.makeText(this, "Procesado correctamente",Toast.LENGTH_SHORT)
            toast.show()

            // Imprimimos la linea de txt mediante la TextView habilitada
            textoResultadoIMC.text = "Tu IMC actual es de $imcFormateado"

            // Clasificación de la OMS del estado nutricional de acuerdo con el IMC
            when (imc) {
                in 0.00..15.99 -> {
                    textoRangoIMC.text = "Clasificación: Delgadez severa"
                }

                in 16.00..16.99 -> {
                    textoRangoIMC.text = "Clasificación: Delagadez moderada"
                }

                in 17.00..18.49 -> {
                    textoRangoIMC.text = "Clasificación: Delgadez leve"
                }

                in 18.50..24.99 -> {
                    textoRangoIMC.text = "Clasificación: Peso normal"
                }

                in 25.00..29.99 -> {
                    textoRangoIMC.text = "Clasificación: Sobrepeso"
                }

                in 30.00..34.99 -> {
                    textoRangoIMC.text = "Clasificación: Obesidad leve"
                }

                in 35.00..39.99 -> {
                    textoRangoIMC.text = "Clasificación: Obesidad media"
                }

                else -> {
                    textoRangoIMC.text = "Clasificación: Obesidad mórbida"
                }
            }
        }
    }

    // Fun para calcular el IMC
    fun calcularIMC(peso: Double, altura: Double): Double {
        val alturaAlCuadrado: Double = altura * altura

        val imc = peso / alturaAlCuadrado
        return imc
    }
}
