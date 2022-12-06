package com.udacity.shoestore.shoeList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel: ViewModel() {

    private var _shoes = MutableLiveData<String>()
    val shoes : LiveData<String>
        get() = _shoes

    fun addShoe(shoe: Shoe?) {
        _shoes.value = shoe?.name + "\n" +
                        shoe?.size.toString() + "\n" +
                        shoe?.company + "\n" +
                        shoe?.description + "\n\n\n"
    }
}