package com.shambu.cloudclipboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shambu.cloudclipboard.model.ClipboardData
import com.shambu.cloudclipboard.utils.SharedPrefWrapper

class MainActivity : AppCompatActivity(), LongClickListener {

    private lateinit var fab: FloatingActionButton
    private lateinit var rv: RecyclerView
    private lateinit var serviceToggleBtn: Button
    private lateinit var adapter: ClipboardListAdapter
    private lateinit var viewModel: MainViewModel
    private lateinit var prefWrapper: SharedPrefWrapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab = findViewById(R.id.add_clip_fab)
        rv = findViewById(R.id.clipboardText_rv)
        serviceToggleBtn = findViewById(R.id.service_toggle_btn)

        prefWrapper = SharedPrefWrapper(application)

        if(prefWrapper.isRunning()) {
            serviceToggleBtn.text = getString(R.string.service_toggle_btn_text_stop)
        } else {
            serviceToggleBtn.text = getString(R.string.service_toggle_btn_text_start)
        }

        rv.layoutManager = LinearLayoutManager(this)
        adapter = ClipboardListAdapter(this)
        rv.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val observer: Observer<List<ClipboardData>> = Observer { data -> adapter.refreshList(data) }

        viewModel.getAllClipData().observe(this, observer)

        fab.setOnClickListener { startActivity(Intent(this, AddClipActivity::class.java)) }

        serviceToggleBtn.setOnClickListener { toggleForegroundService() }
    }

    override fun onLongClick(id: Int) {
       val intent = Intent(this, AddClipActivity::class.java)
       intent.putExtra("ID", id)
       startActivity(intent)
    }

    private fun toggleForegroundService() {
        if(serviceToggleBtn.text == "Start") {
            serviceToggleBtn.text = getString(R.string.service_toggle_btn_text_stop)
            startService(Intent(this, ClipboardService::class.java))
            prefWrapper.toggleServiceState(true)
        } else {
            serviceToggleBtn.text = getString(R.string.service_toggle_btn_text_start)
            stopService(Intent(this, ClipboardService::class.java))
            prefWrapper.toggleServiceState(false)
        }
    }
}
