package com.example.flickrapp

import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.content.res.Configuration
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.flickrapp.databinding.ActivityMainBinding
import com.example.flickrapp.photos.ui.PhotoAdapter
import com.example.flickrapp.utils.GridItemDecoration
import com.example.flickrapp.utils.isLargeSizeScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private companion object {
        const val DEFAULT_COLUMN_COUNT = 1
        const val LARGE_SCREEN_COLUMN_COUNT = 3
    }

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private var adapter: PhotoAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.root.addView(object : View(this) {
            override fun onConfigurationChanged(newConfig: Configuration?) {
                super.onConfigurationChanged(newConfig)
                (binding.listView.layoutManager as GridLayoutManager).spanCount = getSpanCount()
            }
        })
        adapter = PhotoAdapter(::openLink)
        setupRecyclerView(getSpanCount())
        setupObservers()
    }

    override fun onDestroy() {
        binding?.listView?.adapter = null
        adapter = null
        super.onDestroy()
    }

    private fun setupRecyclerView(spanCount: Int) = with(binding.listView) {
        setHasFixedSize(true)
        layoutManager = GridLayoutManager(this@MainActivity, spanCount)
        adapter = this@MainActivity.adapter
        addItemDecoration(GridItemDecoration(resources.getDimensionPixelOffset(R.dimen.margin16), spanCount, true))
    }

    private fun setupObservers() {
        viewModel.state.onEach { state ->
            when (state) {
                MainState.Loading -> binding.progress.visibility = View.VISIBLE
                is MainState.Loaded -> {
                    adapter?.data = state.content
                    binding.progress.visibility = View.GONE
                    //TODO show empty message if needed
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun openLink(link: String) = runCatching {
        startActivity(Intent(ACTION_VIEW, Uri.parse(link)))
    }.onFailure {
        Toast.makeText(this, R.string.cannot_open_link, Toast.LENGTH_SHORT).show()
    }

    private fun getSpanCount() = if (isLargeSizeScreen()) LARGE_SCREEN_COLUMN_COUNT else DEFAULT_COLUMN_COUNT
}