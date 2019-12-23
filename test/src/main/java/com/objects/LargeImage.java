package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Suleman on 11/14/2015.
 */
public class LargeImage extends Object {

    public LargeImage(String fileLoc, int row, int col, float x, float y, float width, float height ){

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

        rect.width = width/columns;
        rect.height= height/rows;

        rect.x = x-width/2 + rect.width/2;
        rect.y = y+height/2 + rect.height/2;

        actualWidth = texture.getWidth()/columns;
        actualHeight = (texture.getHeight()/rows);

        scaleX = rect.width*100/actualWidth;
        scaleY= rect.height*100/actualHeight;
        currentFrame = textureRegion[0];


    }


    @Override
    public void draw(SpriteBatch spriteBatch){
        float tempX,tempY = rect.y;
        for (int i =0;i <rows;i++){
            tempX = rect.x;
            for (int j=0; j <columns;j++){

                spriteBatch.draw(
                        textureRegion[j*i + j],
                        (tempX*100-actualWidth/2), (tempY*100-actualHeight/2),
                        actualWidth/2, actualHeight/2,
                        actualWidth, actualHeight,
                        scaleX,scaleY,
                        rotation);
                tempX = rect.x + rect.width;
            }
            tempY = tempY - rect.height;

        }


    }

}
