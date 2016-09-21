class Frog{
    private String color="Green";
    private int positionx=3;
    private int positiony=3;
    private boolean croak=false;
    private String o="";
    public Frog(int posx, int posy){
        positionx=posx;
        positiony=posy;
    }

    public void setCroak(boolean boo){
        croak=boo;
        return;
    }

    public void downright(){
        positionx++;
        positiony++;
        return;
    }
    public void downleft(){
        positionx--;
        positiony++;
        return;
    }
    public void upright(){
        positionx++;
        positiony--;
        return;
    }
    public void upleft(){
        positionx--;
        positiony--;
        return;
    }
    public void left(){
        positionx--;
        o="left";
        return;
    }
    public void right(){
        positionx++;
        o="right";
        return;
    }
    public void up(){
        positiony--;
        o="up";
        return;
    }
    public void down(){
        positiony++;
        o="down";
        return;
    }
    public void home(){
        positionx=0;
        return;
    }
    public void jump(){
        System.out.println("Jumped");

        return;
    }
    public void brown(){
        color="Brown";
        return;
    }
    public void green(){
        color="Green";
    }
    public boolean croak(){
        //if(croak){System.out.println("Croak");}
        return croak;
    }
    public int getPositionx(){
        return positionx;
    }
    public int getPositiony(){
        return positiony;
    }
    public String getColor(){
        return color;
    }
    public String getOrientation(){
        return o;
    }
    public void setPosition(int position){
        positionx=position;
        return;
    }
}
