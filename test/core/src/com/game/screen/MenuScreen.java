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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

/**
 * Created by Suleman on 11/8/2015.
 */
public class MenuScreen implements Screen {
    Game game;

    SpriteBatch batch;
    Texture img;
    float x;
    float y;
    Stage stage;
    Label label;
    Container labelContainer;
    Label.LabelStyle labelstyle;
    BitmapFont font;


    TextureAtlas buttonatlas;
    TextButton.TextButtonStyle buttonstyle;
    TextButton playButton;
    Skin buttonSkin;
    Container playButtonC;

    public MenuScreen(Game game){
        this.game = game;

    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        x = 100;
        y=100;
        stage = new Stage();

        font = new BitmapFont(Gdx.files.internal("Fonts//PoorRichard.fnt"),false);
        labelstyle = new Label.LabelStyle(font, Color.FIREBRICK);

        label = new Label("ACIDIC 2D:", labelstyle);

        labelContainer = new Container(label);
        labelContainer.setTransform(true);
        labelContainer.setScale(5f);

        labelContainer.setRotation(90f);
        labelContainer.setPosition(labelContainer.getPrefWidth() / 2 + 10, Gdx.graphics.getHeight() / 2 - labelContainer.getPrefHeight() / 2);

        stage.act();
        stage.addActor(labelContainer);

        buttonatlas = new TextureAtlas("Buttons//Menu Button Pack//MenuButton.pack");
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonatlas);
        buttonstyle = new TextButton.TextButtonStyle();
        buttonstyle.up = buttonSkin.getDrawable("Button");
        buttonstyle.over = buttonSkin.getDrawable("Buttonpressed");
        buttonstyle.down = buttonSkin.getDrawable("Buttonpressed");
        buttonstyle.font = font;
        playButton = new TextButton("PLAY", buttonstyle);
        playButtonC = new Container(playButton);
        playButtonC.setTransform(true);
        playButtonC.setRotation(90);
        playButtonC.setScale(2);
        playButtonC.setPosition(Gdx.graphics.getWidth() / 2 - playButtonC.getPrefWidth() / 2, Gdx.graphics.getHeight() / 2 - playButtonC.getPrefHeight() / 2);


        stage.addActor(playButtonC);
        Gdx.input.setInputProcessor(stage);

        playButtonC.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                label.setText("ACIDIC 2D");
                game.setScreen(new GameScreen(game));
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);




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
    public void dispose() {

    }
}
