package com.objects;

/**
 * Created by programmercore on 11/9/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {


    public static Texture BushCat;
    public static Texture BushCatFall;
    public static Texture SpaceShipCat;
    public static Texture SpaceShipCatFall;
    public static Texture AcidShot;
    public static Texture PoisonShot;
    public static Texture Portal;


    public static Texture loadTexture (String file){
        return new Texture(Gdx.files.internal(file));
    }

    public static void load () {

        BushCat = new Texture(Gdx.files.internal("cat/BushCat.png"));
        BushCatFall = new Texture(Gdx.files.internal("cat/BushCatFall.png"));
        SpaceShipCat = new Texture(Gdx.files.internal("cat/SpaceShipCat.png"));
        SpaceShipCatFall = new Texture(Gdx.files.internal("cat/SpaceShipCatFall.png"));
        AcidShot = loadTexture("Shots/AcidShot.png");
        PoisonShot = loadTexture("Shots/PoisonShot.png");
        Portal = loadTexture("Portal/Portal.png");
        }
}


