package com.shambu.cloudclipboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shambu.cloudclipboard.model.ClipboardData

class ClipboardListAdapter(listener: LongClickListener): RecyclerView.Adapter<ClipboardListAdapter.ClipboardViewHolder>() {

    private var adapterList: List<ClipboardData>? = null
    private var listener: LongClickListener = listener


    class ClipboardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var dateTv: TextView = itemView.findViewById(R.id.time_tv)
        private var clipTv: TextView = itemView.findViewById(R.id.clipboardText_tv)

        fun bind(data: ClipboardData, listener: LongClickListener) {
            dateTv.text = data.getDate
            clipTv.text = data.clipboardText
            itemView.setOnLongClickListener {
                listener.onLongClick(data.dataId)
                false
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClipboardViewHolder =
        ClipboardViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.clipboardtext_rv_item, parent, false))

    override fun getItemCount(): Int =
        if(adapterList!=null) adapterList!!.size else 0


    override fun onBindViewHolder(holder: ClipboardViewHolder, position: Int) {
        holder.bind(adapterList!![position], listener)
    }

    fun refreshList(list: List<ClipboardData>) {
        this.adapterList = list
        notifyDataSetChanged()
    }
}