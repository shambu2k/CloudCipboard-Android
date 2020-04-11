package com.shambu.cloudclipboard

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.shambu.cloudclipboard.model.ClipboardData
import com.shambu.cloudclipboard.utils.MainViewModel

class MainActivity : AppCompatActivity() {

    lateinit var fab: FloatingActionButton
    lateinit var rv: RecyclerView
    lateinit var adapter: ClipboardListAdapter
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fab = findViewById(R.id.add_clip_fab)
        rv = findViewById(R.id.clipboardText_rv)

        rv.layoutManager = LinearLayoutManager(this)
        adapter = ClipboardListAdapter()
        rv.adapter = adapter

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        var observer: Observer<List<ClipboardData>> = Observer { data -> adapter.refreshList(data) }

        viewModel.getAllClipData().observe(this, observer)



        fab.setOnClickListener { v -> startActivity(Intent(this, AddClipActivity::class.java))}
    }
}
