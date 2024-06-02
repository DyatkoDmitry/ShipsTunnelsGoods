package com.example.shipstunnelsgoods.model

data class Ship(val id: Int, val good: GOOD, val weight: WEIGHT) {

    override fun toString(): String {
       return "№${id} ${good} ${weight}"
    }
}