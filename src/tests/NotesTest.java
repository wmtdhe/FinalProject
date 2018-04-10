package tests;

import junit.framework.TestCase;
import rhythmGame.Notes;

public class NotesTest extends TestCase {

    public void testHit() {
        Notes notes1=new Notes(250,475);
        Notes notes2=new Notes(350,475);
        Notes notes3=new Notes(450,475);
        Notes notes4=new Notes(550,475);
        //-check bounds
        Notes notes5=new Notes(250,300);
        Notes notes6=new Notes(250,556);
        assertEquals(notes5.hit(68),false);
        assertEquals(notes6.hit(70),false);
        //d
        assertEquals(notes1.hit(68),true);
        assertEquals(notes1.hit(70),false);
        assertEquals(notes1.hit(74),false);
        assertEquals(notes1.hit(75),false);
        //f
        assertEquals(notes2.hit(70),true);
        assertEquals(notes2.hit(68),false);
        assertEquals(notes2.hit(74),false);
        assertEquals(notes2.hit(75),false);
        //j
        assertEquals(notes3.hit(74),true);
        assertEquals(notes3.hit(68),false);
        assertEquals(notes3.hit(70),false);
        assertEquals(notes3.hit(75),false);
        //k
        assertEquals(notes4.hit(75),true);
        assertEquals(notes4.hit(70),false);
        assertEquals(notes4.hit(68),false);
        assertEquals(notes4.hit(74),false);
    }
}