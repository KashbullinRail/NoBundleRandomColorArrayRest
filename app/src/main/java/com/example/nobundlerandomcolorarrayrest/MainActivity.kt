package com.example.nobundlerandomcolorarrayrest

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.setMargins
import com.example.nobundlerandomcolorarrayrest.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createRender()

        binding.btnGenColor.setOnClickListener {
            createRender()
        }

    }

    private fun createRender() {
        renderSquares(createSquares())
    }

    private fun createSquares(): Squares {
        return Squares(
            size = Random.nextInt(5, 13),
            colorProducer = { -Random.nextInt(0xFFFFFF) }
        )
    }

    private fun renderSquares(squares: Squares) = with(binding) {

        colorContainer.removeAllViews()
        val identifier = squares.colors.indices.map { View.generateViewId() }
        for (i in squares.colors.indices) {
            val row = i / squares.size
            val columns = i % squares.size

            val view = View(this@MainActivity)
            view.setBackgroundColor(squares.colors[i])
            view.id = identifier[i]

            val params = ConstraintLayout.LayoutParams(0, 0)
            params.setMargins(resources.getDimensionPixelSize(R.dimen.space))
            view.layoutParams = params

            // Start to X constraint
            if (columns == 0) params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
            else params.startToEnd = identifier[i - 1]

            // End to X constraint
            if (columns == squares.size - 1) params.endToEnd =
                ConstraintLayout.LayoutParams.PARENT_ID
            else params.endToStart = identifier[i + 1]

            // Top to X constraint
            if (row == 0 ) params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
            else params.topToBottom = identifier[i - squares.size]

            // Bottom to X constraint
            if (row == squares.size -1) params.bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
            else params.bottomToTop = identifier[i + squares.size]

            colorContainer.addView(view)

        }

    }

}