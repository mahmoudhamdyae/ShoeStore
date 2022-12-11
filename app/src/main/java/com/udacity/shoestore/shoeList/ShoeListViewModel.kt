package com.udacity.shoestore.shoeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private var _shoes = MutableLiveData<List<Shoe>>()
    val shoes : LiveData<List<Shoe>>
        get() = _shoes

    // A Variable to Navigate to Shoe List Fragment
    private val _navigateToList = MutableLiveData(false)
    val navigateToList : LiveData<Boolean>
        get() = _navigateToList

    // Edit Texts Variables
    val name = MutableLiveData<String>()
    val size = MutableLiveData<Double>()
    val description = MutableLiveData<String>()
    val company = MutableLiveData<String>()

    private val list = mutableListOf<Shoe>()

    // On Click the Save Button
    fun addShoe() {
        list.add(Shoe(
            name = name.value,
            size = size.value,
            description = description.value,
            company = company.value
        ))
        _shoes.value = list.toList()

        _navigateToList.value = true
    }

    fun clearNavigate() {
        _navigateToList.value = false
    }
}