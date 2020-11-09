package com.astralai.screentest

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.astralai.screentest.adapter.ViewPagerAdapter
import com.astralai.screentest.databinding.ActivityMainBinding
import com.astralai.screentest.model.ViewPagerItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ScreenTest)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val colorsList = ArrayList<ViewPagerItem>()
        colorsList.add(ViewPagerItem(R.color.red))
        colorsList.add(ViewPagerItem(R.color.green))
        colorsList.add(ViewPagerItem(R.color.blue))
        colorsList.add(ViewPagerItem(R.color.white))
        colorsList.add(ViewPagerItem(R.color.black))
        colorsList.add(ViewPagerItem(R.color.gray))

        binding.viewPager.adapter = ViewPagerAdapter(colorsList)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) hideSystemUI()
    }

    private fun hideSystemUI() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val flag = WindowInsets.Type.statusBars()
            WindowInsets.Type.navigationBars()
            WindowInsets.Type.captionBar()
            window?.insetsController?.hide(flag)
        } else {
            //noinspection

            // Enables regular immersive mode.
            // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
            // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                    // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN)
        }
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    /*private fun showSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }*/
}
