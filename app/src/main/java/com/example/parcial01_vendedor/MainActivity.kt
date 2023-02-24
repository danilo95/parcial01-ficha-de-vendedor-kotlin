package com.example.parcial01_vendedor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

lateinit var nombre: EditText
lateinit var codigo: EditText
lateinit var ventas: EditText
lateinit var calcular: Button
lateinit var spinner: Spinner
var mesSelecionado: String = "";


class MainActivity : AppCompatActivity() {
    val meses = arrayOf(
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Octubre",
        "Noviembre",
        "Diciembre"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        nombre = findViewById(R.id.nombre)
        codigo = findViewById(R.id.codigo)
        ventas = findViewById(R.id.ventas)
        calcular = findViewById(R.id.calcular)
        spinner = findViewById<Spinner>(R.id.spinner)

        val arrayAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, meses)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                mesSelecionado = meses[p2]

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(
                    applicationContext,
                    "Debes elegir un mes para continuar ",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        calcular.setOnClickListener {
            if (validarDatos()) {
                val intent = Intent(this@MainActivity, ResultadosActivity::class.java)
                intent.putExtra("vendedor", nombre.text.toString())
                intent.putExtra("codigo", codigo.text.toString())
                intent.putExtra("ventas", ventas.text.toString())
                intent.putExtra("mes", mesSelecionado)

                startActivity(intent)
            }
        }
    }


    fun validarDatos(): Boolean {
        var esValido: Boolean = true;

        if (nombre.getText().toString().isEmpty()) {
            nombre.setError("Este campo es requerido")
            esValido = false
        }
        if (nombre.getText().toString().isEmpty()) {
            codigo.setError("Este campo es requerido")
            esValido = false
        }
        if (ventas.getText().toString().trim()
                .isEmpty() || Integer.parseInt(
                ventas.getText().toString().trim()
            ) <= 0
        ) {
            ventas.setError("Este campo es requerido y debe ser mayor a 0")
            esValido = false
        }
        return esValido;
    }
}