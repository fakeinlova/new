package com.example.alpha2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.example.alpha2.registration.*
import kotlinx.android.synthetic.main.activity_scroll.*

class Scroll : AppCompatActivity() {
    private lateinit var first : FirstFr
    private lateinit var second : SecFr
    private lateinit var third : ThirdFr
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll)


        val fragments : ArrayList<Fragment> = arrayListOf(
            FirstFr(), SecFr(), ThirdFr()
        )
        viewpager.adapter = ViewPager(fragments,this)
    }
    fun sSign(view: View){
        startActivity(Intent(this@Scroll, LgRg::class.java))
    }
}