package com.example.simondice

import androidx.room.*

class DatosDec {

    @Entity
    data class Datos(
        @PrimaryKey(autoGenerate = true)val id : Int,
        @ColumnInfo(name = "ronda")val ronda : Int,
        //@ColumnInfo(name = "fecha")val fecha : String?

    )

    @Dao
    interface RecordDao{
        @Query("SELECT ronda FROM datos")
        suspend fun getTopRecord() : Int

        @Query("INSERT INTO datos (ronda) VALUES (1,1)")
        suspend fun insertar()

        @Update
        suspend fun update(record:Datos)
    }
}