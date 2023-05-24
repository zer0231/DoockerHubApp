package com.zero.doockerhubapp.fragments.dashboardFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zero.doockerhubapp.databinding.FragmentDashboardBinding
import com.zero.doockerhubapp.fragments.dashboardFragment.viewModel.DashboardViewModel
import com.zero.doockerhubapp.fragments.loginFragment.viewModel.LoginViewModel
import com.zero.doockerhubapp.utils.NetworkResult
import com.zero.doockerhubapp.utils.PersonalTokenScope
import com.zero.doockerhubapp.utils.sharedPreferenceManager.SharedPreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashBoardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    private val TAG = "DashBoardFragment"
    private val dashboardViewModel: DashboardViewModel by viewModels()

    @Inject
    lateinit var userSharedPreference: SharedPreferenceUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        getSummary()
        binding.logoutBtn.setOnClickListener{
            userSharedPreference.clearUserData()
            findNavController().navigateUp()
        }
        if (userSharedPreference.getPersonalToken().equals("*")) {
            dashboardViewModel.fetchPersonalToken("personalToken", PersonalTokenScope.ADMIN)
            dashboardViewModel.personalTokenResponse.observe(viewLifecycleOwner) { result ->
                when (result) {
                    is NetworkResult.Loading -> {
                        Log.d(TAG, "Loading")
                        binding.test.text = "Loading"
                    }

                    is NetworkResult.Success -> {
                        Log.d(TAG, result.data.toString())
                        binding.test.text = result.data.toString()
                        userSharedPreference.setPersonalToken(result.data?.get("token").toString())
                        userSharedPreference.setUUID(result.data?.get("uuid").toString())
                    }

                    is NetworkResult.Error -> {
                        Log.d(TAG, result.message.toString())

                        binding.test.text = "${result.message} ${result.code}"
                    }
                }
            }
        }
        return binding.root
    }

    private fun getSummary() {

        dashboardViewModel.fetchRepositorySummary()
        dashboardViewModel.imageSummary.observe(viewLifecycleOwner) { result ->
            when (result) {
                is NetworkResult.Success -> {
                    Log.d(TAG, result.data.toString())
                    binding.test.text = "${result.data?.results?.get(0)?.name } \n${result.data?.results?.get(1)?.name }"
                }

                is NetworkResult.Error -> {
                    Log.d(TAG, result.message.toString())
                }

                is NetworkResult.Loading -> {
                    Log.d(TAG, "Loading")
                    binding.test.text = "Loading"
                }
            }


        }
    }
}