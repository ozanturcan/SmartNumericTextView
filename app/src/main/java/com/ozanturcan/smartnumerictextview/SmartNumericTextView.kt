package com.ozanturcan.smartnumerictextview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.smart_numeric_text_view.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class SmartNumericTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    val currentFractionDigit = 2

    init {
        LayoutInflater.from(context).inflate(R.layout.smart_numeric_text_view, this, true)
        attrs?.let {

            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.SmartNumericTextView, 0, 0)

            val textValue = styledAttributes.getString(R.styleable.SmartNumericTextView_text)
            val textColor = styledAttributes.getColor(
                R.styleable.SmartNumericTextView_textColor,
                resources.getColor(R.color.colorPrimary)
            )
            val textSize =
                styledAttributes.getDimensionPixelSize(R.styleable.SmartNumericTextView_textSize, 22)
            val fractionPercentage =
                textSize * styledAttributes.getInteger(R.styleable.SmartNumericTextView_textPercentage, 70) / 100
            val suffixSymbol =
                styledAttributes.getString(R.styleable.SmartNumericTextView_suffixSymbol)
            val textStyle = styledAttributes.getInt(R.styleable.SmartNumericTextView_textStyle, 0)


            setTextStyle(textStyle)
            setTextSizeInteger(textSize)
            setTextSizeFraction(fractionPercentage)
            setTextColor(textColor)
            if (suffixSymbol.isNotEmpty()) {
                setText(textValue, suffixSymbol)
            } else {
                setText(textValue)
            }




            styledAttributes.recycle()
        }


    }

    fun setText(value: String?) {
        setText(value, null)
    }

    fun setText(value: String?, symbols: String?) {
        if (value?.toFloatOrNull() != null) {
            val dfFormat = DecimalFormat(("#" + getDecimalSeparator() + "##"))
            dfFormat.maximumFractionDigits = currentFractionDigit
            dfFormat.minimumFractionDigits = currentFractionDigit
            val tempValue = dfFormat.format(value.toFloat())


            text_integer.text = tempValue.split(getDecimalSeparator())[0]
            if (symbols.isNullOrEmpty()) {

                text_decimal.text = String.format(
                    "%s%s",
                    getDecimalSeparator(), tempValue.split(getDecimalSeparator())[1], symbols
                )
            } else {
                text_decimal.text = String.format(
                    "%s%s %s",
                    getDecimalSeparator(), tempValue.split(getDecimalSeparator())[1], symbols
                )
            }

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

    private fun setTextStyle(textStyle: Int) {
        text_integer.typeface = Typeface.defaultFromStyle(textStyle)
        text_decimal.typeface = Typeface.defaultFromStyle(textStyle)
    }

}
