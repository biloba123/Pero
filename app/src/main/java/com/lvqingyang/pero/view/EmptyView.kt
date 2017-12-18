package com.lvqingyang.pero.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.lvqingyang.pero.R
import kotlinx.android.synthetic.main.ev_failed.view.*
import kotlinx.android.synthetic.main.ev_layout.view.*
import kotlinx.android.synthetic.main.ev_loading.view.*


/**
 * 一句话功能描述
 * 功能详细描述
 * @author Lv Qingyang
 * @see 相关类/方法
 * @since
 * @date 2017/12/17
 * @email biloba12345@gamil.com
 * @github https://github.com/biloba123
 * @blog https://biloba123.github.io/
 */

class EmptyView(context: Context?, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    private lateinit var mViewShow:View

    init {
        val layoutInflater=LayoutInflater.from(context)
        val rootView: View=layoutInflater.inflate(R.layout.ev_layout, this)

        if (context!=null && attrs!=null) {
            val a = context.theme.obtainStyledAttributes(
                    attrs,
                    R.styleable.EmptyView,
                    0, 0)
            try {
                val viewShowId=a.getResourceId(R.styleable.EmptyView_view_show, -1)
                if(viewShowId!=-1) {
                    mViewShow=LayoutInflater.from(context).inflate(viewShowId, null)
                    container.addView(mViewShow, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
                    mViewShow.visibility= View.GONE
                }

                load_text.text =a.getText(R.styleable.EmptyView_load_text)
                fail_ic.setImageResource(a.getResourceId(R.styleable.EmptyView_fail_icon, R.mipmap.ic_launcher))
                fail_text.text=a.getText(R.styleable.EmptyView_fail_text)

            } finally {
                a.recycle()
            }
        }
    }

    public fun loading(){
        layout_failed.visibility= View.GONE
        mViewShow.visibility= View.GONE
        layout_loading.visibility= View.VISIBLE
    }

    public fun failed(){
        layout_loading.visibility= View.GONE
        mViewShow.visibility= View.GONE
        layout_failed.visibility= View.VISIBLE
    }

    public fun succ(){
        layout_loading.visibility= View.GONE
        layout_failed.visibility= View.GONE
        mViewShow.visibility= View.VISIBLE
    }

    public fun setOnRetryListener(lis: OnClickListener){
        layout_failed.setOnClickListener{
            lis.onClick(it)
        }
    }
}