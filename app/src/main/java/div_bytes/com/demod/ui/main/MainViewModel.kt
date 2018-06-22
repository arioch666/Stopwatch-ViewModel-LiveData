package div_bytes.com.demod.ui.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainViewModel : ViewModel() {
    companion object {
        const val PLAY = 1
        const val PAUSE = 0

        const val DEFAULT_TIMER_VALUE = 60
    }

    private var playOrPauseState: MutableLiveData<Int> = MutableLiveData()

    private var timerValue: MutableLiveData<Int> = MutableLiveData()

    private var imageUrl: MutableLiveData<String?> = MutableLiveData()

    var rxDisposable: Disposable? = null

    init {
        playOrPauseState.value = PAUSE
        timerValue.value = DEFAULT_TIMER_VALUE
    }

    val playOrPauseStateNonMutable : LiveData<Int> = playOrPauseState
    val timerValueNonMutable: LiveData<Int> = timerValue
    val imageUrlNonMutable: LiveData<String?> = imageUrl

    fun clearTimer() {
        timerValue.value = DEFAULT_TIMER_VALUE
        pauseTimer()
    }

    fun startTimer() {
        playOrPauseState.value = PLAY
        createTimerObervable()
    }

    fun pauseTimer() {
        playOrPauseState.value = PAUSE
        unSubscribeDisposable()
    }

    fun clickPlayOfPauseTimerButton() {
        when(playOrPauseState.value) {
            PLAY -> pauseTimer()
            PAUSE -> startTimer()
        }
    }

    fun setTimerValue(userValue: Int) {
        timerValue.value = userValue
    }

    private fun createTimerObervable() {

        fun onTick() {
            val currentValue = timerValue.value?.dec()
            timerValue.postValue(currentValue)
            if (currentValue == 0) {
                clearTimer()
            }
        }

        unSubscribeDisposable()

        rxDisposable = Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribe { onTick() }

    }

    private fun unSubscribeDisposable() {
        rxDisposable?.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        unSubscribeDisposable()
    }
}