package com.example.shipstunnelsgoods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.shipstunnelsgoods.databinding.ActivityMainBinding
import com.example.shipstunnelsgoods.model.Ship

class MainActivity : AppCompatActivity() {

    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setObservers()
    }

    private fun setObservers(){
        viewModel.breadDockLiveData.observe(this, Observer {
           setDockViewText(it, binding.breadDock)
        })

        viewModel.bananaDockLiveData.observe(this, Observer {
            setDockViewText(it, binding.bananaDock)
        })

        viewModel.clothesDockLiveData.observe(this, Observer {
            setDockViewText(it, binding.clothesDock)
        })

        viewModel.tunnelChangeLiveData.observe(this, Observer {
            setTunnelViewText(it)
        })
    }

    private fun setDockViewText(ship: Ship?, textView: TextView ){
        if(ship != null)
            textView.text = ship.toString()
        else
            textView.text = ""
    }

    private fun setTunnelViewText(tunnel: ShipQueue){
        val shipViews = arrayOf(binding.firstShip, binding.secondShip,binding.thirdShip, binding.fourthShip, binding.fifthShip)

        val shipQueue = tunnel.getShipQueue()
        shipQueue.toArray().forEachIndexed { index, ship ->
            shipViews[index].text = ship.toString()
        }
    }
}