package com.example.soundproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AlbumAdapter(private val albumList: ArrayList<Albom>) : RecyclerView.Adapter<AlbumAdapter.AlbumHolder>() {

    class AlbumHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val albumName: TextView = itemView.findViewById(R.id.nazvanieAlboma)
        val albumImage: ImageView = itemView.findViewById(R.id.imageAlbum)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.album_item,
            parent, false)
        return AlbumHolder(itemView)
    }

    override fun getItemCount(): Int {

        return albumList.size
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {

        val currentItem = albumList[position]

        holder.albumName.text = currentItem.nazvanie
    }
}