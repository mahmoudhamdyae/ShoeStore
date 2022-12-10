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
import com.udacity.shoestore.shoeList.ShoeListViewModel

class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Initialization
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.name.value = ""
        viewModel.size.value = ""
        viewModel.company.value = ""
        viewModel.description.value = ""

        // Cancel Button
        binding.cancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        // Navigate to Shoe List Fragment
        viewModel.navigateToList.observe(viewLifecycleOwner) {
            if (it) {
                findNavController().navigateUp()
                viewModel.clearNavigate()
            }
        }

        return binding.root
    }
}