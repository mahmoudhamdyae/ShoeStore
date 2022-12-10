package com.udacity.shoestore.shoeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private var _shoes = MutableLiveData<List<Shoe>>()
    val shoes : LiveData<List<Shoe>>
        get() = _shoes

    private val _navigateToList = MutableLiveData(false)
    val navigateToList : LiveData<Boolean>
        get() = _navigateToList

    val name = MutableLiveData<String>()
    val size = MutableLiveData<String>()
    val description = MutableLiveData<String>()
    val company = MutableLiveData<String>()

    private val list = mutableListOf<Shoe>()

    fun addShoe() {
        list.add(Shoe(
            name = name.value,
            size = try { size.value?.toDouble() } catch (_: Exception) { 0.0 },
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