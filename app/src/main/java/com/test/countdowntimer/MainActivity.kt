package com.test.countdowntimer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    private var timerStarted = false
    private var countDownTimer: CountDownTimer? = null

    companion object {
        private val COUNTDOWN_TIME = TimeUnit.MINUTES.toMillis(5)
        private val COUNTDOWN_TICK = TimeUnit.SECONDS.toMillis(1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startStopButton.setOnClickListener {
            if (timerStarted == false) {
                startStopButton.text = getString(R.string.stop_caps)
                timerLaunch()
            } else {
                countDownTimer?.cancel()
                timerStarted = false
                startStopButton.text = getString(R.string.start_caps)
                timerBody.text = getString(R.string.base_time)
            }
        }
    }

    private fun timerLaunch() {
        timerStarted = true

        countDownTimer = object : CountDownTimer(COUNTDOWN_TIME, COUNTDOWN_TICK) {

            override fun onTick(millisUntilFinished: Long) {
                timerBody.text = getString(
                    R.string.countdown_timer_format,
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60
                )
            }

            override fun onFinish() {
                timerBody.text = getString(R.string.base_time)
                startStopButton.text = getString(R.string.start_caps)
            }
        }.start()
    }
}
