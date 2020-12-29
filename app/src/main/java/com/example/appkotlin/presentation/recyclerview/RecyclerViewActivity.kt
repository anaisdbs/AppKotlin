package com.example.appkotlin.presentation.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.example.appkotlin.R
import com.example.appkotlin.data.remote.RecyclerList
import kotlinx.android.synthetic.main.activity_recycler_view.*


class RecyclerViewActivity : AppCompatActivity() {

    lateinit var  recyclerViewAdapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)

        initRecyclerView()
        createData()

    }

    private fun initRecyclerView() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
            recyclerViewAdapter = RecyclerViewAdapter()
            adapter = recyclerViewAdapter

            val decoration = DividerItemDecoration(applicationContext, VERTICAL)
            addItemDecoration(decoration)
        }
    }

    fun createData(){

        var viewModel = ViewModelProvider(this).get(RecyclerActivityViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<RecyclerList>{
            if(it != null){
                recyclerViewAdapter.setListData(it.items)
                recyclerViewAdapter.notifyDataSetChanged()

            }else{
                Toast.makeText(this@RecyclerViewActivity, "Error with api, don't find data", Toast.LENGTH_LONG).show()
            }
        })

        search_button.setOnClickListener{
            viewModel.makeApiCall(search_edit.text.toString())
        }

    }
}