
package tests;

import static org.junit.Assert.*;
import rhythmGame.Player;

import org.junit.Test;

/**
 * Tests for backend functions in Player class
 * @author lechang3
 *
 */
public class PlayerTest {

	@Test
	public void testBasics() {
		Player p = new Player("CL");
		p.setCount();
		assertEquals(p.getCount(),1);
	
	}
	
	
	//Test the setters of the score records
	@Test
	public void testModifyScore() {
		Player p = new Player("CL");
		p.setFirstScores(35);
		assertEquals(p.getSecondScores()[0],0);//only the first song is modified
		assertEquals(p.getFirstScores()[0],35);
		p.setSecondScores(70);
		assertEquals(p.getSecondScores()[0],70);
		p.setFirstScores(70);
		assertEquals(p.getFirstScores()[0],70);//70 is inserted ahead
	
	}
	
	
	//Test the update of achievements
	@Test
	public void testAchievements() {
		Player p = new Player("CL");
		p.setHighScore(300000);
		p.updateAchievement();
		assertTrue(p.getAchievement().achievements[2]);//one achievement achieved
	
	}


}
