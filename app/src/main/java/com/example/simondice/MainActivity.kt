package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var secuencia = ArrayList<String>()
    var select = ArrayList<String>()
    var ronda: Int = 0
    lateinit var textRec: TextView
    lateinit var lose: TextView
    lateinit var numero: TextView
    lateinit var start :Button
    val miModelo by viewModels<MyViewModel>()
    //Declaración de los Botones de colores
    lateinit var rojo : Button
    lateinit var amarillo : Button
    lateinit var verde : Button
    lateinit var azul : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lose= findViewById(R.id.Derrrota)
        numero = findViewById(R.id.Numero)
        start= findViewById(R.id.Comenzar)
        textRec= findViewById(R.id.Ronda)
        //Declaración de los Botones de colores
        rojo = findViewById(R.id.Rojo)
        amarillo= findViewById(R.id.Amarillo)
        verde = findViewById(R.id.Verde)
        azul = findViewById(R.id.Azul)
        start.setOnClickListener { generarSecuencia() }
    }

    //Método random para la generación de secuencia
    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }


    fun generarSecuencia(){
        textRec.text = "Ronda"
        ronda= secuencia.size+1
        numero.setText(ronda.toString())
        val colores = ArrayList<String>()
        colores.addAll(listOf("Rojo", "Azul", "Verde", "Amarillo"))
        val random = Random()

        secuencia.add(colores[random.nextInt(0..4)])
        mostrar()
    }

    fun mostrar(){
        GlobalScope.launch{
            println(miModelo.record.value)
            for (i in 0..secuencia.size) {
                if(i<secuencia.size) {
                val muestra: String = secuencia[i]
                Log.d("datos",muestra)
                    start.text = ""
                    start.setBackgroundResource(R.color.white)
                    delay(500L)
                    if (muestra == "Rojo") {
                        start.text = ""
                        start.setBackgroundResource(R.color.Rojo)
                    } else if (muestra == "Amarillo") {
                        start.text = ""
                        start.setBackgroundResource(R.color.Amarillo)
                    } else if (muestra == "Verde") {
                        start.text = ""
                        start.setBackgroundResource(R.color.Verde)
                    } else if (muestra == "Azul") {
                        start.text = ""
                        start.setBackgroundResource(R.color.Azul)
                    }
                }
                else{
                    start.setBackgroundResource(R.drawable.bordes_redondos)
                    println(miModelo.record.value)
                    seleccion()
                }
               delay(1000L)
            }
        }
    }


    fun seleccion(){
        var contapul = 0
        Log.d("salida","estoy en selección")
                    start.text="Eligiendo"
                    Log.d("salida","otro")
                    //Según el que presionemos se añadirá a la lista select
                    rojo.setOnClickListener {
                        select.add("Rojo")
                        if(secuencia[contapul]!="Rojo"){
                            restart()
                        }
                        contapul= contapul+1
                        if(contapul==secuencia.size)
                            miModelo.SubirRonda()
                            generarSecuencia()
                    }
                    amarillo.setOnClickListener {
                        select.add("Amarillo")
                        if(secuencia[contapul]!="Amarillo"){
                            restart()
                        }
                        contapul= contapul+1
                        if(contapul==secuencia.size)
                            miModelo.SubirRonda()
                            generarSecuencia()
                    }
                    verde.setOnClickListener {
                        select.add("Verde")
                        if(secuencia[contapul]!="Verde"){
                            restart()
                        }
                        contapul= contapul+1
                        if(contapul==secuencia.size)
                            miModelo.SubirRonda()
                            generarSecuencia()
                    }
                    azul.setOnClickListener {
                        select.add("Azul")
                        if(secuencia[contapul]!="Azul"){
                            restart()
                        }
                        contapul= contapul+1
                        if(contapul==secuencia.size)
                            miModelo.SubirRonda()
                            generarSecuencia()
                    }
    }

    fun restart() {
        Log.d("salida", "Se resetea")
        Log.d("MVVC", "Actualiza ronda")
        numero.text = miModelo.record.value.toString()
        textRec.text = "Record"




        GlobalScope.launch(Dispatchers.Main) {
            secuencia.clear()
            select.clear()
            lose.setText("HAS PERDIDO!!")
            delay(3000L)
            start.text = "Empezar!!"
            lose.setText("Presiona el botón central para \n comenzar de nuevo.")
            start.setBackgroundResource(R.drawable.bordes_redondos)
            start.setOnClickListener { generarSecuencia() }
        }

    }


}


