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
                println("test")
                arduino.open(device);

            }

            override fun onArduinoDetached() {
                println("Arduino detached")

            }

            override fun onArduinoMessage(bytes: ByteArray?) {
                println("sending data from arduino: ")
                println(bytes)

            }

            override fun onArduinoOpened() {
                val str = "test"
                arduino.send(str.toByteArray())

            }

            override fun onUsbPermissionDenied() {
                println("Premission denied")
                arduino.reopen()
            }
        })
    }

    fun closeArduino() {
        arduino.unsetArduinoListener()
        arduino.close()
    }


}