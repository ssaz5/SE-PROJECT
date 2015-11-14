package com.game.screen;

import com.Play.GestureProcessor;
import com.acidic.ui.Button;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.objects.*;
import com.objects.Object;

/**
 * Created by Suleman on 11/8/2015.
 */
public class GameScreen implements Screen{
    static final float WORLD_TO_BOX = 0.01f;
    static final float BOX_TO_WORLD = 100f;
    int count;
    Game game;
    World world;

    Box2DDebugRenderer renderer;
    OrthographicCamera camera;
    OrthographicCamera camera2;


    AssetLoader Assets;

    SpriteBatch sb;


    Object spaceship;
    Object wall;
    Object ground;
    Object Background;
    Object canon;


    Stage stage;

    BitmapFont font;











    boolean start = true;

    public GameScreen(Game game){
        this.game = game;

    }

    @Override
    public void show() {
        world = new World(new Vector2(0,-10f),true );
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(2500,1400);
        camera.position.set(1250, 700, 0);
        camera.update();
        camera.translate(-2000, 0);
        camera.update();
        camera2 = new OrthographicCamera(25,14);
        camera2.position.set(12.50f, 7f, 0);
        camera2.update();
        sb = new SpriteBatch();


        sb.setProjectionMatrix(camera.combined);


        spaceship = new AnimatedObject("ship action2/Spaceship.png",1,3,7f,10f,3f,2.4f);
        spaceship.setBodyDynamic(world, 0.3f, 0.4f, 0.5f);


//      wall = new Object("wall.png",1,1,-Gdx.graphics.getWidth()/2+100,-Gdx.graphics.getHeight()/2+100,0.2f,0.2f);


        wall = new Object("wall.png",1,1,1f,2f,2f,4f);
        wall.setBodyStatic(world, 0.5f);


        ground = new Object("wall.png",1,1,12.5f,-0.0f,25f,0.02f);
        ground.setBodyStatic(world,0.5f);

        Background = new Object("Main screen/MainScreen2.jpg",1,1,11f,7f,62f,14f);

        canon = new Canon("Canon gun/Canon.png",2,3,4.5f,2.5f,4.5f,2.0f);
        canon.setBodyStatic(world, 0.5f);
      //  canon.body.setTransform(canon.body.getPosition(),canon.body.getAngle()+0.7854f);

        stage = new Stage();
        font = new BitmapFont(Gdx.files.internal("Fonts/PoorRichard.fnt"),false);

        stage.act();

        Button AcidButton = new Button("Buttons/Menu Button Pack/AcidButton.pack",font, "Acid",1400f,180f,300f,300f);
        AcidButton.setStage(stage);

        Button PoisonButton = new Button("Buttons/Menu Button Pack/PoisonButton.pack",font, "Poison",1800f,180f,300f,300f);
        PoisonButton.setStage(stage);


        GestureProcessor listener = new GestureProcessor();
        listener.setObject(canon);
        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new GestureDetector(listener));
        Gdx.input.setInputProcessor(multiplexer);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (camera.position.x <2950 && start == true){
            camera.translate(10, 0);
            camera.update();
            sb.setProjectionMatrix(camera.combined);
        }
        else{
            start = false;
        }

        if (start==false && camera.position.x >1250){
            camera.translate(-10, 0);
            camera.update();
            sb.setProjectionMatrix(camera.combined);
        }

        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body b : bodies) {
            Object e = (Object) b.getUserData();

            if (e != null) {
                // Update the entities/sprites position and angle
                e.setPosition(b.getPosition().x, b.getPosition().y);
                // We need to convert our angle from radians to degrees
                e.setRotation(MathUtils.radiansToDegrees * b.getAngle());
            }

        }





        sb.begin();

        Background.draw(sb);
        spaceship.draw(sb);
        wall.draw(sb);
        ground.draw(sb);
        canon.draw(sb);
        sb.end();

        stage.draw();





        world.step(1 / 60f, 6, 2);
        renderer.render(world, camera2.combined);

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
