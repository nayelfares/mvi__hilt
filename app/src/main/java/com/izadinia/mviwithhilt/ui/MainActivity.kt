package com.izadinia.mviwithhilt.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.izadinia.mviwithhilt.R
import com.izadinia.mviwithhilt.model.LoginResponse
import com.izadinia.mviwithhilt.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: OnbordingViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        subscribeObservers()
        viewModel.login("nayel.fares@gmail.com","0987")
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<LoginResponse> -> {
                    displayProgressBar(false)
                    Toast.makeText(this,dataState.data.data!![0].accessToken,Toast.LENGTH_SHORT).show()
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    Toast.makeText(this,dataState.exception.toString(),Toast.LENGTH_SHORT).show()
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }


    private fun displayProgressBar(shouldDisplay: Boolean) {
        progress_bar.visibility = if (shouldDisplay) View.VISIBLE else View.GONE
    }

}