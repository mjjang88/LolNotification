package com.mjjang.lolnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.mjjang.lolnotification.databinding.ActivityTutorialBinding

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityTutorialBinding>(this, R.layout.activity_tutorial)
    }
}
