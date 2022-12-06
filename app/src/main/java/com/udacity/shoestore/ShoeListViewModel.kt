package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private var _shoes = MutableLiveData<List<Shoe>>()
    val shoes : LiveData<List<Shoe>>
        get() = _shoes

    init {
        _shoes.value = listOf(Shoe("name" , .9, "company", "description", listOf()))
    }
}