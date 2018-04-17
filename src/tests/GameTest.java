package tests;
/**
 * Testing storage functions in Game class
 * Test coverage cannot reach 100% since exceptions cannot be manually made
 * @author lechang3
 */
//import static org.junit.Assert.*;

import junit.framework.TestCase;
import rhythmGame.Game;
import rhythmGame.Notes;
import rhythmGame.Song;

import java.io.File;


//import org.junit.Test;

public class GameTest extends  TestCase{

	
	//test for loading an existing player's data
	//@Test
	public void testLoad() {
		Game newGame = new Game("CL");
		newGame.player.setCount();
		newGame.end();
		Game anotherGame = new Game("CL");
		assertEquals(anotherGame.player.getCount(),1);
		File file = new File("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\CLData.json");
		assertTrue(file.delete());
		
	}
	
	
	//test for storing two different player's data
	//@Test
	public void testStorage() {
		Game newGame = new Game("CL");
		newGame.player.setHighScore(35);
		newGame.end();
		Game anotherGame = new Game("Siyao");
		assertEquals(anotherGame.player.getHighScore(),0);
		anotherGame.end();
		File file = new File("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\CLData.json");
		assertTrue(file.delete());
		file = new File("C:\\Users\\hq\\eclipse-workspace\\FinalProject.zip_expanded\\SiyaoData.json");
		assertTrue(file.delete());
	}
	
	//test for storing song data
	//@Test
	public void testSongStorage() {
		Game newGame = new Game("Hi");
		Notes[] notes = new Notes[10];
		Song newSong = new Song(notes,"MySong");
		newGame.startSong(newSong);
		newSong.score = 300;
		assertFalse(newGame.songEnd());
		newSong.health = 0;
		assertTrue(newGame.songEnd());
		newGame.storeSongData();
		
		newSong.score = 200;
		newGame.storeSongData();
		newGame.storeSongData();
		newGame.storeSongData();
		
		newSong.score = 100;
		newGame.storeSongData();
		newGame.storeSongData();
		newGame.storeSongData();
		
		newSong.score = 400;
		newGame.storeSongData();
		
		newSong.score = 250;
		newGame.storeSongData();
		newGame.storeSongData();
		
		newSong.score = 10;
		newGame.storeSongData();//it does not rank top ten
		//now check the json file
	}


}
