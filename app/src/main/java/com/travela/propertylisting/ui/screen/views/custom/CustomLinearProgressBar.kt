package com.travela.propertylisting.ui.screen.views.custom

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.travela.propertylisting.R

class CustomLinearProgressBar(context: Context) : FrameLayout(context) {

    init {
        LayoutInflater.from(context).inflate(R.layout.linear_loading, this, true)
    }
}