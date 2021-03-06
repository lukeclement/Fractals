import javafx.application.Application;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.canvas.*;
import javafx.animation.*;
import javafx.scene.image.*;
import java.util.*;
import java.awt.image.*;
import javafx.scene.input.*;
import javafx.event.*;
import javafx.scene.paint.*;
import java.io.*;
import javax.imageio.*;
import javafx.embed.swing.*;

public class main extends Application{
    public int width=1150;
    public int height=1000;
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage mainStage){
        Scanner scan=new Scanner(System.in);
        System.out.println("Which Fractal? Or maybe experiment?");
        System.out.println("[0] Dragon");
        System.out.println("[1] Mandelbrot");
        System.out.println("[2] Modulo times tables");

        int opt=scan.nextInt();
        if(opt==0){
            mainStage.setTitle("Dragon");
        }else if(opt==1){
            mainStage.setTitle("Mandelbrot");
        }else if(opt==2){
          mainStage.setTitle("Times tables");
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
            frog.setPosition(width/2-200,400);
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
        }
        else if(opt==1){
            System.out.println("Press 'e' to zoom into a layer, 'q' to zoom out");
            System.out.println("What colour?");
            System.out.println("[0] Blue");
            System.out.println("[1] Red");
            System.out.println("[2] Green");
            System.out.println("[3] Black");
            int col=scan.nextInt();
            List<Frog> frogs=new ArrayList<>();
            for(int i=0;i<width;i++){
                for(int j=0;j<height/10;j++){
                    frogs.add(new Frog(i,j*10));
                }
                /*
                if(i<width){
                  frogs.add(new Frog(i,0));
                }else if(i<width*2){
                  frogs.add(new Frog(i-width,height*1/4));
                }else if(i<width*3){
                  frogs.add(new Frog(i-width*2,height*2/4));
                }else{
                  frogs.add(new Frog(i-width*3,height*3/4));
                }*/
            }
            final long startNanoTime=System.nanoTime();
            Image back=new Image("back.png");
            new AnimationTimer(){
                public int multiplier=100;
                public double centerX=(double)width/2;
                public double centerY=(double)height/2;
                public void handle(long currentNanoTime){
                    if(input.contains("E")){
                        multiplier++;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("Q")){
                        multiplier--;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("C")){
                        multiplier=multiplier+10;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("Z")){
                        multiplier=multiplier-10;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("RIGHT")){
                        centerX--;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("LEFT")){
                        centerX++;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("UP")){
                        centerY++;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("DOWN")){
                        centerY--;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("D")){
                        centerX=centerX-100;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("A")){
                        centerX=centerX+100;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("W")){
                        centerY=centerY+100;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("S")){
                        centerY=centerY-100;
                        //gc.drawImage(back,0,0);
                    }
                    if(input.contains("O")){
                      multiplier=multiplier+1000;
                    }
                    if(input.contains("U")){
                        multiplier=multiplier/2;
                    }
                    if(input.contains("P")&&input.contains("S")){
                      WritableImage wim = new WritableImage(width, height);
                      canvas.snapshot(null,wim);
                      try{
                        ImageIO.write(SwingFXUtils.fromFXImage(wim, null), "png", new File("TestFractal.png"));
			                  System.out.println("Saved!");
                      }
                      catch(Exception ex){

                      }
                    }
                    if(input.contains("P")&&input.contains("L")){

                    }
                    for(int i=0;i<frogs.size();i++){
                        double im=((double)(frogs.get(i).getPositiony()-centerY+mouseY[0])/multiplier);
                        double r=((double)(frogs.get(i).getPositionx()-centerX+mouseX[0])/multiplier);

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
        if(opt==2){
          System.out.println("A and D to switch factors, W and S to switch references");
          System.out.println("Number of reference points?");
          System.out.print(">>");
          final int ref=scan.nextInt();
          System.out.println("Starting factor?");
          System.out.print(">>");
          final double factor=scan.nextDouble();
          Image back=new Image("back.png");
          new AnimationTimer(){
            public void delay(long s){
              long start=System.currentTimeMillis();
              long end=start+s;
              while(end>start){
                start=System.currentTimeMillis();
              }
            }
            public double f=factor;
            public int r=ref;
            public void handle(long t){
              gc.drawImage(back,0,0);
              gc.setFill(Color.rgb(0,0,0));
              gc.setLineWidth(1);

              if(input.contains("D")){
                gc.drawImage(back,0,0);
                f+=0.1;
                System.out.println(f);
                delay(100);
              }
              if(input.contains("A")){
                gc.drawImage(back,0,0);
                f-=0.1;
                System.out.println(f);
                delay(100);
              }
              if(input.contains("W")){
                gc.drawImage(back,0,0);
                r+=1;
                System.out.println(r);
                delay(100);
              }
              if(input.contains("S")){
                gc.drawImage(back,0,0);
                r-=1;
                System.out.println(r);
                delay(100);
              }
              double inc=(2*Math.PI)/(double)r;
              for(int n=0;n<r;n++){
                gc.fillOval((double)450*Math.sin(n*inc)+width/2,(double)450*Math.cos(n*inc)+height/2,1,1);
              }
              for(int fa=0;fa<r;fa++){
                double gotcha=fa*f;
                gc.strokeLine((double)450*Math.sin(fa*inc)+width/2,(double)450*Math.cos(fa*inc)+height/2,(double)450*Math.sin(gotcha*inc)+width/2,(double)450*Math.cos(gotcha*inc)+height/2);
              }
            }
          }.start();
          mainStage.show();
        }

    }
}
