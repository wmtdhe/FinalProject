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
	public int speed = 2;
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
	public void mapFirstSongEasy() {
		this.speed = 2;
	}
	
	public void mapFirstSongNormal() {
		this.speed = 3;
		addNote(1,5920);
		addNote(2,6179);
		addNote(3,6592);
		addNote(4,7005);
		addNote(1,10807);
		addNote(2,10807);
		addNote(3,11504);
		addNote(4,11504);
		addNote(1,14295);
		addNote(2,14295);
		addNote(3,16156);
		addNote(4,16156);
		addNote(1,18202);
		addNote(2,18202);
		addNote(3,19166);
		addNote(4,19166);
		addNote(1,25457);
		addNote(3,26935);
		addNote(2,30268);
		addNote(4,31322);
		
		//tooku hanare teitano
		addNote(1,36387);
		addNote(2,36701);
		addNote(2,38730);
		addNote(3,38961);
		addNote(4,39193);
		addNote(1,40117);
		addNote(2,40468);
		addNote(2,42456);
		addNote(3,42689);
		addNote(4,42922);
		addNote(1,44301);
		addNote(4,44301);
		addNote(2,45184);
		addNote(3,45184);
		addNote(1,45942);
		addNote(2,46385);
		addNote(3,46630);
		addNote(4,46857);
		
		addNote(1,48024);
		addNote(2,48437);
		addNote(3,48850);
		addNote(2,49877);
		addNote(3,50290);
		addNote(4,50703);
		
		//kimi ni kizuita shisen
		addNote(1,51272);
		addNote(2,51685);
		addNote(2,53611);
		addNote(3,53818);
		addNote(4,54024);
		addNote(1,54991);
		addNote(2,55404);
		addNote(2,57234);
		addNote(3,57440);
		addNote(4,57647);
		
		addNote(1,58958);
		addNote(2,58958);
		addNote(3,59888);
		addNote(4,59888);
		addNote(1,60827);
		addNote(2,61287);
		addNote(3,61509);
		addNote(4,61715);
		
		addNote(1,62220);
		addNote(2,62444);
		addNote(3,62660);
		
		addNote(2,63137);
		addNote(3,63345);
		addNote(4,63555);
		
		addNote(2,64075);
		addNote(3,64285);
		addNote(4,64495);
		
		addNote(3,65930);
		addNote(4,66050);
		addNote(3,66176);
		addNote(4,66289);
		
		//satoori
		addNote(1,67347);
		addNote(4,67783);
		addNote(3,68253);
		addNote(1,69184);
		addNote(2,69419);
		addNote(3,69638);
		addNote(4,69984);
		addNote(3,70109);
		
		
	}
	//
    public void easyYumetourou(){
	    this.speed=2;
	    addNote(2,6522);
        addNote(3,6522);
        addNote(2,12223);
        addNote(3,12223);
        addNote(1,17790);
        addNote(4,17790);
        //
        addNote(1,23451);
        addNote(2,23805);
        addNote(3,23965);
        addNote(4,24632);
        //
        addNote(1,23451);
        addNote(2,23805);
        addNote(3,23965);
        addNote(4,24632);
        //
        addNote(1,24851);
        addNote(2,25205);
        addNote(3,25365);
        addNote(4,26032);
        //
        addNote(1,26353);
        addNote(2,26707);
        addNote(3,26867);
        addNote(4,27534);
        //
        addNote(1,27905);
        addNote(2,28259);
        addNote(3,28419);
        addNote(4,29086);
        //
        addNote(4,29407);
        addNote(3,29763);
        addNote(2,29923);
        addNote(1,30583);
        //
        addNote(4,30954);
        addNote(3,31310);
        addNote(2,31470);
        addNote(1,32130);
        //
        addNote(4,32501);
        addNote(3,32857);
        addNote(2,33017);
        addNote(1,33677);
        //
        addNote(3,33852);
        addNote(4,34030);
        addNote(1,34155);
        addNote(2,34330);
        addNote(2,34657);
        addNote(3,34657);
    }

    public void normalYumetourou(){
	    this.speed=3;
        addNote(2,6522);
        addNote(3,6522);
        addNote(2,12223);
        addNote(3,12223);
        addNote(1,17790);
        addNote(4,17790);
        //
        addNote(1,23451);
        addNote(2,23805);
        addNote(3,23965);
        addNote(4,24632);
        //
        addNote(1,23451);
        addNote(2,23805);
        addNote(3,23965);
        addNote(4,24632);
        //
        addNote(1,24851);
        addNote(2,25205);
        addNote(3,25365);
        addNote(4,26032);
        //
        addNote(1,26353);
        addNote(2,26707);
        addNote(3,26867);
        addNote(4,27534);
        //
        addNote(1,27905);
        addNote(2,28259);
        addNote(3,28419);
        addNote(4,29086);
        //
        addNote(4,29407);
        addNote(3,29763);
        addNote(2,29923);
        addNote(1,30583);
        //
        addNote(4,30954);
        addNote(3,31310);
        addNote(2,31470);
        addNote(1,32130);
        //
        addNote(4,32501);
        addNote(3,32857);
        addNote(2,33017);
        addNote(1,33677);
        //
        addNote(3,33852);
        addNote(4,34030);
        addNote(1,34155);
        addNote(2,34330);
        addNote(1,34657);
        addNote(2,34657);
        addNote(3,34657);
        addNote(4,34657);
        //--
        addNote(1,34660);
        addNote(2,35016);
        addNote(3,35176);
        addNote(4,35836);
        //
        addNote(1,35925);
        addNote(2,36281);
        addNote(3,36441);
        addNote(4,37101);
        //
        addNote(4,37242);
        addNote(3,37598);
        addNote(2,37758);
        addNote(1,38618);
        //
        addNote(1,39153);
        addNote(4,39153);
        addNote(2,39269);
        addNote(3,39269);
        //
        addNote(1,40935);
        addNote(2,40935);
        //
        addNote(1,43591);
        addNote(2,43591);
        //
        addNote(2,46506);
        addNote(3,46506);
        //
        addNote(2,49183);
        addNote(3,49183);
    }
    public void expertYumetourou(){
	    this.speed=4;
        addNote(2,6522);
        addNote(3,6522);
        addNote(2,12223);
        addNote(3,12223);
        addNote(1,17790);
        addNote(4,17790);
        //
        addNote(1,23451);
        addNote(2,23805);
        addNote(3,23965);
        addNote(4,24632);
        //
        addNote(1,23451);
        addNote(2,23805);
        addNote(3,23965);
        addNote(4,24632);
        //
        addNote(1,24851);
        addNote(2,25205);
        addNote(3,25365);
        addNote(4,26032);
        //
        addNote(1,26353);
        addNote(2,26707);
        addNote(3,26867);
        addNote(4,27534);
        //
        addNote(1,27905);
        addNote(2,28259);
        addNote(3,28419);
        addNote(4,29086);
        //
        addNote(4,29407);
        addNote(3,29763);
        addNote(2,29923);
        addNote(1,30583);
        //
        addNote(4,30954);
        addNote(3,31310);
        addNote(2,31470);
        addNote(1,32130);
        //
        addNote(4,32501);
        addNote(3,32857);
        addNote(2,33017);
        addNote(1,33677);
        //
        addNote(3,33852);
        addNote(4,34030);
        addNote(1,34155);
        addNote(2,34330);
        addNote(1,34657);
        addNote(2,34657);
        addNote(3,34657);
        addNote(4,34657);
        //--
        addNote(1,34660);
        addNote(2,35016);
        addNote(3,35176);
        addNote(4,35836);
        //
        addNote(1,35925);
        addNote(2,36281);
        addNote(3,36441);
        addNote(4,37101);
        //
        addNote(4,37242);
        addNote(3,37598);
        addNote(2,37758);
        addNote(1,38618);
        //
        addNote(1,39153);
        addNote(4,39153);
        addNote(2,39269);
        addNote(3,39269);
        //
        addNote(1,40935);
        addNote(2,40935);
        //
        addNote(1,43591);
        addNote(2,43591);
        //
        addNote(2,46506);
        addNote(3,46506);
        //
        addNote(2,49183);
        addNote(3,49183);
        //----------------------- 一波乱按
        addNote(2,51284);
        addNote(3,51384);
        addNote(1,51300);
        addNote(4,51484);
        addNote(2,51640);
        addNote(3,51750);
        addNote(1,51863);
        addNote(4,51990);
        addNote(2,52220);
        addNote(3,52354);
        addNote(1,52486);
        addNote(4,52650);
        addNote(2,52790);
        addNote(3,52940);
        addNote(1,53120);
        addNote(3,53256);


    }
	
	private int find(int time, float next) {
		return (int)(time+next*(60*1000/145));
	}

	
	private void addNote(int pos, int nanosec) {
		int x = 0;
		if(pos == 1)x = 202;
		if(pos == 2)x = 302;
		if(pos == 3)x = 402;
		if(pos == 4)x = 502;
		Notes note = new Notes(x,450-(nanosec/20)*(this.speed));
		add(note);
	}
	
	private void add(Notes note) {
		Notes[] old = this.notes;
		Notes[] now = new Notes[old.length+1];
		for (int i = 0; i<old.length;i++) {
			now[i] = old[i];
		}
		now[old.length] = note;
		this.notes = now;
	}
	
	

}
