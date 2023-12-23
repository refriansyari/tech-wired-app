package com.refri.techwired.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.refri.techwired.R
import com.refri.techwired.databinding.ActivityMainBinding
import com.refri.techwired.ui.about.AboutFragment
import com.refri.techwired.ui.newslist.NewsListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        changeStatusBarColor("#0C59B3")
        bindViews()

        val newsFragment = NewsListFragment()
        val aboutFragment = AboutFragment()

        setCurrentFragment(newsFragment)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.mAbout -> setCurrentFragment(aboutFragment)
                R.id.mList -> setCurrentFragment(newsFragment)
            }
            true
        }


    }

    private fun setCurrentFragment(fragment : Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.flFragment,fragment)
            commit()
        }

    private fun bindViews(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    private fun changeStatusBarColor(hexColor: String) {
        // Color must be in hexadecimal format
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.parseColor(hexColor)
    }
}