package aep.nasser.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Inimigo {

	public double x, y, dx, dy;
	public double spd = 2;

	public static BufferedImage[] iniSprite;

	public int curFrames = 0, maxFrames = 10, maxAnim = 6, curAnim = 0;

	public Inimigo(int x, int y) {
		this.x = x;
		this.y = y;

		double radius = Math.atan2((Game.HEIGHT / 2 - 20) - y, (Game.WIDTH / 2 - 20) - x);
		this.dx = Math.cos(radius);
		this.dy = Math.sin(radius);
		iniSprite = new BufferedImage[6];
		iniSprite[0] = Game.sheet.getSprite(0, 0, 16, 16);
		iniSprite[1] = Game.sheet.getSprite(16, 0, 16, 16);
		iniSprite[2] = Game.sheet.getSprite(30, 0, 16, 16);

		iniSprite[3] = Game.sheet.getSprite(47, 0, 16, 16);
		iniSprite[4] = Game.sheet.getSprite(63, 0, 16, 16);
		iniSprite[5] = Game.sheet.getSprite(80, 0, 16, 16);

		if(Game.score >= 5) {
			spd = 3;
		}
		
		if(Game.score >= 15) {
			spd = 4;
		}
		
		if(Game.score >= 30) {
			spd = 5;
		}
		
		
	}

	public void update() {
		x += (dx * spd);
		y += (dy * spd);
		if (new Rectangle((int) x, (int) y, 40, 40).intersects(Game.maskBuraco)) {
			Game.inimigo.remove(this);
			Game.score = 0;
			Game.auxScore = 0;
			return;
		}

		
		if (y > (Game.HEIGHT / 2)) {
			curFrames++;
			curAnim = 3;
			if (curFrames == maxFrames) {
				curAnim++;
				if (curAnim == maxAnim) {
					curAnim = 3;
				}
				curFrames = 0;
			}
		}
		
		if(y < (Game.HEIGHT / 2)) {
		curFrames++;
		if (curFrames == maxFrames) {
			curAnim++;
			if (curAnim == 3) {
				curAnim = 0;
			}
			curFrames = 0;
		}
	}

		// Verificar colisão com pontos do mouse!
		verificaColisao();
	}

	public void verificaColisao() {
		if (Game.isPressed) {
			Game.isPressed = false;
			
			if (Game.mx >= x && Game.mx <= x + 40) {
				if (Game.my >= y && Game.my <= y + 40) {
					Game.inimigo.remove(this);
					Game.score++;
					Game.auxScore++;
					return;
				}
			}

		}
	}

	public void render(Graphics g) {
		g.drawImage(iniSprite[curAnim], (int) x, (int) y, 40, 40, null);
		// g.setColor(Color.red);
		// g.fillRect((int)x, (int)y, 40, 40);

	}

}
