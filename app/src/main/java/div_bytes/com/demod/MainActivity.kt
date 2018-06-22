package div_bytes.com.demod

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import div_bytes.com.demod.ui.main.CounterFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                    .replace(R.id.container, CounterFragment.newInstance())
//                    .commitNow()
//        }
    }

}
