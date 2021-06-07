package com.ziddan.eplgo.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ziddan.eplgo.R
import com.ziddan.eplgo.core.data.Resource
import com.ziddan.eplgo.core.ui.ClubAdapter
import com.ziddan.eplgo.databinding.FragmentHomeBinding
import com.ziddan.eplgo.detail.DetailClubActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val clubAdapter = ClubAdapter()
            clubAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailClubActivity::class.java)
                intent.putExtra(DetailClubActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            homeViewModel.club.observe(viewLifecycleOwner, { club ->
                if (club != null) {
                    when (club) {
                        is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            clubAdapter.setData(club.data)
                        }
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            binding.viewError.root.visibility = View.VISIBLE
                            binding.viewError.tvError.text =
                                club.message ?: getString(R.string.something_wrong)
                        }
                    }
                }
            })

            with(binding.rvClub) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = clubAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}