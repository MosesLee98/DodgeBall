package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by gcmos on 2017-10-31.
 */

public class MainMenu implements Screen {

    final DodgeBall game;
    Texture menuTitle;
    Texture menuMessage;

    public MainMenu(final DodgeBall game) {
        this.game = game;
        menuTitle = new Texture("menuTitle.png");
        menuMessage = new Texture("menuMessage.png");
    }

    @Override
    public void show() {

    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(menuTitle,Gdx.graphics.getWidth()/2 - menuTitle.getWidth()/2, Gdx.graphics.getHeight() - Gdx.graphics.getHeight()/3);
        game.batch.draw(menuMessage,Gdx.graphics.getWidth()/2 + 25 - menuTitle.getWidth()/2, Gdx.graphics.getHeight()/2);
        game.font.draw(game.batch, "Your score was ", 100, 50);
        game.batch.end();

        if (Gdx.input.isTouched()) {
            game.setScreen(new MainGame(game));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        menuTitle.dispose();
        menuMessage.dispose();
    }
}
