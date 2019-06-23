package com.example.enghacks_2019.util

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar

fun View.setupSnackbar(
    lifecycleOwner: LifecycleOwner,
    snackbarEvent: LiveData<Int>,
    timeLength: Int
) {

    snackbarEvent.observe(lifecycleOwner, Observer { stringRes ->
        Snackbar.make(this, context.getString(stringRes), timeLength).show()
    })
}