package com.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.objects.AssetLoader;

/**
 * Created by programmercore on 11/11/15.
 */
public class HelpScreen implements Screen {

    Game game;
    Texture img;
    Stage stage;

    AssetLoader assetLoader;

    public HelpScreen(Game game, AssetLoader assetLoader){
        this.game = game;
        this.assetLoader = assetLoader;
    }

    @Override
    public void show(){
        stage = new Stage();
        stage.act();
        img = new Texture(Gdx.files.internal(""));
        TextureRegion imgRegion = new TextureRegion(img, 0, 0, 0, 0);
        Image image = new Image(imgRegion);
        stage.addActor(image);


    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isTouched())
            game.setScreen(new MenuScreen(game, assetLoader));

        stage.draw();
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

