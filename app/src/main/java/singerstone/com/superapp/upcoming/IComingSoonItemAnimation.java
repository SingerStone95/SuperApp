package singerstone.com.superapp.upcoming;

public interface IComingSoonItemAnimation {
    void changeToBig();

    void changeToMid();

    void changeToSmall();

    int STATE_SMALL = -1;
    int STATE_MID = 0;
    int STATE_BIG = 1;

}
