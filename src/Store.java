import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Store {

	//cantidad de cuadrados para poner las torres del juego
	public static int shopWidth = 8;
	public static int buttonSize = 52; //pixels
	public static int espacioBotones = 3;
	public static int espacioRoom = 27;
	public static int iconSize = 20;
	public static int iconSpace = 6;
	public static int iconTextY = 15;
	
	//hago un par de botones
	public Rectangle[] button = new Rectangle[shopWidth];
	// para guardar la vida y monedas
	public Rectangle buttonHealth;
	public Rectangle buttonCoins;

	public Store() {
		define();
	}
	
	
	
	public void define(){
		//inicializo la posicion de los botones de la tienda
		for(int i=0; i<button.length; i++){
			button[i] = new Rectangle((
										Screen.myWidth/2) - ((shopWidth * (buttonSize+espacioBotones))/2) + ((buttonSize + espacioBotones)*i), //se crean los botones en el centro de la pantalla
										(Screen.room.block[Screen.room.worldHeight-1][0].y) + Screen.room.blockSize + espacioRoom, //baja los botones debajo del room;
										buttonSize, 
										buttonSize);	
		}
		
		//inicializo donde va a aparecer los iconos d la vida y monedas
		buttonHealth = new Rectangle(Screen.room.block[0][0].x -1, button[0].y, iconSize, iconSize);
		buttonCoins = new Rectangle(Screen.room.block[0][0].x -1, button[0].y + button[0].height - iconSize, iconSize, iconSize);
		
	}

	public void draw(Graphics g){
		for(int i=0; i<button.length; i++){
			//cuando el raton este sobre la imagen ke se ilumine
			if(button[i].contains(Screen.mouse)){
				g.setColor(new Color(255,255,255, 100));
				g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
				
			}
			//g.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
 			
			//dibujo los botones de la tienda con un dibujo dentro 
			g.drawImage(Screen.tileset_res[0], button[i].x, button[i].y, button[i].width, button[i].height, null);
		}
		
		
		//g.fillRect(buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height); //antes estaba este que no tenia imagen
		//dibujo el boton/icono de la vida
		g.drawImage(Screen.tileset_res[1], buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height, null);
		//dibujo el icono de monedas
		g.drawImage(Screen.tileset_res[2], buttonCoins.x, buttonCoins.y, buttonCoins.width, buttonCoins.height, null);
	
		//cambio la fuente para escribir la cantidad de monedas y vida
		g.setFont(new Font("Courier New", Font.BOLD, 14));
		g.setColor(new Color(255,255,255));
		g.drawString("" + Screen.vida, buttonHealth.x + buttonHealth.width + iconSpace, buttonHealth.y + iconTextY);
		g.drawString("" + Screen.coins, buttonCoins.x + buttonCoins.width + iconSpace, buttonCoins.y + iconTextY);
	    
	}
}

















