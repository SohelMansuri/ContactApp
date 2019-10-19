package com.sohel.mansuri.contactapp

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import com.sohel.mansuri.contactapp.viewmodels.ListActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*

class ListActivity : AppCompatActivity() {

    private lateinit var listActivityViewModel: ListActivityViewModel
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listActivityViewModel = ViewModelProviders.of(this@ListActivity).get(ListActivityViewModel::class.java)
        listActivityViewModel.init()
        listActivityViewModel.getListOfContactsInStringFormat().observe(this, Observer<List<String>> {
            adapter.notifyDataSetChanged()
        })

        initListView()
    }

    private fun initListView() {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listActivityViewModel.getListOfContactsInStringFormat().value ?: listOf<String>())
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            startAddEditContactActivity(listActivityViewModel.getContactIDFromName(adapter.getItem(position)))
        }
    }

    private fun startAddEditContactActivity(idOfContactToEdit: Int? = null) {
        val intent = Intent(this, AddEditContactActivity::class.java)
        intent.putExtra("CONTACT_ID", idOfContactToEdit ?: -1)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_add -> startAddEditContactActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listActivityViewModel.getListOfContactsInStringFormat().value ?: listOf<String>())
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
