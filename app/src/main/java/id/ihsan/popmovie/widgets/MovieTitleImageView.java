package id.ihsan.popmovie.widgets;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @author Ihsan Helmi Faisal <ihsan.helmi@ovo.id>
 * @since 2017.10.07
 */
public class MovieTitleImageView extends AppCompatImageView {

    public MovieTitleImageView(Context context) {
        super(context);
    }

    public MovieTitleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieTitleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        setMeasuredDimension(width, (int) (width * 0.55f));
    }
}
