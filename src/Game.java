public class Game {
    public Player player;
    public Song currentSong;

    public Game(String name){
    	this.player = new Player(name);
    	this.currentSong = null;
    }
}
