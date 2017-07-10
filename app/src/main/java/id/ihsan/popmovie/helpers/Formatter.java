package id.ihsan.popmovie.helpers;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class Formatter {

    public static String formatDoubleToString(double value) {
        DecimalFormat decimalFormat = new DecimalFormat("0.#");
        return decimalFormat.format(value);
    }

    public static String parseYear(String date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        try {
            Date d = format.parse(date);
            SimpleDateFormat df = new SimpleDateFormat("yyyy", Locale.US);
            return df.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }
}
