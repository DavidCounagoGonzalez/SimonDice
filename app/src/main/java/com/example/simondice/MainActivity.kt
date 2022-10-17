package com.example.simondice

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var resultado : String = "Acierto"
    var secuencia = ArrayList<String>()
    var select = ArrayList<String>()
    val start :Button = findViewById(R.id.Comenzar)

    fun Comenzar(){
        GenerarSecuencia()
        if(resultado=="Acierto"){
            GenerarSecuencia()
        }
        else{
         println("Has perdido, la secuencia era " + secuencia + " y tu selección fallaste en " + select + " \n Llegaste a la ronda: " + secuencia.size)
        }
    }

    fun Random.nextInt(range: IntRange): Int {
        return range.start + nextInt(range.last - range.start)
    }

    val rojo : Button = findViewById(R.id.Rojo)
    val amarillo : Button = findViewById(R.id.Amarillo)
    val verde : Button = findViewById(R.id.Verde)
    val azul : Button = findViewById(R.id.Azul)

    fun Seleccion(){
        rojo.setOnClickListener{
            select.add("Rojo")
        }
        amarillo.setOnClickListener {
            select.add("Amarillo")
        }
        verde.setOnClickListener{
            select.add("Verde")
        }
        azul.setOnClickListener{
            select.add("Azul")
        }
    }

    fun GenerarSecuencia(){
        var ronda : Int = secuencia.size
        var colores = ArrayList<String>()
        colores.addAll(listOf("Rojo", "Azul", "Verde", "Amarillo"))
        var random = Random()

        println(random.nextInt(0..4))
        secuencia.add(colores[random.nextInt(0..4)])
        println(secuencia)

    }

    fun Mostrar (){
        for (i in 0..secuencia.size){
            var muestra : String = secuencia[i]
            try {
                Thread.sleep(2*1000)
            }
            catch (e : Exception){
                println(e)
            }
            if (muestra=="Rojo"){
                start.setBackgroundColor(R.id.Rojo)
            }
            else if (muestra=="Amarillo"){
                start.setBackgroundColor(R.id.Amarillo)
            }
            else if (muestra=="Verde"){
                start.setBackgroundColor(R.id.Verde)
            }
            else if (muestra=="Azul"){
                start.setBackgroundColor(R.id.Azul)
            }
            else{
                println("Esto no debería haber pasado")
                break
            }
        }
        start.setBackgroundColor(R.id.Comenzar)
    }

    //En la función de selección del usuario habilitar la pulsación de los colores

}