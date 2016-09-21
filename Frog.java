class Frog{
    private int positionx=0;
    private int positiony=0;
    private String o="";
    public Frog(int posx, int posy){
        positionx=posx;
        positiony=posy;
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
    public int getPositionx(){
        return positionx;
    }
    public int getPositiony(){
        return positiony;
    }
    public String getOrientation(){
        return o;
    }
    public void setPosition(int positionX,int positionY){
        positionx=positionX;
        positiony=positionY;
        return;
    }
}
