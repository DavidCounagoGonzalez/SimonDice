package com.example.simondice

import android.app.Application
import android.provider.Settings.Global
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.NullPointerException

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG_LOG: String = "mensaje ViewModel"

    // Este va a ser nuestra lista de la secuencia
    val record = MutableLiveData<Int>()
    private val context = getApplication<Application>().applicationContext
    private var room : DatosDB.datosDB? = null

    init {
        Log.d(TAG_LOG, "Inicializa livedata")
        room = Room.databaseBuilder(context, DatosDB.datosDB::class.java, "Datos").build()

    }

    fun inicio(){
        val inicio = GlobalScope.launch(Dispatchers.Main) {
            record.value = room!!.datosDao().getTopRecord()
            println("Base de Datos : " + room!!.datosDao().getTopRecord())
        }
        inicio.start()
    }

    fun recDis(){
        val coRu = GlobalScope.launch(Dispatchers.Main) {
            try {
                record.value = room!!.datosDao().getTopRecord()
                println("Base de Datos : " + room!!.datosDao().getTopRecord())
            }catch (ex : NullPointerException){
                room!!.datosDao().insertar()
                record.value = room!!.datosDao().getTopRecord()
            }
        }
        coRu.start()
    }

    fun actualizarBase (){
        val corrutina = GlobalScope.launch(Dispatchers.Main) {
            room!!.datosDao().update(DatosDec.Datos(1,record.value!!))
        }
        corrutina.start()
    }

    fun SubirRonda(){
        record.value = record.value?.plus(1)
    }

    fun ReiniciaRonda(){
        record.value= 0
    }

/*
    fun añadirRecord(ronda: Int ) {
        if(record.size==0){
            record.clear()
            record.add(ronda)
            // Actualizamos el livedata, de esta manera si hay un observador, este recibirá la nueva lista
            livedata_ronda.setValue(record)
            Log.d(TAG_LOG, "Añadimos Array al livedata:" + record.toString())
        }
        else{
            if(record[0]<ronda) {
                record.clear()
                record.add(ronda)
                // Actualizamos el livedata, de esta manera si hay un observador, este recibirá la nueva lista
                livedata_ronda.setValue(record)
            }
            // La mostramos en el logcat
            Log.d(TAG_LOG, "Añadimos Array al livedata:" + record.toString())
        }
    }*/


}

