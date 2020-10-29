package cl.smq.laikapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.smq.laikapp.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: MainActivityBinding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

