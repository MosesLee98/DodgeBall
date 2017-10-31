package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

import java.util.Random;

public class MainGame implements Screen {

	final DodgeBall game;

	// Circles Textures
	Texture circle1;
	Texture circle2;
	Texture circle3;
	Texture circle4;
	Texture fingerCircle;


	// Circle Hitboxes
	Circle circle1Hitbox;
	Circle circle2Hitbox;
	Circle circle3Hitbox;
	Circle circle4Hitbox;
	Circle fingerHitbox;

	// Misc.
	Random rng = new Random(); // rng
	float maxWidth;
	float maxHeight;
	Music music;
	public int score = 0;
	BitmapFont font;

	// Positions of Circles
	float X;
	float Y;
	float X2;
	float Y2;
	float X3;
	float Y3;
	float X4;
	float Y4;

	// Velocity of Circles
	int vX = 10;
	int vY = 13;
	int vX2 = 12;
	int vY2 = 15;
	int vX3 = 14;
	int vY3 = 17;
	int vX4 = 16;
	int vY4 = 19;

	// Size of Circles
	float size1;
	float size2;
	float size3;
	float size4;

	public MainGame (final DodgeBall game) {

		this.game = game;

		// Music
		music = Gdx.audio.newMusic(Gdx.files.internal("backgroundMusic.mp3"));

		// Font
		font = new BitmapFont();
		font.setColor(Color.CYAN);
		font.getData().setScale(8);

		// Initialize Textures
		circle1 = new Texture("circle1.png");
		circle2 = new Texture("circle2.png");
		circle3 = new Texture("circle3.png");
		circle4 = new Texture("circle4.png");
		fingerCircle = new Texture("fingerCircle.png");

		// Initialize Hitboxes
		fingerHitbox = new Circle();
		circle1Hitbox = new Circle();
		circle2Hitbox = new Circle();
		circle3Hitbox = new Circle();
		circle4Hitbox = new Circle();

		// Positions of Screen
		maxWidth = Gdx.graphics.getWidth();
		maxHeight = Gdx.graphics.getHeight();

		// Size and Position of Circles
		X = rng.nextFloat()*Gdx.graphics.getWidth();
		Y = rng.nextFloat()*Gdx.graphics.getHeight();
		X2 = rng.nextFloat()*Gdx.graphics.getWidth();
		Y2 = rng.nextFloat()*Gdx.graphics.getHeight();
		X3 = rng.nextFloat()*Gdx.graphics.getWidth();
		Y3 = rng.nextFloat()*Gdx.graphics.getHeight();
		X4 = rng.nextFloat()*Gdx.graphics.getWidth();
		Y4 = rng.nextFloat()*Gdx.graphics.getHeight();
		size1 = Gdx.graphics.getWidth()/6;
		size2 = Gdx.graphics.getWidth()/8;
		size3 = Gdx.graphics.getWidth()/10;
		size4 = Gdx.graphics.getWidth()/12;

		music.play();
		music.setLooping(true);
		music.setVolume(0.7f);

	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.begin();

		// draw score
		score++;
		Gdx.app.log("Score", String.valueOf(score));
		font.draw(game.batch, String.valueOf(score), 100, 200);

		fingerHitbox.set(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY(), fingerCircle.getWidth()/2);
		circle1Hitbox.set(X,Y, circle1.getWidth()/2);
		circle2Hitbox.set(X2,Y2, circle2.getWidth()/2);
		circle3Hitbox.set(X3,Y3, circle3.getWidth()/2);
		circle4Hitbox.set(X4,Y4, circle4.getWidth()/2);

		// Circle where finger is
		if (Gdx.input.isTouched()) {

			game.batch.draw(fingerCircle,Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

		}

		if (Intersector.overlaps(fingerHitbox, circle1Hitbox) ||
				Intersector.overlaps(fingerHitbox, circle2Hitbox) ||
				Intersector.overlaps(fingerHitbox, circle3Hitbox) ||
				Intersector.overlaps(fingerHitbox, circle4Hitbox)) {

			Gdx.input.vibrate(500);
			dispose();
			game.setScreen(new MainMenu(game));
			}

		// Draw Circles
		game.batch.draw(circle1,X - circle1.getWidth()/2,Y - circle1.getHeight()/2);
		game.batch.draw(circle2,X2 - circle2.getWidth()/2,Y2 - circle2.getHeight()/2);
		game.batch.draw(circle3,X3 - circle3.getWidth()/2,Y3 - circle3.getHeight()/2);
		game.batch.draw(circle4,X4 - circle4.getWidth()/2,Y4 - circle4.getHeight()/2);


		// Velocity of each Circle
		X += vX;  // Circle 1
		Y += vY;
		X2 += vX2; // Circle 2
		Y2 -= vY2;
		X3 -= vX3; // Circle 3
		Y3 += vY3;
		X4 -= vX4; // Circle 4
		Y4 -= vY4;

		// Collisions with walls *** CAN MAKE A METHOD TO REPLACE
		// Circle 1
		if (X > maxWidth) {
			vX = -vX;
		} else if (X < 0) {
			vX = -vX;
		}
		if (Y > maxHeight) {
			vY = -vY;
		} else if (Y < 0){
			vY = -vY;
		}

		// Circle 2
		if (X2 > maxWidth) {
			vX2 = -vX2;
		} else if (X2 < 0) {
			vX2 = -vX2;
		}
		if (Y2 > maxHeight) {
			vY2 = -vY2;
		} else if (Y2 < 0) {
			vY2 = -vY2;
		}

		// Circle 3
		if (X3 > maxWidth) {
			vX3 = -vX3;
		} else if (X3 < 0) {
			vX3 = -vX3;
		}
		if (Y3 > maxHeight) {
			vY3 = -vY3;
		} else if (Y3 < 0) {
			vY3 = -vY3;
		}

		// Circle 4
		if (X4 > maxWidth) {
			vX4 = -vX4;
		} else if (X4 < 0) {
			vX4 = -vX4;
		}
		if (Y4 > maxHeight) {
			vY4 = -vY4;
		} else if (Y4 < 0) {
			vY4 = -vY4;
		}

		if (!Gdx.input.isTouched()) {
			Gdx.input.vibrate(500);
			game.setScreen(new MainMenu(game));
		}

		game.batch.end();
	}

	@Override
	public void show() {
		music.play();
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
		circle1.dispose();
		circle2.dispose();
		circle3.dispose();
		circle4.dispose();
		fingerCircle.dispose();
		music.dispose();

	}
}
