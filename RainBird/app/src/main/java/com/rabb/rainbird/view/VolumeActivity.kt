package com.rabb.rainbird.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.rabb.rainbird.databinding.ActivityVolumeBinding
import com.rabb.rainbird.model.IntentConstants.NO_VOLUME
import com.rabb.rainbird.model.IntentConstants.YEP_VOLUME
import com.rabb.rainbird.model.ResultsConstants
import com.rabb.rainbird.util.DialogsUtils

class VolumeActivity : AppCompatActivity(),
    DialogsUtils.YesDialogListener,
    DialogsUtils.NoDialogListener   {

    private lateinit var binding: ActivityVolumeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityVolumeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (supportActionBar != null) {
            this.supportActionBar?.hide();
        }

        binding.btnWithVolume.setOnClickListener {
            val intent = Intent(this@VolumeActivity, MainActivity::class.java)
            intent.putExtra("yep", YEP_VOLUME)
            startActivity(intent)
        }

        binding.btnWithoutVolume.setOnClickListener {
            openDialog()

            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@VolumeActivity, MainActivity::class.java)
                intent.putExtra("no", NO_VOLUME)
                startActivity(intent)
                finish()
            }, 2500)

        }

    }

    private fun openDialog() {
        DialogsUtils().show(supportFragmentManager,"Dialog")
    }

    override fun onYesClicked() {
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)
    }

    override fun onNoClicked() {
     // Nothing to do just stay in Volume

//        val intent = Intent(this, SplashActivity::class.java)
//        startActivity(intent)
//        finish()
    }

}