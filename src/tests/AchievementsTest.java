package tests;
/**
 * @author szheng20
 */

import junit.framework.TestCase;
import rhythmGame.Achievements;

public class AchievementsTest extends TestCase {
    Achievements achievements=new Achievements(0,2);
    Achievements achievements1 = new Achievements(1,100);
    Achievements achievements2 = new Achievements(2,100);
    Achievements achievements3 = new Achievements(10,100);
    Achievements achievements4 = new Achievements(11,1000000);
    Achievements achievements5 = new Achievements(10,10000);
    public void testPlayedOnce() {
        assertEquals(achievements1.playedOnce(),true);
        assertEquals(achievements2.playedOnce(),true);
        assertEquals(achievements.playedOnce(),false);
        //System.out.println(achievements.count);
    }

    public void testPlayedTenTimes() {
        assertEquals(achievements.playedTenTimes(),false);
        assertEquals(achievements3.playedTenTimes(),true);
        assertEquals(achievements4.playedTenTimes(),true);
        assertEquals(achievements2.playedTenTimes(),false);
    }

    public void testTenK() {
        assertEquals(achievements2.tenK(),false);
        assertEquals(achievements5.tenK(),true);
        assertEquals(achievements4.tenK(),true);
    }

    public void testAhundredK() {
        assertEquals(achievements4.ahundredK(),true);
        assertEquals(achievements .ahundredK(),false);
        assertEquals(achievements5.ahundredK(),false);
        assertEquals(achievements2.ahundredK(),false);
    }
}