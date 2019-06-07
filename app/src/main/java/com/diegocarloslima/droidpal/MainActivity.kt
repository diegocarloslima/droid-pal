package com.diegocarloslima.droidpal

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.diegocarloslima.droidpal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.main_nav_fragment)
        appBarConfiguration = AppBarConfiguration(binding.mainNavView.menu, binding.mainDrawerLayout)

        setSupportActionBar(binding.mainAppBar.mainToolbar)

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.mainNavView.setupWithNavController(navController)

        printBuildVersion()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // TODO: Remove this
    @SuppressLint("LogNotTimber")
    private fun printBuildVersion() {
        android.util.Log.d("PALTEST", "BASE_OS:${Build.VERSION.BASE_OS}" +
                "\nCODENAME:${Build.VERSION.CODENAME}" +
                "\nINCREMENTAL:${Build.VERSION.INCREMENTAL}" +
                "\nPREVIEW_SDK_INT:${Build.VERSION.PREVIEW_SDK_INT}" +
                "\nRELEASE:${Build.VERSION.RELEASE}" +
                "\nSDK_INT:${Build.VERSION.SDK_INT}" +
                "\nSECURITY_PATCH:${Build.VERSION.SECURITY_PATCH}")
    }


    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("LogNotTimber")
    private fun printBuild() {
        android.util.Log.d("PALTEST", "BOARD:${Build.BOARD}" +
                "\nBOOTLOADER:${Build.BOOTLOADER}" +
                "\nBRAND:${Build.BRAND}" +
                "\nCPU_ABI:${Build.CPU_ABI}" +
                "\nCPU_ABI2:${Build.CPU_ABI2}" +
                "\nDEVICE:${Build.DEVICE}" +
                "\nDISPLAY:${Build.DISPLAY}" +
                "\nFINGERPRINT:${Build.FINGERPRINT}" +
                "\nHARDWARE:${Build.HARDWARE}" +
                "\nHOST:${Build.HOST}" +
                "\nID:${Build.ID}" +
                "\nMANUFACTURER:${Build.MANUFACTURER}" +
                "\nMODEL:${Build.MODEL}" +
                "\nPRODUCT:${Build.PRODUCT}" +
                "\nRADIO:${Build.RADIO}" +
                "\nRADIO method:${Build.getRadioVersion()}" +
                "\nSERIAL:${Build.SERIAL}" +
                "\nSERIAL method:${Build.getSerial()}" +
                "\nSUPPORTED_32_BIT_ABIS:${Build.SUPPORTED_32_BIT_ABIS}" +
                "\nSUPPORTED_64_BIT_ABIS:${Build.SUPPORTED_64_BIT_ABIS}" +
                "\nSUPPORTED_ABIS:${Build.SUPPORTED_ABIS}" +
                "\nTAGS:${Build.TAGS}" +
                "\nTIME:${Build.TIME}" +
                "\nTYPE:${Build.TYPE}" +
                "\nUSER:${Build.USER}" +
    }


}
