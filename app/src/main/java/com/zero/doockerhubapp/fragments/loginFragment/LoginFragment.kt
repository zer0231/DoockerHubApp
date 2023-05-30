package com.zero.doockerhubapp.fragments.loginFragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.zero.doockerhubapp.R
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

       binding.loginBtn.setOnClickListener{
           val password = binding.passwordTiet.text.toString()
           val userName = binding.usernameTiet.text.toString()
           login(userName,password)
       }
        return binding.root
    }

    private fun login(userName:String,password:String) {

        loginViewModel.fetchLoginResponse(userName, password)
        loginViewModel.loginResponse.observe(viewLifecycleOwner) { result ->  //IN CASE OF ACTIVITY USE `this` INSTEAD OF `viewLifeCycleOwner`
            when (result) {
                is NetworkResult.Loading -> {
                    Log.d(TAG, "LOADING")
                }

                is NetworkResult.Success -> {
                    userSharedPreferenceUtil.setUserName(userName)
                    userSharedPreferenceUtil.setUserToken(result.data?.get("token").toString())
                    findNavController().navigate(R.id.action_loginFragment_to_dashBoardFragment)
                }

                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),"${result.message}",Toast.LENGTH_SHORT).show()
                    Log.d(TAG, "${result.message}")
                    if(result.code == 403){
                        Log.d(TAG,result.message!!)
                    }

                }
            }

        }

    }
}



