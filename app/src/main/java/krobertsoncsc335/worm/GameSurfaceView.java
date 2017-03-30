package krobertsoncsc335.worm;

/**
 * Created by Kevron on 2/22/2017.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

public class GameSurfaceView extends SurfaceView implements Runnable {
    private final GameActivity gameActivity;
    boolean isRunning = false;     // true when activity is active and running
    private Thread thread = null;  // the thread that's doing the drawing.
    // note that this is NOT the main UI thread
    // we spawned this thread
    private WormSprite worm = new WormSprite();
    private Bitmap bugBitmap;
    private int bugX, bugY;
    Random newBugPosition = new Random();


    public GameSurfaceView(Context context) {
        super(context);
        gameActivity = (GameActivity)context;
        //bugBitmap = DisplayAdvisor.loadBitmap(gameActivity.getResources(), R.drawable.bug);
        bugX = (int)(50 * DisplayAdvisor.scaleX);
        bugY = (int)(50 * DisplayAdvisor.scaleY);
        bugBitmap = DisplayAdvisor.loadScaledToIdeal(gameActivity.getResources(), (int)(100* DisplayAdvisor.scaleX), (int)(100 * DisplayAdvisor.scaleY), R.drawable.bug);

    }

    public void run() {
        while (isRunning) {
            // Get the canvas
            SurfaceHolder surfaceHolder = getHolder();
            if (!surfaceHolder.getSurface().isValid()) {
                continue;
            }
            Canvas canvas = surfaceHolder.lockCanvas();

            worm.updatePosition();

            if(worm.checkIfWormTouchedItself()){
                gameActivity.showGameResult(worm.body.size());
            }
            worm.checkIfAteBug(bugX, bugY);
            if(worm.isBugCollision){
                canvas.drawBitmap(bugBitmap, newBugPosition.nextInt(DisplayAdvisor.maxX),
                        newBugPosition.nextInt(DisplayAdvisor.maxY), null);
            }


            if(gameActivity.wasTouched){
                Point p = gameActivity.getTouch();
                worm.handleTouch(p);
            }


            drawEverything(canvas);


            // We're going to be doing a lot more things here.

            // Display our canvas
            surfaceHolder.unlockCanvasAndPost(canvas);

            try {
                Thread.sleep(175);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void drawEverything(Canvas canvas) {
        canvas.drawARGB(255, 0, 183, 255);
        worm.draw(canvas);
        canvas.drawBitmap(bugBitmap, 75, 75, null);
    }

    public void onResume() {
        // We've become active.  Create a thread
        // and start drawing.
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public void onPause() {
        // We're no longer active.
        isRunning = false;  // This will stop the loop in run and thread will end.
        while (thread != null) {
            try {
                thread.join();  // Wait for thread to end.
                thread = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void moveBug(int bugX, int bugY){
        Canvas canvas = new Canvas();
        bugX = newBugPosition.nextInt(DisplayAdvisor.maxX);
        bugY = newBugPosition.nextInt(DisplayAdvisor.maxY);

    }





}
