package com.example.enghacks_2019.util

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.example.enghacks_2019.ArduinoViewModel
import me.aflak.arduino.Arduino

fun  Fragment.getViewModel(): ArduinoViewModel {
    val arduino = Arduino(this.requireContext())
    val repository = InjectorUtils.getNoteRepository(this.requireContext())

    return ViewModelProviders.of(this, ViewModelFactory(repository, arduino))
        .get(ArduinoViewModel::class.java)
}