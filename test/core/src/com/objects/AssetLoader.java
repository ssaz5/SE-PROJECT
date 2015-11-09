package com.objects;

/**
 * Created by programmercore on 11/9/15.
 */

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AssetLoader {

    public static Texture background;
    public static TextureRegion backgroundRegion;
    public static Texture burnBush;
    public static TextureRegion burnBushRegion;
    public static Texture bush_one;
    public static TextureRegion bush_oneRegion;
    public static Texture bush_two;
    public static TextureRegion bush_twoRegion;
    public static Texture weaponButton_one;
    public static TextureRegion weaponButton_oneRegion;
    public static Texture weaponButtonpressed_one;
    public static TextureRegion weaponButtonpressed_oneRegion;
    public static Texture weaponButton_two;
    public static TextureRegion weaponButton_twoRegion;
    public static Texture weaponButtonpressed_two;
    public static TextureRegion weaponButtonpressed_twoRegion;
    public static Texture canon_onebar;
    public static TextureRegion canon_onebarRegion;
    public static Texture canon_twobar;
    public static TextureRegion canon_twobarRegion;
    public static Texture canon_threebar;
    public static TextureRegion canon_threebarRegion;
    public static Texture canon_empty;
    public static TextureRegion canon_emptyRegion;
    public static Texture canon_full;
    public static TextureRegion canon_fullRegion;
    public static Texture rocket_one;
    public static TextureRegion rocket_oneRegion;
    public static Texture rocket_two;
    public static TextureRegion rocket_twoRegion;
    public static Texture spaceshipone_one;
    public static TextureRegion spaceshipone_oneRegion;
    public static Texture spaceshiptwo_one;
    public static TextureRegion spaceshiptwo_oneRegion;
    public static Texture spaceshipthree_one;
    public static TextureRegion spaceshipthree_oneRegion;
    public static Texture spaceshipone_two;
    public static TextureRegion spaceshipone_twoRegion;
    public static Texture spaceshiptwo_two;
    public static TextureRegion spaceshiptwo_twoRegion;
    public static Texture spaceshipthree_two;
    public static TextureRegion spaceshipthree_twoRegion;
    public static Texture gamescreen;
    public static TextureRegion gamescreenRegion;
    public static Texture bushcat_one;
    public static TextureRegion bushcat_oneRegion;
    public static Texture bushcat_two;
    public static TextureRegion bushcat_twoRegion;
    public static Texture bushcat_three;
    public static TextureRegion bushcat_threeRegion;
    public static Texture bushcatfall_one;
    public static TextureRegion bushcatfall_oneRegion;
    public static Texture bushcatfall_two;
    public static TextureRegion bushcatfall_twoRegion;
    public static Texture spacecat_one;
    public static TextureRegion spacecat_oneRegion;
    public static Texture spacecat_two;
    public static TextureRegion spacecat_twoRegion;
    public static Texture spacecat_three;
    public static TextureRegion spacecat_threeRegion;
    public static Texture spacecatfall_one;
    public static TextureRegion spacecatfall_oneRegion;
    public static Texture spacecatfall_two;
    public static TextureRegion spacecatfall_twoRegion;


    public static Texture loadTexture (String file){
        return new Texture(Gdx.files.internal(file));
    }

    public static void load () {
        background = loadTexture("Main screen/Dome_City-01.jpg");
        backgroundRegion = new TextureRegion(background);
        burnBush = loadTexture("Bushes/1-09-09.jpg");
        burnBushRegion = new TextureRegion(burnBush);
        bush_one = loadTexture("Bushes/22-09.jpg");
        bush_oneRegion = new TextureRegion(bush_one);
        bush_two = loadTexture("Bushes/32-09.jpg");
        bush_twoRegion = new TextureRegion(bush_two);
        weaponButton_one = loadTexture("Buttons/button_3-02.jpg");
        weaponButton_oneRegion = new TextureRegion(weaponButton_one);
        weaponButtonpressed_one = loadTexture("Buttons/button_2-02.jpg");
        weaponButtonpressed_oneRegion = new TextureRegion(weaponButtonpressed_one);
        weaponButton_two = loadTexture("Buttons/Button_1-02.jpg");
        weaponButton_twoRegion = new TextureRegion(weaponButton_two);
        weaponButtonpressed_two = loadTexture("Buttons/Button_press-02.jpg");
        weaponButtonpressed_twoRegion = new TextureRegion(weaponButtonpressed_two);
        canon_onebar = loadTexture("Canon gun/1-02.jpg");
        canon_onebarRegion = new TextureRegion(canon_onebar);
        canon_twobar = loadTexture("Canon gun/Half_empty-02.jpg");
        canon_twobarRegion = new TextureRegion(canon_twobar);
        canon_threebar = loadTexture("Canon gun/2-02.jpg");
        canon_threebarRegion = new TextureRegion(canon_threebar);
        canon_empty = loadTexture("Canon gun/Empty-02.jpg");
        canon_emptyRegion = new TextureRegion(canon_empty);
        canon_full = loadTexture("Canon gun/full_load-02.jpg");
        canon_fullRegion = new TextureRegion(canon_full);
        rocket_one = loadTexture("Rocket_cat/rocket2-02.jpg");
        rocket_oneRegion = new TextureRegion(rocket_one);
        rocket_two = loadTexture("Rocket_cat/rocket21-02.jpg");
        rocket_twoRegion = new TextureRegion(rocket_two);
        spaceshipone_one = loadTexture("ship action2/1-02.jpg");
        spaceshipone_oneRegion = new TextureRegion(spaceshipone_one);
        spaceshiptwo_one = loadTexture("ship action2/spaceship_2-02.jpg");
        spaceshiptwo_oneRegion = new TextureRegion(spaceshiptwo_one);
        spaceshipthree_one = loadTexture("ship action2/spaceship_3-02.jpg");
        spaceshipthree_oneRegion = new TextureRegion(spaceshipthree_one);
        spaceshipone_two = loadTexture("ship action2/Ship action 1/spaceship_1-02.jpg");
        spaceshipone_twoRegion = new TextureRegion(spaceshipone_two);
        spaceshiptwo_two = loadTexture("ship action2/Ship action 1/spaceship_2-02.jpg");
        spaceshiptwo_twoRegion = new TextureRegion(spaceshiptwo_two);
        spaceshipthree_two = loadTexture("ship action2/Ship action 1/spaceship_3-02.jpg");
        spaceshipthree_twoRegion = new TextureRegion(spaceshipthree_two);
        gamescreen = loadTexture("Main screen/mainscreen_firecity-02.jpg");
        gamescreenRegion = new TextureRegion(gamescreen);
        bushcat_one = loadTexture("cat/Bush cat/1.jpg");
        bushcat_oneRegion = new TextureRegion(bushcat_one);
        bushcat_two = loadTexture("cat/Bush cat/2.jpg");
        bushcat_twoRegion = new TextureRegion(bushcat_two);
        bushcat_three = loadTexture("cat/Bush cat/3.jpg");
        bushcat_threeRegion = new TextureRegion(bushcat_three);
        bushcatfall_one = loadTexture("cat/Bush cat/Cat fall/1-02.jpg");
        bushcatfall_oneRegion = new TextureRegion(bushcatfall_one);
        bushcatfall_two = loadTexture("cat/Bush cat/Cat fall/2.jpg");
        bushcatfall_twoRegion = new TextureRegion(bushcatfall_two);
        spacecat_one = loadTexture("cat/spaceship cat/10-02.jpg");
        spacecat_oneRegion = new TextureRegion(spacecat_one);
        spacecat_two = loadTexture("cat/spaceship cat/11-02.jpg");
        spacecat_twoRegion = new TextureRegion(spacecat_two);
        spacecat_three = loadTexture("cat/spaceship cat/12.jpg");
        spacecat_threeRegion = new TextureRegion(spacecat_three);
        spacecatfall_one = loadTexture("cat/spaceship cat/13-02.jpg");
        spacecatfall_oneRegion = new TextureRegion(spacecatfall_one);
        spacecatfall_two = loadTexture("cat/spaceship cat/14-02.jpg");
        spacecatfall_twoRegion = new TextureRegion(spacecatfall_two);
    }
}


