package com.test.mvpdemo

import android.app.Application
import com.test.mvpdemo.data.network.NetworkHandler

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        /**
         * initialize Network handler on application create
         */
        NetworkHandler.init()
    }
}