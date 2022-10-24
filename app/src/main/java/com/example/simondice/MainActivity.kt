package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        start.setOnClickListener { generarSecuencia() }
    }

    var resultado : String = "Acierto"
    var secuencia = ArrayList<String>()
    var select = ArrayList<String>()
    val start :Button = findViewById(R.id.Comenzar)

    //Método random para la generación de secuencia
    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }


    fun generarSecuencia(){
        // var ronda : Int = secuencia.size
        val colores = ArrayList<String>()
        colores.addAll(listOf("Rojo", "Azul", "Verde", "Amarillo"))
        val random = Random()

        println(random.nextInt(0..4))
        secuencia.add(colores[random.nextInt(0..4)])
        println(secuencia)
        mostrar()
    }

    fun mostrar (){
        GlobalScope.launch(Dispatchers.Main) {
            for (i in 0..secuencia.size) {
                val muestra: String = secuencia[i]

                if (muestra == "Rojo") {
                    start.text = ""
                    start.setBackgroundColor(R.id.Rojo)
                } else if (muestra == "Amarillo") {
                    start.text = ""
                    start.setBackgroundColor(R.id.Amarillo)
                } else if (muestra == "Verde") {
                    start.text = ""
                    start.setBackgroundColor(R.id.Verde)
                } else if (muestra == "Azul") {
                    start.text = ""
                    start.setBackgroundColor(R.id.Azul)
                } else {
                    println("Esto no debería haber pasado")
                    break
                }
                delay(2000L)
            }
        }
        start.setBackgroundColor(R.id.Comenzar)
        seleccion()
    }

    //DEclaración de los Botones de colores
    val rojo : Button = findViewById(R.id.Rojo)
    val amarillo : Button = findViewById(R.id.Amarillo)
    val verde : Button = findViewById(R.id.Verde)
    val azul : Button = findViewById(R.id.Azul)

    fun seleccion(){
        //Mientras la lista de nuestra selección sea distinta a la de la secuencia seguiremos presionando botones
        while (select.size!=secuencia.size) {
            start.text="Eligiendo"
            //Habilitamos los botones para poder seleccionarlos
            rojo.isEnabled
            amarillo.isEnabled
            verde.isEnabled
            azul.isEnabled
            //Según el que presionemos se añadirá a la lista select
            rojo.setOnClickListener {
                select.add("Rojo")
            }
            amarillo.setOnClickListener {
                select.add("Amarillo")
            }
            verde.setOnClickListener {
                select.add("Verde")
            }
            azul.setOnClickListener {
                select.add("Azul")
            }
        }
        println(select)
        comprobar()
    }
    fun comprobar(){

    }

}