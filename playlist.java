package application;
//Made by: Sushim Mishra, Brian Lee, and Miguel Velasco
//Texas Tech University 2023
/**Playlist class that extends the player class and allows for managing multiple music object files*/
class Playlist extends Player{
	
	/*Changes the current index number of the array list as well as the Music object the new index number points to.*/
	public void changeTrackNumber(int newTrackNumber)
	{
		trackNumber = newTrackNumber;
		currentMusic = musicCollection.get(trackNumber);
	}
	
	/*Method that adds a song to the array list at the position of the current index number.*/
  public void addSongToPlaylist(String name, String artist, String filePath)
  {
    musicCollection.add(trackNumber + 1,new MusicObject(name,artist,filePath));
    changeTrackNumber(trackNumber + 1);
  }

  /*Method that changes the song in the array list by incrementing or decrementing the index number with wraparound.*/
  public void seek(int direction)
  {
	  if (direction == -1)
	  {
		  if (trackNumber == 0)
		  {
			  changeTrackNumber(musicCollection.size()-1);
		  }
		  else changeTrackNumber(trackNumber-1);
	  }
	  else if (direction == 1)
	  {
		  if (trackNumber == musicCollection.size()-1)
		  {
			  changeTrackNumber(0);
		  }
		  else changeTrackNumber(trackNumber+1);
	  }
  }
  
  /*Method that removes a song from the playlist and changes the index number if necessary.
   * Returns success or fail string.*/
  public String removeSongFromPlaylist()
  {    
	if (musicCollection.size()==1)
	{
		return "fail";
	}//Due to possible null pointer exceptions, there has to always be at least one song in the playlist.
    musicCollection.remove(trackNumber);
    if (trackNumber >= musicCollection.size()){
    	changeTrackNumber (trackNumber - 1);
    }
    else changeTrackNumber(trackNumber);
	return "success";

  }

  /*Method that displays all of the songs in the playlist in a multi-line format.*/
  public String displayPlaylist()
  {
	  String output; /*Return string that will be concatenated with new lines for each enhanced for-loop iteration
	  					of the music objects in the array list.*/
	  if (musicCollection.size() == 1)
		 {
		  	output = "There is one song in the Playlist:\n";
		 }
	  else output = "There are " + musicCollection.size() + " songs in the Playlist:\n";
	  
      for (Music song : musicCollection)
      {
    	  output += "\nName: " + song.getName() + "\nArtist: " + song.getArtist() + "\n\n";
      }
      return output;
  }

  /*Method that uses the java.util.Collections shuffle function to randomize the playlist.*/
  public void shufflePlaylist()
  {
    java.util.Collections.shuffle(musicCollection);
    changeTrackNumber(trackNumber);
  }
}
