package com.shambu.cloudclipboard

import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.shambu.cloudclipboard.model.ClipboardData
import java.util.*

class AddClipActivity : AppCompatActivity() {

    lateinit var clipEdt: EditText
    lateinit var addBtn: Button
    lateinit var viewModel: AddClipViewModel
    lateinit var clipboardManager: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_clip)
        clipEdt = findViewById(R.id.clip_edt)
        addBtn = findViewById(R.id.add_btn)

        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        viewModel = ViewModelProvider(this).get(AddClipViewModel::class.java)


        addBtn.setOnClickListener { v -> addBtnTasks()}
    }

    fun addBtnTasks() {
        viewModel.insertClipData(ClipboardData(Date(), getTextFromClipboard()))
        finish();
    }
    fun getTextFromClipboard(): String {
        val abc = clipboardManager?.getPrimaryClip()
        val item = abc?.getItemAt(0)
        clipEdt.setText(item?.text.toString())
        return item?.text.toString()
    }
}
