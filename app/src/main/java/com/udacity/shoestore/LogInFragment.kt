package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLogInBinding

class LogInFragment : Fragment() {

    private lateinit var binding: FragmentLogInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogInBinding.inflate(inflater)

        // Log In
        binding.logInButton.setOnClickListener {
            findNavController().navigate(
                LogInFragmentDirections.actionLogInFragmentToMainFragment())
        }

        // Sign Up
        binding.signUpButton.setOnClickListener {
            findNavController().navigate(
                LogInFragmentDirections.actionLogInFragmentToMainFragment())
        }

        return binding.root
    }
}