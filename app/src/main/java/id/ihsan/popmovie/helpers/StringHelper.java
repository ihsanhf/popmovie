package id.ihsan.popmovie.helpers;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class StringHelper {

    public static boolean isEmptyString(String text) {
        return text == null || text.isEmpty() && text.equals("");
    }
}
