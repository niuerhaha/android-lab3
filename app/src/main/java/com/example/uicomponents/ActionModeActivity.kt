package com.example.uicomponents

import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ActionModeActivity : AppCompatActivity() {

    private val data = mutableListOf("One", "Two", "Three", "Four", "Five")
    private lateinit var adapter: ArrayAdapter<String>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actionmode)

        listView = findViewById(R.id.listView_action)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_activated_1, data)
        listView.adapter = adapter
        listView.choiceMode = AbsListView.CHOICE_MODE_MULTIPLE_MODAL
        listView.setMultiChoiceModeListener(object : AbsListView.MultiChoiceModeListener {
            override fun onItemCheckedStateChanged(
                mode: ActionMode?,
                position: Int,
                id: Long,
                checked: Boolean
            ) {
                mode?.title = "${listView.checkedItemCount} selected"
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                menuInflater.inflate(R.menu.menu_actionmode, menu)
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = false

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                if (item?.itemId == R.id.action_delete) {
                    val checked = listView.checkedItemPositions
                    val toRemove = mutableListOf<Int>()
                    for (i in 0 until data.size) {
                        if (checked.get(i)) toRemove.add(i)
                    }
                    for (i in toRemove.asReversed()) {
                        data.removeAt(i)
                    }
                    adapter.notifyDataSetChanged()
                    Toast.makeText(
                        this@ActionModeActivity,
                        "Deleted ${toRemove.size} items",
                        Toast.LENGTH_SHORT
                    ).show()
                    mode?.finish()
                    return true
                }
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {}
        })
    }
}
