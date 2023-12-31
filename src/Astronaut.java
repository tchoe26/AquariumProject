import java.awt.*;
import java.util.Scanner;

/**
 * Created by chales on 11/6/2017.
 */
public class Astronaut {

    //VARIABLE DECLARATION SECTION
    //Here's where you state which variables you are going to use.
    public String name;                //holds the name of the hero
    public int xpos;                //the x position
    public int ypos;                //the y position


    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;
    public int height;
    public boolean isAlive;
    boolean isBouncing;
    //a boolean to denote if the hero is alive or dead.
    public Rectangle rec;

    // METHOD DEFINITION SECTION

    // Constructor Definition
    // A constructor builds the object when called and sets variable values.


    //This is a SECOND constructor that takes 3 parameters.  This allows us to specify the hero's name and position when we build it.
    // if you put in a String, an int and an int the program will use this constructor instead of the one above.
    public Astronaut(int pXpos, int pYpos, int pWidth, int pHeight, int xSpeed, int ySpeed) {
        xpos = pXpos;
        ypos = pYpos;
        dx = xSpeed;
        dy = ySpeed;
        width = pWidth;
        height = pHeight;
        isAlive = true;
        rec = new Rectangle(xpos, ypos, width, height);
        isBouncing=false;

 
    } // constructor

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;
        rec = new Rectangle(xpos, ypos, width, height);
    }
    public void wrap() {
        if ((xpos+width)<0) {
            xpos=1000;
        }
        if (xpos>1000) {
            xpos= -width;
        }
        if ((ypos+height)<0) {
            ypos=700;
        }
        if (ypos>700) {
            ypos = -height;
        }
        move();
    }
    public void bounce() {
       if ((xpos<(width)||xpos>(1000-width)) && !isBouncing) {
           dx = -dx;
           isBouncing=true;
       }
       if (!(xpos<(width)||xpos>(1000-width))) {
           isBouncing=false;
       }
        if ((ypos<0) && !isBouncing) {
            dy = -dy;
            System.out.println(ypos);
            ypos=0;
            isBouncing=true;
        }
        if (ypos>(690-height)) {
            dy=-dy;
            ypos = 690-height;
            isBouncing=true;
        }
        if (!(ypos<10||ypos>(690-height))) {
            isBouncing = false;
        }
        move();
    }
}






