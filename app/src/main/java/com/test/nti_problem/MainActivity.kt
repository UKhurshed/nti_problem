package com.test.nti_problem

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.test.nti_problem.databinding.ActivityMainBinding
import com.test.nti_problem.ui.MainViewModel
import com.test.nti_problem.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private var binding: ActivityMainBinding? = null
    private val mainBinding get() = binding!!
    private val mainTag = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        //After click button (GET IP) we send request to server and show result in UI
        mainBinding.getIp.setOnClickListener {
            mainViewModel.result.observe(this, { result ->
                when (result.status) {
                    Status.ERROR -> {
                        Log.d(mainTag, "Status Error: ${result.message}")
                        mainBinding.progressBar.isVisible = false
                        mainBinding.verticalLinear.isVisible = false
                        Snackbar.make(
                            View(this@MainActivity),
                            "Something was wrong",
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                    Status.LOADING -> {
                        Log.d(mainTag, "Status Loading")
                        mainBinding.progressBar.isVisible = true
                        mainBinding.verticalLinear.isVisible = false
                    }
                    Status.SUCCESS -> {
                        Log.d(mainTag, "Status Success")
                        mainBinding.progressBar.isVisible = false
                        mainBinding.verticalLinear.isVisible = true
                        mainBinding.ipText.text = result.data?.ip ?: "none"
                    }
                }
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}