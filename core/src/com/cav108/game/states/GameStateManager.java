package com.cav108.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;
    private double playStateElapsedTime;
    public GameStateManager(){
        states = new Stack<State>();
    }
    public void push(State state){
        states.push(state);
    }

    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        states.peek().render(sb);
    }

    public void setPlayStateElapsedTime(double elapsedTime) {
        this.playStateElapsedTime = elapsedTime;
    }

    public double getPlayStateElapsedTime() {
        return playStateElapsedTime;
    }
}   //end of GameStateManager.java
