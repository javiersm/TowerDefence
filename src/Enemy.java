import java.awt.*;

public class Enemy extends Rectangle {
	public int xC, yC;
	public int enemySize = 52;
	public int enemyWalk = 0;
	public int left = 0, right = 0, haciaArriba = 0, haciaAbajo = 0;
	public int enemyID = Value.enemyAir;
	public boolean inGame = false; // para cuando no este ya en el juego
	
	public Enemy() {
		
	}
	
	public void generaEnemy(int enemyID){
		//comprobar donde va a empezar el enemigo
			for(int y=0; y<Screen.room.block.length; y++){
				if(Screen.room.block[y][0].groundID == Value.groundRoad){
					setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, enemySize, enemySize);
					xC = 0;
					yC = y; //esto es para saber en que posicion esta cada enemigo
				}
			}
			this.enemyID = enemyID;
			inGame = true; //se ha creado un nuevo enemigo	
			
	}
	
	public int walkFrame = 0, walkSpeed = 40;
	public void physic(){
		if(walkFrame >= walkSpeed){
			x += 1;
			walkFrame = 0;
		}else{
			walkFrame += 1;
		}
		
		
	}

	public void draw(Graphics g){
			g.drawImage( Screen.tileset_enemy[enemyID], x, y, width, height, null);
		
		
	}
	
	
}
