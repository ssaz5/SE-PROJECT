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
public class CreditsScreen implements Screen {

    Game game;
    SpriteBatch batch;
    Texture img;
    float x;
    float y;
    Stage stage;
    Label label, label1, label2, label3;
    Container labelContainer, labelContainer1, labelContainer2, labelContainer3;
    Label.LabelStyle labelstyle;
    BitmapFont font;

    public CreditsScreen(Game game){
        this.game = game;
    }

    @Override
    public void show(){
        batch = new SpriteBatch();
        img = new Texture(Gdx.files.internal("Main screen/Background.jpg"));
        TextureRegion imgRegion = new TextureRegion(img, 0, 0, 1280, 914);

        Image background = new Image(imgRegion);
        background.scaleBy(1.5f, 1.5f);

        stage = new Stage();

        font = new BitmapFont(Gdx.files.internal("Fonts//PoorRichard.fnt"),false);
        labelstyle = new Label.LabelStyle(font, Color.WHITE);

        label = new Label("PRODUCED BY:-", labelstyle);
        labelContainer = new Container(label);
        labelContainer.setTransform(true);
        labelContainer.setScale(4f);
        labelContainer.setRotation(90f);
        labelContainer.setPosition(labelContainer.getPrefWidth() / 2 + 10, Gdx.graphics.getHeight() / 2 - labelContainer.getPrefHeight() / 2);

        label1 = new Label("Syed Suleman Abbas Zaidi", labelstyle);
        labelContainer1 = new Container(label1);
        labelContainer1.setTransform(true);
        labelContainer1.setScale(2.5f);
        labelContainer1.setRotation(90f);
        labelContainer1.setPosition(labelContainer1.getPrefWidth() / 2 + 810, Gdx.graphics.getHeight() / 2 - labelContainer1.getPrefHeight() / 2);

        label2 = new Label("Syed Mohammad Taha Zaidi", labelstyle);
        labelContainer2 = new Container(label2);
        labelContainer2.setTransform(true);
        labelContainer2.setScale(2.5f);
        labelContainer2.setRotation(90f);
        labelContainer2.setPosition(labelContainer2.getPrefWidth() / 2 + 910, Gdx.graphics.getHeight() / 2 - labelContainer2.getPrefHeight() / 2);

        label3 = new Label("Asjad Sohail", labelstyle);
        labelContainer3 = new Container(label3);
        labelContainer3.setTransform(true);
        labelContainer3.setScale(2.5f);
        labelContainer3.setRotation(90f);
        labelContainer3.setPosition(labelContainer3.getPrefWidth() / 2 + 1110, Gdx.graphics.getHeight() / 2 - labelContainer3.getPrefHeight() / 2);

        stage.act();

        stage.addActor(background);
        stage.addActor(labelContainer);
        stage.addActor(labelContainer1);
        stage.addActor(labelContainer2);
        stage.addActor(labelContainer3);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isTouched())
            game.setScreen(new MenuScreen(game));

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
