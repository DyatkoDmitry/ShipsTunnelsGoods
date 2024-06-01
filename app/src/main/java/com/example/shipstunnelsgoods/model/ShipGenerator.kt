package com.example.shipstunnelsgoods.model

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShipGenerator(val shipsAmount: Int) {
    private var id: Int = 0

    fun getShipFlow(): Flow<Ship> = flow{
        repeat(shipsAmount){
            delay(1000)
            emit(getRandomShip())
        }
    }

    private fun getRandomShip(): Ship{
        val good = GOOD.entries.random()
        val weight = WEIGHT.entries.random()
        id++
        return Ship(id, good, weight)
    }
}