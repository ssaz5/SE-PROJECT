package com.acidic.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;


public class Buttons {
    private float position_x, position_y, width, height;

    //Take two textures one for pressed state and one when the button is not pressed
    private TextureRegion buttonUp;
    private TextureRegion buttonDown;

    private Rectangle bounds;
    private boolean isPressed = false;

    public Buttons ( float position_x, float position_y, float width,
                     float height, TextureRegion buttonUp, TextureRegion buttonDown ){
        this.position_x = position_x;
        this.position_y = position_y;
        this.width = width;
        this.height = height;
        this.buttonUp = buttonUp;
        this.buttonDown = buttonDown;

        bounds = new Rectangle(position_x, position_y, width, height);
    }

    public boolean isClicked ( int ScreenX, int ScreenY ){
        //Return true if is clicked
        return bounds.contains( ScreenX, ScreenY );
    }

    public void draw ( SpriteBatch batcher ){
        //Draw the image when in pressed state
        if (isPressed)
            batcher.draw(buttonDown, position_x, position_y, width, height);
            //Draw the image when is not in pressed state
        else
            batcher.draw(buttonUp, position_x, position_y, width, height);
    }

    public boolean isTouchDown ( int ScreenX, int ScreenY ){
        if ( bounds.contains( ScreenX, ScreenY ) ){
            isPressed = true;
            return true;
        }
        return false;
    }

    public boolean isTouchUp ( int ScreenX, int ScreenY ){

        //The button is in a pressed state
        if ( bounds.contains( ScreenX, ScreenY ) && isPressed ){
            isPressed = false;
            return true;
        }

        //the button is released and cancels the press
        isPressed = false;
        return false;
    }
}
