package com.objects;

import com.Enums.ObjectType;
import com.Enums.ShotType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Suleman on 11/14/2015.
 */
public class Canon extends Object {

    int fuel;
    int shotcount=0;

    public Canon(String fileLoc, int row, int col, float x, float y , float width, float height){
        this.objectType = ObjectType.Canon.Canon;
        id = count;
        count++;

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
        this.objectType = ObjectType.Canon.Canon;
        id = count;
        count++;

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
        if (fuel < 4){
        fuel++;
        }
    }
    public void decreaseFuel(){
        fuel--;
    }

    public void changeAngle(boolean isup){
        //use gesture and change angle of the body(box2d)

    }

    public Shot fire(World world, ShotType type, AssetLoader assetLoader){
        //Generate a shot
        if (fuel >0) {
            Shot shot = new Shot(type, rect.x + (rect.width / 2) * MathUtils.cos(body.getAngle()), rect.y + (rect.width / 2) * MathUtils.sin(body.getAngle()) + 0.5f, 1, 1, rotation, world, assetLoader);
            shot.body.applyLinearImpulse(1.5f * MathUtils.cos(shot.body.getAngle()),
                    1.5f * MathUtils.sin(shot.body.getAngle()),
                    shot.body.getWorldPoint(new Vector2(0f, 0f)).x, shot.body.getWorldCenter().y
                    , true);
            shotcount++;
            if (shotcount == 4) {
                decreaseFuel();
                shotcount = 0;
            }
            return shot;
        }
        return null;

    }

    @Override
    public void draw(SpriteBatch spriteBatch){

        spriteBatch.draw(textureRegion[fuel],(rect.x*100-actualWidth/2), (rect.y*100-actualHeight/2),actualWidth/2, actualHeight/2, actualWidth, actualHeight,scaleX,scaleY,rotation);

    }



}
