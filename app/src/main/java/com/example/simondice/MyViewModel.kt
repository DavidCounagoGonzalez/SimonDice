package com.example.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG_LOG: String = "mensaje ViewModel"

    // Este va a ser nuestra lista de la secuencia
    val record = MutableLiveData<Int>()
    val livedata_ronda = MutableLiveData<Int>()
    private val context = getApplication<Application>().applicationContext

    init {
        Log.d(TAG_LOG, "Inicializa livedata")
        livedata_ronda.value = 0
        val db = Room.databaseBuilder(context, DatosDB.datosDB::class.java, "Datos").build()

        val datos = db.datosDao()
        val topRecord = datos.getTopRecord()
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

