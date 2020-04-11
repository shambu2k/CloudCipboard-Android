package com.shambu.cloudclipboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shambu.cloudclipboard.model.ClipboardData

class ClipboardListAdapter: RecyclerView.Adapter<ClipboardListAdapter.ClipboardViewHolder>() {

    private var adapterList: List<ClipboardData>? = null



    class ClipboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date_tv: TextView
        var clip_tv: TextView

        init {
            date_tv = itemView.findViewById(R.id.time_tv)
            clip_tv = itemView.findViewById(R.id.clipboardText_tv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipboardViewHolder {
        return ClipboardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.clipboardtext_rv_item, parent, false))
    }

    override fun getItemCount(): Int {
        return if(adapterList!=null) adapterList!!.size else 0
    }

    override fun onBindViewHolder(holder: ClipboardViewHolder, position: Int) {
        var data: ClipboardData = adapterList!!.get(position)
        holder.date_tv.setText(data.getDate)
        holder.clip_tv.setText(data.clipboardText)
    }

    fun refreshList(list: List<ClipboardData>) {
        this.adapterList = list
        notifyDataSetChanged()
    }
}