package com.example.myapplication

import androidx.lifecycle.ViewModel

class ViewModelMain :ViewModel() {
    var count =20

    val fib=ArrayList<Int>()
    fun updateCount(update:Int){
        count=update
    }
    fun getList(): ArrayList<Int> {
        fib.clear()
        var first= 2
        var secound=3
        for(i in 1..count){
            fib.add(first)
            val next=first+secound
           first=secound
           secound=next

        }
        return fib

    }
}