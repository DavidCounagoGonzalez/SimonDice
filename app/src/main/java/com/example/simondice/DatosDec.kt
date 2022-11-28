package com.example.simondice

import androidx.room.*

class DatosDec {

    @Entity
    data class Datos(
        @PrimaryKey(autoGenerate = true)val id : Int,
        @ColumnInfo(name = "ronda")val ronda : Int,
        @ColumnInfo(name = "fecha")val decha : String?

    )

    @Dao
    interface RecordDao{
        @Query("SELECT ronda FROM datos where ronda = (SELECT max(ronda) FROM Datos")
        fun getTopRecord() : Int

        @Insert
        fun insert(dato : Datos)
    }
}