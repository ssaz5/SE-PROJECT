package com.game.screen;

import com.Play.GestureProcessor;
import com.Play.ScreenGestureProcessor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.objects.*;
import com.objects.Object;

/**
 * Created by programmercore on 11/11/15.
 */
public class HelpScreen implements Screen {

    Game game;
    SpriteBatch batch;
    Texture img;
   // Stage stage;
    OrthographicCamera camera;
    com.objects.Object background;

    public HelpScreen(Game game){
        this.game = game;
    }

    @Override
    public void show(){

        batch = new SpriteBatch();
        camera = new OrthographicCamera(2500,1400);
        camera.position.set(1250, 700, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        img = new Texture(Gdx.files.internal("Main screen/Help.png"));
        background = new Object(img, 1, 1, 25f ,7f, 14f, 50f);
        background.setRotation(90f);
/*
        stage = new Stage();
        stage.act();
        img = new Texture(Gdx.files.internal(""));
        TextureRegion imgRegion = new TextureRegion(img, 0, 0, 0, 0);
        Image image = new Image(imgRegion);
        stage.addActor(image);
*/
        ScreenGestureProcessor listener = new ScreenGestureProcessor();
        listener.setCamera(camera);
        InputMultiplexer multiplexer = new InputMultiplexer();
        //multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new GestureDetector(listener));
        Gdx.input.setInputProcessor(multiplexer);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    //    if(Gdx.input.isTouched())
    //        game.setScreen(new MenuScreen(game));


        batch.begin();

        background.draw(batch);
        batch.end();
 //       stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose(){

    }
}

