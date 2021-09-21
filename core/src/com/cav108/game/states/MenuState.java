package com.cav108.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.cav108.game.FloppyBird;

public class MenuState extends State{
    private Texture background;
    private Texture playButton;
    private Texture aboutButton;
    private float playButtonXPos;
    private float playButtonYPos;
    private float aboutButtonXPos;
    private float aboutButtonYPos;

    public MenuState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, FloppyBird.WIDTH , FloppyBird.HEIGHT  );
        background = new Texture("bg.png");
        playButton = new Texture("playBtn.png");
        playButtonXPos = cam.position.x - (playButton.getWidth() / 2);
        playButtonYPos = cam.position.y - (playButton.getHeight() / 2);
        aboutButton = new Texture("aboutBtn.png");
        aboutButtonXPos = cam.position.x - (aboutButton.getWidth() / 2);
        aboutButtonYPos = cam.position.y - (aboutButton.getHeight() * 2);
    }

    @Override
    protected void handleInput() {
        Vector3 touchPoint = new Vector3();

        if(Gdx.input.justTouched()){
            cam.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            //check if playButton is touched
            if (touchPoint.x >= playButtonXPos && touchPoint.x <= playButtonXPos + playButton.getWidth()
            && touchPoint.y >= playButtonYPos && touchPoint.y <= playButtonYPos + playButton.getHeight()){
                gsm.set(new PlayState(gsm));
            }//else check if aboutButton is touched
            else if (touchPoint.x >= aboutButtonXPos && touchPoint.x <= aboutButtonXPos + aboutButton.getWidth()
            && touchPoint.y >= aboutButtonYPos && touchPoint.y <= aboutButtonYPos + aboutButton.getHeight()){
                gsm.set(new AboutState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0, FloppyBird.WIDTH, FloppyBird.HEIGHT);
        sb.draw(playButton, playButtonXPos, playButtonYPos  );
        sb.draw(aboutButton, aboutButtonXPos, aboutButtonYPos  );
        sb.end();
    }


    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
        System.out.println("Menu state disposed!");
    }
}   //end of MenuState.java
