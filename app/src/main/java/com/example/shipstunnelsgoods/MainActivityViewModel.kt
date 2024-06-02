package com.example.shipstunnelsgoods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shipstunnelsgoods.model.Ship

class MainActivityViewModel: ViewModel() {

    private val tunnel = Tunnel(::changingState)

    private val _breadDockLiveData: MutableLiveData<Ship?> = MutableLiveData()
    val breadDockLiveData: LiveData<Ship?> = _breadDockLiveData

    private val _bananaDockLiveData: MutableLiveData<Ship?> = MutableLiveData()
    val bananaDockLiveData: LiveData<Ship?> = _bananaDockLiveData

    private val _clothesDockLiveData: MutableLiveData<Ship?> = MutableLiveData()
    val clothesDockLiveData: LiveData<Ship?> = _clothesDockLiveData

    private val _tunnelChangeLiveData: MutableLiveData<ShipQueue> = MutableLiveData()
    val tunnelChangeLiveData: LiveData<ShipQueue> = _tunnelChangeLiveData

    private fun changingState(){

        _tunnelChangeLiveData.postValue(tunnel.shipQueue)

        _breadDockLiveData.postValue(tunnel.breadDock.ship)
        _bananaDockLiveData.postValue(tunnel.bananaDock.ship)
        _clothesDockLiveData.postValue(tunnel.clothesDock.ship)
    }
}