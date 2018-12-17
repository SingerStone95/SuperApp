package singerstone.com.superapp.Dialog;

/**
 * author : yogachen
 * date   : 2018/12/14下午3:38
 * desc   :
 */
public class DialogChooseItem implements IDialogItem {
    public CharSequence mText;
    public FlexibleFragmentDialog.OnDialogItemClickListener mClickListener;

    @Override
    public CharSequence getText() {
        return mText;
    }

    @Override
    public FlexibleFragmentDialog.OnDialogItemClickListener getClickListener() {
        return mClickListener;
    }
}
