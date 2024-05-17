package com.example.soundproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.soundproject.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var albumRecycleView: RecyclerView
    private lateinit var albumArrayList: ArrayList<Albom>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = Firebase.database.reference




        installSplashScreen()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        albumArrayList = arrayListOf<Albom>()
        albumRecycleView = binding.rcView
        albumRecycleView.layoutManager = LinearLayoutManager(this)
        albumRecycleView.setHasFixedSize(true)
        albumArrayList = arrayListOf<Albom>()
        getAlbumData()


        binding.apply {
            navMenu.setNavigationItemSelectedListener {
                when(it.itemId) {
                    R.id.createAlboom -> {
                        // Создать альбом
                        drawer.closeDrawer(GravityCompat.START)
                        albomText.visibility = View.VISIBLE
                        albomInput.visibility = View.VISIBLE
                        firstTrack.visibility = View.VISIBLE
                        secondTrack.visibility = View.VISIBLE
                        thirdTrack.visibility = View.VISIBLE
                        fourthTrack.visibility = View.VISIBLE
                        fithTrack.visibility = View.VISIBLE
                        btnCreateAlbom.visibility = View.VISIBLE
                        musicianInput.visibility = View.VISIBLE

                        rcView.visibility = View.GONE
                        catalogText.visibility = View.GONE
                    }
                    R.id.home -> {
                        drawer.closeDrawer(GravityCompat.START)
                        // Главное меню
                        albomText.visibility = View.GONE
                        albomInput.visibility = View.GONE
                        firstTrack.visibility = View.GONE
                        secondTrack.visibility = View.GONE
                        thirdTrack.visibility = View.GONE
                        fourthTrack.visibility = View.GONE
                        fithTrack.visibility = View.GONE
                        btnCreateAlbom.visibility = View.GONE

                        rcView.visibility = View.VISIBLE
                        catalogText.visibility = View.VISIBLE


                    }
                }
                true
            }
            btnCreateAlbom.setOnClickListener {
                val album = albomInput.text.toString()
                val musician = musicianInput.text.toString()
                val firstTrack = firstTrack.text.toString()
                val secondTrack = secondTrack.text.toString()
                val thirdTrack = thirdTrack.text.toString()
                val fourthTrack = fourthTrack.text.toString()
                val fithTrack = fithTrack.text.toString()

                createAlboom(album, musician, firstTrack, secondTrack, thirdTrack, fourthTrack, fithTrack)
            }

            btnOpen.setOnClickListener {
                drawer.openDrawer(GravityCompat.START)
            }
        }
    }

    private fun createAlboom(alboom: String, msucian: String, firstTrack: String, secondTrack: String,
                             thirdTrack: String, fourthTrack: String, fithTrack: String) {
        val album = Albom(alboom, msucian, firstTrack, secondTrack, thirdTrack, fourthTrack, fithTrack)

        database.child("Albums").child(alboom).setValue(album)
        Toast.makeText(this, "Альбом успешно создан!", Toast.LENGTH_SHORT).show()
    }

    private fun getAlbumData() {
        database = FirebaseDatabase.getInstance().getReference("Albums")

        database.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){

                    for (albumSnapshot in snapshot.children) {

                        val album = albumSnapshot.getValue(Albom::class.java)
                        albumArrayList.add(album!!)

                    }

                    albumRecycleView.adapter = AlbumAdapter(albumArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}