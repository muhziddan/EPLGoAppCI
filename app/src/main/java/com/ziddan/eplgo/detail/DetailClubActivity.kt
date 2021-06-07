package com.ziddan.eplgo.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.ziddan.eplgo.R
import com.ziddan.eplgo.core.domain.model.Club
import com.ziddan.eplgo.databinding.ActivityDetailClubBinding
import org.koin.android.viewmodel.ext.android.viewModel

class DetailClubActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    private val detailClubViewModel: DetailClubViewModel by viewModel()
    private lateinit var binding: ActivityDetailClubBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailClubBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val detailClub = intent.getParcelableExtra<Club>(EXTRA_DATA)
        showDetailClub(detailClub)
    }

    private fun showDetailClub(detailClub: Club?) {
        detailClub?.let {
            supportActionBar?.title = detailClub.strTeam
            binding.content.tvDetailDescription.text = detailClub.strDescriptionEN
            Glide.with(this@DetailClubActivity)
                .load(detailClub.strStadiumThumb)
                .into(binding.ivDetailImage)

            var statusFavorite = detailClub.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailClubViewModel.setFavoriteClub(detailClub, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_white
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_not_favorite_white
                )
            )
        }
    }
}
