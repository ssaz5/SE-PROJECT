package com.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Suleman on 11/11/2015.
 */
public class AnimatedObject extends Object {

    Animation animation;


    float stateTime;

    public AnimatedObject(){};

    public AnimatedObject(String fileLoc, int row, int col, float x, float y, float width, float height ){

        id = count;
        count++;

        body = null;

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

        stateTime = 0f;

        animation = new Animation(0.25f,textureRegion);



    }

    public AnimatedObject(Texture texture, int row, int col, float x, float y, float width, float height ){


        id = count;
        count++;
        body = null;

        columns = col;
        rows = row;
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




    }


    @Override
    public void draw(SpriteBatch spriteBatch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = (TextureRegion) animation.getKeyFrame(stateTime, true);
        //spriteBatch.draw(currentFrame,(rect.x-rect.width/2)*100, (rect.y-rect.height/2)*100,(texture.getWidth()/columns)/2, (texture.getHeight()/rows)/2, texture.getWidth()/columns, texture.getHeight()/rows,scaleX,scaleY,rotation);
        spriteBatch.draw(currentFrame,(rect.x*100-actualWidth/2), (rect.y*100-actualHeight/2),actualWidth/2, actualHeight/2, actualWidth, actualHeight,scaleX,scaleY,rotation);
        //spriteBatch.draw(currentFrame, rect.x, rect.y,rect.x, rect.y, rect.width, rect.height,1,1,rotation,(int)rect.x,(int) rect.y,(int)rect.width,(int) rect.height,false,false);

    }

}
