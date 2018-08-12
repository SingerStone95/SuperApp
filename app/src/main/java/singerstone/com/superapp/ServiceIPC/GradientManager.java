package singerstone.com.superapp.ServiceIPC;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Point;
import android.graphics.Shader;

import java.util.Random;


public class GradientManager {
    private Random mRandom = new Random();
    private Context mContext;
    private Point mSize;

    public GradientManager(Context context, Point size) {
        this.mContext = context;
        this.mSize = size;
    }

    // Custom method to generate a random LinearGradient
    protected LinearGradient getFirstLinearGradient() {
        LinearGradient gradient = new LinearGradient(
                0,
                0,
                mSize.x,
                mSize.y,
                getFirstColors(), // Colors to draw the gradient
                null, // No position defined
                Shader.TileMode.CLAMP // Shader tiling mode
        );
        return gradient;
    }


    protected int[] getFirstColors() {

        int[] colors = new int[2];
        colors[1] = Color.parseColor("#ffb48b");
        colors[0] = Color.parseColor("#ffdd00");
        return colors;
    }

    protected LinearGradient getSecondLinearGradient() {
        LinearGradient gradient = new LinearGradient(
                0,
                0,
                mSize.x,
                mSize.y,
                getSecondColors(), // Colors to draw the gradient
                null, // No position defined
                Shader.TileMode.CLAMP // Shader tiling mode
        );
        return gradient;
    }


    protected int[] getSecondColors() {

        int[] colors = new int[2];
        colors[0] = Color.parseColor("#b3dbd3");
        colors[1] = Color.parseColor("#d2cdc2");
        return colors;
    }

    protected LinearGradient getThirdLinearGradient() {
        LinearGradient gradient = new LinearGradient(
                0,
                0,
                mSize.x,
                mSize.y,
                getThirdColors(), // Colors to draw the gradient
                null, // No position defined
                Shader.TileMode.CLAMP // Shader tiling mode
        );
        return gradient;
    }


    protected int[] getThirdColors() {

        int[] colors = new int[2];
        colors[0] = Color.parseColor("#f29a57");
        colors[1] = Color.parseColor("#f67474");
        return colors;
    }
}
