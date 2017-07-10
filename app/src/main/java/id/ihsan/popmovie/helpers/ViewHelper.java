package id.ihsan.popmovie.helpers;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class ViewHelper {

    public static void hideSoftKeyboard(Context context) {
        // Check if no view has focus:
        View view = ((Activity) context).getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Hides soft keyboard.
     *
     * @param editText EditText which has focus
     */
    public static void hideSoftKeyboard(Context context, EditText editText) {
        if (editText == null)
            return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    /**
     * Shows soft keyboard.
     *
     * @param editText EditText which has focus
     */
    public static void showSoftKeyboard(Context context, EditText editText) {
        if (editText == null)
            return;
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Service.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);
    }

    public static void showToast(Context context, String message) {
        if (!StringHelper.isEmptyString(message)) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public static TextInputLayout getTextInputLayout(@NonNull EditText editText) {
        View currentView = editText;
        for (int i = 0; i < 2; i++) {
            ViewParent parent = currentView.getParent();
            if (parent instanceof TextInputLayout) {
                return (TextInputLayout) parent;
            } else {
                currentView = (View) parent;
            }
        }
        return (TextInputLayout) editText.getParentForAccessibility();
    }
}
