package com.example.ch8_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.KeyEvent
import android.widget.Toast
import com.example.ch8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    var initTime = 0L
    var pauseTime = 0L



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //스타트 버튼이 눌리면
        binding.startButton.setOnClickListener{
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            //시작
            binding.chronometer.start()

            //화면에있는 버튼들의 옵션을 바꾼다.
            binding.stopButton.isEnabled=true
            binding.resetButton.isEnabled=true
            binding.startButton.isEnabled=false
        }

        //스톱버튼이 눌리면
        binding.stopButton.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()


            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=true
            binding.startButton.isEnabled=true

        }

        //리셋 버튼이 눌리면
        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()

            binding.stopButton.isEnabled=false
            binding.resetButton.isEnabled=false
            binding.startButton.isEnabled=true

        }

    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //3초이내에 백버튼을 두번 누르면 종료 아니면 한번 더 묻기
            if(System.currentTimeMillis() - initTime > 3000){
                Toast.makeText(this,"종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }

        return super.onKeyDown(keyCode, event)
    }

}