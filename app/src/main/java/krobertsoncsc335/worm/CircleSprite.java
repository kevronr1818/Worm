package krobertsoncsc335.worm;

/**
 * Created by Kevron on 2/24/2017.
 */
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

import java.util.Random;

public class CircleSprite {
    int curX = 100;
    int curY = 100;
    int radius = 50;
    Paint paint;
    Random random;
    Direction direction = Direction.EAST;
    //Direction direction;
    private int speedX = 20;
    private int speedY = 0;
    private int speed = 100;



    public CircleSprite() {
        paint = new Paint();
        paint.setARGB(255, 255, 0, 0);
        random = new Random(System.currentTimeMillis());
        radius = (int) (50*DisplayAdvisor.scaleX);
    }

    public void draw(Canvas canvas) {
        //canvas.drawARGB(255, 0, 183, 255);
        canvas.drawCircle(curX, curY, radius, paint);
    }

    public void updatePosition() {
        switch(direction) {
            case NORTH:
                curY -= speed;
                break;
            case EAST:
                curX += speed;
                break;
            case SOUTH:
                curY += speed;
                break;
            case WEST:
                curX -= speed;
                break;

        }

        checkSpeeds();
    }

    private void checkSpeeds() {
        switch (direction) {
            case NORTH:
                if (curY < radius) direction = Direction.EAST;
                break;
            case EAST:
                if (curX > DisplayAdvisor.maxX - radius) direction = Direction.SOUTH;
                break;
            case SOUTH:
                if (curY > DisplayAdvisor.maxY - radius) direction = Direction.WEST;
                break;
            case WEST:
                if (curX < radius) direction = Direction.NORTH;
                break;
        }
    }

    public void setPosition(Point p){
        curX = p.x;
        curY = p.y;

    }

    public void handleTouch(Point p) {
        switch (direction){
            case NORTH:
            case SOUTH:
                if (p.x < curX){
                    direction = Direction.WEST;
                }
                else{
                    direction = Direction.EAST;
                }
                break;
            case EAST:
            case WEST:
                if (p.y < curY){
                    direction = Direction.NORTH;
                }
                else{
                    direction = Direction.SOUTH;
                }
                break;
        }
    }

    public int getRadius() {
        return radius;
    }

    public Point getPosition() {
        return new Point(curX, curY);
    }
}

