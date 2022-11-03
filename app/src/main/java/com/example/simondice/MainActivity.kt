package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var secuencia = ArrayList<String>()
    var select = ArrayList<String>()
    lateinit var lose: TextView
    lateinit var start :Button
    //Declaración de los Botones de colores
    lateinit var rojo : Button
    lateinit var amarillo : Button
    lateinit var verde : Button
    lateinit var azul : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lose= findViewById(R.id.Derrrota)
        start= findViewById(R.id.Comenzar)
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
        //Por alguna razón no se muestran los fondos en el botón central
        println(secuencia.size)
       // GlobalScope.launch(Dispatchers.Main) {
            for (i in 0..secuencia.size) {
                if(i<secuencia.size) {
                val muestra: String = secuencia[i]
                println(muestra)
                println("Llegué")
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
                    }
                }
                else{
                    start.setBackgroundColor(R.id.Comenzar)
                    seleccion()
                }
               // delay(2000L)
            }
        //}
    }


    fun seleccion(){
        println("si¿?")
        //Mientras la lista de nuestra selección sea distinta a la de la secuencia seguiremos presionando botones
        for (i in 0.. secuencia.size){
        if (i<secuencia.size) {
            start.text="Eligiendo"
            //Según el que presionemos se añadirá a la lista select
            rojo.setOnClickListener {
                select.add("Rojo")
                println(select)
            }
            amarillo.setOnClickListener {
                select.add("Amarillo")
                println(select)
            }
            verde.setOnClickListener {
                select.add("Verde")
                println(select)
            }
            azul.setOnClickListener {
                select.add("Azul")
                println(select)
            }
        }
        else {
            println(select)
            comprobar()
        }
            //Necesito encontrar una manera de pausar el loop mientras el usuario no haya presionado el boton
    }
    }
    fun comprobar(){
        println("estoy aquí¿?")
            for (i in 0..select.size) {
                    if (secuencia[i]!=select[i]) {
                        restart()
                    }
            }
        generarSecuencia()
    }

    fun restart() {
        GlobalScope.launch(Dispatchers.Main) {
            secuencia.clear()
            select.clear()
            lose.setText("HAS PERDIDO!!")
            delay(5000L)
            start.text = "Empezar!!"
            lose.setText("Presiona el botón central para \n comenzar de nuevo.")
        }
    }


}