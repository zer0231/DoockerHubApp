package com.zero.doockerhubapp.fragments.dashboardFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.zero.doockerhubapp.databinding.FragmentDashboardBinding
import com.zero.doockerhubapp.fragments.dashboardFragment.adapters.RepositoriesAdapter
import com.zero.doockerhubapp.fragments.dashboardFragment.model.Results
import com.zero.doockerhubapp.fragments.dashboardFragment.viewModel.DashboardViewModel
import com.zero.doockerhubapp.utils.LoadingHandler
import com.zero.doockerhubapp.utils.NetworkResult
import com.zero.doockerhubapp.utils.sharedPreferenceManager.SharedPreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashBoardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val TAG = "DashBoardFragment"
    private val dashboardViewModel: DashboardViewModel by viewModels()
    private var repoItemList: ArrayList<Results> = ArrayList()
    private lateinit var loading: LoadingHandler

    @Inject
    lateinit var userSharedPreference: SharedPreferenceUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        loading = LoadingHandler(requireContext(), binding.constraintParent)
        getImageSummary()
        return binding.root
    }

    private fun getImageSummary() {
        dashboardViewModel.fetchRepositorySummary()
        dashboardViewModel.imageSummary.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    loading.stopAnimation()
                    repoItemList = result.data?.results ?: arrayListOf()
                    val adapter = RepositoriesAdapter(repoItemList, requireContext())
                    binding.repoListRv.layoutManager = LinearLayoutManager(requireContext())
                    binding.repoListRv.adapter = adapter
                }

                is NetworkResult.Error -> {
                    loading.stopAnimation()
                    Log.d(TAG, result.message.toString())
                }

                is NetworkResult.Loading -> {
                    loading.startAnimation()

                }
            }


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}