package com.example.shipstunnelsgoods

import com.example.shipstunnelsgoods.model.Ship
import com.example.shipstunnelsgoods.model.ShipGenerator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.LinkedBlockingQueue

class ShipQueue(val changingQueue:()->Unit){
    private val shipGenerator = ShipGenerator(AppConst.GENERATED_AMOUNT)

    private val linkedBlockingQueue = LinkedBlockingQueue<Ship>(AppConst.TUNNEL_SIZE)

    init {
        startGeneratingShips()
    }

    private fun startGeneratingShips(){
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            shipGenerator.getShipFlow().collect{
                ship -> linkedBlockingQueue.put(ship)
                changingQueue.invoke()
            }
        }
    }

    fun getShipQueue() = linkedBlockingQueue
}