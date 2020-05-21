package com.mjjang.lolnotification.manager

import android.app.Application
import android.content.Context

class App : Application() {
    lateinit var context: Context

    init{
        instance = this
    }

    companion object {
        private var instance: App? = null
        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }
}