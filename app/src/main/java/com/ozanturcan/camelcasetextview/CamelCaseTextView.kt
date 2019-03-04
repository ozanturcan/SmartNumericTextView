package com.ozanturcan.camelcasetextview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.camel_case_text_view.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class CamelCaseTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {



    init {
        LayoutInflater.from(context).inflate(R.layout.camel_case_text_view, this, true)
        attrs?.let {

            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.CamelCaseTextView, 0, 0)

            val tempVal = styledAttributes.getString(R.styleable.CamelCaseTextView_text)
            val textColor =
                styledAttributes.getColor(
                    R.styleable.CamelCaseTextView_textColor,
                    resources.getColor(R.color.colorPrimary)
                )
            val integerTextSize =
                styledAttributes.getDimensionPixelSize(R.styleable.CamelCaseTextView_textSize, 22)
            val fractionTextSize = integerTextSize *
                styledAttributes.getInteger(R.styleable.CamelCaseTextView_textPercentage, 70)/100

            setTextSizeInteger(integerTextSize)
            setTextSizeFraction(fractionTextSize)
            setTextColor(textColor)
            setText(tempVal)

            styledAttributes.recycle()
        }


    }

    fun setText(value: String?) {

        if (value?.toFloatOrNull() != null) {
            val dfFormat = DecimalFormat(("#" + getDecimalSeparator() + "##"))
            dfFormat.maximumFractionDigits = 2
            dfFormat.minimumFractionDigits = 2
            val tempValue = dfFormat.format(value.toFloat())

            text_integer.text = tempValue.split(getDecimalSeparator())[0]
            text_decimal.text = String.format(
                "%s%s",
                getDecimalSeparator(), tempValue.split(getDecimalSeparator())[1]
            )

        } else {
//             Number olmaması durumunda uygulanacak işlem
            setText("00" + getDecimalSeparator() + "00")
        }

    }

    private fun setTextColor(colorID: Int) {
        text_integer.setTextColor(colorID)
        text_decimal.setTextColor(colorID)
    }

    private fun setTextSizeInteger(textSize: Int) {
        text_integer.textSize = textSize.toFloat()
    }

    private fun setTextSizeFraction(textSize: Int) {
        text_decimal.textSize = textSize.toFloat()
    }

    private fun getDecimalSeparator(locale: Locale): Char {
        val formatSymbols = DecimalFormatSymbols.getInstance(locale)
        return formatSymbols.decimalSeparator
    }

    private fun getDecimalSeparator(): Char {
        return getDecimalSeparator(Locale.getDefault())
    }

}
