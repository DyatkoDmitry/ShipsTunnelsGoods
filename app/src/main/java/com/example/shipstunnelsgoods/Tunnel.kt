package com.example.shipstunnelsgoods

import com.example.shipstunnelsgoods.model.GOOD
import com.example.shipstunnelsgoods.model.Ship
import java.util.concurrent.Executors

class Tunnel(val changingQueue:()->Unit) {

    val shipQueue = ShipQueue(changingQueue)

    private val executorService = Executors.newFixedThreadPool(3)

    val breadDock = Dock(GOOD.BREAD,this, changingQueue)
    val bananaDock = Dock(GOOD.BANANA,this, changingQueue)
    val clothesDock = Dock(GOOD.CLOTHES,this, changingQueue)

    init {
        executorService.execute(breadDock)
        executorService.execute(bananaDock)
        executorService.execute(clothesDock)
    }

    @Synchronized fun getShipByType(dockType: GOOD): Ship?{
        val iterator = shipQueue.getShipQueue().iterator()

        while (iterator.hasNext()){
            val ship = iterator.next()
            if (ship.good == dockType) {
                iterator.remove()
                changingQueue.invoke()
                return ship
            }
        }
        return null
    }
}