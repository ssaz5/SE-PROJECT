package com.game.screen;

import com.Enums.CatType;
import com.Enums.ObjectType;
import com.Play.GestureProcessor;
import com.acidic.ui.Button;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.objects.*;
import com.objects.Object;
import com.Enums.ShotType;
import java.util.Random;



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
    private long lastSpawnTime;

    public  AssetLoader Assets;

    SpriteBatch sb;


    Object spaceship;
    Object wall;
    Object ground;
    Object portal;
    Object Background;
    Canon canon;

    Array<Object> toDispose;

    Stage stage;

    BitmapFont font;


    Array<Cat> Katz;
    Array<Cat> setToFall;


    Array<Shot> shots;

    float time = 0;
    float difficulty = 0;

    AssetLoader assetLoader;

    boolean start = true;

    int scoreCount= 0;

    public GameScreen(Game game, AssetLoader assetLoader){
        this.game = game;
        this.assetLoader = assetLoader;

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
        Random randomGenerator = new Random();
        lastSpawnTime = TimeUtils.nanoTime();
        sb.setProjectionMatrix(camera.combined);


        toDispose = new Array<Object>();

        spaceship = new AnimatedObject("ship action2/Spaceship.png",1,3,33.5f,11f,4f,3.2f);
        spaceship.setBodyStatic(world, 0.3f);
        spaceship.setType(ObjectType.Solid);


//      wall = new Object("wall.png",1,1,-Gdx.graphics.getWidth()/2+100,-Gdx.graphics.getHeight()/2+100,0.2f,0.2f);


        wall = new Object("wall.png",1,1,1f,2f,2f,4f);
        wall.setBodyStatic(world, 0.5f);
        wall.setType(ObjectType.Solid);

        portal =  new Object(assetLoader.Portal, 1,1,13.5f,2f,23f,2f);
        portal.setBodyStatic(world, 1);
        portal.setType(ObjectType.Solid);

        ground = new Object("wall.png",1,1,12.5f,-0.0f,25f,0.02f);
        ground.setBodyStatic(world, 0.5f);
        ground.setType(ObjectType.Solid);

        Background = new Object("Main screen/MainScreen2.jpg",1,1,11f,7f,62f,14f);
        Background.setType(ObjectType.Empty);

        canon = new Canon("Canon gun/Canon.png",2,3,4.5f,2.5f,4.5f,2.0f);
        canon.setBodyStatic(world, 0.5f);
      //  canon.body.setTransform(canon.body.getPosition(),canon.body.getAngle()+0.7854f);
        canon.body.setActive(false);




        stage = new Stage(new FitViewport(2500f,1400f));
        font = new BitmapFont(Gdx.files.internal("Fonts/PoorRichard.fnt"),false);

        stage.act();

        Label.LabelStyle labelStyle;
        labelStyle = new Label.LabelStyle(font, com.badlogic.gdx.graphics.Color.FIREBRICK);
        final Label scoreLabel;
        scoreLabel = new Label("Score: " + scoreCount,labelStyle);
        final Container scoreLabelContainer = new Container(scoreLabel);
        scoreLabelContainer.setTransform(true);
        scoreLabelContainer.setOrigin(0f,0f);
        scoreLabelContainer.setPosition(250, 1350);
        scoreLabelContainer.scaleBy(2);

        stage.addActor(scoreLabelContainer);


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


        shots = new Array<Shot>();

        AcidButton.buttonContainer.addListener(new InputListener() {
            @Override
            public boolean  touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Shot shot = canon.fire(world, ShotType.AcidShot, assetLoader);
                if(shot != null) {
                    shots.add(shot);
                }
                return true;
            }


        });

        PoisonButton.buttonContainer.addListener(new InputListener() {
            @Override

            public boolean  touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Shot shot = canon.fire(world, ShotType.PoisonShot, assetLoader);
                if (shot != null) {
                    shots.add(shot);
                }
                return true;
            }
        });

        Katz = new Array<Cat>();
        setToFall = new Array<Cat>();


        world.setContactListener(new ContactListener() {

            @Override
            public void beginContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();
                if (fixtureA.getBody() != canon.body && fixtureB.getBody() != canon.body){
                    Object object =(Object) fixtureA.getBody().getUserData();
                    if (object.getObjectType() == ObjectType.Shot){
                        Shot shot = (Shot) object;
                        Object ob =(Object) fixtureB.getBody().getUserData();
                        boolean destroyShot=true;
                        if (ob.getObjectType() == ObjectType.Cat){
                            Cat cat = (Cat) ob;
                            if ( cat.catType == CatType.BushCat && shot.shotType == ShotType.PoisonShot || cat.catType == CatType.SpaceShipCat && shot.shotType == ShotType.AcidShot) {

                                if(!cat.isFalling) {
                                    setToFall.add(cat);
                                    scoreCount++;
                                    scoreLabel.setText("Score: "+scoreCount);
                                }
                                else{
                                    destroyShot = false;
                                }

                            }
                        }
                        if (destroyShot) {
                            shots.removeValue(shot, false);
                            toDispose.add(shot);
                        }
                    }
                    else if (object.getObjectType() == ObjectType.Cat){
                        Cat cat = (Cat) object;
                        Object ob =(Object) fixtureB.getBody().getUserData();
                        if(ob.getObjectType() != ObjectType.Shot && ob.getObjectType() != ObjectType.Cat && cat.isFalling ){
                            Katz.removeValue(cat,false);
                            toDispose.add(cat);
                        }
                    }
                    object =(Object) fixtureB.getBody().getUserData();
                    if( object.getObjectType() == ObjectType.Shot){
                        Shot shot = (Shot) object;
                        Object ob =(Object) fixtureA.getBody().getUserData();
                        boolean destroyShot = true;
                        if (ob.getObjectType() == ObjectType.Cat){
                            Cat cat = (Cat) ob;
                            if ( cat.catType == CatType.BushCat && shot.shotType == ShotType.PoisonShot || cat.catType == CatType.SpaceShipCat && shot.shotType == ShotType.AcidShot) {
                                if(!cat.isFalling) {
                                    setToFall.add(cat);
                                    scoreCount++;
                                    scoreLabel.setText("Score: "+scoreCount);
                                }
                                else{
                                    destroyShot = false;
                                }
                            }
                        }

                        if (destroyShot) {
                            shots.removeValue(shot, false);
                            toDispose.add(shot);
                        }
                    }
                    else if (object.getObjectType() == ObjectType.Cat){
                        Cat cat = (Cat) object;
                        Object ob =(Object) fixtureA.getBody().getUserData();
                        if(ob.getObjectType() != ObjectType.Shot && ob.getObjectType() != ObjectType.Cat && cat.isFalling ){
                            Katz.removeValue(cat,false);
                            toDispose.add(cat);
                        }
                    }


                }
            }

            @Override
            public void endContact(Contact contact) {
                Fixture fixtureA = contact.getFixtureA();
                Fixture fixtureB = contact.getFixtureB();


            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {
            }



            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {
            }

        });
    }



    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;



    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        time +=Gdx.graphics.getDeltaTime();




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

        //Checks whether to spawn a cat or not
        if(time >4f-difficulty) {
            if (randInt(0, 1) > 0) {
                Cat cat = new Cat(CatType.SpaceShipCat,1,3,1,2,25f,(float)randInt(6,12),2,3,assetLoader);
                cat.setBodyStatic(world,0.5f);
                Katz.add(cat);
            } else {
                Cat cat = new Cat(CatType.BushCat,1,3,1,2,25f,(float)randInt(6,12),2,3,assetLoader);
                cat.setBodyStatic(world,0.5f);
                Katz.add(cat);
            }
            time = 0;
            if(difficulty<2.5) {
                difficulty += 0.05f;
            }
        }





        sb.begin();

        Background.draw(sb);
        spaceship.draw(sb);
        wall.draw(sb);
        ground.draw(sb);
        portal.draw(sb);
        int index = 0;
        for (Cat cat : Katz){
            cat.draw(sb);
            if (cat.rect.x > 0 ) {
                if (!cat.isFalling) {
                    cat.body.setTransform(cat.body.getPosition().x - 0.1f, cat.body.getPosition().y, cat.body.getAngle());
                }
            }
            else{
                Katz.removeIndex(index);
                toDispose.add(cat);
            }
            index++;

        }


        canon.draw(sb);



        for (Shot shot : shots){
            shot.draw(sb);

        }




        sb.end();

        stage.draw();





        world.step(1 / 60f, 6, 2);
        //renderer.render(world, camera2.combined);

        index = 0;
        for (Object object : toDispose){
            object.dispose();
            toDispose.removeIndex(index);
        }

        index = 0;
        for (Cat object : setToFall){
            object.setFalling();
            setToFall.removeIndex(index);
        }


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
