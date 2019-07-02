package in.co.halexo.angry.righttobeauty.custom_textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

import in.co.halexo.angry.righttobeauty.R;


public class sCustomButtonRaleway extends AppCompatButton {
    public sCustomButtonRaleway(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.sCustomTextViewRaleway);
        String fontName = a.getString(R.styleable.sCustomTextViewRaleway_fontName);
        if (fontName != null) {
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/" + fontName));
        } else {
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/" + getResources().getString(R.string.fontRegular)));
        }
        a.recycle();
    }
}
