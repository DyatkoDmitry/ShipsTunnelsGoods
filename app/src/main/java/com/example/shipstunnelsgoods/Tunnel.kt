package com.example.shipstunnelsgoods

import com.example.shipstunnelsgoods.model.Ship
import com.example.shipstunnelsgoods.model.ShipGenerator
import java.util.concurrent.LinkedBlockingQueue

class Tunnel {
    private val shipGenerator = ShipGenerator(50)

    private val shipQueue = LinkedBlockingQueue<Ship>(5)

}