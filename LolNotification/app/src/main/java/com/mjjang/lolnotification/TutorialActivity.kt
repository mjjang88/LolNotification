package com.mjjang.lolnotification

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import com.mjjang.lolnotification.databinding.ActivityTutorialBinding
import kotlinx.android.synthetic.main.activity_tutorial.*

class TutorialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityTutorialBinding>(this, R.layout.activity_tutorial)

        image_tutorial_close.setOnClickListener {
            finish()
        }

        btn_tutorial_start.setOnClickListener {
            finish()
        }
    }
}