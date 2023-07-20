package com.zhili.zljx.widget.view

import android.content.Context
import android.text.SpannableString
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.zhili.zljx.R
import com.zhili.zljx.ext.parseColor


/**
 *@Auther KLOD
 *2023/4/3 19:43
 */
class KeyValueLayout : ConstraintLayout {
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        initView(context, attrs)

    }

    private var keyTv: TextView? = null
    private var valueTv: TextView?=null


    fun setValueTvText(content:String){
        valueTv?.text = content
    }
    fun setValueTvText(content:SpannableString){
        valueTv?.text = content
    }
    fun setKeyTvText(content:String){
        keyTv?.text = content
    }
    fun setValueTvColor(color:Int){
        valueTv?.setTextColor(color)
    }
    fun setKeyTvTextColor(color:Int){
        keyTv?.setTextColor(color)
    }

    private fun initView(context: Context, attrs: AttributeSet) {

        LayoutInflater.from(context).inflate(R.layout.layout_key_value, this)
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.KeyValueLayout)

        keyTv = findViewById(R.id.klodKey)
        valueTv = findViewById(R.id.klodValue)

        //设置文字
        keyTv?.text = typedArray.getText(R.styleable.KeyValueLayout_klod_key)
        valueTv?.text = typedArray.getText(R.styleable.KeyValueLayout_klod_value)
        //设置文字大小
        // 可以看到TypedValue.COMPLEX_UNIT_SP，也就是说size是sp类型的，而getDimension最后给出的是px，
        // 导致getDimension之后再setTextSize时size又再次乘了density最终导致显示异常，
        // 所以在自定义布局设置size的时候不能使用默认的setTextSize(int size)而应该采用

        keyTv?.setTextSize(TypedValue.COMPLEX_UNIT_PX,typedArray.getDimension(R.styleable.KeyValueLayout_klod_key_size, 42f))
        valueTv?.setTextSize(TypedValue.COMPLEX_UNIT_PX,typedArray.getDimension(R.styleable.KeyValueLayout_klod_value_size, 42f))
//        valueTv?.textSize = typedArray.getDimension(R.styleable.KeyValueLayout_klod_value_size, 0f)
        //设置字体颜色
        keyTv?.setTextColor(
            typedArray.getColor(
                R.styleable.KeyValueLayout_klod_key_color,
                "#666666".parseColor()
            )
        )
        valueTv?.setTextColor(
            typedArray.getColor(
                R.styleable.KeyValueLayout_klod_value_color,
                "#333333".parseColor()

            )
        )
        typedArray.recycle()


    }


}