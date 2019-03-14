# SmartNumericTextView

[![](https://jitpack.io/v/ozanturcan/SmartNumericTextView.svg)](https://jitpack.io/#ozanturcan/SmartNumericTextView)

Add it in your root build.gradle at the end of repositories:
```xml

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Step 2. Add the dependency
```xml

	dependencies {
	        implementation 'com.github.ozanturcan:SmartNumericTextView:Tag'
	}
```

Smart Numeric TextView using for showing number with a symbol. integer and the decimal parts can be a different color and size 
<th>

<p align="center">
<img align="center" src="https://github.com/ozanturcan/SmartNumericTextView/blob/master/screenshot/smartnumericscreenshot.png"  height="450"/>
</p>

<p>
<b>textSize: </b> Set the text size to a given value for integer and decimal parts.
</p>
<p>
<b>textPercentage: </b> Set the default text size to a given value of percantage for decimal part.
</p>
<p>
<b>textStyle: </b> Set the text style as italic / bold / normal.
</p>
<p>
<b>textColor: </b> Sets the text color for all parts.
</p>
<p>
<b>secondaryTextColor: </b> Sets the text color for decimal part. if this not defined default color using for this.
</p>
<p>
<b>suffixSymbol: </b> set symbol or text as a suffix. exp: $ ₺ € ℓ  or kg , mm, lt .... 
</p>

<b>text: </b> Sets the text to be displayed.


```xml
  <com.ozanturcan.smartnumerictextview.SmartNumericTextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:textSize="30dp"
            app:textPercentage="100"
            app:textStyle="bold"
            app:textColor="@color/colorPrimary"
            app:secondaryTextColor="@color/colorPrimaryDark"
            app:suffixSymbol="€"
            app:text="750.00"/>
         
```
