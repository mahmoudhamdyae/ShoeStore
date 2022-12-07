package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.udacity.shoestore.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_log_in, container, false)

        // Log In
        binding.logInButton.setOnClickListener {
            saveStateAndNavigate()
        }

        // Sign Up
        binding.signUpButton.setOnClickListener {
            saveStateAndNavigate()
        }

        return binding.root
    }

    private fun saveStateAndNavigate() {
        // Save State
        val appPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = appPreferences.edit()
        val isFirstTime = appPreferences.getBoolean("isFirstTime", true)
        if (isFirstTime) {
            // Implement your first time logic
            editor.putBoolean("isFirstTime", false)
            editor.apply()
        }

        // Navigate To Shoe List Fragment
        findNavController().navigate(
            LogInFragmentDirections.actionLogInFragmentToShoeListFragment())
    }
}