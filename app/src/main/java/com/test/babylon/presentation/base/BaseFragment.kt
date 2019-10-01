package com.test.babylon.presentation.base

import android.widget.Toast
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment(), View {
    override fun displayToast(message: String, toastLength: Int) {
        Toast.makeText(context, message, toastLength).show()
    }

}