package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe

class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)

        // Cancel Button
        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        val shoe = Shoe("name" , .9, "company", "description", listOf())

        // Save Button
        binding.saveButton.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToShoeListFragment(shoe))
        }

        return binding.root
    }
}