package com.github.gon2gon2.mdtdd.view.timer

import com.github.gon2gon2.mdtdd.services.TimerService
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JOptionPane
import javax.swing.JPanel
import javax.swing.JTextField

class TimerPanel {
    val panel = JPanel()
    private val startButton = JButton("Start")
    private val stopButton = JButton("Stop")
    private val timeInput = JTextField(5) // For setting minutes
    private val timerLabel = JLabel("00:00")
    private val timerService = TimerService()

    init {
        setupUI()
        setupActions()
    }

    private fun setupUI() {
        panel.apply {
            add(timeInput)
            add(startButton)
            add(stopButton)
            add(timerLabel)
        }
    }

    private fun setupActions() {
        startButton.addActionListener {
            val time = timeInput.text.toIntOrNull()?.times(60) ?: return@addActionListener
            if (time > 0) {
                timerService.startTimer(time, this::updateTimerUI, this::timerFinished)
            }
        }

        stopButton.addActionListener {
            timerService.stopTimer()
            timerLabel.text = "00:00"
        }
    }

    private fun updateTimerUI(timeLeftInSeconds: Int) {
        val minutes = timeLeftInSeconds / 60
        val seconds = timeLeftInSeconds % 60
        timerLabel.text = String.format("%02d:%02d", minutes, seconds)
    }

    private fun timerFinished() {
        JOptionPane.showMessageDialog(panel, "Time's up!", "Timer", JOptionPane.INFORMATION_MESSAGE)
    }
}
