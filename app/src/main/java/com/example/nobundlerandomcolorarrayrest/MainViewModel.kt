package com.example.nobundlerandomcolorarrayrest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainViewModel(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _squares = savedStateHandle.getLiveData<Squares>(KEY_SQUARE_STATE)
    val squares: LiveData<Squares> = _squares

    init {
        if (!savedStateHandle.contains(KEY_SQUARE_STATE))
            savedStateHandle[KEY_SQUARE_STATE] = createSquares()
    }

    fun generateSquares() {
        _squares.value = createSquares()
    }

    private fun createSquares(): Squares {
        return Squares(
            size = Random.nextInt(5, 13),
            colorProducer = { -Random.nextInt(0xFFFFFF) }
        )
    }


    companion object {
        const val KEY_SQUARE_STATE = "squares"
    }

}