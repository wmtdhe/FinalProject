package tests;

import junit.framework.TestCase;
import rhythmGame.Notes;
import rhythmGame.Song;

public class SongTest extends TestCase {

    public void testUpdateHealth() {
        Notes[] notes = new Notes[4];
        notes[0]=new Notes(200,600);
        notes[1]=new Notes(200,300);
        notes[2]=new Notes(200,130);
        notes[3]=new Notes(200,23);
        notes[3].hit=0;
        for(int i=0;i<4;i++){
            notes[i].passed();
        }
        Song song=new Song(notes,"player1");
        song.updateHealth();
        assertEquals(song.health,95);


    }

    public void testSongFinished() {
        Notes[] notes = new Notes[4];
        notes[0]=new Notes(200,600);
        notes[1]=new Notes(200,300);
        notes[2]=new Notes(200,130);
        notes[3]=new Notes(200,23);
        notes[3].hit=0;
        for(int i=0;i<4;i++){
            notes[i].passed();
        }
        Song song=new Song(notes,"player1");
        assertEquals(song.songFinished(),false);
    }
}