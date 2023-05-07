package ru.startandroid.develop.actionmode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView.MultiChoiceModeListener
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    var actionMode: ActionMode? = null
    private var lvActionMode: ListView? = null
    var LOG_TAG = "myLogs"

    private val data = arrayOf("one", "two", "three", "four", "five")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(this,
        android.R.layout.simple_list_item_activated_1, data)
        lvActionMode = findViewById(R.id.lvActionMode)
        lvActionMode!!.adapter = adapter
        lvActionMode!!.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
        lvActionMode!!.setMultiChoiceModeListener(object : MultiChoiceModeListener {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                mode!!.menuInflater.inflate(R.menu.context, menu)
                return true
            }
            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem): Boolean {
                mode!!.finish()
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
            }

            override fun onItemCheckedStateChanged(
                mode: ActionMode?,
                position: Int,
                id: Long,
                checked: Boolean
            ) {
                Log.d(LOG_TAG, "position = $position, checked = $checked")
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
}