package com.example.simondice

import androidx.room.Database
import androidx.room.RoomDatabase


class DatosDB {

    @Database(entities = [DatosDec.Datos::class], version = 1)
    abstract class datosDB : RoomDatabase(){
        abstract fun datosDao() : DatosDec.RecordDao
    }

}