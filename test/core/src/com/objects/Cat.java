package com.objects;

import com.Enums.CatType;
import com.Enums.ObjectType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;


public class Cat extends AnimatedObject {

    Animation animationTwo;
    public boolean isFalling = false;
    Texture fallTexture;
    Animation temp;

    public CatType catType;


    public Cat(String fileLoc1,String fileLoc2, int row1, int col1,int row2,int col2 ,float x, float y, float width, float height) {
        this.objectType = ObjectType.Canon.Cat;
        id = count;
        count++;


        temp = null;

        body = null;

        fallTexture = new Texture(Gdx.files.internal(fileLoc2));

        columns = col1;
        rows = row1;
        rect = new Rectangle();
        texture = new Texture(Gdx.files.internal(fileLoc1));
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

        stateTime = 0f;

        animation = new Animation(0.25f,textureRegion);

        this.rows = row2;
        this.columns = col2;

        temp = animation;


    }

    public Cat(Texture texture, Texture fallTexture, int row1, int col1,int row2, int col2, float x, float y, float width, float height) {
        this.objectType = ObjectType.Canon.Cat;
        id = count;
        count++;


        temp = null;

        body = null;

        this.fallTexture = fallTexture;

        columns = col1;
        rows = row1;
        rect = new Rectangle();
        this.texture = texture;
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

        stateTime = 0f;

        animation = new Animation(0.25f,textureRegion);

        this.rows = row2;
        this.columns = col2;

        temp = animation;
    }

    public Cat(CatType catType, int row1, int col1, int row2, int col2, float x, float y, float width, float height, AssetLoader assetLoader){
        this.objectType = ObjectType.Canon.Cat;
        id = count;
        count++;

        temp = null;

        body = null;
        this.catType = catType;

        switch (catType){
            case BushCat:
                texture = assetLoader.BushCat;
                fallTexture = assetLoader.BushCatFall;
                break;
            case SpaceShipCat:
                texture = assetLoader.SpaceShipCat;
                fallTexture = assetLoader.SpaceShipCatFall;
                break;
        }

        columns = col1;
        rows = row1;
        rect = new Rectangle();

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

        stateTime = 0f;

        animation = new Animation(0.25f,textureRegion);

        this.rows = row2;
        this.columns = col2;

        temp = animation;


    }

    public void setFalling(){
        isFalling = true;
        //texture.dispose();
        textureRegion = null;
        animation = null;


        BodyDef.BodyType bodyType = body.getType();

        //for (Fixture f: body.getFixtureList()) {
          //  body.destroyFixture(f);
        //}

        World world = body.getWorld();
        world.destroyBody(body);

        TextureRegion[][] tmp = TextureRegion.split(fallTexture, fallTexture.getWidth()/columns, fallTexture.getHeight()/rows);
        textureRegion = new TextureRegion[rows*columns];

        int index=0;
        for (int i =0; i < rows; i++){
            for (int j=0; j<columns;j++){
                textureRegion[index++] = tmp[i][j];
            }

        }

        rect.width = 2.0f*rect.width;
        rect.height= rect.height*0.75f;

        actualWidth = fallTexture.getWidth()/columns;
        actualHeight = (fallTexture.getHeight()/rows);

        scaleX = rect.width*100/actualWidth;
        scaleY= rect.height*100/actualHeight;

        stateTime = 0f;

        animationTwo = new Animation(0.25f,textureRegion);
        temp = animationTwo;

        fixtureDef.restitution = 0.5f;
        fixtureDef.friction = 0.5f;

        setBodyDynamic(world,0.5f,0.5f,0.5f);



    }


    @Override
    public void draw(SpriteBatch spriteBatch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = temp.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame,(rect.x*100-actualWidth/2), (rect.y*100-actualHeight/2),actualWidth/2, actualHeight/2, actualWidth, actualHeight,scaleX,scaleY,rotation);


    }


}
