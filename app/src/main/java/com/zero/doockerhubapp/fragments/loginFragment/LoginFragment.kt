package com.zero.doockerhubapp.fragments.loginFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.zero.doockerhubapp.databinding.FragmentLoginBinding
import com.zero.doockerhubapp.fragments.loginFragment.viewModel.LoginViewModel
import com.zero.doockerhubapp.utils.NetworkResult
import com.zero.doockerhubapp.utils.sharedPreferenceManager.SharedPreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val loginViewModel: LoginViewModel by viewModels()

    @Inject
    lateinit var userSharedPreferenceUtil: SharedPreferenceUtil
    private val TAG = "LoginFragment"
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        val userName: String? = userSharedPreferenceUtil.getUserName()
        if(userName == "*"){
            binding.button.text = "Login"
            fetchData()
        }else{
            binding.button.text = userName
        }
        return binding.root
    }

    private fun fetchData() {
        val username = "zer0231"
        val password = "09spongeb0b"
        loginViewModel.fetchLoginResponse(username, password)
        loginViewModel.loginResponse.observe(viewLifecycleOwner) { result ->  //IN CASE OF ACTIVITY USE `this` INSTEAD OF `viewLifeCycleOwner`
            when (result) {
                is NetworkResult.Loading -> {
                    Log.d(TAG, "LOADING")
                }

                is NetworkResult.Success -> {
                    userSharedPreferenceUtil.setUserName(username)
                    userSharedPreferenceUtil.setUserToken(result.data?.get("token").toString())
                    Log.d(TAG, result.data.toString())
                }

                is NetworkResult.Error -> {
                    Log.d(TAG, "${result.message}")

                }
            }

        }

    }
}



