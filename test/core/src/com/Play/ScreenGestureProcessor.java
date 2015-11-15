package com.Play;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by programmercore on 11/15/15.
 */
public class ScreenGestureProcessor implements GestureDetector.GestureListener{

    OrthographicCamera camera;

    public void setCamera(OrthographicCamera camera){
        this.camera = camera;
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
        if (deltaY < 0) {
            camera.position.set(Gdx.graphics.getWidth() + 0.01f, Gdx.graphics.getHeight(), 0);
        }
        if (deltaY > 0){
            camera.position.set(Gdx.graphics.getWidth() - 0.01f, Gdx.graphics.getHeight(), 0);
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
