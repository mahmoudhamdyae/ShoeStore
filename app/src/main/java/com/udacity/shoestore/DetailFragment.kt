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

        // Save Button
        binding.saveButton.setOnClickListener {
            val shoe = getShoeDetail()
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToShoeListFragment(shoe))
        }

        return binding.root
    }

    private fun getShoeDetail() : Shoe {
        val name = binding.nameEditText.text.toString()
        val size = try {
            binding.sizeEditText.text.toString().toDouble()
        } catch (_: Exception) {
            .0
        }
        val company = binding.companyEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()

        return Shoe(name, size, company, description, listOf())
    }
}