package com.example.objectboxexample

import android.app.Application
import io.objectbox.BoxStore
/**
class ObjectBoxApp : Application() {

    private lateinit var boxStore: BoxStore

    override fun onCreate() {
        super.onCreate()

        boxStore = MyObjectBox.builder().androidContext(this).build()
    }

    /** Provide access to the boxStore throughout the application */
    fun getBoxStore(): BoxStore {
        return boxStore
    }
}
*/