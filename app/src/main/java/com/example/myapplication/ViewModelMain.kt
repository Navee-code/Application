package com.example.myapplication

import androidx.lifecycle.ViewModel

class ViewModelMain :ViewModel() {
    var count =10

    var first:Int = 2
    var secound:Int = 3
    fun updateCount(update:Int){
        count=update
    }
}