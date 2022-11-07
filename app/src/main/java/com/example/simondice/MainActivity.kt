package com.example.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    var secuencia = ArrayList<String>()
    var select = ArrayList<String>()
    // var ronda: Int = 0
    lateinit var lose: TextView
    lateinit var numero: TextView
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
        numero = findViewById(R.id.Numero)
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
        //ronda= secuencia.size+1
        //numero.setText(ronda.toString())
        val colores = ArrayList<String>()
        colores.addAll(listOf("Rojo", "Azul", "Verde", "Amarillo"))
        val random = Random()

        secuencia.add(colores[random.nextInt(0..4)])
        println(secuencia)
        mostrar()
    }

    fun mostrar (){
        //Por alguna razón no se muestran los fondos en el botón central
        var waitMuestra : Job? = null
        waitMuestra = GlobalScope.launch{
            for (i in 0..secuencia.size) {
                if(i<secuencia.size) {
                val muestra: String = secuencia[i]
                Log.d("datos",muestra)
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
                    seleccion()
                }
               delay(2000L)
            }
        }
    }


    fun seleccion(){
        Log.d("salida","estoy en selección")
        var espera : Job? = null
        espera = GlobalScope.launch {
            for (i in 0.. secuencia.size){
                if (i<secuencia.size) {
                    start.text="Eligiendo"
                    Log.d("salida","otro")
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
                    delay(5000)
                }
                else {
                    comprobar()
                }
            //Necesito encontrar una manera de pausar el loop mientras el usuario no haya presionado el boton
            }
        }
    }
    fun comprobar(){
        println(select)
        Log.d("salida", "estoy aquí¿?")
            for (j in 0..secuencia.size) {
                if(j<secuencia.size) {
                    if (secuencia[j] != select[j]) {
                        restart()
                        break;
                    }
                }
                else{
                    select.clear()
                    Log.d("fallo","lo hace")
                    generarSecuencia()
                }
            }
    }

    fun restart() {
        Log.d("salida", "Se resetea")
        GlobalScope.launch(Dispatchers.Main) {
            secuencia.clear()
            select.clear()
            lose.setText("HAS PERDIDO!!")
            delay(5000L)
            start.text = "Empezar!!"
            lose.setText("Presiona el botón central para \n comenzar de nuevo.")
            start.setBackgroundResource(R.drawable.bordes_redondos)
            start.setOnClickListener { generarSecuencia() }
        }

    }


}