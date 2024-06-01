package com.example.shipstunnelsgoods

import androidx.lifecycle.MutableLiveData
import com.example.shipstunnelsgoods.model.GOOD
import com.example.shipstunnelsgoods.model.Ship
import com.example.shipstunnelsgoods.model.WEIGHT

class Dock(val dockType: GOOD, val tunnel: Tunnel, val dockLiveData: MutableLiveData<Ship?>): Thread() {

    private var isActive = true

    override fun run() {
        while (isActive){

            val ship = tunnel.getShipByType(dockType)
            ship?.let {
                dockLiveData.postValue(it)
                Thread.sleep(getLoadTime(ship))
                dockLiveData.postValue(AppConst.END_OF_LOAD)
            }
        }
    }

    private fun getLoadTime(ship: Ship):Long{
        return when (ship.weight){
            WEIGHT.LIGHT -> AppConst.LIGHT_LOADTIME
            WEIGHT.MEDIUM -> AppConst.MEDIUM_LOADTIME
            WEIGHT.HEAVY -> AppConst.HEAVY_LOADTIME
        }
    }

    fun disable(){
        isActive = false
    }
}