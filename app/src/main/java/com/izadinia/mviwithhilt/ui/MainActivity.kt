package com.izadinia.mviwithhilt.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.izadinia.mviwithhilt.R
import com.izadinia.mviwithhilt.model.Blog
import com.izadinia.mviwithhilt.util.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        subscribeObservers()

        viewModel.setStateEvent(MainStateEvent.GetBlogEvent)
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(this, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<Blog>> -> {
                    displayProgressBar(false)
                    printBlogTitles(dataState.data)
                }
                is DataState.Error -> {
                    displayProgressBar(false)
                    showError(dataState.exception.message)
                }
                is DataState.Loading -> {
                    displayProgressBar(true)
                }
            }
        })
    }

    private fun showError(message: String?) {
        text.text = message ?: "Unknown Error"
    }

    private fun displayProgressBar(shouldDisplay: Boolean) {
        progress_bar.visibility = if (shouldDisplay) View.VISIBLE else View.GONE
    }

    private fun printBlogTitles(blogs: List<Blog>) {
        val sb = StringBuilder()
        for (blog in blogs) {
            sb.append(blog.title).append("\n")
        }
        text.text = sb.toString()

    }
}