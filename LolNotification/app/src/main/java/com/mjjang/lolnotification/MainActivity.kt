package com.mjjang.lolnotification

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
}
