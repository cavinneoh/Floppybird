package com.cav108.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.cav108.game.FloppyBird;

public class GameOverState extends State {
    private Texture background;
    private Texture gameOverImage;
    private Texture backButton;
    private BitmapFont font = new BitmapFont();
    private float backButtonXPos;
    private float backButtonYPos;

    public GameOverState(GameStateManager gsm){
        super(gsm);
        cam.setToOrtho(false, FloppyBird.WIDTH , FloppyBird.HEIGHT  );
        background = new Texture("bg.png");
        gameOverImage = new Texture ("gameover.png");
        backButton = new Texture ("backBtn.png");
        backButtonXPos = 0;
        backButtonYPos = FloppyBird.HEIGHT - backButton.getHeight();
    }

    @Override
    protected void handleInput() {
        Vector3 touchPoint = new Vector3();

        if(Gdx.input.justTouched()){
            cam.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(),0));
            if (touchPoint.x >= backButtonXPos && touchPoint.x <= backButtonXPos + backButton.getWidth()
                    && touchPoint.y >= backButtonYPos && touchPoint.x <= backButtonYPos + backButton.getWidth()) {
                gsm.set(new MenuState(gsm));
            }
        }
    }

    @Override
    public void update(float dt) { handleInput(); }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();

        sb.draw(background, 0, 0, FloppyBird.WIDTH, FloppyBird.HEIGHT);
        sb.draw(gameOverImage, cam.position.x - gameOverImage.getWidth() / 2, cam.position.y + gameOverImage.getHeight());
        GlyphLayout textBox = new GlyphLayout();
        String text = "You have flown for " + gsm.getPlayStateElapsedTime()
                + " second(s). \nTap the arrow  above to return to menu.";
        textBox.setText(font, text);
        font.getData().setScale(1.3f);
        font.draw(sb, text, cam.position.x - (textBox.width / 2 ), cam.position.y);
        sb.draw(backButton, backButtonXPos, backButtonYPos );
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        font.dispose();
        System.out.println("Game Over state disposed!");
    }
}   //end of GameOverState.Java
