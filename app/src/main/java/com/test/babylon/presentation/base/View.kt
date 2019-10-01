package com.test.babylon.presentation.base

import android.widget.Toast

interface View {
    fun displayToast(message: String, toastLength: Int = Toast.LENGTH_SHORT)
}