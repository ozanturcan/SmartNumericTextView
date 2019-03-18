package com.ozanturcan.smartnumerictextview

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.smart_numeric_text_view.view.*
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

class SmartNumericTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {


    private val currentFractionDigit = 2

    init {
        LayoutInflater.from(context).inflate(R.layout.smart_numeric_text_view, this, true)
        attrs?.let {
            val styledAttributes = context.obtainStyledAttributes(it, R.styleable.SmartNumericTextView, 0, 0)


            val textValue = styledAttributes.getString(R.styleable.SmartNumericTextView_text)
            val suffixSymbol = styledAttributes.getString(R.styleable.SmartNumericTextView_suffixSymbol)
            val textSize = styledAttributes.getDimensionPixelSize(R.styleable.SmartNumericTextView_textSize, 24)
            val decimalPercentage = styledAttributes.getInteger(R.styleable.SmartNumericTextView_textPercentage, 100)
            val textStyle = styledAttributes.getInt(R.styleable.SmartNumericTextView_textStyle, 0)

            val textColor = styledAttributes.getColor(R.styleable.SmartNumericTextView_textColor, 0xFF808080.toInt())
            val secondaryTextColor = styledAttributes.getColor(
                R.styleable.SmartNumericTextView_secondaryTextColor,
                textColor
            )



            setTextStyle(textStyle)
            setTextSizeInteger(textSize)
            setTextSizeDecimal((textSize * decimalPercentage) / 100)
            setTextColor(textColor, secondaryTextColor)
            if (!suffixSymbol.isNullOrEmpty()) {
                setText(textValue, suffixSymbol)
            } else {
                setText(textValue)
            }

            styledAttributes.recycle()
        }
    }


    private fun setTextSizeInteger(textSize: Int) {
        text_integer.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    private fun setTextSizeDecimal(textSize: Int) {
        text_decimal.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }


    private fun setDecimalColor(colorID: Int) {
        text_decimal.setTextColor(colorID)

    }

    //region Public Functions
    fun setText(value: String?, symbols: String? = "") {
        if (value?.toFloatOrNull() != null) {
            val dfFormat = DecimalFormat(("#" + getDecimalSeparator() + "##"))
            dfFormat.maximumFractionDigits = currentFractionDigit
            dfFormat.minimumFractionDigits = currentFractionDigit
            val tempValue = dfFormat.format(value.toFloat())


            text_integer.text = tempValue.split(getDecimalSeparator())[0]
            if (symbols.isNullOrEmpty()) {

                text_decimal.text = String.format(
                    "%s%s",
                    getDecimalSeparator(), tempValue.split(getDecimalSeparator())[1]
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

    fun setTextSize(textSize: Int, decimalPercentage: Int = 100) {
        setTextSizeInteger(textSize)
        setTextSizeDecimal((decimalPercentage * textSize) / 100)
    }

    fun setTextColor(@ColorInt primaryColorID: Int, @ColorInt secondaryColorID: Int = primaryColorID) {
        text_integer.setTextColor(primaryColorID)
        setDecimalColor(secondaryColorID)
    }
    //endregion


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
