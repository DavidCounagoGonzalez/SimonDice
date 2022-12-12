package com.example.simondice

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.NullPointerException

class MyViewModel(application: Application) : AndroidViewModel(application) {

    private val TAG_LOG: String = "mensaje ViewModel"

    // Este va a ser nuestra lista de la secuencia
    val record = MutableLiveData<Int>()
    val livedata_ronda = MutableLiveData<Int>()
    private val context = getApplication<Application>().applicationContext
    private var room : DatosDB.datosDB? = null

    init {
        Log.d(TAG_LOG, "Inicializa livedata")
        livedata_ronda.value = 0
        room = Room.databaseBuilder(context, DatosDB.datosDB::class.java, "Datos").build()

        val Coroom = GlobalScope.launch(Dispatchers.Main) {
            try{
                record.value = room!!.datosDao().getTopRecord()
            }catch(ex : NullPointerException){
                room!!.datosDao().insertar()
                record.value = room!!.datosDao().getTopRecord()
            }
        }
        Coroom.start()
    }

    fun actulizar(){
        record.value = livedata_ronda.value
    }

    fun SubirRonda(){
        livedata_ronda.value = livedata_ronda.value?.plus(1)
    }

    fun ReiniciaRonda(){
        livedata_ronda.value= 0
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

