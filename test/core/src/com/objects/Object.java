package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
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

    float scaleX;
    float scaleY;

    float actualWidth;
    float actualHeight;


    public Object(){

    }
    public Object(String fileLoc, int row, int col, float x, float y , float width, float height){



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

        spriteBatch.draw(currentFrame,(rect.x*100-actualWidth/2), (rect.y*100-actualHeight/2),actualWidth/2, actualHeight/2, actualWidth, actualHeight,scaleX,scaleY,rotation);

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


    public Body setBodyStatic(World world, float density){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(rect.x-rect.width/2,rect.y-rect.height/2);

        Body body = world.createBody(bodyDef);

        PolygonShape sShape = new PolygonShape();
        sShape.setAsBox(rect.width/2, rect.height/2, new Vector2(rect.width/2, rect.height/2),0f);

        body.createFixture(sShape,density);
        sShape.dispose();
        return body;
    }

    public Body setBodyDynamic(World world, float density, float friction, float restitution){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(rect.x-rect.width/2,rect.y-rect.height/2);

        Body body = world.createBody(bodyDef);

        PolygonShape dShape = new PolygonShape();
        dShape.setAsBox(rect.width/2, rect.height/2, new Vector2(0, 0),0f);
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

