package com.udacity.shoestore.shoeList

import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceManager
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.models.Shoe

@Suppress("DEPRECATION")
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

        // Observe Shoes
        viewModel.shoes.observe(viewLifecycleOwner) {
            val parentLayout = binding.linearLayout
            val childView1 = createChildView(parentLayout, shoe)
            val childView2 = createChildView(parentLayout, shoe)
            parentLayout.addView(childView1)
            parentLayout.addView(childView2)
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
        val childView: View = layoutInflater.inflate(R.layout.shoe_item, parentLayout, false)
        childView.findViewById<TextView>(R.id.shoe_name).text = shoe?.name
        childView.findViewById<TextView>(R.id.shoe_size).text = shoe?.size.toString()
        childView.findViewById<TextView>(R.id.shoe_description).text = shoe?.description
        childView.findViewById<TextView>(R.id.shoe_company).text = shoe?.company
        return layoutInflater.inflate(R.layout.shoe_item, parentLayout, false)
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