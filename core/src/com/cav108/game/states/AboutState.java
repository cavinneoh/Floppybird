package com.cav108.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cav108.game.FloppyBird;

public class AboutState extends State {
    private Texture background;
    private BitmapFont font = new BitmapFont();

    public AboutState (GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, FloppyBird.WIDTH , FloppyBird.HEIGHT  );
        background = new Texture("bg.png");
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            gsm.set(new MenuState(gsm));
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
        GlyphLayout textBox = new GlyphLayout();
        String text = "This is a game created by 172106 for Anglia Ruskin University MOD002691."
                + "\nCreated with LibGDX engine, it is capable of running on Android and Desktop."
                + "\nTap anywhere to return to menu.";
        textBox.setText(font, text);
        font.draw(sb, text, cam.position.x - (textBox.width / 2 ), cam.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
        System.out.println("About state disposed!");
    }
}   //end of AboutState.java
