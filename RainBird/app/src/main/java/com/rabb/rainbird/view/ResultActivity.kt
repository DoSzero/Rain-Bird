package com.rabb.rainbird.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rabb.rainbird.databinding.ActivityResultBinding
import com.rabb.rainbird.model.ResultsConstants

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnFinish.setOnClickListener {
            startActivity(Intent(this@ResultActivity, SplashActivity::class.java))
        }

        val userName = intent.getStringExtra(ResultsConstants.USER_NAME)
        binding.tvName.text = userName

        val userScore = intent.getStringExtra(ResultsConstants.TOTAL_SCORE)
        binding.tvScore.text = userScore
    }
}