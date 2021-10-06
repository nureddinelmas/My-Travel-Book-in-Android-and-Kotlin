package com.nureddinelmas.kotlinmaps.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.nureddinelmas.kotlinmaps.R
import com.nureddinelmas.kotlinmaps.adapter.PlaceAdapter
import com.nureddinelmas.kotlinmaps.databinding.ActivityMainBinding
import com.nureddinelmas.kotlinmaps.model.Place
import com.nureddinelmas.kotlinmaps.roomdb.PlaceDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
   lateinit var button : Button
   private val compositeDisposable = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = Room.databaseBuilder(applicationContext, PlaceDatabase::class.java, "Places").build()
        val placeDao = db.placeDao()

        compositeDisposable.add(
            placeDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse)
        )
        button = findViewById<Button>(R.id.button)



        button.setOnClickListener {
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

    }

    private fun handleResponse(placeList : List<Place>){
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = PlaceAdapter(placeList)
        binding.recyclerView.adapter = adapter

    }
       override fun onCreateOptionsMenu(menu: Menu?): Boolean{
           val menuInflater = menuInflater
           menuInflater.inflate(R.menu.place_menu, menu)

            return super.onCreateOptionsMenu(menu)
        }

        override fun onOptionsItemSelected(item: MenuItem): Boolean{
            if (item.itemId == R.id.add_place){
                val intent = Intent(this, MapsActivity::class.java)
                intent.putExtra("info", "new")
                startActivity(intent)
            }
            return super.onOptionsItemSelected(item)
        }


    
}