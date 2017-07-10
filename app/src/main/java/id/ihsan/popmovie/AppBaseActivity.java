package id.ihsan.popmovie;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public abstract class AppBaseActivity extends AppCompatActivity {

    protected ProgressDialog dialog;

    abstract public void findViews();

    abstract public void initViews();

    abstract public void initListeners();

    protected void showDialog() {
        if (dialog == null) {
            dialog = new ProgressDialog(this);
            dialog.setMessage(getString(R.string.progress_dialog_message));
            dialog.setCancelable(false);
        }
        if (!dialog.isShowing())
            dialog.show();
    }

    protected void dismissDialog() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }
}
