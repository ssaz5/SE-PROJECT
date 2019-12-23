package com.acidic.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;


public class Button {
    TextureAtlas buttonAtlas;
    TextButton button;
    Skin buttonSkin;
    TextButton.TextButtonStyle buttonStyle;
    public Container buttonContainer;

    public Button(String fileLoc, BitmapFont font,String name, float x, float y, float width, float height){
        buttonAtlas = new TextureAtlas(Gdx.files.internal(fileLoc));
        buttonSkin = new Skin();
        buttonSkin.addRegions(buttonAtlas);
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.up = buttonSkin.getDrawable(name+"1");
        buttonStyle.over = buttonSkin.getDrawable(name+"2");
        buttonStyle.down = buttonSkin.getDrawable(name+"2");
        buttonStyle.font = font;

        button = new TextButton("",buttonStyle);
        buttonContainer = new Container(button);
        buttonContainer.setTransform(true);
        buttonContainer.setPosition(x,y);
        float scaleX = width/(buttonSkin.getRegion(name+"1")).getRegionWidth();
        float scaleY = height/(buttonSkin.getRegion(name+"1")).getRegionHeight();
        buttonContainer.setScale(scaleX, scaleY);




    }

    public void setStage(Stage stage){
        stage.addActor(buttonContainer);
    }



}
