package com.game.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.objects.*;
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
import com.objects.Object;

/**
 * Created by Suleman on 11/8/2015.
 */
public class MenuScreen implements Screen {
    Game game;

    SpriteBatch batch;
    OrthographicCamera camera;
    com.objects.Object background;
    Texture img;
    Stage stage;
    Label label;
    Container labelContainer;
    Label.LabelStyle labelstyle;
    BitmapFont font;
    TextureAtlas buttonatlas;
    TextButton.TextButtonStyle buttonstyle;
    Skin buttonSkin;

    TextButton playButton;
    TextButton helpButton;
    TextButton scoreButton;
    TextButton creditsButton;
    Container playButtonC;
    Container helpButtonC;
    Container scoreButtonC;
    Container creditsButtonC;

    public MenuScreen(Game game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera(2500,1400);
        camera.position.set(1250, 700, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        img = new Texture(Gdx.files.internal("Main screen//Background.jpg"));
//        TextureRegion imgRegion = new TextureRegion(img, 0, 0, 1600 , 900);

//        Image background = new Image(imgRegion);
//        background.scaleBy(Gdx.graphics.getWidth() / background.getImageWidth(), Gdx.graphics.getHeight() / background.getImageHeight());
        background = new Object(img, 1, 1, 12.5f ,7f, 14f, 25f);
        background.setRotation(90f);
        stage = new Stage();

        font = new BitmapFont(Gdx.files.internal("Fonts//PoorRichard.fnt"),false);
        labelstyle = new Label.LabelStyle(font, Color.WHITE);

        label = new Label("ACIDIC 2D", labelstyle);

        labelContainer = new Container(label);
        labelContainer.setTransform(true);
        labelContainer.setScale(5f);

        labelContainer.setRotation(90f);
        labelContainer.setPosition(labelContainer.getPrefWidth() / 2 + 10, Gdx.graphics.getHeight() / 2 - labelContainer.getPrefHeight() / 2);

        stage.act();

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

        scoreButton = new TextButton ("SCORE", buttonstyle);
        scoreButtonC = new Container(scoreButton);
        scoreButtonC.setTransform(true);
        scoreButtonC.setRotation(90);
        scoreButtonC.setScale(2);
        scoreButtonC.setPosition(Gdx.graphics.getWidth() / 2 - scoreButtonC.getPrefWidth() / 2 + 200, Gdx.graphics.getHeight() / 2 - scoreButtonC.getPrefHeight() / 2);

        helpButton = new TextButton ("HELP", buttonstyle);
        helpButtonC = new Container(helpButton);
        helpButtonC.setTransform(true);
        helpButtonC.setRotation(90);
        helpButtonC.setScale(2);
        helpButtonC.setPosition(Gdx.graphics.getWidth() / 2 - helpButtonC.getPrefWidth() / 2 + 400, Gdx.graphics.getHeight() / 2 - helpButtonC.getPrefHeight() / 2);

        creditsButton = new TextButton("CREDITS", buttonstyle);
        creditsButtonC = new Container(creditsButton);
        creditsButtonC.setTransform(true);
        creditsButtonC.setRotation(90);
        creditsButtonC.setScale(2);
        creditsButtonC.setPosition(Gdx.graphics.getWidth() / 2 - creditsButtonC.getPrefWidth() / 2 + 600, Gdx.graphics.getHeight() / 2 - creditsButtonC.getPrefHeight() / 2);

//        stage.addActor(background);
        stage.addActor(labelContainer);
        stage.addActor(playButtonC);
        stage.addActor(scoreButtonC);
        stage.addActor(helpButtonC);
        stage.addActor(creditsButtonC);
        Gdx.input.setInputProcessor(stage);

        playButtonC.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new GameScreen(game));
                return true;
            }
        });

        scoreButtonC.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new ScoreScreen(game));
                return true;
            }
        });

        helpButtonC.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                game.setScreen(new HelpScreen(game));
                return true;
            }
        });

        creditsButtonC.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                label.setText("ACIDIC 2D");

                game.setScreen(new CreditsScreen(game));
                return true;
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();

        background.draw(batch);
        batch.end();
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
