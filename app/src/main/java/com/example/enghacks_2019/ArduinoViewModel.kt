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
                arduino.open(device);

            }

            override fun onArduinoDetached() {


            }

            override fun onArduinoMessage(bytes: ByteArray?) {
                return bytes.toInt()

            }

            override fun onArduinoOpened() {
                val str = "test"
                arduino.send(str.toByteArray())

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