package com.mjjang.lolnotification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import com.mjjang.lolnotification.databinding.ActivityMainBinding
import com.mjjang.lolnotification.manager.AppPreference

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        if (AppPreference.getFirstVisited()) {
            AppPreference.putFirstVisited(false)
            val intent = Intent(this, TutorialActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onBackPressed() {
        val systemTiem = System.currentTimeMillis()
        if (systemTiem > backKeyPressTime + BACK_KEY_PRESS_TIME) {
            backKeyPressTime = systemTiem
            Toast.makeText(this, R.string.back_press_message, Toast.LENGTH_SHORT).show()
        } else {
            finish()
        }
    }

    companion object {
        var backKeyPressTime : Long = System.currentTimeMillis();
        val BACK_KEY_PRESS_TIME : Int = 2000;
    }
}
