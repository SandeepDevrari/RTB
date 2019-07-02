package in.co.halexo.angry.righttobeauty.custom_textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import in.co.halexo.angry.righttobeauty.R;


public class sCustomEditTextRaleway extends AppCompatEditText implements TextView.OnEditorActionListener{
    public sCustomEditTextRaleway(Context context) {
        super(context);
        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/" + getResources().getString(R.string.fontRegular)));
    }

    public sCustomEditTextRaleway(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.sCustomTextViewRaleway);
        String fontName = a.getString(R.styleable.sCustomTextViewRaleway_fontName);
        if (fontName != null) {
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/" + fontName));
        } else {
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "fon/" + getResources().getString(R.string.fontRegular)));
        }
        a.recycle();
    }

    public sCustomEditTextRaleway(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.sCustomTextViewRaleway);
        String fontName = a.getString(R.styleable.sCustomTextViewRaleway_fontName);
        if (fontName != null) {
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/" + fontName));
        } else {
            this.setTypeface(Typeface.createFromAsset(context.getAssets(), "font/" + getResources().getString(R.string.fontRegular)));
        }
        boolean isNumber = a.getBoolean(R.styleable.sCustomTextViewRaleway_isNumber,false);
        //boolean isPassword = a.getBoolean(R.styleable.zCustomButton_isPassword,false);
        boolean isSmall = a.getBoolean(R.styleable.sCustomTextViewRaleway_isSmall,false);
        this.setTextColor(ContextCompat.getColor(context, R.color.colorText));

        this.setImeOptions(EditorInfo.IME_ACTION_DONE);
        if(isNumber){
            this.setInputType(InputType.TYPE_CLASS_NUMBER| InputType.TYPE_NUMBER_FLAG_DECIMAL);
        } else if (isSmall) {
            this.setInputType(InputType.TYPE_CLASS_TEXT );
        } else {
            this.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        }
        a.recycle();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        switch (actionId){
            case EditorInfo.IME_ACTION_DONE:{
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return true;
            }
            default:{
                return false;
            }
        }
    }
}
