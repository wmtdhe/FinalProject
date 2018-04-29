package rhythmGame;

/**
 * @author szheng20
 */

public class Notes {
    public int x;
    public int y;//current y position (with offset)
    public int original_y;
    public int pass;//passed the target line
    public int read;
    public int hit;//This is the "score counted"
    public boolean counted;//missed and have decreased the health

    public Notes(int x, int y){
        this.x =x;
        this.y =y;
        this.pass=0;
        this.read=0;
        this.hit=0;
        this.original_y = y;
        this.counted = false;
    }

    /**
     * notes used or not
     */
    public void passed(){
        if(this.y>500){
            this.pass=1;
        }
    }

    /**
     *
     * @param key key pressed
     * @return hit or miss
     */
    public boolean hit(int key){
        //int key = e.getKeyCode();
        if(this.y>=450 & this.y<=500){
            //j
            if(key==74 & this.x==450){
                this.hit=1;
                return true;
            }
            //k
            else if(key==75 & this.x==550){
                this.hit=1;
                return true;
            }
            //d
            else if(key==68 & this.x==250) {
                this.hit=1;
                return true;
            }
            //f
            else if(key==70 & this.x==350){
                this.hit=1;
                return true;
            }
            else{return false;}
        }
        else{
            return false;
        }
    }
}