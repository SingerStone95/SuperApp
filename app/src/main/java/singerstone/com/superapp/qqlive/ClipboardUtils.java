package singerstone.com.superapp.qqlive;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * @Description:
 * @Author: yogachen
 * @Time: 2018/9/17 15:28
 */
public class ClipboardUtils {
    /**
     * 获取当前剪贴板的文本
     *
     * @param context
     * @return
     */
    public static String getClipboardText(Context context) {
        if (context == null) {
            return "";
        }
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData clipRead = clipboard.getPrimaryClip();
        if (clipRead == null || clipRead.getItemCount() == 0)
            return "";
        String text = clipRead.getItemAt(0).getText().toString();
        return text;
    }


    /**
     * 向剪贴板写入文本
     *
     * @param context
     * @param text    可以是空串，不可是NULL
     */
    public static void writeTextToClipboard(Context context, String text) {
        if (context == null || text == null) {
            return;
        }
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        //写文本
        ClipData clipWrite = ClipData.newPlainText("txvideo", text);
        clipboard.setPrimaryClip(clipWrite);
    }

}
