package com.sangmin.firstaid.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sangmin.atopy.CategoryListActivity
import com.sangmin.atopy.R
import com.sangmin.atopy.TestActivity
import com.sangmin.atopy.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return  binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.category1Layout.setOnClickListener {
            val intent = Intent(context, CategoryListActivity::class.java)
            intent.putExtra("category","category1")
            startActivity(intent)
        }


        binding.category2Layout.setOnClickListener {
            val intent = Intent(context, TestActivity::class.java)
            startActivity(intent)
        }

    }
}