package com.example.enghacks_2019

import android.hardware.usb.UsbDevice
import androidx.lifecycle.ViewModel
import com.example.enghacks_2019.cache.Repository
import me.aflak.arduino.Arduino
import me.aflak.arduino.ArduinoListener

class ArduinoViewModel(
    private val repository: Repository,
    private val arduino: Arduino
) : ViewModel() {

    fun setupListener() {
        arduino.setArduinoListener(object : ArduinoListener {
            override fun onArduinoAttached(device: UsbDevice?) {

            }

            override fun onArduinoDetached() {

            }

            override fun onArduinoMessage(bytes: ByteArray?) {

            }

            override fun onArduinoOpened() {

            }

            override fun onUsbPermissionDenied() {

            }
        })
    }

    fun closeArduino() {
        arduino.unsetArduinoListener()
        arduino.close()
    }
}