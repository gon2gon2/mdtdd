package com.github.gon2gon2.mdtdd.services


import javax.swing.Timer

class TimerService {
    private var countdownTimer: Timer? = null
    private var timeLeftInSeconds: Int = 0
    private var onTick: ((Int) -> Unit)? = null
    private var onFinish: (() -> Unit)? = null

    fun startTimer(durationInSeconds: Int, onTick: (Int) -> Unit, onFinish: () -> Unit) {
        this.onTick = onTick
        this.onFinish = onFinish
        timeLeftInSeconds = durationInSeconds

        countdownTimer?.stop() // Stop any existing timer

        countdownTimer = Timer(1000) { _ ->
            if (timeLeftInSeconds > 0) {
                timeLeftInSeconds--
                onTick(timeLeftInSeconds)
            } else {
                countdownTimer?.stop()
                onFinish()
            }
        }.apply { start() }
    }

    fun stopTimer() {
        countdownTimer?.stop()
        timeLeftInSeconds = 0
    }
}
