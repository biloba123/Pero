package com.lvqingyang.pero

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.lvqingyang.pero.net.getPero
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_pero.*

class MainActivity : AppCompatActivity() {

    val TAG="MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadPero()
        ev.setOnRetryListener(View.OnClickListener {
            loadPero()
        })

    }

    private fun loadPero(){
        ev.loading()
        Thread{
            try {
                val pero= getPero()
                runOnUiThread{
                    ev.succ()
                    tv_title.text=pero.title
                    tv_auther.text=pero.auther
                    tv_content.text=pero.content.replace(' ','\n')
                }
            }catch (e: Exception){
                e.printStackTrace()
                runOnUiThread { ev.failed() }
            }

        }.start()
    }
}
