package com.example.nobundlerandomcolorarrayrest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel: ViewModel() {

    private val _squares = MutableLiveData<Squares>()
    val squares: LiveData<Squares> = _squares

    init {
        generateSquares()
    }

    fun generateSquares(){
        _squares.value = createSquares()
    }

    private fun createSquares(): Squares {
        return Squares(
            size = Random.nextInt(5, 13),
            colorProducer = { -Random.nextInt(0xFFFFFF) }
        )
    }

}