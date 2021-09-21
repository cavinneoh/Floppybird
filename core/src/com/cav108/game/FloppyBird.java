package com.cav108.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cav108.game.states.GameStateManager;
import com.cav108.game.states.MenuState;

public class FloppyBird extends ApplicationAdapter {
	public static final int WIDTH = 500;
	public static final int HEIGHT = 700;
	public static final String TITLE = "Floppy Bird";
	private GameStateManager gsm;
	private SpriteBatch batch;
	private Music music;
	@Override
	public void create () {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		music = Gdx.audio.newMusic(Gdx.files.internal("sandstorm.mp3"));
		music.setLooping(true);
		music.setVolume(0.3f);
		music.play();
		gsm.push(new MenuState(gsm));
	}
	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	@Override
	public void dispose () {
		super.dispose();
		music.dispose();
	}
}	//end of FloppyBird.Java
