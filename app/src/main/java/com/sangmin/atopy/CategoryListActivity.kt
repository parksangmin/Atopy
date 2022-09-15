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


    lateinit var myRef2 : DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.category1_list)

        val items = ArrayList<Model>()
        val rvAdapter = CategoryRVAdapter(baseContext, items)
        val category = intent.getStringExtra("category")

        val database = Firebase.database





        if (category == "category2") {

            myRef2 = database.getReference("atopy2").child("test1")


        } else if (category == "category3") {


            myRef2 = database.getReference("atopy2").child("test2")


        } else if(category == "category4")

            myRef2 = database.getReference("atopy2").child("test3")




        //        Firebase Realtime Database 데이터 읽기
        val postListener2 = object : ValueEventListener {
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
        myRef2.addValueEventListener(postListener2)











//        val myRef2 = database.getReference("atopy2")
//        myRef2.child("test1").push().setValue(
//            Model("title4","https://w7.pngwing.com/pngs/738/161/png-transparent-circle-area-symbol-number-font-1-text-black-number.png","https://blog.naver.com/dhwkffks7/222875602164")
//
//        )
//        myRef2.child("test2").push().setValue(
//            Model("title5","https://w7.pngwing.com/pngs/738/161/png-transparent-circle-area-symbol-number-font-1-text-black-number.png","https://blog.naver.com/dhwkffks7/222875600065")
//
//        )
//        myRef2.child("test3").push().setValue(
//            Model("title6","https://w7.pngwing.com/pngs/738/161/png-transparent-circle-area-symbol-number-font-1-text-black-number.png","https://blog.naver.com/dhwkffks7/222875602164")
//
//        )


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



////
//
//val myRef = database.getReference("atopy")
//
//
////        Firebase Realtime Database 데이터 읽기
//val postListener1 = object : ValueEventListener {
//    override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//        for (dataModel in dataSnapshot.children) {
//            Log.d("Category1ListActivity", dataModel.toString())
//            val item = dataModel.getValue(Model::class.java)
//            items.add(item!!)
//        }
//        rvAdapter.notifyDataSetChanged()
//        Log.d("Category1ListActivity", items.toString())
//
//
//    }
//
//
//    override fun onCancelled(databaseError: DatabaseError) {
//        // Getting Post failed, log a message
//        Log.w("Category1ListActivity", "loadPost:onCancelled", databaseError.toException())
//    }
//}
//myRef.addValueEventListener(postListener1)








