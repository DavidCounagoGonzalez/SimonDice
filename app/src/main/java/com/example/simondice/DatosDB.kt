package com.example.simondice

import androidx.room.*


class DatosDB {

    @Database(entities = [DatosDec.Datos::class], version = 1)
    abstract class datosDB : RoomDatabase(){
        abstract fun datosDao() : DatosDec.RecordDao
    }

}