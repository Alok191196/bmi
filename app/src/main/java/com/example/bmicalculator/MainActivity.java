package com.example.bmicalculator;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int BMI_LIMIT_MINIMUM = 0;
    public static final int BMI_LIMIT_MAXIMUM = 60;
    public static final double BMI_UNDERWEIGHT_MIN_LIMIT = 0;
    public static final double BMI_UNDERWEIGHT_MAX_LIMIT = 18.5;
    public static final double BMI_NORMAL_MIN_LIMIT = 18.5;
    public static final double BMI_NORMAL_MAX_LIMIT = 25.0;
    public static final double BMI_OVERWEIGHT_MIN_LIMIT = 25.0;
    public static final double BMI_OVERWEIGHT_MAX_LIMIT = 60.0;
    public static final double FEET_CONSTANT = 0.3048;
    public static final double INCH_CONSTANT = 0.0254;
    public static final int CENTIMETER_CONSTANT = 100;
    public static final double KILOGRAM_CONSTANT = 0.453592;
    public static final double TEXT_VIEW_LIMIT = 999.99;
    public static final String POUND = "Pound";
    public static final String KILOGRAM = "Kilogram";
    public static final int NUMBER_ONE = 1;
    public static final int NUMBER_ZERO = 0;
    public static final String STRING_ZERO = "0";
    public static final String STRING_ONE = "1";
    public static final String STRING_TWO = "2";
    public static final String STRING_THREE = "3";
    public static final String STRING_FOUR = "4";
    public static final String STRING_FIVE = "5";
    public static final String STRING_SIX = "6";
    public static final String STRING_SEVEN = "7";
    public static final String STRING_EIGHT = "8";
    public static final String STRING_NINE = "9";
    public static final String STRING_DECIMAL_WITH_ZERO = "0.";
    public static final String DECIMAL = ".";
    public static final String EMPTY_STRING = "";
    public static final String RESULT = "RESULT";
    public static final String RESULT_STATUS = "RESULT_STATUS";
    public static final String HEIGHT_LABEL = "HEIGHT_LABEL";
    public static final String WEIGHT_LABEL = "WEIGHT_LABEL";
    public static final String HEIGHT_CATEGORY = "HEIGHT_CATEGORY";
    public static final String WEIGHT_CATEGORY = "WEIGHT_CATEGORY";
    public static final String RESULT_LAYOUT = "RESULT_LAYOUT";
    public static final String KEYPAD_LAYOUT = "KEYPAD_LAYOUT";
    public static final String CENTIMETER = "Centimeter";
    public static final String INCH = "Inch";
    public static final String METER = "Meter";
    public static final String FEET = "Feet";


    private TextView mWeightButtonTextView, mHeightButtonTextView, mBmiResultTextView, mBmiResultStatus,
          mWeightLabelTextView, mHeightLabelTextView;

    private View mKeypadView, mResultView;

    private Dialog mDialogWeight, mDialogHeight;

    private String resultWeightTextView = "";
    private String resultHeightTextView = "";

    private boolean mWeightViewSelected = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializing();

        if (savedInstanceState != null) {
            mBmiResultTextView.setText(savedInstanceState.getString(RESULT));
            mBmiResultStatus.setText(savedInstanceState.getString(RESULT_STATUS));
            mHeightButtonTextView.setText(savedInstanceState.getString(HEIGHT_LABEL));
            mWeightButtonTextView.setText(savedInstanceState.getString(WEIGHT_LABEL));
            mHeightLabelTextView.setText(savedInstanceState.getString(HEIGHT_CATEGORY));
            mWeightLabelTextView.setText(savedInstanceState.getString(WEIGHT_CATEGORY));
            mResultView.setVisibility(savedInstanceState.getInt(RESULT_LAYOUT));
            mKeypadView.setVisibility(savedInstanceState.getInt(KEYPAD_LAYOUT));
        }

    }

    private void initializing() {

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setElevation(NUMBER_ONE);
        getSupportActionBar().setCustomView(R.layout.title_bar);

        mWeightLabelTextView = findViewById(R.id.weight_label);
        mHeightLabelTextView = findViewById(R.id.height_label);

        View mWeightSelector = findViewById(R.id.weight_selector);
        mWeightSelector.setOnClickListener(this);

        View mHeightSelector = findViewById(R.id.height_selector);
        mHeightSelector.setOnClickListener(this);

        View mDropDownWeight = findViewById(R.id.down_arrow_weight);
        mDropDownWeight.setOnClickListener(this);

        mBmiResultTextView = findViewById(R.id.bmi_result);
        mBmiResultStatus = findViewById(R.id.bmi_status);

        View mDropDownHeight = findViewById(R.id.down_arrow_height);
        mDropDownHeight.setOnClickListener(this);

        mKeypadView = findViewById(R.id.constraint_layout_keyboard);
        mKeypadView.setVisibility(View.VISIBLE);
        mResultView = findViewById(R.id.constraint_layout_result);
        mResultView.setVisibility(View.GONE);

        mWeightButtonTextView = findViewById(R.id.weight_value);
        mWeightButtonTextView.setTextColor(getResources().getColor(R.color.textViewHoverColor));

        mHeightButtonTextView = findViewById(R.id.height_value);
        mHeightButtonTextView.setTextColor(getResources().getColor(R.color.textViewNormalColor));
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(RESULT, mBmiResultTextView.getText().toString());
        outState.putString(RESULT_STATUS, mBmiResultStatus.getText().toString());
        outState.putString(HEIGHT_LABEL, mHeightButtonTextView.getText().toString());
        outState.putString(WEIGHT_LABEL, mWeightButtonTextView.getText().toString());
        outState.putString(HEIGHT_CATEGORY, mHeightLabelTextView.getText().toString());
        outState.putString(WEIGHT_CATEGORY, mWeightLabelTextView.getText().toString());
        outState.putInt(RESULT_LAYOUT, mResultView.getVisibility());
        outState.putInt(KEYPAD_LAYOUT, mKeypadView.getVisibility());
    }

    public void changeToCentimeter(View v) {
        mHeightLabelTextView.setText(CENTIMETER);
        mDialogHeight.dismiss();
        mKeypadView.setVisibility(View.VISIBLE);
        mResultView.setVisibility(View.INVISIBLE);
    }

    public void changeToInch(View v) {
        mHeightLabelTextView.setText(INCH);
        mDialogHeight.dismiss();
        mKeypadView.setVisibility(View.VISIBLE);
        mResultView.setVisibility(View.INVISIBLE);
    }

    public void changeToMeter(View v) {
        mHeightLabelTextView.setText(METER);
        mDialogHeight.dismiss();
        mKeypadView.setVisibility(View.VISIBLE);
        mResultView.setVisibility(View.INVISIBLE);
    }

    public void changeToFeet(View v) {
        mHeightLabelTextView.setText(FEET);
        mDialogHeight.dismiss();
        mKeypadView.setVisibility(View.VISIBLE);
        mResultView.setVisibility(View.INVISIBLE);
    }

    public void changeToKilogram(View v) {
        mWeightLabelTextView.setText(KILOGRAM);
        mDialogWeight.dismiss();
        mKeypadView.setVisibility(View.VISIBLE);
        mResultView.setVisibility(View.INVISIBLE);
    }

    public void changeToPound(View v) {
        mWeightLabelTextView.setText(POUND);
        mDialogWeight.dismiss();
        mKeypadView.setVisibility(View.VISIBLE);
        mResultView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {
                        resultWeightTextView = resultWeightTextView.replace(STRING_ZERO, STRING_ONE);
                    } else {
                        resultWeightTextView += STRING_ONE;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {
                        resultHeightTextView = resultHeightTextView.replace(STRING_ZERO, STRING_ONE);
                    } else {
                        resultHeightTextView += STRING_ONE;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);
                }
                break;

            case R.id.btn_two:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {
                        resultWeightTextView = resultWeightTextView.replace(STRING_ZERO, STRING_TWO);
                    } else {
                        resultWeightTextView += STRING_TWO;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {
                        resultHeightTextView = resultHeightTextView.replace(STRING_ZERO, STRING_TWO);
                    } else {
                        resultHeightTextView += STRING_ONE;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);

                }
                break;

            case R.id.btn_three:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {
                        resultWeightTextView = resultWeightTextView.replace(STRING_ZERO, STRING_THREE);
                    } else {
                        resultWeightTextView += STRING_THREE;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {
                        resultHeightTextView = resultHeightTextView.replace(STRING_ZERO, STRING_THREE);
                    } else {
                        resultHeightTextView += STRING_THREE;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);

                }
                break;

            case R.id.btn_four:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {
                        resultWeightTextView = resultWeightTextView.replace(STRING_ZERO, STRING_FOUR);
                    } else {
                        resultWeightTextView += STRING_FOUR;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {
                        resultHeightTextView = resultHeightTextView.replace(STRING_ZERO, STRING_FOUR);
                    } else {
                        resultHeightTextView += STRING_FOUR;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);

                }
                break;

            case R.id.btn_five:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {
                        resultWeightTextView = resultWeightTextView.replace(STRING_ZERO, STRING_FIVE);
                    } else {
                        resultWeightTextView += STRING_FIVE;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {
                        resultHeightTextView = resultHeightTextView.replace(STRING_ZERO, STRING_FIVE);
                    } else {
                        resultHeightTextView += STRING_FIVE;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);

                }
                break;

            case R.id.btn_six:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {
                        resultWeightTextView = resultWeightTextView.replace(STRING_ZERO, STRING_SIX);
                    } else {
                        resultWeightTextView += STRING_SIX;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {
                        resultHeightTextView = resultHeightTextView.replace(STRING_ZERO, STRING_SIX);
                    } else {
                        resultHeightTextView += STRING_SIX;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);

                }
                break;

            case R.id.btn_seven:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {
                        resultWeightTextView = resultWeightTextView.replace(STRING_ZERO, STRING_SEVEN);
                    } else {
                        resultWeightTextView += STRING_SEVEN;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {
                        resultHeightTextView = resultHeightTextView.replace(STRING_ZERO, STRING_SEVEN);
                    } else {
                        resultHeightTextView += STRING_SEVEN;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);

                }
                break;

            case R.id.btn_eight:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {
                        resultWeightTextView = resultWeightTextView.replace(STRING_ZERO, STRING_EIGHT);
                    } else {
                        resultWeightTextView += STRING_EIGHT;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {
                        resultHeightTextView = resultHeightTextView.replace(STRING_ZERO, STRING_EIGHT);
                    } else {
                        resultHeightTextView += STRING_EIGHT;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);

                }
                break;

            case R.id.btn_nine:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {
                        resultWeightTextView = resultWeightTextView.replace(STRING_ZERO, STRING_NINE);
                    } else {
                        resultWeightTextView += STRING_NINE;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {
                        resultHeightTextView = resultHeightTextView.replace(STRING_ZERO, STRING_NINE);
                    } else {
                        resultHeightTextView += STRING_NINE;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);

                }
                break;

            case R.id.btn_zero:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.equals(STRING_ZERO)) {

                    } else {
                        resultWeightTextView += STRING_ZERO;
                        if (Double.parseDouble(resultWeightTextView) > TEXT_VIEW_LIMIT) {
                            resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                        mWeightButtonTextView.setText(resultWeightTextView);
                    }
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.equals(STRING_ZERO)) {

                    } else {
                        resultHeightTextView += STRING_ZERO;
                        if (Double.parseDouble(resultHeightTextView) > TEXT_VIEW_LIMIT) {
                            resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                            break;
                        }
                        mHeightButtonTextView.setText(resultHeightTextView);
                    }

                }
                break;

            case R.id.btn_go:
                String userHeight = mHeightButtonTextView.getText().toString().trim();
                String userHeightPreference = mHeightLabelTextView.getText().toString().trim();

                String userWeight = mWeightButtonTextView.getText().toString().trim();
                String userWeightPreference = mWeightLabelTextView.getText().toString().trim();

                calculateBMI(userHeight, userHeightPreference, userWeight, userWeightPreference);

                break;

            case R.id.btn_back_space:
                if (mWeightViewSelected) {
                    if ((resultWeightTextView != null) && (resultWeightTextView.length() > NUMBER_ZERO)) {
                        resultWeightTextView = resultWeightTextView.substring(NUMBER_ZERO, resultWeightTextView.length() - NUMBER_ONE);
                        mWeightButtonTextView.setText(resultWeightTextView);
                    }
                }
                if (!mWeightViewSelected) {
                    if ((resultHeightTextView != null) && (resultHeightTextView.length() > NUMBER_ZERO)) {
                        resultHeightTextView = resultHeightTextView.substring(NUMBER_ZERO, resultHeightTextView.length() - NUMBER_ONE);
                        mHeightButtonTextView.setText(resultHeightTextView);
                    }

                }
                break;

            case R.id.btn_decimal:
                if (mWeightViewSelected) {
                    if (resultWeightTextView.length() == NUMBER_ZERO) {
                        resultWeightTextView += STRING_DECIMAL_WITH_ZERO;
                    }
                    if (!resultWeightTextView.contains(DECIMAL)) {
                        resultWeightTextView += DECIMAL;
                    }
                    mWeightButtonTextView.setText(resultWeightTextView);
                }
                if (!mWeightViewSelected) {
                    if (resultHeightTextView.length() == NUMBER_ZERO) {
                        resultHeightTextView += STRING_DECIMAL_WITH_ZERO;
                    }

                    if (!resultHeightTextView.contains(DECIMAL)) {
                        resultHeightTextView += DECIMAL;
                    }
                    mHeightButtonTextView.setText(resultHeightTextView);
                }
                break;

            case R.id.btn_ac:
                if (mWeightViewSelected) {
                    mWeightButtonTextView.setText(STRING_ZERO);
                    resultWeightTextView = EMPTY_STRING;
                }
                if (!mWeightViewSelected) {
                    mHeightButtonTextView.setText(STRING_ZERO);
                    resultHeightTextView = EMPTY_STRING;
                }
                break;

            case R.id.down_arrow_weight:
                mDialogWeight = new Dialog(this);
                mDialogWeight.setContentView(R.layout.weight_category_container);
                mDialogWeight.show();
                break;

            case R.id.down_arrow_height:
                mDialogHeight = new Dialog(this);
                mDialogHeight.setContentView(R.layout.height_category_container);
                mDialogHeight.show();
                break;

            case R.id.weight_selector:
                mHeightButtonTextView.setTextColor(getResources().getColor(R.color.textViewNormalColor));
                mWeightButtonTextView.setTextColor(getResources().getColor(R.color.textViewHoverColor));
                mWeightViewSelected = true;
                mResultView.setVisibility(View.GONE);
                mKeypadView.setVisibility(View.VISIBLE);
                break;

            case R.id.height_selector:
                mWeightButtonTextView.setTextColor(getResources().getColor(R.color.textViewNormalColor));
                mHeightButtonTextView.setTextColor(getResources().getColor(R.color.textViewHoverColor));
                mWeightViewSelected = false;
                mResultView.setVisibility(View.GONE);
                mKeypadView.setVisibility(View.VISIBLE);
                break;

           /* case R.id.id_centimeter:
                mHeightLabelTextView.setText("Centimeter");
                mDialogHeight.dismiss();
                mKeypadView.setVisibility(View.VISIBLE);
                mResultView.setVisibility(View.INVISIBLE);
                break;

            case R.id.id_inch:
                mHeightLabelTextView.setText("Inch");
                mDialogHeight.dismiss();
                mKeypadView.setVisibility(View.VISIBLE);
                mResultView.setVisibility(View.INVISIBLE);
                break;

            case R.id.id_meter:
                mHeightLabelTextView.setText("Meter");
                mDialogHeight.dismiss();
                mKeypadView.setVisibility(View.VISIBLE);
                mResultView.setVisibility(View.INVISIBLE);
                break;

            case R.id.id_feet:
                mHeightLabelTextView.setText("Feet");
                mDialogHeight.dismiss();
                mKeypadView.setVisibility(View.VISIBLE);
                mResultView.setVisibility(View.INVISIBLE);
                break;

            case R.id.id_kilogram:
                mWeightLabelTextView.setText("Kilogram");
                mDialogWeight.dismiss();
                mKeypadView.setVisibility(View.VISIBLE);
                mResultView.setVisibility(View.INVISIBLE);
                break;
            case R.id.id_pound:
                mWeightLabelTextView.setText("Pound");
                mDialogWeight.dismiss();
                mKeypadView.setVisibility(View.VISIBLE);
                mResultView.setVisibility(View.INVISIBLE);
                break;
*/
            default:
                break;
        }

    }

    private void calculateBMI(String userHeight, String userHeightPreference, String userWeight, String userWeightPreference) {
        double userWeightInKilogram = NUMBER_ZERO;
        double userHeightInMeter = NUMBER_ZERO;
        if (userWeightPreference.equals(POUND)) {
            userWeightInKilogram = Double.parseDouble(userWeight) * KILOGRAM_CONSTANT;
        }
        if (userWeightPreference.equals(KILOGRAM)) {
            userWeightInKilogram = Double.parseDouble(userWeight);
        }

        switch (userHeightPreference) {
            case CENTIMETER:
                userHeightInMeter = Double.parseDouble(userHeight) / CENTIMETER_CONSTANT;
                break;
            case METER:
                userHeightInMeter = Double.parseDouble(userHeight);
                break;

            case FEET:
                userHeightInMeter = Double.parseDouble(userHeight) * FEET_CONSTANT;
                break;

            case INCH:
                userHeightInMeter = Double.parseDouble(userHeight) * INCH_CONSTANT;
                break;
        }

        double result = (userWeightInKilogram) / (Math.pow(userHeightInMeter, 2));
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String convertedResult = decimalFormat.format(result);

        if (result > BMI_LIMIT_MINIMUM && result < BMI_LIMIT_MAXIMUM) {

            if (result > BMI_UNDERWEIGHT_MIN_LIMIT && result < BMI_UNDERWEIGHT_MAX_LIMIT) {
                mBmiResultStatus.setText(getResources().getText(R.string.underWeight));
                mBmiResultStatus.setTextColor(getResources().getColor(R.color.underWeight));
            } else if (result > BMI_NORMAL_MIN_LIMIT && result < BMI_NORMAL_MAX_LIMIT) {
                mBmiResultStatus.setText(getResources().getText(R.string.normal));
                mBmiResultStatus.setTextColor(getResources().getColor(R.color.normal));
            } else if (result > BMI_OVERWEIGHT_MIN_LIMIT && result < BMI_OVERWEIGHT_MAX_LIMIT) {
                mBmiResultStatus.setText(getResources().getText(R.string.over_weight));
                mBmiResultStatus.setTextColor(getResources().getColor(R.color.overWeight));
            }

            mKeypadView.setVisibility(View.GONE);
            mResultView.setVisibility(View.VISIBLE);
            mBmiResultTextView.setText(convertedResult);
        } else {
            Toast.makeText(this, "Invalid BMI! Please check your input", Toast.LENGTH_LONG).show();
        }
    }

}
