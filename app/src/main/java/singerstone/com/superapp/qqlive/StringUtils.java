package singerstone.com.superapp.qqlive;


import androidx.annotation.Nullable;

public class StringUtils {

    public static boolean isEmpty(@Nullable CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
