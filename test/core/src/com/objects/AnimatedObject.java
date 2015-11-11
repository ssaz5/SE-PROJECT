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

    public AnimatedObject(String fileLoc, int row, int col, float x, float y ){
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

        stateTime = 0f;

        animation = new Animation(0.25f,textureRegion);

    }

    @Override
    public void draw(SpriteBatch spriteBatch){
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(stateTime, true);
        spriteBatch.draw(currentFrame,rect.x-rect.width/2, rect.y-rect.height/2,rect.width/2, rect.height/2, rect.width, rect.height,1,1,rotation);

        //spriteBatch.draw(currentFrame, rect.x, rect.y,rect.x, rect.y, rect.width, rect.height,1,1,rotation,(int)rect.x,(int) rect.y,(int)rect.width,(int) rect.height,false,false);

    }

}
