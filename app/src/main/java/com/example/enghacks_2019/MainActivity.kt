package com.example.enghacks_2019

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.core.view.GravityCompat
import androidx.appcompat.app.ActionBarDrawerToggle
import android.view.MenuItem
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.enghacks_2019.databinding.ActivityMainBinding
import java.security.AccessController

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Data binding
        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // Initialize the NavController and AppBarConfiguration
        navController = Navigation.findNavController(this, R.id.nav_host_frag)
        appBarConfiguration = AppBarConfiguration(navController.graph)


        // Setup of Action bar
        setSupportActionBar(binding.toolbarMain)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}
