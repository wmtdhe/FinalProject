public class Achievements {
    //public boolean[] achievements;
    public int score;
    public int count;

    public Achievements(int count, int score) {
        this.count=count;
        this.score=score;
    }
    // more to add in the future
    /**
     * -
     * @return true when players play more than once
     */
    public boolean playedOnce() {
        if(this.count>=1){
            return true;
        }
        else
        {return true;}
    }

    /**
     * -
     * @return true when players play more than 10 times
     */
    public boolean playedTenTimes(){
        if(this.count>=10){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * -
     * @return true when players score more than 10000
     */
    public boolean tenK(){
        if(this.score>10000){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * -
     * @return true when players score more than 100000
     */
    public boolean ahundredK(){
        if(this.score>100000){
            return true;
        }
        else{
            return false;
        }
    }
}

