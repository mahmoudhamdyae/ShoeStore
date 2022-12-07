package com.udacity.shoestore.shoeList

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding
import com.udacity.shoestore.models.Shoe

@Suppress("DEPRECATION")
class ShoeListFragment: Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private val viewModel: ShoeListViewModel by activityViewModels()

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

        // Observe Shoes
        viewModel.shoes.observe(viewLifecycleOwner) {
            val parentLayout = binding.linearLayout
            for (item in it) {
                val childView = createChildView(parentLayout, item)
                parentLayout.addView(childView)
            }
        }

        // Log In
        val appPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val isFirstTime = appPreferences.getBoolean("isFirstTime", true)
        if (isFirstTime) {
            // Implement your first time logic
            findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToWelcomeFragment())
        }

        // This allows onCreateOptionsMenu to be called
        setHasOptionsMenu(true)

        return binding.root
    }

    private fun createChildView(parentLayout: LinearLayout, shoe: Shoe?) : View {
        val childView = layoutInflater.inflate(R.layout.shoe_item, parentLayout, false)
        val bindingItem: ShoeItemBinding = ShoeItemBinding.bind(childView)
        bindingItem.shoe = shoe

        return childView
    }

    // Sign Out

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.sign_out_menu -> signOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun signOut() {
        // Change The Variable To False For Next Registration
        val appPreferences = PreferenceManager.getDefaultSharedPreferences(requireContext())
        val editor = appPreferences.edit()
        editor.putBoolean("isFirstTime", true)
        editor.apply()

        // Navigate To Log In
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLogInFragment())
    }
}