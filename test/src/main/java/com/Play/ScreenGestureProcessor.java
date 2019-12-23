package com.Play;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.game.screen.MenuScreen;
import com.objects.AssetLoader;

/**
 * Created by programmercore on 11/15/15.
 */
public class ScreenGestureProcessor implements GestureDetector.GestureListener{

    OrthographicCamera camera;
    Game game;
    AssetLoader assetLoader;
    SpriteBatch spriteBatch;

    public void setGame(Game game){this.game = game;}

    public void setCamera(OrthographicCamera camera){
        this.camera = camera;
    }

    public void setAssetLoader(AssetLoader assetLoader){
        this.assetLoader = assetLoader;
    }

    public void setSpriteBatch(SpriteBatch spriteBatch){this.spriteBatch = spriteBatch;}

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

        game.setScreen(new MenuScreen(game,assetLoader));

        return false;
    }

    @Override
    public boolean fling(float velocityX, float velocityY, int button) {
        return false;
    }

    @Override
    public boolean pan(float x, float y, float deltaX, float deltaY) {

        if (camera.position.x <3750){
            if (deltaX < 0) {
                camera.translate(50, 0);
            }
        }
        if (camera.position.x > 1250){
            if (deltaX > 0) {
                camera.translate(-50, 0);
            }
            camera.update();
            spriteBatch.setProjectionMatrix(camera.combined);
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
