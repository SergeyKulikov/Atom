package com.great_systems.atom.ui.charge

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.great_systems.atom.R
import com.great_systems.atom.entity.Charger
import java.util.*

class ChargerAdapter(private val colors: List<Int>, private val onClick: (city: String) -> Unit) : RecyclerView.Adapter<ChargerAdapter.ViewHolder>() {
    private var list = listOf<Charger>()

    class ViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {

        val root: View = view
        val name: TextView = root.findViewById(R.id.name)
        val addr: TextView = root.findViewById(R.id.addr)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.charger_layout, parent, false)


        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(v: List<Charger>) {
        this.list = v.sortedBy { it.name }
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged", "UseCompatLoadingForDrawables")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.name.text = item.name
        holder.addr.text = item.address
        if (item.busy) {
            holder.itemView.setBackgroundColor(colors[0])
        } else {
            holder.itemView.setBackgroundColor(colors[1])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}