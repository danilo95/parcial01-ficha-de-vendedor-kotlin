package com.example.parcial01_vendedor

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

lateinit var regresar: Button
lateinit var vendedorLabel: TextView
lateinit var codigoLabel: TextView
lateinit var montoLabel: TextView
lateinit var mesLabel: TextView
lateinit var comisionLabel: TextView
lateinit var porcentajeComisionLabel: TextView
var porcentajeComision="0.0"

class ResultadosActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        //labels
        vendedorLabel=findViewById(R.id.vendedorLabel)
        codigoLabel=findViewById(R.id.codigoLabel)
        montoLabel=findViewById(R.id.montoLabel)
        mesLabel=findViewById(R.id.mesLabel)
        comisionLabel=findViewById(R.id.comisionLabel)
        porcentajeComisionLabel=findViewById(R.id.PorcentajeComision)
        //
        //variables
        val bundle = intent.extras
        val vendedor = bundle?.getString("vendedor")
        val codigo = bundle?.getString("codigo")
        val ventas = bundle?.getString("ventas")
        val mes = bundle?.getString("mes")
        val comisionARecibir=calcularComision((ventas.toString()).toDouble())

       //
        vendedorLabel.setText(vendedor)
        codigoLabel.setText(codigo)
        montoLabel.setText("$ "+ventas)
        mesLabel.setText(mes)
        comisionLabel.setText("$ "+comisionARecibir)
        porcentajeComisionLabel.setText(porcentajeComision+"%")


        regresar = findViewById(R.id.regresar)

        regresar.setOnClickListener {

            val intent = Intent(this@ResultadosActivity, MainActivity::class.java)
            startActivity(intent)

        }
    }

    private fun calcularComision(monto:Double):Double {
        var total:Double=0.0;
        porcentajeComision="0.0"
        if (monto > 500 && monto <1000) {
            total= (monto*0.05);
            porcentajeComision="0.05"
        }
        if (monto > 1000 && monto<2000){
            total= (monto*0.10);
            porcentajeComision="0.10"
        }
        if (monto > 2000 && monto<3000){
            total= (monto*0.15);
            porcentajeComision="0.15"
        }
        if (monto > 3000 && monto<4000){
            total= (monto*0.20);
            porcentajeComision="0.20"
        }
        if (monto >= 4000 ){
            total= (monto*0.30);
            porcentajeComision="0.30"
        }
        return total
    }


}