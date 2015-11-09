package com.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

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


    public GameScreen(Game game){
        this.game = game;

    }

    @Override
    public void show() {
        world = new World(new Vector2(0,-10f),true );
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());


        BodyDef staticBodyDef = new BodyDef();
        staticBodyDef.position.set(0,-720);


        Body staticBody = world.createBody(staticBodyDef);
        PolygonShape ground = new PolygonShape();
        ground.setAsBox(camera.viewportWidth, 2 );
        staticBody.createFixture(ground, 0.0f);
        ground.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        if (Gdx.input.isTouched()){
           count +=1;
        }
        if (count > 10){
            game.setScreen(new MenuScreen(game));

        }

        camera.update();
        world.step(1 / 60f, 6, 2);
        renderer.render(world, camera.combined);

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
