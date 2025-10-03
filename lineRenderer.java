import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class lineRenderer extends JFrame implements KeyListener{
    public static boolean leftKey;
    public static boolean rightKey;
    public static boolean upKey;
    public static boolean downKey;
    public static boolean wKey;
    public static boolean aKey;
    public static boolean sKey;
    public static boolean dKey;
    public static int width = 10;
    public static int height = 10;
    public static String[] pixels = new String[width * height];
    public static String black = "\u001B[47m  ";
    public static String white = "\u001B[100m  ";

    public static float x1 = 0;
    public static float y1 = -3;
    public static float x2 = 3;
    public static float y2 = 0;
    
    public lineRenderer(){
        this.setTitle("Line Renderer");
        this.setSize(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(this);
        this.setVisible(true);
        gameLoop();
    }

    public static void main(String[] args){
        new lineRenderer();
    }

    static void gameLoop(){
        while (1==1){
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            for (int i = 0; i < pixels.length; i++){
                pixels[i] = white;
            }

            //movement for point 1
            if (dKey && x1 < 9){
                x1++;
            }
            if (aKey && x1 > 0){
                x1--;
            }
            if (wKey && y1 < 0){
                y1++;
            }
            if (sKey && y1 > -9){
                y1--;
            }

            //movement for point 2
            if (rightKey && x2 < 9){
                x2++;
            }
            if (leftKey && x2 > 0){
                x2--;
            }
            if (upKey && y2 < 0){
                y2++;
            }
            if (downKey && y2 > -9){
                y2--;
            }

            setPixel(x1, y1, black);
            setPixel(x2, y2, black);

            drawLine(x1, y1, x2, y2);

            for (int i = 0; i < height; i++){
                for (int j = 0; j < width; j++){
                    System.out.print(pixels[i * width + j]);
                }
                System.out.println("\u001B[0m");
            }

            try{
                Thread.sleep(100);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    static void drawLine(float x1, float y1, float x2, float y2){
        float slope = 0;
        float x;
        float y;

        if (x2 > x1 && y2 > y1){
            slope = (y2 - y1)/(x2 - x1);
        }
        else if (x2 < x1 && y2 > y1){
            slope = (y2 - y1)/(x1 - x2);
        }
        else if (x2 > x1 && y2 < y1){
            slope = (y1 - y2)/(x2 - x1);
        }
        else if (x2 < x1 && y2 < y1){
            slope = (y1 - y2)/(x1 - x2);
        }

        if (slope <= 1){
            if (y2 > y1){
                y = y1;
            }
            else{
                y = y2;
            }
            
            if (x2 > x1){
                for (float X = x1; X < x2; X++){
                    setPixel(X, y, black);
                    y += slope;
                }
            }
            else{
                for (float X = x2; X < x1; X++){
                    setPixel(X, y, black);
                    y += slope;
                }
            }
        }
        else{
            
            if (x2 > x1 && y2 > y1){
                slope = (x2 - x1)/(y2 - y1);
            }
            else if (x2 < x1 && y2 > y1){
                slope = (x1 - x2)/(y2 - y1);
            }
            else if (x2 > x1 && y2 < y1){
                slope = (x2 - x1)/(y1 - y2);
            }
            else if (x2 < x1 && y2 < y1){
                slope = (x1 - x2)/(y1 - y2);
            }
            
            if (x2 > x1){
                x = x1;
            }
            else{
                x = x2;
            }
            
            if (y2 > y1){
                for (float Y = y1; Y < y2; Y++){
                    setPixel(x, Y, black);
                    x += slope;
                }
            }
            else{
                for (float Y = y2; Y < y1; Y++){
                    setPixel(x, Y, black);
                    x += slope;
                }
            }
        }
    }
    
    static void setPixel(float x, float y, String color){
        pixels[Math.round(Math.abs(y)) * width + Math.round(x)] = color;
    }

    public void keyTyped(KeyEvent e){

    }
    
    public void keyPressed(KeyEvent e){
        switch (e.getKeyCode()){
                case 37: leftKey = true;
                break;
                case 38: upKey = true;
                break;
                case 39: rightKey = true;
                break;
                case 40: downKey = true;
                break;
                case 87: wKey = true;
                break;
                case 65: aKey = true;
                break;
                case 83: sKey = true;
                break;
                case 68: dKey = true;
                break;
        }
    }

    public void keyReleased(KeyEvent e){
        switch (e.getKeyCode()){
                case 37: leftKey = false;
                break;
                case 38: upKey = false;
                break;
                case 39: rightKey = false;
                break;
                case 40: downKey = false;
                break;
                case 87: wKey = false;
                break;
                case 65: aKey = false;
                break;
                case 83: sKey = false;
                break;
                case 68: dKey = false;
                break;
        }
    }
}