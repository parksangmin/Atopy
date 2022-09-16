package com.sangmin.atopy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.sangmin.atopy.adapters.CategoryRVAdapter
import com.sangmin.atopy.data.Model

class CategoryListActivity : AppCompatActivity() {


    lateinit var myRef : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category1_list)

        val items = ArrayList<Model>()
        val rvAdapter = CategoryRVAdapter(baseContext, items)

        val database = Firebase.database


        val category = intent.getStringExtra("category")


        if (category == "category1") {

             myRef = database.getReference("atopy")


        }else if(category == "category2") {

             myRef = database.getReference("atopy2")



        }

        //        Firebase Realtime Database 데이터 읽기
        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (dataModel in dataSnapshot.children) {
                    Log.d("Category1ListActivity", dataModel.toString())
                    val item = dataModel.getValue(Model::class.java)
                    items.add(item!!)
                }
                rvAdapter.notifyDataSetChanged()
                Log.d("Category1ListActivity", items.toString())


            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("Category1ListActivity", "loadPost:onCancelled", databaseError.toException())
            }
        }
        myRef.addValueEventListener(postListener)















//        val myRef2 = database.getReference("atopy2")
//        myRef2.push()
//            .setValue(Model("기도폐쇄", "https://www.gyeongnam.go.kr/01_potal/images/welfare/pic2f.gif", "https://blog.naver.com/dhwkffks7/222869936745"))
//        myRef2.push()
//            .setValue(Model("중독", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/58/GHS-pictogram-skull.svg/250px-GHS-pictogram-skull.svg.png","https://blog.naver.com/dhwkffks7/222869940230"))
//        myRef2.push()
//            .setValue(Model("심장마비", "https://www.gyeongnam.go.kr/01_potal/images/welfare/picd3.gif","https://blog.naver.com/dhwkffks7/222873265243"))



        val rv: RecyclerView = findViewById(R.id.rv)


//        items.add(Model("title1","https://w7.pngwing.com/pngs/738/161/png-transparent-circle-area-symbol-number-font-1-text-black-number.png","https://blog.naver.com/dhwkffks7/222875602164"))
//        items.add(Model("title2","https://w7.pngwing.com/pngs/738/161/png-transparent-circle-area-symbol-number-font-1-text-black-number.png","https://blog.naver.com/dhwkffks7/222875602164"))
//        items.add(Model("title3","https://w7.pngwing.com/pngs/738/161/png-transparent-circle-area-symbol-number-font-1-text-black-number.png","https://blog.naver.com/dhwkffks7/222875602164"))


        rv.adapter = rvAdapter


        rv.layoutManager = LinearLayoutManager(this)

        rvAdapter.itemClick =
            object : CategoryRVAdapter.ItemClick {
                override fun onClick(view: View, position: Int) {
                    Toast.makeText(baseContext, items[position].title, Toast.LENGTH_SHORT).show()

                    val intent = Intent(this@CategoryListActivity, CategoryShowActivity::class.java)
                    intent.putExtra("url", items[position].webUrl)
                    startActivity(intent)
                }
            }
    }
}



//










