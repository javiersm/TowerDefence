import java.awt.Color;

import javax.swing.*;

import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Image.*;
import java.io.*;



public class Screen extends JPanel implements Runnable {  //PARA HACER EL GAME LOOP NECESITO ESTE q implemente RUNABLE

	
	public Thread thread = new Thread(this);
	public static boolean isFirst = true;
	
	// creo el array donde cargare las imagenes del fondo y del aire
	public static Image[] tileset_ground = new Image[100];
	public static Image[] tileset_air = new Image[100];
	public static Image[] tileset_res = new Image[100]; 
	public static Image[] tileset_enemy = new Image[100];
	
	
	public static int myWidth, myHeight;
	public static int coins = 10;
	public static int vida = 100;
	
	public static Room room;
	public static Save save;
	public static Store store;
	public static Enemy[] enemigos= new Enemy[100];
	
	//aqui se guarda la posicion del raton
	public static Point mouse = new Point(0,0);
	
	
	public Screen(Frame frame) {	
		
		//Al crear la Screen le tengo que pasar un objeto de tipo Frame para poder recoger los eventos de el
		frame.addMouseListener(new KeyHandler());
		frame.addMouseMotionListener(new KeyHandler());
		
		thread.start();
	}
	
	public void define(){
		//aqui definiremos los niveles (los bloques q se van a ver, las fisicas, etc)
		room = new Room();
		save = new Save();
		store = new Store();
			
		for(int i=0; i<tileset_ground.length; i++){
			
			tileset_ground[i] = new ImageIcon("res/tileset_ground.png").getImage();
			//tileset_ground[i] = new ImageIcon(getClass().getClassLoader().getResource("res/corazon.png"));		
			tileset_ground[i] = createImage(new FilteredImageSource(tileset_ground[i].getSource(), new CropImageFilter(0, 26*i, 26, 26)));
		}
		
		for(int i=0; i<tileset_air.length; i++){
			tileset_air[i] = new ImageIcon("res/tileset_air.png").getImage();
			tileset_air[i] = createImage(new FilteredImageSource(tileset_air[i].getSource(), new CropImageFilter(0, 26*i, 26, 26)));
		}
	
		//tileset_res[0] =  = new ImageIcon(this.getClass().getClassLoader().getResource("res\\store.png"));
		//ImageIcon fillingIcon = new ImageIcon(getClass().getClassLoader().getResource("res/store.png"));
		tileset_res[0] = new ImageIcon("res/store.png").getImage(); //En este array de imagenes guardo la imagen de la tienda
		tileset_res[1] = new ImageIcon("res/corazon.png").getImage(); // guardo el corazon
		tileset_res[2] = new ImageIcon("res/moneda.png").getImage(); // guardo la imagen moneda
		tileset_enemy[0] = new ImageIcon("res/enemy1.png").getImage();
	
		save.loadSave(new File("save/mision1.ulixava"));
		
		// aqui es donde se crean los enemigos
		for(int i=0; i<enemigos.length; i++){
			enemigos[i] = new Enemy();
			//enemigos[i].generaEnemy(0);
		}
	
		
	}
	
	
	public void paintComponent(Graphics g){
		if(isFirst){
			myWidth = getWidth();
			myHeight = getHeight();
			define(); //va a definir todos los niveles
			isFirst = false;
		}
		
		//color del fondo 
		g.setColor(new Color(50,50,50));
		//borra la pantalla despues de hacer cosas en la pantalla
		g.fillRect(0, 0, getWidth(), getHeight());
		//voy a hacerle un marco al juego
		g.setColor(Color.BLACK);
		g.drawLine(room.block[0][0].x-1, 0, room.block[0][0].x-1, room.block[room.worldHeight-1][0].y +room.blockSize); //marco izquierdo
		g.drawLine(room.block[0][room.worldWidth-1].x + room.blockSize, 0, room.block[0][room.worldWidth-1].x + room.blockSize, room.block[room.worldHeight-1][0].y +room.blockSize); //marco derecho
		g.drawLine(room.block[0][0].x, room.block[room.worldHeight-1][0].y + room.blockSize, room.block[0][room.worldWidth-1].x + room.blockSize, room.block[room.worldHeight-1][0].y + room.blockSize); //marco inferior
		
		room.draw(g); //dibuja el mapa del juego 
		
		
		//dibujo los enemigos justo despues del mapa
		for(int i=0; i<enemigos.length; i++){
			if(enemigos[i].inGame){
				enemigos[i].draw(g);
			}
		}
		
		store.draw(g); //dibuja la tienda del juego
	}


	public int spawnTime = 2400, spawnFrame = 0;
	public void creadorDeEnemigos(){
		if(spawnFrame >= spawnTime){
			for(int i=0;i<enemigos.length;i++){
				if(!enemigos[i].inGame){
					enemigos[i].generaEnemy(0);
					break;
				}
			}
			spawnFrame = 0;
		}else{
			spawnFrame += 1;
		}
	}
	
	
	// GAME LOOP   (lo consigo implementando el runnable)
	public void run() {
		
		while(true){
			
			if(!isFirst){
				room.physic();
				creadorDeEnemigos();
				for(int i=0; i<enemigos.length; i++){
					if(enemigos[i].inGame){
						enemigos[i].physic();
					}
				}
			}
						 
			repaint(); //el metodo repaint siempre se llama desde este metodo
			
			try{
				Thread.sleep(1);
			} catch(Exception e){}
		}
		
	}
	
}











