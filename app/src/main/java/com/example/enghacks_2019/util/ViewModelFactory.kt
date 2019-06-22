package com.example.enghacks_2019.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.enghacks_2019.ArduinoViewModel
import com.example.enghacks_2019.cache.Repository
import me.aflak.arduino.Arduino

class ViewModelFactory(
    private val repository: Repository,
    private val arduino: Arduino
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            ArduinoViewModel(repository, arduino) as T
}