package com.touchetime.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.touchetime.databinding.ActivityMainBinding
import com.touchetime.presentation.ui.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        showHomeFragment()
    }

    private fun showFragment(fragment: Fragment, tag: String) {
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.container.id, fragment, tag)
            .commit()
    }

    private fun showHomeFragment() {
        showFragment(HomeFragment.netInstance(), HomeFragment::class.java.name)
    }
}
