package application;
//Made by: Sushim Mishra, Brian Lee, and Miguel Velasco
//Texas Tech University 2023
import java.util.ArrayList;

/**Abstract class for playing the music. Will be extended in the playlist class*/
abstract class Player {
	
protected ArrayList<MusicObject> musicCollection = new ArrayList<MusicObject>() {{
	add(new MusicObject("[insert name]","[insert artist]","[insert file name, but replace slashes with double slashes]"));
}};// represents an array list of Music objects with one Music object already in the array list.

protected int trackNumber = 0;// represents the current index number in the array list.

protected MusicObject currentMusic = musicCollection.get(0);// represents the Music object the index number is pointing to.

	/*Method that returns a String indicating a song is playing.*/
  public String playMusic()
  {
    return "Now playing " + currentMusic.getName() + "\nby " + currentMusic.getArtist();
  }
  
  /*Method that returns a String indicating a song is paused.*/
  public String pauseMusic()
  {
    return "Current song: " + currentMusic.getName() + "\nby " + currentMusic.getArtist();
  }
  
  /*Method that just returns the name and artist information.*/
  public String stringMusic()
  {
	  return currentMusic.getName() + " by " + currentMusic.getArtist();
  }
  
  /*Method that returns the file path string of the current Music object.*/
  public String getCurrentFile()
  {
	  return currentMusic.getFilePath();
  }
  
  /*Abstract methods that will be defined in the playlist class.*/
  public abstract void addSongToPlaylist(String name, String artist, String filePath);
  public abstract void seek(int direction);
  public abstract String removeSongFromPlaylist();
  public abstract String displayPlaylist();
  public abstract void shufflePlaylist();
}
