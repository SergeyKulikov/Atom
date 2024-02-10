package com.great_systems.atom.ui.city

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.great_systems.atom.R
import java.util.*

class CityAdapter(private val onClick: (city: String) -> Unit) :
    RecyclerView.Adapter<CityAdapter.ViewHolder>() {
    private var list = listOf<String>()
    private var selected: String? = null

    class ViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {

        val root: View = view
        val name: TextView = root.findViewById(R.id.city_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_layout, parent, false)

        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(v: List<String>) {
        this.list = v
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        if (selected == null) {
            selected = item
            onClick.invoke(item)
        }

        holder.name.text = item
        holder.name.setOnClickListener {
            selected = item
            notifyDataSetChanged()
            onClick.invoke(item)
        }

        if (item == selected) {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(holder.root.context, R.color.selected_color)
            )
        } else {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(holder.root.context, android.R.color.white)
            )
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}