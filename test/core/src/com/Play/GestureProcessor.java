package com.Play;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.objects.Object;

/**
 * Created by Suleman on 11/14/2015.
 */
public class GestureProcessor implements GestureDetector.GestureListener {

    Object object;

    public void setObject(Object object){
        this.object = object;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
        return false;
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
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
            if (deltaY < 0 && object.body.getAngle()<1.5708f) {
                object.body.setTransform(object.body.getPosition(),object.body.getAngle()+0.1f);
            }
            if (deltaY > 0 && object.body.getAngle()>0){
                object.body.setTransform(object.body.getPosition(),object.body.getAngle()-0.1f);
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
}
