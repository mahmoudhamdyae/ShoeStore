package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.shoeList.ShoeListViewModel

class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)

        // Cancel Button
        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Save Button
        binding.saveButton.setOnClickListener {
            viewModel.addShoe(getShoeDetail())
            findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToShoeListFragment())
        }

        return binding.root
    }

    // Get Details from EditTexts
    private fun getShoeDetail() : Shoe {
        val name = binding.nameEditText.text.toString()
        val size = try {
            binding.sizeEditText.text.toString().toDouble()
        } catch (_: Exception) {
            .0
        }
        val company = binding.companyEditText.text.toString()
        val description = binding.descriptionEditText.text.toString()

        return Shoe(name, size, company, description)
    }
}