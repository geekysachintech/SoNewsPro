package com.mrmobo.sonews.ui

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.mrmobo.sonews.R
import com.mrmobo.sonews.db.ArticleDatabase
import com.mrmobo.sonews.repository.NewsRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    lateinit var appSettingsPrefs: SharedPreferences
    lateinit var sharedPrefEdit: SharedPreferences.Editor
    var isNightModeOn: Boolean = false

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appSettingsPrefs =  getSharedPreferences("AppSettingPrefs", 0)
        sharedPrefEdit =  appSettingsPrefs.edit()
         isNightModeOn = appSettingsPrefs.getBoolean("NightMode", false)



        if (isNightModeOn){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.card_out_background)))
        //supportActionBar?.setT

        //val color: String = R.color.card_out_background.toString()


        //supportActionBar?.setHomeAsUpIndicator(R.drawable.dark_mode)
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())



    }

  //  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        return super.onCreateOptionsMenu(menu)
//        menuInflater.inflate(R.menu.menu_actions, menu)
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//      when(item.itemId){
//          R.id.dark_mode -> Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show()
//      }
//        return super.onOptionsItemSelected(item)
//    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_actions, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when(item.itemId) {
        R.id.dark_mode -> {
            if (isNightModeOn) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                sharedPrefEdit.putBoolean("NightMode", false)
                sharedPrefEdit.apply()
                Toast.makeText(this, "Dark Mode Disabled", Toast.LENGTH_SHORT).show()
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                sharedPrefEdit.putBoolean("NightMode", true)
                Toast.makeText(this, "Dark Mode Enabled", Toast.LENGTH_SHORT).show()
                sharedPrefEdit.apply()
            }
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}
