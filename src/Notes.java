import java.awt.event.KeyEvent;

public class Notes {
    public int x;
    public int y;
    public Notes(int x, int y){
        this.x =x;
        this.y =y;
    }
    public boolean hit(int key){
        //int key = e.getKeyCode();
        if(this.y>=450 & this.y<=500){
            //j
            if(key==74 & this.x==450){
                return true;
            }
            //k
            else if(key==75 & this.x==550){
                return true;
            }
            //d
            else if(key==68 & this.x==250) {
                return true;
            }
            //f
            else if(key==70 & this.x==350){
                return true;
            }
            else{return false;}
        }
        else{
            return false;
        }
    }
}
