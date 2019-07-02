package in.co.halexo.angry.righttobeauty.custom_textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import in.co.halexo.angry.righttobeauty.R;

/**
 * Created by Angry on 7/17/2018.
 */

public class sCustomTextViewMontez extends AppCompatTextView {
    public sCustomTextViewMontez(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.sCustomTextViewRaleway);
        String fontName = a.getString(R.styleable.sCustomTextViewRaleway_fontName);
        if (fontName != null) {
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/" + fontName));
        } else {
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/" + getResources().getString(R.string.fontMontez)));
        }
        a.recycle();
    }
    public sCustomTextViewMontez(Context context){
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/" + getResources().getString(R.string.fontMontez)));
    }
}
