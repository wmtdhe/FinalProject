package rhythmGame;
/**
 * Pop up a high score display window when the player ranked on it.
 * @author lechang3
 */
import javax.swing.*;
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
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.DefaultTableCellRenderer;



public class HighScore extends JFrame {
	
	public HighScore(Game game, Song song) {
		
		String[] columnNames = new String[]{"Name",
                "Score"};
		JSONArray data = game.loadSongData(song);
		String[][] table = new String[10][2];
		
		//the current ranked score entry to highlight
		int highlight = 0;
		
		//parse the data from json and populate the table array
		for (int i = 0; i<10; i++) {
			try{
				JSONObject iter = (JSONObject)data.get(i);
				if((boolean)iter.get("last") == true)highlight = i;
				table[i][0] = (String)iter.get("name");
				table[i][1] = String.valueOf(iter.get("score"));
				
			}
			catch (IndexOutOfBoundsException e) {//data has less than 10 entries
				break;
			}
		}
		
			//create table model
			final Class[] columnClass = new Class[] {
	            Integer.class, String.class, Double.class, Boolean.class
	        };
	        
	        DefaultTableModel model = new DefaultTableModel(table, columnNames) {
	            @Override
	            public boolean isCellEditable(int row, int column)
	            {
	                return false;
	            }
	            @Override
	            public Class<?> getColumnClass(int columnIndex)
	            {
	                return columnClass[columnIndex];
	            }
	        };
		
	        
	    //using highlight renderer    
		final int hl = highlight;
	    JTable render = new JTable(model){
	        public TableCellRenderer getCellRenderer(int row, int column) {
	            if (row == hl) {
	                return new CellHighlighterRenderer();
	            }
	            return super.getCellRenderer(row, column);
	        }
	    };;
	    
	    //adding table to the window
		this.add(render,BorderLayout.CENTER);
		this.setTitle("High Score");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(400, 200);
        
        
        
		
		
		
		}
		
		
	public static void main(String []args){
		Game newGame = new Game("Hi");
		Song newSong = new Song(null,"MySong");
		HighScore hs = new HighScore(newGame,newSong);
	}
	

}


//creating a custom highlight renderer
class CellHighlighterRenderer extends DefaultTableCellRenderer {

    private static final long serialVersionUID = 1L;

    public CellHighlighterRenderer() {
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            setBackground(Color.YELLOW);
            setOpaque(true);
        
        return this;
    }

}


