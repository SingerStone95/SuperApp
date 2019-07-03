package singerstone.com.superapp.upcoming;

public interface IComingSoonItemAnimation {
    void changeToBig();

    void changeToMid();

    void changeToSmall();

    /**
     * 动画进度回调，千万别做耗时操作
     * @param callback
     */
    void registerAnimationEndListener(AnimationEndCallback callback);

    int STATE_SMALL = -1;
    int STATE_MID = 0;
    int STATE_BIG = 1;

}
