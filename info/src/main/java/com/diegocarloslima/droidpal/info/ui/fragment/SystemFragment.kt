package com.diegocarloslima.droidpal.info.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.diegocarloslima.droidpal.info.R
import com.diegocarloslima.droidpal.info.ui.viewmodel.InfoViewModelFactory
import com.diegocarloslima.droidpal.info.ui.viewmodel.SystemViewModel
import dagger.android.AndroidInjection
import javax.inject.Inject

class SystemFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: InfoViewModelFactory

    internal lateinit var viewModel: SystemViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[SystemViewModel::class.java]

        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)
        val tv = view.findViewById<TextView>(R.id.dashboard_tv)
        tv.setText(viewModel.test)
        return view
    }
}