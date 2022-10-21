package com.rabb.rainbird.view

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.rabb.rainbird.R
import com.rabb.rainbird.`interface`.CloseTask
import com.rabb.rainbird.databinding.ActivityMainBinding
import com.rabb.rainbird.model.IntentConstants.NO_VOLUME
import com.rabb.rainbird.model.IntentConstants.YEP_VOLUME
import com.rabb.rainbird.model.ResultsConstants

class MainActivity: AppCompatActivity(), CloseTask {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mBirdView : BirdView

    @SuppressLint("CutPasteId", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.score.visibility = View.GONE
        mBirdView = BirdView(this,this)

        // Start GameView
        binding.startBtn.setOnClickListener {
            if (binding.etName.text?.isEmpty() == true) {
                Toast.makeText(this@MainActivity, "Пожалуйста, введите Ваше имя", Toast.LENGTH_SHORT).show()
            } else {
                mBirdView.setBackgroundResource(R.drawable.ic_sky)
                binding.rootLayout.addView(mBirdView)

                // Off Visibility of View
                binding.startBtn.visibility = View.GONE
                binding.tilName.visibility = View.GONE
                binding.tvDescription.visibility = View.GONE
                binding.etName.visibility = View.GONE
                binding.cvMain.visibility = View.GONE
            }
        }

        // Volume
        val no = intent.getIntExtra("no",NO_VOLUME)
        val yep = intent.getIntExtra("no",YEP_VOLUME)

        if (yep == 1) {
            playAudioRaw()
        }

//        if (no == 2) {
//            Toast.makeText(this, "Без звука!",Toast.LENGTH_SHORT).show()
//        }
    }

    @SuppressLint("SetTextI18n")
    override fun closeGame(mScore: Int) {
        binding.score.text = "Score : $mScore"
        binding.rootLayout.removeView(mBirdView)

        val intent = Intent(this@MainActivity, ResultActivity::class.java)
        // Save Name
        intent.putExtra(ResultsConstants.USER_NAME, binding.etName.text.toString())
        intent.putExtra(ResultsConstants.TOTAL_SCORE,binding.score.text.toString())
        startActivity(intent)
        finish()
    }

    private fun playAudioRaw() {
        val player = MediaPlayer.create(this,R.raw.skywaing)
        player.start()
    }
}