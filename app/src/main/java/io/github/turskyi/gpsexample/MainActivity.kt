package io.github.turskyi.gpsexample

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private lateinit var locationManager: LocationManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLocationServices()
        checkIfThereIsGpsOnDevice()
        checkEnableGPS()
        initListeners()
    }

    private fun initListeners() {
        btnOpenSettings.setOnClickListener {
            val gpsOptionsIntent = Intent(
                    Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            startActivity(gpsOptionsIntent)
        }

        /** These buttons will never work */
//        btnTurnOn.setOnClickListener {
//            val intent = Intent("android.location.GPS_ENABLED_CHANGE")
//            intent.putExtra("enabled", true)
//            applicationContext.sendBroadcast(intent)
//        }
//
//        btnTurnOff.setOnClickListener {
//            val intent = Intent("android.location.GPS_ENABLED_CHANGE")
//            intent.putExtra("enabled", false)
//            applicationContext.sendBroadcast(intent)
//        }
    }

    private fun initLocationServices() {
        locationManager =
                getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    private fun checkEnableGPS() {
        val providerIsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
        if (providerIsEnabled) {
            /* GPS Enabled */
            tv.text = getString(R.string.gps_access, providerIsEnabled.toString())
        } else {
            tv.text = getString(R.string.gps_turned_on)
        }
    }

    private fun checkIfThereIsGpsOnDevice() {
        val pm = this.packageManager

        Toast.makeText(
                this,
                if (pm.hasSystemFeature(PackageManager.FEATURE_LOCATION_GPS)) "There is GPS" else "There is no GPS",
                Toast.LENGTH_LONG).show()
    }
}