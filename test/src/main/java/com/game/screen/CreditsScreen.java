package com.game.screen;

import com.Play.ScreenGestureProcessor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
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

    public AssetLoader assetLoader;

    public CreditsScreen(Game game, AssetLoader assetLoader){
        this.game = game;
        this.assetLoader = assetLoader;
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

        ScreenGestureProcessor gestureProcessor = new ScreenGestureProcessor();
        gestureProcessor.setCamera(camera);
        gestureProcessor.setAssetLoader(assetLoader);
        gestureProcessor.setGame(game);
        gestureProcessor.setSpriteBatch(batch);
        Gdx.input.setInputProcessor(new GestureDetector(gestureProcessor));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


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
