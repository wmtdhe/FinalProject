package rhythmGame;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Game {
    public Player player;
    public Song currentSong;

    public Game(String name){
    	if(!this.loadPlayerData(name)) {
    		this.player = new Player(name);
    	}
    	
    	this.currentSong = null;
    }
    
    
    
    
    /**
	 * Store player data to json
	 */
	public void storePlayerData() {
		JSONObject obj = new JSONObject();
		obj.put("name", player.name);
		obj.put("count", player.getCount());
		obj.put("highScore", player.getHighScore());
		obj.put("first", player.getFirstScores().toString());
		obj.put("second", player.getSecondScores());
		obj.put("expert", player.expert);
		try {FileWriter file = new FileWriter(player.name+"Data.json");
		try {
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			file.close();
		}} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * Load a player's data
	 */
	public boolean loadPlayerData(String name) {
		
		try {
		FileReader file = new FileReader(name+"Data.json");
		JSONParser parser = new JSONParser();
		try {Object a = parser.parse(file);
			this.player = new Player((JSONObject)a);
		
		
		
		}catch(ParseException e) {e.printStackTrace();} catch (IOException e) {e.printStackTrace();}
		}catch (FileNotFoundException e) {
            return false;
        }
		
		return true;
	}
	
	
	/**
	 * 
	 */
	public void end() {
		storePlayerData();
	}
	
}
