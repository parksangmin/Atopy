package com.sangmin.atopy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sangmin.atopy.adapters.CategoryRVAdapter
import com.sangmin.atopy.data.Model

class CategoryListActivity : AppCompatActivity() {

    lateinit var rvAdapter: CategoryRVAdapter
    val mList = ArrayList<Model>()
    val database = Firebase.database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category1_list)

        val rv = findViewById<RecyclerView>(R.id.rv)
        val category = intent.getStringExtra("category")

        rvAdapter = CategoryRVAdapter(this, mList)
        rv.adapter = rvAdapter
        rv.layoutManager = LinearLayoutManager(this)

        database.getReference("atopy2").child(category.toString())
            .addValueEventListener(object : ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                    for (child in snapshot.children) {
                        mList.add(
                            Model(
                                child.child("title").value.toString(),
                                child.child("imageUrl").value.toString(),
                                child.child("webUrl").value.toString(),
                                )
                        )
                    }
                    rvAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {

                }
            })

    }
}



//









