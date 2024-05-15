package com.example.shipstunnelsgoods.model

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ShipGenerator(val shipsCount: Int) {

    val shipFlow: Flow<Ship> = flow{
        repeat(shipsCount){
            emit(getRandomShip())
        }
    }

    private fun getRandomShip(): Ship{
        val good = GOOD.entries.random()
        val weight = WEIGHT.entries.random()
        return Ship(good, weight)
    }
}