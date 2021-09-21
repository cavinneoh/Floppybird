package com.cav108.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.TimeUtils;


public class Bird {
    private static final int GRAVITY =-15;
    private static final int MOVEMENT = 100;
    private float viewportHeight;
    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Animation birdAnimation;
    private Texture texture;
    private Sound flap;


    public Bird(int x, int y, float viewportHeight){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0,0,0);
        this.viewportHeight = viewportHeight;
        texture = new Texture("birdanimation.png");
        birdAnimation = new Animation(new TextureRegion(texture), 3, 0.5f);
        bounds = new Rectangle(x , y , texture.getWidth() / 3 - 2, texture.getHeight() - 2);
        flap = Gdx.audio.newSound(Gdx.files.internal("sfx_wing.ogg"));
    }

    public void update(float dt){
        birdAnimation.update(dt);
        if (position.y > 0){
            velocity.add(0, GRAVITY, 0);
        }   //GRAVITY is set to -15
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);

        if(position.y < 0){ //set game floor
            position.y = 0; //prevent bird from flying past floor
        }
        if (position.y >= viewportHeight){   //set game ceiling
            velocity.y = 0; //prevents bird from flying off screen
            position.y = viewportHeight;
        }
        velocity.scl(1/dt);
        bounds.set(position.x + 2 , position.y + 3, texture.getWidth() / 3 - 4, texture.getHeight() - 4);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 250;
        flap.play(0.5f);
    }
    public Rectangle getBounds(){
        return bounds;
    }

    public void dispose(){
        texture.dispose();
        flap.dispose();
    }
}   //end of Bird.java
