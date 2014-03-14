import java.awt.*;

/*
 *  EN ESTA CLASE DECLARO CADA BLOQUE
 *  especifico como es cada cuadrado q compone la ROOM
 *  y ademas especifico la imagen que se debe cargar en cada block
 */


public class Block extends Rectangle {
	
	public int groundID;
	public int airID;

	public Block(int x, int y, int width, int height, int groundID, int airID) {
		setBounds(x, y, width, height);
		this.groundID = groundID;
		this.airID = airID;
	}
	
	public void draw(Graphics g){
		//g.drawRect(x, y, width, height);
		g.drawImage(Screen.tileset_ground[groundID], x, y, width, height, null);
		
		
		//aqui compruebo si debo pintar la imagen
		if(airID != Value.airAir){
			g.drawImage(Screen.tileset_air[airID], x, y, width, height, null);
		}
	}

}
