

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
/**
 * GamePanel
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    // private static final long serialVersionUID = 1L;
    public static final int WIDTH = 400;
    public static final int HEIGHT = 400;

    //Render
    private Graphics2D g2d;
    private BufferedImage image;

    //Game loop
    private Thread thread;
    private boolean running;
    private long targetTime;

    //Game Stuff
    private final int  SIZE = 10;
    private Entity head, apple, body;
    private ArrayList<Entity> snake, wall, wall2, wall3, wall4, wall5, wall6, wall7, wall8;
    private boolean gameover;
    private int score;
    private int level;
    private int lvlscore; 

    
    //movement
    private int dx, dy;
    //key input
    private boolean up, down, right, left, start, pause;


    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
        addKeyListener(this);
    }

    @Override
	public void addNotify() {
        super.addNotify();
        thread = new Thread(this);
        thread.start();
    }

    private void setFPS(int fps) {
        targetTime = 1000/fps;
    }

    @Override
	public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_UP) {
            up = true;
        } if (k == KeyEvent.VK_DOWN) {
            down = true;
        } if (k == KeyEvent.VK_LEFT) {
            left = true;
        } if (k == KeyEvent.VK_RIGHT) {
            right = true;
        } if (k == KeyEvent.VK_ENTER) {
            start = true;
        } if (k == KeyEvent.VK_SPACE) {
            pause = true;
        }
    }

    @Override
	public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_UP) {
            up = false;
        } if (k == KeyEvent.VK_DOWN) {
            down = false;
        } if (k == KeyEvent.VK_LEFT) {
            left = false;
        } if (k == KeyEvent.VK_RIGHT) {
            right = false;
        } if (k == KeyEvent.VK_ENTER) {
            start = false;
        } if (k == KeyEvent.VK_SPACE) {
            pause = false;
        } 
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    public void run() {
		if(running) return;
		init();
		long startTime;
		long elapsed;
		long wait;
		while(running){
			startTime = System.nanoTime();
			
			update();
			requestRender();
			
			elapsed = System.nanoTime() - startTime;
			wait = targetTime - elapsed / 1000000;
			if(wait> 0){
				try{
					Thread.sleep(wait);
				}catch(Exception e){
					e.printStackTrace();
					
				}
			}
			
		} 
    }

    private void init() {
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        g2d = image.createGraphics();
        running = true;
        setUpLevel1();
        gameover = false;
        setFPS(10);
    }

    private void setUpLevel1() {
        pause = false;
        gameover = false;
        snake = new ArrayList<Entity>();
        head = new  Entity(SIZE);
        head.setPosition(WIDTH/2, HEIGHT/2);
        snake.add(head);

        for (int i = 1; i < 10; i++) {
            Entity e = new Entity(SIZE);
            e.setPosition(head.getX() + (i * SIZE), head.getY());
            snake.add(e);
        }

        apple = new Entity(SIZE);
        setApple();
        score = 0;
		lvlscore=0;
		level=0;
        setFPS(10);
        dy = 0;
        dx = 0 ;
    }

    public void setApple() {
        int x = (int)(Math.random() * (WIDTH - SIZE));
        int y = (int)(Math.random() * (HEIGHT - SIZE));
        y = y - (y % SIZE);
        x = x - (x % SIZE);
        apple.setPosition(x, y);

        for (Entity e : snake) {
            if (e.isCollison(apple)) {
                setApple();
            }
        }
        if (level > 0) {
			for(Entity e: wall){
				if(e.isCollison(apple)){
					setApple();
				}
			}
			for(Entity e: wall2){
				if(e.isCollison(apple)){
					setApple();
				}
			}
			for(Entity e: wall3){
				if(e.isCollison(apple)){
					setApple();
				}
			}
			for(Entity e: wall4){
				if(e.isCollison(apple)){
					setApple();
				}
			}
			for(Entity e: wall5){
				if(e.isCollison(apple)){
					setApple();
				}
			}
			for(Entity e: wall6){
				if(e.isCollison(apple)){
					setApple();
				}
			}
			for(Entity e: wall7){
				if(e.isCollison(apple)){
					setApple();
				}
			}
			for(Entity e: wall8){
				if(e.isCollison(apple)){
					setApple();
				}
			}
		}

    }

    
    private void requestRender() {
        render(g2d);
        Graphics g = getGraphics();
        g.drawImage(image, 0, 0, null);//
        g.dispose();
    }

    public void setWall() {
		if(level >0 ) {
			wall = new ArrayList<Entity>();
			wall2 = new ArrayList<Entity>();
			wall3 = new ArrayList<Entity>();
			wall4 = new ArrayList<Entity>();
			
			wall5 = new ArrayList<Entity>();
			wall6 = new ArrayList<Entity>();
			wall7 = new ArrayList<Entity>();
			wall8 = new ArrayList<Entity>();
			
			for (int i = level; i > 0; i--) {
				int i2 = i * 10;
				Entity e = new Entity(SIZE);
				Entity e2 = new Entity(SIZE);
				Entity e3 = new Entity(SIZE);
				Entity e4 = new Entity(SIZE);
				
				Entity e5 = new Entity(SIZE);
				Entity e6 = new Entity(SIZE);
				Entity e7 = new Entity(SIZE);
				Entity e8 = new Entity(SIZE);
				
				int x = WIDTH-10;
				int y = HEIGHT-10;
				int a = 0;
				int b = 0;					
				
				e.setPosition(x, (WIDTH-10) - i2);
				e2.setPosition((HEIGHT-10) -i2, y);
				e3.setPosition(a, i2);
				e4.setPosition(i2,b);
				
				e5.setPosition(x,a+i2);
				e6.setPosition(b+i2,y);
				e7.setPosition(a, x-i2);
				e8.setPosition(y-i2,b);
				
				wall.add(e);
				wall2.add(e2);
				wall3.add(e3);
				wall4.add(e4);
				
				wall5.add(e5);
				wall6.add(e6);
				wall7.add(e7);
				wall8.add(e8);
			}
		}
    }  
        
    private void update() {
        if (gameover) {
            if (start) {
                setUpLevel1();
            }
            return;
        }
        if (up && dy == 0 ) {
            dy = -SIZE;
            dx = 0; 
            left = false;
            right = false;
        }
        if (down && dy == 0) {
            dy = SIZE;
            dx = 0;
            left = false;
            right = false;
        }
        if (left && dx == 0) {
            dy = 0;
            dx = - SIZE;
            up = false;
            down = false;
        }
        if (right && dx == 0) {
            dy = 0;
            dx = SIZE;
            up = false;
            down = false;
        }

        if (start) {
            setUpLevel1();
        } if (pause) {
			dx = 0;
			dy = 0;
		}

        if (dx != 0 || dy != 0) {
            for (int i = snake.size()-1; i > 0; i--) {
                snake.get(i).setPosition(snake.get(i - 1).getX(), snake.get(i - 1).getY());  
            }
            head.move(dx, dy);
        }

        if (apple.isCollison(head)) {
            setApple();
            score++;
			lvlscore++;
            Entity e = new Entity(SIZE);
            e.setPosition(head.getX(), head.getY());
            snake.add(e);  
            if (lvlscore>4) {
				level++;
				setFPS(10+level);
				lvlscore=0;
				setWall();	
			}         
        }

        if (head.getX()<0) { 
            head.setX(WIDTH);
        } if (head.getY()<0) {
            head.setY(HEIGHT);
        } if (head.getX()>WIDTH) {
             head.setX(0);
        } if (head.getY()>HEIGHT) {
             head.setY(0);
        }
        //event game over
        for (int i = snake.size() -2 ;i > 0;i--) {
			body = snake.get(i);
		    if (body.isCollison(head)) {
                System.out.println("Hit!");
                gameover = true;
                break;
            }
        }
        if (level > 0) {
            for (Entity e: wall) {
                if (e.isCollison(head)) {
                    gameover = true;
                    break;
                }
            }
            for (Entity e: wall2) {
                if(e.isCollison(head)){
                    gameover = true;
                    break;
                }
            }
            for (Entity e: wall3) {
                if (e.isCollison(head)) {
                    gameover = true;
                    break;
                }
            }
            for (Entity e: wall4) {
                if (e.isCollison(head)) {
                    gameover = true;
                    break;
                }
            }
            for (Entity e: wall5) {
                if (e.isCollison(head)) {
                    gameover = true;
                    break;
                }
            }
            for (Entity e: wall6) {
                if (e.isCollison(head)) {
                    gameover = true;
                    break;
                }
            }
            for (Entity e: wall7) {
                if (e.isCollison(head)) {
                    gameover = true;
                    break;
                }
            }
            for (Entity e: wall8) {
                if (e.isCollison(head)) {
                    gameover =true;
                    break;
                }
            }
        }
            
    }


    public void render(Graphics2D g2d){
        g2d.clearRect(0, 0, WIDTH, HEIGHT);
        g2d.setColor(Color.BLUE);
        for (Entity e : snake) {
            e.render(g2d);
        }
        g2d.setColor(Color.RED);
        apple.render(g2d);

        g2d.setColor(Color.WHITE);
		g2d.drawString("Score : " + score, 10, 20);
		
		g2d.setColor(Color.WHITE);
		g2d.drawString("Level : " + level, 10, 35);
		if(gameover){						
			g2d.setColor(Color.WHITE);
			g2d.drawString("To restart press Enter button.",WIDTH /3, HEIGHT / 2 + 15);
			g2d.setFont(new Font("TimesRoman", Font.BOLD, 15)); 
			g2d.setColor(Color.RED);
			g2d.drawString("Game over!", WIDTH /3, HEIGHT / 2);
		}
		if(pause){
			g2d.setColor(Color.WHITE);
			g2d.drawString("Game paused!", WIDTH /3, HEIGHT / 2);
		}
		
		if(level>0){
			System.out.println("wall" + wall);
			g2d.setColor(Color.GRAY);
			for(Entity e : wall){
			e.render(g2d);
			}
			for(Entity e : wall2){
				e.render(g2d);
			}
			for(Entity e : wall3){
				e.render(g2d);
			}
			for(Entity e : wall4){
				e.render(g2d);
			}
			for(Entity e : wall5){
				e.render(g2d);
			}
			for(Entity e : wall6){
				e.render(g2d);
			}
			for(Entity e : wall7){
				e.render(g2d);
			}
			for(Entity e : wall8){
				e.render(g2d);
			}
		
		}
		
	}

    

    
}