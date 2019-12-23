package com.Play;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.objects.Canon;

/**
 * Created by Suleman on 11/14/2015.
 */
public class GestureProcessor implements GestureDetector.GestureListener {

    Canon object;

    public void setObject(Canon object){
        this.object = object;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
        if (count >1){
            object.increaseFuel();
        }
        return false;
    }

    @Override
    public boolean longPress(float x, float y) {
        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {
        if (x > (object.rect.x-object.rect.width/2)*100 && x <(object.rect.x + object.rect.width/2)*100) {
            if (deltaY < 0 && object.body.getAngle()< Math.PI/2) {
                object.body.setTransform(object.body.getPosition(),object.body.getAngle()+0.1f);
            }
            else if (object.body.getAngle()> Math.PI){
                object.body.setTransform(object.body.getPosition(),(float) (Math.PI/2)-0.3f);
            }
            if (deltaY > 0 && object.body.getAngle()>0){
                object.body.setTransform(object.body.getPosition(),object.body.getAngle()-0.1f);
            }
            else if (object.body.getAngle()< 0){
                object.body.setTransform(object.body.getPosition(),0);
            }
        }

        return false;
    }

    @Override
    public boolean panStop(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean zoom(float initialDistance, float distance) {
        return false;
    }

    @Override
    public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
        return false;
    }

    @Override
    public void pinchStop() {
        throw new RuntimeException("what is this");
    }
}
