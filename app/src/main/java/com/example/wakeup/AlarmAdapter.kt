package com.example.wakeup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.wakeup.databinding.AlarmListBinding

class AlarmAdapter(val items: List<Place>, private val clickListener: (alarm: Place) -> Unit ) : RecyclerView.Adapter<AlarmAdapter.AlarmViewHolder>() {
    class AlarmViewHolder(val binding: AlarmListBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlarmViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.alarm_list,parent,false)
        val viewHolder = AlarmViewHolder(AlarmListBinding.bind(view))
        view.setOnClickListener {
            clickListener.invoke(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: AlarmViewHolder, position: Int) {
        holder.binding.place = items[position]
    }

    override fun getItemCount() = items.size
}