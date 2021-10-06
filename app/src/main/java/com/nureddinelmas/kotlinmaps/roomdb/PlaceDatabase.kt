package com.nureddinelmas.kotlinmaps.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nureddinelmas.kotlinmaps.model.Place

@Database(entities = [Place::class], version = 1)
abstract class PlaceDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
}