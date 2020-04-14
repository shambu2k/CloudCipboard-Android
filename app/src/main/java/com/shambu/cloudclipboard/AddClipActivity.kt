package com.shambu.cloudclipboard

import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import com.shambu.cloudclipboard.model.ClipboardData
import java.util.*

class AddClipActivity : AppCompatActivity() {

    private lateinit var clipEdt: EditText
    private lateinit var addBtn: Button
    private lateinit var delBtn: Button
    private lateinit var viewModel: AddClipViewModel
    private lateinit var clipboardManager: ClipboardManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_clip)
        clipEdt = findViewById(R.id.clip_edt)
        addBtn = findViewById(R.id.add_btn)
        delBtn = findViewById(R.id.del_btn)

        clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        viewModel = ViewModelProvider(this).get(AddClipViewModel::class.java)


        if(intent.getIntExtra("ID", -10) != -10){
            delBtn.visibility = View.VISIBLE
            val observer = Observer<ClipboardData> { data -> clipEdt.setText(data.clipboardText)}
            viewModel.getClipDataById(intent.getIntExtra("ID", -10)).observe(this, observer)
        }

        addBtn.setOnClickListener {addUpdateData()}

        delBtn.setOnClickListener {deleteData()}
    }

    private fun addUpdateData() {
        if(intent.getIntExtra("ID", -10)==-10){
            viewModel.insertClipData(ClipboardData(Date(), getTextFromClipboard()))
            finish()
        } else {
            viewModel.updateClipData(ClipboardData(Date(), getTextFromClipboard(), intent.getIntExtra("ID", -10)))
            finish()
        }
    }

    private fun deleteData() {
        viewModel.deleteClipData(ClipboardData(Date(), getTextFromClipboard(), intent.getIntExtra("ID", -10)))
        finish()
    }

    private fun getTextFromClipboard(): String {
        val abc = clipboardManager.primaryClip
        val item = abc?.getItemAt(0)
        return if(TextUtils.isEmpty(clipEdt.text.toString())){
            clipEdt.setText(item?.text.toString())
            item?.text.toString()
        } else {
            clipEdt.text.toString()
        }

    }
}
