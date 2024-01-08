//Basic Game Application
//Version 2
// Basic Object, Image, Movement
// Astronaut moves to the right.
// Threaded

//K. Chun 8/2018

//*******************************************************************************
//Import Section
//Add Java libraries needed for the game
//import java.awt.Canvas;

//Graphics Libraries
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//*******************************************************************************
// Class Definition Section

public class BasicGameApp implements Runnable {

   //Variable Definition Section
   //Declare the variables used in the program 
   //You can set their initial values too
   
   //Sets the width and height of the program window
	final int WIDTH = 1000;
	final int HEIGHT = 700;

   //Declare the variables needed for the graphics
	public JFrame frame;
	public Canvas canvas;
   public JPanel panel;
   public Color c = new Color (255, 255, 255);
   public int scoreCounter1=0;
   public String scoreCounterString1= "0";
   public int scoreCounter2=0;
   public String scoreCounterString2= "0";
   public boolean isPowerUp1 = false;
	public BufferStrategy bufferStrategy;
	public Image powerUp1Pic;
	public Image paddle1Pic;
	public Image paddle2Pic;
	public Image PongBallPic;
	public Image blackBackground;

   //Declare the objects used in the program
   //These are things that are made up of more than one variable type
	private Astronaut paddle1;
	private Astronaut paddle2;
	private Astronaut pongBall;
	private Astronaut powerUp1;
	public int paddle1Random;
	public int paddle2Random;
	public int powerUp1Random;
	public int whoHitLast;


	public void waitForPowerUp() {
		//power up
		if (isPowerUp1) {
			if (pongBall.rec.intersects(powerUp1.rec)) {

				System.out.println("power up trigger");
				if (whoHitLast ==2)
					paddle2.height = paddle2.height*2;
				if (whoHitLast ==1)
					paddle1.height = paddle1.height*2;
				isPowerUp1 = false;
			}
			System.out.println(pongBall.rec);
			System.out.println(powerUp1.rec);


			System.out.println("waiting to be hit");

		}
		else {
			powerUp1Random = (int)(Math.random()*50);
			System.out.println(powerUp1Random);
			if (powerUp1Random == 1) {
				isPowerUp1 = true;
				//powerUp1.xpos = (int)(Math.random()*900) +50;
				//powerUp1.ypos = (int)(Math.random()*600) +50;
				System.out.println("power up appears");
				System.out.println(isPowerUp1);
			}

		}
	}

	//Pauses or sleeps the computer for the amount specified in milliseconds
	public void pause(int time ){
		//sleep
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {

		}
	}


   // Main method definition
   // This is the code that runs first and automatically
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();   //creates a new instance of the game
		new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method  
	}


   // Constructor Method
   // This has the same name as the class
   // This section is the setup portion of the program
   // Initialize your variables and construct your program objects here.
	public BasicGameApp() {
      
      setUpGraphics();
       
      //variable and objects
      //create (construct) the objects needed for the game and load up 
		paddle1Pic = Toolkit.getDefaultToolkit().getImage("PongPaddle.png"); //load the picture
		paddle1 = new Astronaut(960, (int) ((Math.random())*700), 10, 100, 0, 8);
		paddle2Pic = Toolkit.getDefaultToolkit().getImage("PongPaddle.png");
		paddle2 = new Astronaut(30, (int) ((Math.random())*700), 10, 100, 0, 8);
		PongBallPic = Toolkit.getDefaultToolkit().getImage("PongBall-01.png");
		pongBall = new Astronaut (400,400, 10, 10, 10, 10);
		powerUp1Pic = Toolkit.getDefaultToolkit().getImage("lightningsymbol.jpg");
		powerUp1 = new Astronaut ((int)(Math.random()*900)+50, ((int)(Math.random()*600)+50), 50, 50, (int)(Math.random()*5), (int)(Math.random()*5));
		blackBackground = Toolkit.getDefaultToolkit().getImage("blackBackground.png");

	}// BasicGameApp()

   
//*******************************************************************************
//User Method Section
//
// put your code to do things here.

   // main thread
   // this is the code that plays the game after you set things up
	public void run() {

      //for the moment we will loop things forever.
		while (true) {

         moveThings();  //move all the game objects
         render();  // paint the graphics
         pause(20); // sleep for 10 ms
		}
	}


	public void moveThings()
	{
		waitForPowerUp();
		if (pongBall.dx>0)
			whoHitLast=2;
		if (pongBall.dx<0)
			whoHitLast=1;

      //calls the move( ) code in the objects
		paddle1.bounce();
		paddle2.bounce();
		pongBall.bounce();
		powerUp1.wrap();



		// pong ball bounces off paddles
		if (pongBall.rec.intersects(paddle1.rec) || pongBall.rec.intersects(paddle2.rec)) {
			pongBall.dx=-pongBall.dx;
		}

		//paddles switch direction randomly
		paddle1Random = (int)(Math.random()*50);
		paddle2Random = (int)(Math.random()*50);
		if (paddle1Random==1) {
			paddle1.dy = -paddle1.dy;
			System.out.println("paddle 1 switch");
		}
		if (paddle2Random==1) {
			paddle2.dy = -paddle2.dy;
			System.out.println("paddle 2 switch");
		}

		//scoring mechanism
		if (pongBall.xpos > 990) {
			scoreCounter1++;
			scoreCounterString1 = String.valueOf(scoreCounter1);
			System.out.println(scoreCounterString1);
			pongBall.xpos = 500;
			pongBall.ypos = 350;

		}
		if (pongBall.xpos < 10) {
			scoreCounter2++;
			scoreCounterString2 = String.valueOf(scoreCounter2);
			System.out.println(scoreCounterString2);
			pongBall.xpos = 500;
			pongBall.ypos = 350;
		}



	}



   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();  
      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
		Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
		g.clearRect(0, 0, WIDTH, HEIGHT);

      //draw the image of the astronaut
		g.drawImage(blackBackground, 0, 0, WIDTH, HEIGHT, null);
		if (isPowerUp1) {
			g.drawImage(powerUp1Pic, powerUp1.xpos, powerUp1.ypos, powerUp1.width, powerUp1.height, null);
		}
		g.drawImage(paddle1Pic, paddle1.xpos, paddle1.ypos, paddle1.width, paddle1.height, null);
		g.drawImage(PongBallPic, pongBall.xpos, pongBall.ypos, pongBall.width, pongBall.height, null);
		g.drawImage(paddle2Pic, paddle2.xpos, paddle2.ypos, paddle2.width, paddle2.height, null);

		//scoreboard

		g.setFont(new Font("Ser", Font.PLAIN, 50));
		g.setColor(c);
		g.drawString(scoreCounterString1, 250, 50);
		g.drawString(scoreCounterString2, 750, 50);


		g.dispose();

		bufferStrategy.show();
	}
}
