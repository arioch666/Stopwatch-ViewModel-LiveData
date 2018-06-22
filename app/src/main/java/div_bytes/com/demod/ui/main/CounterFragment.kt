package div_bytes.com.demod.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import div_bytes.com.demod.R
import kotlinx.android.synthetic.main.main_fragment.*

class CounterFragment : Fragment() {

    companion object {
        fun newInstance() = CounterFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.let{
            viewModel = ViewModelProviders.of(it).get(MainViewModel::class.java)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.timerValueNonMutable.observe(this, Observer {
            Log.d("STOPWATCH", "Value we got is: $it")
            message.text = it.toString()

        })

        viewModel.imageUrlNonMutable.observe(this, Observer {
            //TODO("USER GLIDE TO SHOW IMAGE")
        })

    }

}
