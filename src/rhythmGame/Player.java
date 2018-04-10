package rhythmGame;
/**
 * Containing each player's data
 * @author lechang3
 *
 */

import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

public class Player {

	public String name;
	private int highScore;//highest score on record (for achievement use)
	private int[] firstSongScores;//highest ten scores of this player on the first song
	private int[] secondSongScores;
	private Achievements playerAchievement;
	private int count;//total play times
	public boolean expert;//reached expert level
	
	
	public Player(String name){
		this.name = name;
		this.setHighScore(0);
		this.firstSongScores = new int[10];
		this.secondSongScores = new int[10];
		this.expert = false;
		this.count = 0;
		
		this.updateAchievement();
		
		
	}
	
	
	/**
	 * Load the player data directly from json file
	 * @param data
	 */
	Player(JSONObject data){
		this.name = (String)data.get("name");
		this.setHighScore(((Long)data.get("highScore")).intValue());
		String line = (String)data.get("first");
		this.firstSongScores = convert(line);
		line = (String)data.get("second");
		this.secondSongScores = (convert(line));
		this.count = ((Long)data.get("count")).intValue();
		this.expert = (boolean)data.get("expert");
		this.updateAchievement();
		
	}
	
	
	/**
	 * Convert the score array from string to int[]
	 * @param line
	 * @return
	 */
	private int[] convert (String line) {
		line = line.replace("[","");
		line = line.replace("]", "");
		line = line.replace(" ", "");
		int[] numbers = Arrays.stream(line.split(",")).mapToInt(Integer::parseInt).toArray();
		return numbers;
	}

	//getter setter for highest score
	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	
	//getter and accumulator for play count
	public int getCount() {
		return count;
	}
	
	public void setCount() {
		this.count++;
	}
	
	
	//getters for high scores
	public int[] getFirstScores() {
		return firstSongScores;
	}
	
	public int[] getSecondScores() {
		return secondSongScores;
	}
	
	
	//setters for high scores. Return sorted arrays.
	public void setFirstScores(int score) {
		int[] scores = this.getFirstScores();
		for(int i=0; i<10; i++) {
			if (scores[i]<score) {
				this.firstSongScores = insertScore(score,i,scores);
			}
		}
	}

	public void setSecondScores(int score) {
		int[] scores = this.getSecondScores();
		for(int i=0; i<10; i++) {
			if (scores[i]<score) {
				this.secondSongScores = insertScore(score,i,scores);
			}
		}
	}
	
	/**
	 * Helper function updating the score array.
	 * @param score
	 * @param index
	 * @param scores
	 * @return updated score array
	 */
	private int[] insertScore(int score, int index, int[] scores) {
		for(int i = index+1; i<10; i++) {
			scores[i] = scores[i-1];
		}
		scores[index] = score;
		return scores;
	}

	/**
	 * @return the playerAchievement
	 */
	public Achievements getAchievement() {
		return playerAchievement;
	}

	/**
	 * @param playerAchievement the playerAchievement to set
	 */
	public void updateAchievement() {
		this.playerAchievement = new Achievements(this.count, this.highScore);
	}
	
	
	
	
	
	

}
