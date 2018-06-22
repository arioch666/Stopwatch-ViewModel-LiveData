package div_bytes.com.demod.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import div_bytes.com.demod.R
import kotlinx.android.synthetic.main.display_control_fragment.*


/**
 *@author div@hello.com (div-hello)
 */

class ControlsFragment: Fragment() {

    lateinit var viewModel: MainViewModel

    override fun onResume() {
        super.onResume()

        viewModel.apply {
            //Set Observers
            playOrPauseStateNonMutable.observe(this@ControlsFragment, Observer {
                when (it) {
                    MainViewModel.PLAY -> pausePlayButton.setText(R.string.pause)
                    MainViewModel.PAUSE -> pausePlayButton.setText(R.string.play)
                }
            })
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        activity?.let {
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.display_control_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pausePlayButton.setOnClickListener {
            //Perform Action from View Model
            viewModel.clickPlayOfPauseTimerButton()
        }

        clearButton.setOnClickListener {
            //Perform Action from View Model
            viewModel.clearTimer()
        }

        inputEditText.addTextChangedListener(object: TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.toString()?.toIntOrNull()?.let{
                    viewModel.setTimerValue(it)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })
    }

}