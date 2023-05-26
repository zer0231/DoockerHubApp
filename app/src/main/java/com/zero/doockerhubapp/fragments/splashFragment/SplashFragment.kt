package com.zero.doockerhubapp.fragments.splashFragment

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.zero.doockerhubapp.R
import com.zero.doockerhubapp.databinding.FragmentSplashscreenBinding
import com.zero.doockerhubapp.utils.sharedPreferenceManager.SharedPreferenceUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashscreenBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashscreenBinding.inflate(inflater, container, false)
        val actionNav: Int = if (sharedPreferenceUtil.getUserToken().equals("*")) {
            R.id.action_splashFragment_to_loginFragment
        } else {
            R.id.action_splashFragment_to_dashBoardFragment
        }
        val timer = object : CountDownTimer(2000, 100) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                findNavController().navigate(actionNav)
            }
        }

        timer.start()
        return binding.root
    }
}