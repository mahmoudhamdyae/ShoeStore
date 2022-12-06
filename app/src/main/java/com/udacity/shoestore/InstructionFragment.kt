package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.models.Shoe

class InstructionFragment: Fragment() {

    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInstructionBinding.inflate(inflater)

        binding.button.setOnClickListener {
            val shoe = Shoe("name" , .9, "company", "description", listOf())
            findNavController().navigate(
                InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment(shoe))
        }

        return binding.root
    }
}