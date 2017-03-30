package krobertsoncsc335.worm;

import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayList;


/**
 * Created by Kevron on 3/6/2017.
 */

public class WormSprite {

    public ArrayList<CircleSprite> body;
    public boolean isBugCollision;




    public WormSprite(){

        body = new ArrayList<CircleSprite>();
        CircleSprite circle1 = new CircleSprite();
        CircleSprite circle2 = new CircleSprite();
        CircleSprite circle3 = new CircleSprite();

        int x = (int) (100 * DisplayAdvisor.scaleX);
        int y = (int) (100 * DisplayAdvisor.scaleY);

        circle1.setPosition(new Point(x,y));
        circle2.setPosition(new Point(x, y + 2* circle2.getRadius()));
        circle3.setPosition(new Point(x, y + 4* circle2.getRadius()));
        body.add(circle1);
        body.add(circle2);
        body.add(circle3);
    }

    public void draw(Canvas canvas){
        for (CircleSprite c: body){
            c.draw(canvas);
        }
    }

    public void handleTouch(Point p){
        body.get(0).handleTouch(p); //head of worm
    }

    public void updatePosition(){
        Point positionForReuse = body.get(0).getPosition();
        body.get(0).updatePosition();

        for(int i=1; i<body.size(); i++){
            Point temp = body.get(i).getPosition();
            body.get(i).setPosition(positionForReuse);
            positionForReuse = temp;
        }

        if(isBugCollision){
            isBugCollision = false;
            CircleSprite c = new CircleSprite();
            c.setPosition(positionForReuse);
            body.add(c);
        }
    }

    public void checkIfAteBug(int bugX, int bugY) {
        double dist = Math.sqrt(
                Math.pow(body.get(0).curX - bugX, 2) + Math.pow(body.get(0).curY - bugY, 2));
        if(dist < 2* body.get(0).getRadius()){
            isBugCollision = true;

        }
        else{
            isBugCollision = false;
        }
    }


    public boolean checkIfWormTouchedItself(){

        for(int i=1; i<body.size(); i++){
            if(body.get(i).getPosition().equals(body.get(0).getPosition())){
                return true;

            }
        }
        return false;
    }






}
