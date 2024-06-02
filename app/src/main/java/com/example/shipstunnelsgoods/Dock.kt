package com.example.shipstunnelsgoods

import com.example.shipstunnelsgoods.model.GOOD
import com.example.shipstunnelsgoods.model.Ship
import com.example.shipstunnelsgoods.model.WEIGHT

class Dock(val dockType: GOOD, val tunnel: Tunnel, val changingQueue:()->Unit): Thread() {

    var ship: Ship? = null

    override fun run() {
        try {
            while (!isInterrupted) {
                ship = tunnel.getShipByType(dockType)

                ship?.let {
                    changingQueue.invoke()
                    Thread.sleep(getLoadTime(ship!!))
                }
            }
        } catch (e: InterruptedException){
            ship = null
            changingQueue.invoke()
        }
    }

    private fun getLoadTime(ship: Ship):Long{
        return when (ship.weight){
            WEIGHT.LIGHT -> AppConst.LIGHT_LOADTIME
            WEIGHT.MEDIUM -> AppConst.MEDIUM_LOADTIME
            WEIGHT.HEAVY -> AppConst.HEAVY_LOADTIME
        }
    }
}