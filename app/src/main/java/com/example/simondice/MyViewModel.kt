package com.example.simondice

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MyViewModel() : ViewModel() {

    private val TAG_LOG: String = "mensaje ViewModel"

    // Este va a ser nuestra lista de la secuencia
    val record = mutableListOf<Int>()
    val livedata_ronda = MutableLiveData<MutableList<Int>>()

    init {
        Log.d(TAG_LOG, "Inicializa livedata")
        livedata_ronda.value = record
    }


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
    }


}