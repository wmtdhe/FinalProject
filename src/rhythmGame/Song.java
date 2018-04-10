package rhythmGame;
/**
 * 
 * @author szheng20
 *
 */

public class Song {
	public Notes notes[];
	public int health;
	public int score;
	public String name;
	//combo implementation in the future
	public Song(Notes[] notes, String name){
		this.notes=notes; //manually mapped beats
		this.health=100; //full
		this.name = name;
	}
	/**
	 * deduct health if players miss beats
	 */
	public void updateHealth(){
		int size = this.notes.length;
		for(int i =0;i<size;i++){
			if(this.notes[i].read==0 && this.notes[i].pass==1){
				if(this.notes[i].guo==0){
					this.health-=5;
					this.notes[i].read=1;
				}
				else if(this.notes[i].guo==1){this.notes[i].read=1;}
			}
		}
	}
	/**
	 * check if a song is finished.
	 */
	public boolean songFinished(){
		int size = this.notes.length;
		for(int i=0;i<size;i++){
			if(this.notes[i].pass==0){
				return false;
			}
		}
		return true;
	}


}
