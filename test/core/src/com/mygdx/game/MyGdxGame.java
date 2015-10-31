package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;



public class MyGdxGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	int x;
	int y;


	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		x = 100;
		y=100;

	}


	public void update(){
		x=x+5;
		if((x%100 != 0)){
			y=y+5;

		}
		if(x%1600 == 0){
			x = 0;

		}
		if(y%800 == 0){
			y = 0;

		}

	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
		update();
	}
}
