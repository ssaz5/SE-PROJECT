package com.mygdx.game;


import com.badlogic.gdx.Game;

import com.game.screen.MenuScreen;
import com.objects.AssetLoader;


public class MyGdxGame extends Game {

	Game game;
	public AssetLoader assetLoader;



	@Override
	public void create () {
		game = this;
		assetLoader = new AssetLoader();
		assetLoader.load();
		setScreen(new MenuScreen(game, assetLoader));


	}



	public com.objects.AssetLoader getAssetLoader() {
		return assetLoader;
	}

	@Override
	public void render () {
	super.render();


	}
}