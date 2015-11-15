package com.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.objects.Object;
/**
 * Created by programmercore on 11/11/15.
 */
public class CreditsScreen implements Screen {

    Game game;
    OrthographicCamera camera;
    Object background;
    SpriteBatch batch;
    Texture img;
    float x;
    float y;
    Stage stage;
    Label label, label1, label2, label3;
    Container labelContainer, labelContainer1, labelContainer2, labelContainer3, imageCont;
    Label.LabelStyle labelstyle;
    BitmapFont font;

    public CreditsScreen(Game game){
        this.game = game;
    }

    @Override
    public void show(){
        batch = new SpriteBatch();
        camera = new OrthographicCamera(2500,1400);
        camera.position.set(1250, 700, 0);
        camera.update();

        batch.setProjectionMatrix(camera.combined);


        img = new Texture(Gdx.files.internal("Main screen/Credits.jpg"));
        background = new Object(img, 1, 1, 25f ,7f, 14f, 50f);
        background.setRotation(90f);
     //   TextureRegion imgRegion = new TextureRegion(img, 0, 0, 800, 2860);

//        Image background = new Image(imgRegion);
  //      imageCont = new Container(background);
    //    imageCont.setTransform(true);
//        imageCont.setOrigin(imageCont.getWidth()/2,imageCont.getHeight()/2);
      //  imageCont.setPosition(0 + Gdx.graphics.getWidth(), 0);
       // imageCont.setRotation(90f);
//        background.scaleBy(1.5f, 1.5f);

       // stage = new Stage();
/*
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
*/
  //      stage.act();

    //    stage.addActor(imageCont);
 //       stage.addActor(labelContainer);
 //       stage.addActor(labelContainer1);
 //       stage.addActor(labelContainer2);
//        stage.addActor(labelContainer3);
      //  Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(Gdx.input.isTouched())
            game.setScreen(new MenuScreen(game));


        batch.begin();

        background.draw(batch);
        batch.end();
        //stage.draw();
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
