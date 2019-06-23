package com.example.enghacks_2019

import android.hardware.usb.UsbDevice
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.enghacks_2019.cache.ExternalMsg
import com.example.enghacks_2019.cache.Repository
import com.example.enghacks_2019.cache.Result
import kotlinx.coroutines.launch
import me.aflak.arduino.Arduino
import me.aflak.arduino.ArduinoListener

class ArduinoViewModel(
    private val repository: Repository,
    private val arduino: Arduino
) : ViewModel() {

    private val _toast = MutableLiveData<Int>()
    val toastMsg: LiveData<Int> = _toast

    private val _msgs = MutableLiveData<List<ExternalMsg>>().apply { value = emptyList() }
    val msgLog: LiveData<List<ExternalMsg>> = _msgs

    private val _latest = MutableLiveData<ExternalMsg>()
    val latest: LiveData<ExternalMsg> = _latest

    private val _popup = MutableLiveData<Int>()
    val popup: LiveData<Int> = _popup

    /*
    fun getAll() {
        viewModelScope.launch {
            val result = repository.getAll()

            if (result is Result.Success) {
                val msgList = result.data

                _msgs.value = msgList
            }
        }
    }

    fun latestMsg() {
        viewModelScope.launch {
            val result = repository.getLatest()

            if (result is Result.Success) {
                _latest.value = result.data
            }
        }
    }
    */

    fun sendBack(signal: String) {
        if (signal == "Wack") {
            return
        }
        arduino.send(signal.toByteArray())
    }

    fun setupListener() {
        arduino.setArduinoListener(object : ArduinoListener {
            override fun onArduinoAttached(device: UsbDevice?) {
                viewModelScope.launch {
                    _toast.value = R.string.toast_attached
                }
                arduino.open(device)
            }

            override fun onArduinoDetached() {
                viewModelScope.launch {
                    _toast.value = R.string.toast_detach
                }
            }

            override fun onArduinoMessage(bytes: ByteArray?) {
                val msg = String(bytes!!)
                viewModelScope.launch {
                    repository.insert(ExternalMsg(msg))
                    when (msg) {
                        "T" -> _popup.value = R.string.dialog_reset
                        "D" -> _popup.value = R.string.dialog_alarm
                        else -> _popup.value = R.string.dialog_error
                    }
                }
            }

            override fun onArduinoOpened() {
                val str = "howdy"
                arduino.send(str.toByteArray())
            }

            override fun onUsbPermissionDenied() {
                arduino.reopen()
            }
        })
    }

    fun closeArduino() {
        arduino.unsetArduinoListener()
        arduino.close()
    }


}