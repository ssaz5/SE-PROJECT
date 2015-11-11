package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;


/**
 * Created by Suleman on 11/9/2015.
 */
public class Object {
    static final int ROW=5;
    int rows;
    int columns;

    Texture texture;
    TextureRegion[] textureRegion;
    public Rectangle rect;
    float rotation;

    TextureRegion currentFrame;






    public Object(){

    }
    public Object(String fileLoc, int row, int col, float x, float y ){
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
        rect.width = texture.getWidth()/columns;
        rect.height= texture.getHeight()/rows;
    }

    public Object(Texture t, int row, int col, float x, float y ){
        columns = col;
        rows = row;

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
        rect.width = texture.getWidth()/columns;
        rect.height= texture.getHeight()/rows;
        currentFrame = textureRegion[0];
    }



    public void draw(SpriteBatch spriteBatch){
        spriteBatch.draw(currentFrame,rect.x-rect.width/2, rect.y-rect.height/2,rect.width/2, rect.height/2, rect.width, rect.height,1,1,rotation);

    }

    public void dispose(){


    }

    public void setPosition(float x, float y){
        rect.x = x;
        rect.y = y;


    }

    public void setRotation(float rotation){
        this.rotation = rotation;

    }


    public void setBodyStatic(World world, float density){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(rect.x,rect.y);

        Body body = world.createBody(bodyDef);

        PolygonShape sShape = new PolygonShape();
        sShape.setAsBox(rect.width,rect.height);

        body.createFixture(sShape,density);
        sShape.dispose();

    }

    public Body setBodyDynamic(World world, float density, float friction, float restitution){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(rect.x, rect.y);

        Body body = world.createBody(bodyDef);

        CircleShape dShape = new CircleShape();
        dShape.setRadius((((rect.height>rect.width)?rect.width:rect.height)/2));
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = dShape;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.density = density;

        Fixture fixture = body.createFixture(fixtureDef);

        dShape.dispose();

        body.setUserData(this);

        return body;


    }





}

