package singerstone.com.superapp.Dialog;


public interface IDialogItem {
    CharSequence getText();

    FlexibleFragmentDialog.OnDialogItemClickListener getClickListener();
}
