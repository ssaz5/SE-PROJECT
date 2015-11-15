package com.objects;

import com.Enums.ShotType;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Suleman on 11/15/2015.
 */
public class Shot extends Object {

    ShotType shotType;

    public Shot(Texture t, int row, int col, float x, float y, float width, float height, ShotType shotType){

        this.shotType = shotType;
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

    public Shot(ShotType shotType, float x, float y, float width, float height, float rotation, World world,AssetLoader assetLoader){

        this.shotType = shotType;
        columns = 1;

        rows = 1;
        rect = new Rectangle();
        switch (shotType){
            case AcidShot:
                texture = assetLoader.AcidShot;
                break;
            case PoisonShot:
                texture = assetLoader.PoisonShot;
                break;
        }


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
        setBodyDynamic(world,0.1f,0.5f,0.1f);
        
        body.setTransform(body.getPosition(), MathUtils.degreesToRadians*rotation);

    }







}



