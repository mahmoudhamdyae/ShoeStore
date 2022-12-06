package com.udacity.shoestore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentShoeListBinding

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

        binding.fab.setOnClickListener {
//            findNavController().navigate()
            Toast.makeText(context, "fab clicked", Toast.LENGTH_SHORT).show()
        }

        viewModel.shoes.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, it.size.toString(), Toast.LENGTH_SHORT).show()
        })

        return binding.root
    }
}