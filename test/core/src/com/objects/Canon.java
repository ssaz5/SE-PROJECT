package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Suleman on 11/14/2015.
 */
public class Canon extends Object {

    int fuel;

    public Canon(String fileLoc, int row, int col, float x, float y , float width, float height){
        fuel = 4;
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

    public Canon(Texture t, int row, int col, float x, float y, float width, float height ){
        fuel = 4;
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

    public void increaseFuel(){
        fuel++;
    }
    public void decreaseFuel(){
        fuel--;
    }

    public void changeAngle(boolean isup){
        //use gesture and change angle of the body(box2d)

    }

    public void fire(){
        //Generate a shot
    }

    @Override
    public void draw(SpriteBatch spriteBatch){

        spriteBatch.draw(textureRegion[fuel],(rect.x*100-actualWidth/2), (rect.y*100-actualHeight/2),actualWidth/2, actualHeight/2, actualWidth, actualHeight,scaleX,scaleY,rotation);

    }



}
