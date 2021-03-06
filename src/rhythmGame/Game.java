package rhythmGame;
/**
 * Game Class
 * @author lechang3
 */
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.*;

public class Game {
    public Player player;
    public Song currentSong;
    public boolean ranked;
    public int easySpeed;
    public int normalSpeed;
    public int exSpeed;

    /**
     * If player data exists, load the data,
     * else create the data.
     * @param name
     */
    public Game(String name){	
    	if(!this.loadPlayerData(name)) {
    		this.player = new Player(name);
    	}
    	this.easySpeed = 2;
    	this.normalSpeed = 3;
    	this.exSpeed = 4;
    	
    	this.currentSong = null;
    }
    
    
    /**
     * Start playing a song.
     * @param song
     */
    public void startSong(Song song) {
    	this.currentSong = song;
    }
    
    
    /**
	 * Store player data to json
	 */
	public void storePlayerData() {
		JSONObject obj = new JSONObject();
		obj.put("name", player.name);
		obj.put("count", player.getCount());
		obj.put("highScore", player.getHighScore());
		obj.put("first",Arrays.toString(player.getFirstScores()));
		obj.put("second", Arrays.toString(player.getSecondScores()));
		obj.put("expert", player.expert);
		obj.put("avatar", player.getAvatar());
		try {FileWriter file = new FileWriter(player.name+"Data.json");
		
			file.write(obj.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + obj);
		
			file.close();
		} catch (IOException e) {
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
		}catch(ParseException |IOException e){e.printStackTrace();}
		
			try {
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		}catch (FileNotFoundException e) {
            return false;
        }
		
		return true;
	}
	
	/**
	 * check if the song ranking file already exists
	 * @param currentSong
	 * @return song ranking JSONArray if found, null if not found.
	 */
	
	public JSONArray loadSongData(Song currentSong) {
		try {
			FileReader file = new FileReader(currentSong.name+"SongData.json");
			JSONParser parser = new JSONParser();
			try {Object a = parser.parse(file);
			return (JSONArray)a;
		}catch(ParseException e) {} catch (IOException e) {}
		}catch (FileNotFoundException e) {
        }
		return null;
		
	}
	
	/**
	 * Update the song ranking data after each song play.
	 */
	public void storeSongData() {
		
		//see if the song's data already exists
		JSONArray data = loadSongData(currentSong);
		if (data != null) {
			data = insertScore(data);//sort current score into the array
		}
		else {
			data = new JSONArray();
			addNewEntry(data);
		}
		
		//store the data to json file
		try {FileWriter file = new FileWriter(currentSong.name+"SongData.json");
		
		file.write(data.toJSONString());
		System.out.println("Successfully Copied JSON Object to File...");
		System.out.println("\nJSON Object: " + data);
	
		file.close();
	} catch (IOException e) {
	}
		
		
	}
	
	/**
	 * Helper function. Insert the data into the array according to its "score"
	 * @param data JSONObject in the form of {name,score}
	 * @return the sorted JSONArray
	 */
	
	private JSONArray insertScore(JSONArray data) {
		for (int i = 0; i<10; i++) {
			try{JSONObject iter = (JSONObject)data.get(i);
			
			int score = ((Long)iter.get("score")).intValue();
			if (score<currentSong.score) {//insert here
				this.ranked = true;
					JSONArray newData = new JSONArray();
					for (int j = 0; j<10; j++) {
						if(j==i) {
							addNewEntry(newData);
						}
						else if(j<i) {
							JSONObject k = (JSONObject)data.get(j);
							k.put("last", false);
							newData.add(k);
						}
						else {
							try {JSONObject k = (JSONObject)data.get(j-1);
								k.put("last", false);
								newData.add(k);} catch (IndexOutOfBoundsException e){return newData;}
					}
				}
					return newData;
			
			
		}
		
		}
		catch (IndexOutOfBoundsException e) {//data has less than 10 entries
			addNewEntry(data);
			this.ranked = true;
			return data;
		}
	}
		//score does not reach top 10
		this.ranked = false;
		return data;
	}


	/**
	 * Add current score as a new entry to the record
	 * @param data
	 */
	private void addNewEntry(JSONArray data) {
		JSONObject entry = new JSONObject();
		entry.put("name",player.name);
		entry.put("score", currentSong.score);
		entry.put("last", true);
		data.add(entry);
	}
	
	
	
	
	/**
	 * End game and update data
	 */
	public void end() {
		storePlayerData();
	}
	
	/**
	 * Check if a song is ended and update the song ranking info
	 */
	public boolean songEnd() {
		if(currentSong.health==0)return true;
		if(currentSong.songFinished()) {
			storeSongData();
			return true;
		}
		return false;
	}
	
}
