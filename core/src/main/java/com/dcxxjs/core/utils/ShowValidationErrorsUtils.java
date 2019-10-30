package com.dcxxjs.core.utils;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.mobsandgeeks.saripaar.ValidationError;

import java.util.List;

public class ShowValidationErrorsUtils {
    /**
     * 显示验证错误代码
     * @param activity
     * @param errors
     */
    public  static  void ShowValidationErrors(Activity activity, List<ValidationError> errors)
    {
        for (ValidationError error : errors) {
            View view = error.getView();

            String message = error.getCollatedErrorMessage(activity);
            Log.d("出现错误",message);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            }
        }
    }
}
