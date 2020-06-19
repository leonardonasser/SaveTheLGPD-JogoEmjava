package aep.nasser.main;

import java.util.Random;

public class Spawner {

	public int  curTime = 0,targetTime = 60*2;
	
	public Random random;
	
	public Spawner() {
		random = new Random();
	}
	
	public void update() {
		curTime++;
		if(curTime == targetTime) {
			curTime = 0;
			if(random.nextInt(100) < 50) {
			Game.inimigo.add(new Inimigo(random.nextInt(Game.WIDTH - 40),-40));	
			}
			else
			{
			Game.inimigo.add(new Inimigo(random.nextInt(Game.WIDTH - 40),Game.HEIGHT-40));	
			}
		}
	}

}
