package com.sangmin.atopy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sangmin.atopy.databinding.ActivityLoginBinding
import com.sangmin.atopy.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_test)

        binding.category2Layout.setOnClickListener {
            val intent = Intent(this, CategoryListActivity::class.java)
            intent.putExtra("category","test1")
            startActivity(intent)
        }

        binding.category3Layout.setOnClickListener {
            val intent = Intent(this, CategoryListActivity::class.java)
            intent.putExtra("category","test2")
            startActivity(intent)
        }
        binding.category4Layout.setOnClickListener {
            val intent = Intent(this, CategoryListActivity::class.java)
            intent.putExtra("category","test3")
            startActivity(intent)
        }
    }


}