import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.animation.*;
import javafx.scene.image.*;
import java.util.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.paint.*;

public class main extends Application{
    public int width=1000;
    public int height=1000;
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage mainStage){
        Scanner scan=new Scanner(System.in);
        System.out.println("Which Fractal?");
        System.out.println("[0] Dragon");
        System.out.println("[1] Mandelbrot");
        int opt=scan.nextInt();
        if(opt==0){
            mainStage.setTitle("Dragon");
        }else if(opt==1){
            mainStage.setTitle("Mandelbrot");
        }
        Group root=new Group();
        Scene scene=new Scene(root);
        mainStage.setScene(scene);

        Canvas canvas=new Canvas(width,height);
        root.getChildren().add(canvas);
        GraphicsContext gc=canvas.getGraphicsContext2D();
        List<String> input=new ArrayList<>();
        double[] mouseX=new double[1];
        double[] mouseY=new double[1];

        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
              String code = e.getCode().toString();
              // only add once... prevent duplicates
              if ( !input.contains(code) )
                  input.add( code );
            }
        });
        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
            public void handle(KeyEvent e){
                String code = e.getCode().toString();
                input.remove( code );
            }
        });
        mouseX[0]=0;
        mouseY[0]=0;
        scene.setOnMouseClicked(new EventHandler<MouseEvent>(){
            public void handle(MouseEvent e){
                double mX=e.getX();
                double mY=e.getY();
                mouseX[0]=mX;
                mouseY[0]=mY;
            }
        });



        Frog frog=new Frog(0,0);
        if(opt==0){
            frog.setPosition(width/2-200,500);
            System.out.println("How many pixles per line?");
            int a=scan.nextInt();
            System.out.println("How many lines? (Tip:10000000 is max)");
            int b=scan.nextInt();
            System.out.println("Multiple colours?");
            System.out.println("[1] Yes");
            System.out.println("[0] No");
            int c=scan.nextInt();


            List<Integer> paces=dragon.lists(b);

            final long startNanoTime=System.nanoTime();
            frog.down();
            new AnimationTimer(){
                public int i=0;
                public int j=0;
                public void handle(long currentNanoTime){
                    gc.fillOval(frog.getPositionx(),frog.getPositiony(),1,1);
                    i++;
                    if(c==1){
                        if(i>(int)Math.pow(2,j)){
                            System.out.println("Done "+(i-1)+" lines (2^"+j+")");
                            j++;
                            switch(j%7){
                                case 0:gc.setFill(Color.rgb(0,0,255));
                                    break;
                                case 1:gc.setFill(Color.rgb(0,255,0));
                                    break;
                                case 2:gc.setFill(Color.rgb(255,0,0));
                                    break;
                                case 3:gc.setFill(Color.rgb(0,255,255));
                                    break;
                                case 4:gc.setFill(Color.rgb(255,0,255));
                                    break;
                                case 5:gc.setFill(Color.rgb(255,255,0));
                                    break;
                                case 6:gc.setFill(Color.rgb(0,0,0));
                                    break;
                            }

                        }
                    }
                    int direction=3;
                    if(i<paces.size()){
                        direction=paces.get(i);
                    }
                    if(i==paces.size()){
                        System.out.println("Done");
                    }
                    if(frog.getOrientation().equals("up")){
                        switch(direction){
                            case 1:for(int j=0;j<a;j++){
                                    frog.left();
                                    gc.fillOval(frog.getPositionx(),frog.getPositiony(),1,1);
                                }break;
                            case 0:for(int j=0;j<a;j++){
                                    frog.right();
                                    gc.fillOval(frog.getPositionx(),frog.getPositiony(),1,1);
                                }break;
                        }
                    }else if(frog.getOrientation().equals("down")){
                        switch(direction){
                            case 1:for(int j=0;j<a;j++){
                                    frog.right();
                                    gc.fillOval(frog.getPositionx(),frog.getPositiony(),1,1);
                                }break;
                            case 0:for(int j=0;j<a;j++){
                                    frog.left();
                                    gc.fillOval(frog.getPositionx(),frog.getPositiony(),1,1);
                                }break;
                        }
                    }else if(frog.getOrientation().equals("right")){
                        switch(direction){
                            case 1:for(int j=0;j<a;j++){
                                frog.up();
                                gc.fillOval(frog.getPositionx(),frog.getPositiony(),1,1);
                            }break;
                            case 0:for(int j=0;j<a;j++){
                                frog.down();
                                gc.fillOval(frog.getPositionx(),frog.getPositiony(),1,1);
                            }break;
                        }
                    }else if(frog.getOrientation().equals("left")){
                        switch(direction){
                            case 1:for(int j=0;j<a;j++){
                                frog.down();
                                gc.fillOval(frog.getPositionx(),frog.getPositiony(),1,1);
                            }break;
                            case 0:for(int j=0;j<a;j++){
                                frog.up();
                                gc.fillOval(frog.getPositionx(),frog.getPositiony(),1,1);
                            }break;
                        }
                    }
                }
            }.start();
            mainStage.show();
        }else if(opt==1){
            System.out.println("Press 'e' to zoom into a layer, 'q' to zoom out");
            System.out.println("What colour?");
            System.out.println("[0] Blue");
            System.out.println("[1] Red");
            System.out.println("[2] Green");
            System.out.println("[3] Black");
            int col=scan.nextInt();
            List<Frog> frogs=new ArrayList<>();
            for(int i=0;i<width;i++){
                frogs.add(new Frog(i,0));
            }
            final long startNanoTime=System.nanoTime();
            Image back=new Image("back.png");
            new AnimationTimer(){
                public int multiplier=100;
                public double centerX=500;
                public double centerY=500;
                public void handle(long currentNanoTime){
                    if(input.contains("E")){
                        multiplier++;
                        gc.drawImage(back,0,0);
                    }
                    if(input.contains("Q")){
                        multiplier--;
                        gc.drawImage(back,0,0);
                    }
                    if(input.contains("RIGHT")){
                        centerX--;
                        gc.drawImage(back,0,0);
                    }
                    if(input.contains("LEFT")){
                        centerX++;
                        gc.drawImage(back,0,0);
                    }
                    if(input.contains("UP")){
                        centerY--;
                        gc.drawImage(back,0,0);
                    }
                    if(input.contains("DOWN")){
                        centerY++;
                        gc.drawImage(back,0,0);
                    }
                    for(int i=0;i<frogs.size();i++){
                        double im=((((double)frogs.get(i).getPositiony()/multiplier)*-1)+centerY/multiplier)*((200.0/multiplier)/(500.0/multiplier));
                        double r=(((double)frogs.get(i).getPositionx()/multiplier)-centerX/multiplier)*((200.0/multiplier)/(500.0/multiplier));

                        Complex c = new Complex(r,im);
                        Complex z = new Complex(0,0);
                        int loops=0;
                        while((z.getReal()<=2.0E+307||z.getImaginary()<=2.0E+307)&&loops<255){
                            z=(z.square()).plus(c);
                            loops++;
                        }
                        switch(col){
                            case 0: gc.setFill(Color.rgb(0,0,loops));
                            break;
                            case 1: gc.setFill(Color.rgb(loops,0,0));
                            break;
                            case 2: gc.setFill(Color.rgb(0,loops,0));
                            break;
                            case 3: gc.setFill(Color.rgb(255-loops,255-loops,255-loops));
                            break;
                        }
                        gc.fillOval(frogs.get(i).getPositionx(),frogs.get(i).getPositiony(),1,1);
                        if(frogs.get(i).getPositiony()==height){
                            int x=frogs.get(i).getPositionx();
                            frogs.get(i).setPosition(x,-1);
                        }
                        frogs.get(i).down();
                    }

                }
            }.start();
            mainStage.show();
        }

    }
}
