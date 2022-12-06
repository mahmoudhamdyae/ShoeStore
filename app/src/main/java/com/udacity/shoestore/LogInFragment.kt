package com.udacity.shoestore

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
            Toast.makeText(context, "log", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                LogInFragmentDirections.actionLogInFragmentToShoeListFragment(null))
        }

        // Sign Up
        binding.signUpButton.setOnClickListener {
            Toast.makeText(context, "sign", Toast.LENGTH_SHORT).show()
            findNavController().navigate(
                LogInFragmentDirections.actionLogInFragmentToShoeListFragment(null))
        }

        return binding.root
    }
}