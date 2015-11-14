package com.mygdx.game;


import com.badlogic.gdx.Game;

import com.game.screen.MenuScreen;


public class MyGdxGame extends Game {

	Game game;




	@Override
	public void create () {
		game = this;
		setScreen(new MenuScreen(game));


	}




	@Override
	public void render () {
	super.render();


	}
}