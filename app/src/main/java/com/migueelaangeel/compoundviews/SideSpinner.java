package com.migueelaangeel.compoundviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SideSpinner extends LinearLayout {

    private Button mPreviousButton;
    private Button mNextButton;

    private CharSequence[] mSpinnerValues = null;
    private int mSelectedIndex = -1;

    public SideSpinner(Context context) {
        super(context);
        initializeViews(context);
    }

    public SideSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray;
        typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.SideSpinner);
        mSpinnerValues = typedArray
                .getTextArray(R.styleable.SideSpinner_values);
        typedArray.recycle();

        initializeViews(context);
    }

    public SideSpinner(Context context,
                       AttributeSet attrs,
                       int defStyle) {
        super(context, attrs, defStyle);

        TypedArray typedArray;
        typedArray = context
                .obtainStyledAttributes(attrs, R.styleable.SideSpinner);
        mSpinnerValues = typedArray
                .getTextArray(R.styleable.SideSpinner_values);
        typedArray.recycle();

        initializeViews(context);
    }

    /**
     * Inflates the views in the layout.
     *
     * @param context
     *           the current context for the view.
     */
    private void initializeViews(Context context) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.sidespinner_view, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // When the controls in the layout are doing being inflated, set
        // the callbacks for the side arrows.
        super.onFinishInflate();

        // When the previous button is pressed, select the previous value
        // in the list.
        mPreviousButton = (Button) this
                .findViewById(R.id.sidespinner_view_previous);
        mPreviousButton
                .setBackgroundResource(android.R.drawable.ic_media_previous);

        mPreviousButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (mSelectedIndex > 0) {
                    int newSelectedIndex = mSelectedIndex - 1;
                    setSelectedIndex(newSelectedIndex);
                }
            }
        });

        // When the next button is pressed, select the next item in the
        // list.
        mNextButton = (Button)this
                .findViewById(R.id.sidespinner_view_next);
        mNextButton
                .setBackgroundResource(android.R.drawable.ic_media_next);
        mNextButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (mSpinnerValues != null
                        && mSelectedIndex < mSpinnerValues.length - 1) {
                    int newSelectedIndex = mSelectedIndex + 1;
                    setSelectedIndex(newSelectedIndex);
                }
            }
        });

        // Select the first value by default.
        setSelectedIndex(0);
    }

    public void setValues(CharSequence[] values) {
        mSpinnerValues = values;

        // Select the first item of the string array by default since
        // the list of value has changed.
        setSelectedIndex(0);
    }

    /**
     * Sets the selected index of the spinner.
     *
     * @param index
     *           the index of the value to select.
     */
    public void setSelectedIndex(int index) {
        // If no values are set for the spinner, do nothing.
        if (mSpinnerValues == null || mSpinnerValues.length == 0)
            return;

        // If the index value is invalid, do nothing.
        if (index < 0 || index >= mSpinnerValues.length)
            return;

        // Set the current index and display the value.
        mSelectedIndex = index;
        TextView currentValue;
        currentValue = (TextView)this
                .findViewById(R.id.sidespinner_view_current_value);
        currentValue.setText(mSpinnerValues[index]);

        // If the first value is shown, hide the previous button.
        if (mSelectedIndex == 0)
            mPreviousButton.setVisibility(INVISIBLE);
        else
            mPreviousButton.setVisibility(VISIBLE);

        // If the last value is shown, hide the next button.
        if (mSelectedIndex == mSpinnerValues.length - 1)
            mNextButton.setVisibility(INVISIBLE);
        else
            mNextButton.setVisibility(VISIBLE);
    }

    /**
     * Gets the selected value of the spinner, or null if no valid
     * selected index is set yet.
     *
     * @return the selected value of the spinner.
     */
    public CharSequence getSelectedValue() {
        // If no values are set for the spinner, return an empty string.
        if (mSpinnerValues == null || mSpinnerValues.length == 0)
            return "";

        // If the current index is invalid, return an empty string.
        if (mSelectedIndex < 0 || mSelectedIndex >= mSpinnerValues.length)
            return "";

        return mSpinnerValues[mSelectedIndex];
    }

    /**
     * Gets the selected index of the spinner.
     *
     * @return the selected index of the spinner.
     */
    public int getSelectedIndex() {
        return mSelectedIndex;
    }



}
