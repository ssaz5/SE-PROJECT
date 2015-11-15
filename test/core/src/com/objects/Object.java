package com.objects;

import com.Enums.ObjectType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.MassData;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by Suleman on 11/9/2015.
 */
public class Object {

    ObjectType objectType;

    static int count = 0;
    int id;
    static final int ROW=5;
    int rows;
    int columns;

    Texture texture;
    TextureRegion[] textureRegion;
    public Rectangle rect;
    float rotation =0;

    TextureRegion currentFrame;

    float scaleX;
    float scaleY;

    float actualWidth;
    float actualHeight;

    public Body body;
    FixtureDef fixtureDef;


    public Object(){

    }
    public Object(String fileLoc, int row, int col, float x, float y , float width, float height){

        id = count;
        count++;

        columns = col;
        rows = row;
        rect = new Rectangle();
        texture = new Texture(Gdx.files.internal(fileLoc));
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/columns, texture.getHeight()/rows);
        textureRegion = new TextureRegion[rows*columns];
        int index=0;
        for (int i =0; i < rows; i++){
            for (int j=0; j<columns;j++){
                textureRegion[index++] = tmp[i][j];
            }

        }
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height= height;

        actualWidth = texture.getWidth()/columns;
        actualHeight = (texture.getHeight()/rows);

        scaleX = rect.width*100/actualWidth;
        scaleY= rect.height*100/actualHeight;
        currentFrame = textureRegion[0];

        body = null;
    }

    public Object(Texture t, int row, int col, float x, float y, float width, float height ){

        id = count;
        count++;

        columns = col;
        rows = row;
        rect = new Rectangle();
        texture = t;
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth()/columns, texture.getHeight()/rows);
        textureRegion = new TextureRegion[rows*columns];
        int index=0;
        for (int i =0; i < rows; i++){
            for (int j=0; j<columns;j++){
                textureRegion[index++] = tmp[i][j];
            }

        }
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height= height;

        actualWidth = texture.getWidth()/columns;
        actualHeight = (texture.getHeight()/rows);

        scaleX = rect.width*100/actualWidth;
        scaleY= rect.height*100/actualHeight;
        currentFrame = textureRegion[0];

        body = null;
    }


    public void setType(ObjectType objectType){
        this.objectType = objectType;
    }

    public void draw(SpriteBatch spriteBatch){

        spriteBatch.draw(currentFrame, (rect.x * 100 - actualWidth / 2), (rect.y * 100 - actualHeight / 2), actualWidth / 2, actualHeight / 2, actualWidth, actualHeight, scaleX, scaleY, rotation);

    }

    public void dispose(){
        for (Fixture f: body.getFixtureList()) {
            body.destroyFixture(f);
        }
        body.getWorld().destroyBody(body);

    }


    public void setPosition(float x, float y){
        rect.x = x;
        rect.y = y;


    }

    public void setRotation(float rotation){
        this.rotation = rotation;

    }


public Body setBodyStatic(World world, float density)                    {
        BodyDef bodyDef = new BodyDef();
    bodyDef.position.set(rect.x, rect.y);

        body = world.createBody(bodyDef);

        PolygonShape sShape = new PolygonShape();
        sShape.setAsBox(rect.width/2, rect.height/2, new Vector2(0,0), 0);
        fixtureDef = new FixtureDef();
        fixtureDef.density = density;


        body.createFixture(sShape,fixtureDef.density);
        sShape.dispose();

        body.setUserData(this);
        return body;
    }

    public Body setBodyDynamic(World world, float density, float friction, float restitution){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(rect.x,rect.y);

        body = world.createBody(bodyDef);

        PolygonShape dShape = new PolygonShape();
        dShape.setAsBox(rect.width / 2, rect.height / 2, new Vector2(0, 0), 0);
        fixtureDef = new FixtureDef();
        fixtureDef.shape = dShape;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.density = density;

        Fixture fixture = body.createFixture(fixtureDef);

        dShape.dispose();

        body.setUserData(this);

        return body;


    }

    public Body setBodyDynamic(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(rect.x,rect.y);

        body = world.createBody(bodyDef);

        PolygonShape dShape = new PolygonShape();
        dShape.setAsBox(rect.width/2, rect.height/2, new Vector2(0,0),0);

        fixtureDef.shape = dShape;


        body.createFixture(fixtureDef);

        dShape.dispose();

        body.setUserData(this);

        return body;

    }

    public  Body setBodyStatic(World world){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(rect.x,rect.y);

        body = world.createBody(bodyDef);

        PolygonShape sShape = new PolygonShape();
        sShape.setAsBox(rect.width/2, rect.height/2, new Vector2(0,0), 0);

        fixtureDef.shape = sShape;

        body.createFixture(sShape,fixtureDef.density);
        sShape.dispose();

        body.setUserData(this);


        return body;
    }

    public boolean equals(Object object){
        if (id == object.id){
            return true;
        }
        else{
            return false;
        }
    }

    public ObjectType getObjectType(){
        return   objectType;
    }

}

