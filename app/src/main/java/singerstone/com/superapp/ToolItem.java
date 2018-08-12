package singerstone.com.superapp;

/**
 * Created by chenbinhao on 2017/7/5.
 * YY:909075276
 */

public class ToolItem {
    private int resourseId;
    private String text;

    public ToolItem(int resourseId,String text){
        this.resourseId=resourseId;
        this.text=text;
    }
    public int getResourseId() {
        return resourseId;
    }

    public void setResourseId(int resourseId) {
        this.resourseId = resourseId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
