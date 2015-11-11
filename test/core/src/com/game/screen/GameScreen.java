package com.game.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
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

    AssetLoader Assets;

    SpriteBatch sb;

    Object spaceship;

    public GameScreen(Game game){
        this.game = game;

    }

    @Override
    public void show() {
        world = new World(new Vector2(0,-10f),true );
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

        sb = new SpriteBatch();
        spaceship = new AnimatedObject("ship action2/Spaceship.png",1,3,500,500);


        BodyDef staticBodyDef = new BodyDef();
        staticBodyDef.position.set(0, -720);


        Body staticBody = world.createBody(staticBodyDef);
        PolygonShape ground = new PolygonShape();
        ground.setAsBox(camera.viewportWidth, 2);
        staticBody.createFixture(ground, 1.0f);
        ground.dispose();
//
//        BodyDef dynamicBodyDef = new BodyDef();
//        dynamicBodyDef.type = BodyDef.BodyType.DynamicBody;
//        //dynamicBodyDef.position.set(spaceship.rect.x,spaceship.rect.y);
//        dynamicBodyDef.position.set(0,720);
//        Body dynamicBody = world.createBody(dynamicBodyDef);
//        CircleShape shape = new CircleShape();
//        shape.setRadius(((spaceship.rect.height>spaceship.rect.width)?spaceship.rect.width:spaceship.rect.height)/2);
//        FixtureDef dynamicFixtureDef = new FixtureDef();
//        dynamicFixtureDef.shape = shape;
//        dynamicFixtureDef.density = 0.5f;
//        dynamicFixtureDef.friction = 0.4f;
//        dynamicFixtureDef.restitution = 1f;
//
//
//
//        Fixture dynamicFixture = dynamicBody.createFixture(dynamicFixtureDef);
//        shape.dispose();
//
//        dynamicBody.setUserData(spaceship);
        spaceship.setBodyDynamic(world,0.3f,0.4f,0.5f);



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
        Array<Body> bodies = new Array<Body>();
        world.getBodies(bodies);

        for (Body b : bodies) {
            AnimatedObject e = (AnimatedObject) b.getUserData();

            if (e != null) {
                // Update the entities/sprites position and angle
                e.setPosition(b.getPosition().x, b.getPosition().y);
                // We need to convert our angle from radians to degrees
                e.setRotation(MathUtils.radiansToDegrees * b.getAngle());
            }

        }

        sb.begin();
        sb.setProjectionMatrix(camera.combined);
        spaceship.draw(sb);
        sb.end();

        camera.update();
        world.step(1 / 60f, 6, 2);
        //renderer.render(world, camera.combined);

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
