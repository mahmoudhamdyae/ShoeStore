package com.udacity.shoestore.shoeList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

class ShoeListFragment: Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShoeListBinding.inflate(inflater)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // fab clicked
        binding.fab.setOnClickListener {
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToDetailFragment())
        }

        // Get Shoes from Previous Screen
        var shoe : Shoe? = null
        try {
            shoe = ShoeListFragmentArgs.fromBundle(requireArguments()).shoe
        } catch (_: Exception) {}
        if (shoe != null)
            viewModel.addShoe(shoe)

        viewModel.shoes.observe(viewLifecycleOwner) {
            val parentLayout = binding.linearLayout
            val childView = layoutInflater.inflate(R.layout.shoe_item, parentLayout, false)
            childView.findViewById<TextView>(R.id.shoe_name).text = shoe?.name
            childView.findViewById<TextView>(R.id.shoe_size).text = shoe?.size.toString()
            childView.findViewById<TextView>(R.id.shoe_description).text = shoe?.description
            childView.findViewById<TextView>(R.id.shoe_company).text = shoe?.company
            parentLayout.addView(childView)
        }

        // Log In
        val appPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isFirstTime = appPreferences.getBoolean("isFirstTime", true)
        if (isFirstTime) {
            // Implement your first time logic
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToWelcomeFragment())
        }

        return binding.root
    }
}