package com.touchetime.presentation.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.touchetime.databinding.ActivityMainBinding
import com.touchetime.presentation.ui.fragments.home.HomeFragment

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        showHomeFragment()
    }

    private fun showHomeFragment() {
        supportFragmentManager.commit(true) {
            replace(
                viewBinding.container.id,
                HomeFragment.newInstance(),
                HomeFragment::class.java.name
            )
        }
    }

    fun navigateToFragment(
        fragment: Fragment,
        tag: String,
    ) {
        supportFragmentManager
            .beginTransaction()
            .replace(viewBinding.container.id, fragment, tag)
            .addToBackStack(null)
            .commit()
    }
}
