package com.ziddan.eplgo.maps

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziddan.eplgo.core.ui.ClubAdapter
import com.ziddan.eplgo.detail.DetailClubActivity
import com.ziddan.eplgo.maps.databinding.ActivityFavoriteBinding
import com.ziddan.eplgo.maps.di.favoriteModule
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title = "Favorite"

        getFavoriteData()
    }

    private fun getFavoriteData() {
        val clubAdapter = ClubAdapter()
        clubAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailClubActivity::class.java)
            intent.putExtra(DetailClubActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.favoriteClub.observe(this, { dataClub ->
            clubAdapter.setData(dataClub)
            binding.viewEmpty.root.visibility =
                if (dataClub.isNotEmpty()) View.GONE else View.VISIBLE
        })

        with(binding.rvClub) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = clubAdapter
        }
    }
}