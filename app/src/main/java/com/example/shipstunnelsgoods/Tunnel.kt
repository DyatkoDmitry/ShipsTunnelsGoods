package com.example.shipstunnelsgoods

import com.example.shipstunnelsgoods.model.GOOD
import com.example.shipstunnelsgoods.model.Ship
import com.example.shipstunnelsgoods.model.ShipGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.LinkedBlockingQueue

class Tunnel(val changingQueue:()->Unit) {
    private val shipGenerator = ShipGenerator(AppConst.GENERATED_AMOUNT)

    private val shipQueue = LinkedBlockingQueue<Ship>(AppConst.TUNNEL_SIZE)

    init {
        startShipAcceptance()
    }

    private fun startShipAcceptance(){
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            shipGenerator.getShipFlow().collect{
                ship -> shipQueue.put(ship)
                changingQueue.invoke()
            }
        }
    }

    fun getShipQueue() = shipQueue

    @Synchronized fun getShipByType(dockType: GOOD): Ship?{
        val iterator = shipQueue.iterator()

        while (iterator.hasNext()){
            val ship = iterator.next()
            if (ship.good == dockType) {
                iterator.remove()
                return ship
            }
        }
        return null
    }
}