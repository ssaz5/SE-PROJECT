package com.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.objects.AssetLoader;

/**
 * Created by programmercore on 11/11/15.
 */
public class ScoreScreen implements Screen {

    Game game;
    SpriteBatch batch;
    Texture img;
    Stage stage;
    Label label;
    Container labelContainer;
    Label.LabelStyle labelstyle;
    BitmapFont font;
    TextureAtlas buttonatlas;
    TextButton.TextButtonStyle buttonstyle;
    Skin buttonSkin;

    public AssetLoader assetLoader;

    public ScoreScreen(Game game, AssetLoader assetLoader){
        this.game = game;
        this.assetLoader = assetLoader;
    }

    @Override
    public void show(){
        stage = new Stage();
        stage.act();

        batch = new SpriteBatch();
        img = new Texture(Gdx.files.internal("Main screen/Background.jpg"));
        TextureRegion imgRegion = new TextureRegion(img, 0, 0, 1280, 914);

        Image background = new Image(imgRegion);
        background.scaleBy(1.5f, 1.5f);

        font = new BitmapFont(Gdx.files.internal("Fonts//PoorRichard.fnt"),false);
        labelstyle = new Label.LabelStyle(font, Color.WHITE);

        label = new Label("High Scores", labelstyle);
        labelContainer = new Container(label);
        labelContainer.setTransform(true);
        labelContainer.setScale(4f);
        labelContainer.setRotation(90f);
        labelContainer.setPosition(labelContainer.getPrefWidth() / 2 + 10, Gdx.graphics.getHeight() / 2 - labelContainer.getPrefHeight() / 2);

        stage.addActor(background);

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

