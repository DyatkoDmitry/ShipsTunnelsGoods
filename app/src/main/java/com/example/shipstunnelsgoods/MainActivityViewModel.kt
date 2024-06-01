package com.example.shipstunnelsgoods

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shipstunnelsgoods.model.GOOD
import com.example.shipstunnelsgoods.model.Ship
import java.util.concurrent.Executors

class MainActivityViewModel: ViewModel() {

    private val executorService = Executors.newFixedThreadPool(3)

    private val _breadDockLiveData: MutableLiveData<Ship?> = MutableLiveData()
    val breadDockLiveData: LiveData<Ship?> = _breadDockLiveData

    private val _bananaDockLiveData: MutableLiveData<Ship?> = MutableLiveData()
    val bananaDockLiveData: LiveData<Ship?> = _bananaDockLiveData

    private val _clothesDockLiveData: MutableLiveData<Ship?> = MutableLiveData()
    val clothesDockLiveData: LiveData<Ship?> = _clothesDockLiveData

    private val _tunnelChangeLiveData: MutableLiveData<Tunnel> = MutableLiveData()
    val tunnelChangeLiveData: LiveData<Tunnel> = _tunnelChangeLiveData

    private val tunnel = Tunnel(::changingQueue)

    init {
        val breadDock = Dock(GOOD.BREAD,tunnel,_breadDockLiveData)
        val bananaDock = Dock(GOOD.BANANA,tunnel,_bananaDockLiveData)
        val clothesDock = Dock(GOOD.CLOTHES,tunnel,_clothesDockLiveData)

        executorService.execute(breadDock)
        executorService.execute(bananaDock)
        executorService.execute(clothesDock)
    }

    private fun changingQueue(){
        _tunnelChangeLiveData.postValue(tunnel)
    }

    override fun onCleared() {
        super.onCleared()
        executorService.shutdown()
    }
}