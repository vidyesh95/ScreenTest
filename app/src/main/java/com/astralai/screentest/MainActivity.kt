package com.astralai.screentest

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.viewpager2.widget.ViewPager2
import com.astralai.screentest.adapter.ViewPagerAdapter
import com.astralai.screentest.databinding.ActivityMainBinding
import com.astralai.screentest.model.ViewPagerItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_ScreenTest)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        view = binding.root
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
            // Tell the window that we want to handle/fit any system windows
            WindowCompat.setDecorFitsSystemWindows(window, false)

            val controller = view.windowInsetsController

            // Hide the keyboard (IME)
            controller?.hide(WindowInsets.Type.ime())

            // Sticky Immersive is now ...
            controller?.systemBarsBehavior = BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE

            // When we want to hide the system bars
            controller?.hide(WindowInsets.Type.systemBars())

            /*val flag = WindowInsets.Type.statusBars()
            WindowInsets.Type.navigationBars()
            WindowInsets.Type.captionBar()
            window?.insetsController?.hide(flag)*/
        } else {
            //noinspection
            @Suppress("DEPRECATION")
            // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
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
}
