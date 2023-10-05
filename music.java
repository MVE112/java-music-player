package application;
//Made By: Sushim Mishra, Brian Lee, and Miguel Velasco
//Texas Tech University Spring 2023
/**Interface for the Music object. Methods will be implemented in the MusicObject class.*/
interface Music {
  public void setName(String n);
  public void setArtist(String a);
  public void setFilePath(String f);
  public String getName();
  public String getArtist();
  public String getFilePath();
}

/**Class that will implement the Music interface.*/
class MusicObject implements Music{
	private String name; // name of the song in Music object
	private String artist; // artist name in Music object
	private String filePath; //stores the name of the File Path on the computer so that Java Media Player can identify the file from the computer to play the song.
  
	/*parameterized constructor for Music object*/
  public MusicObject(String n, String a, String f) {
    name = n;
    artist = a;
    filePath = f;
  }
  
  /*changes the name of the song in Music object*/
  public void setName(String n) {
    name = n;
  }

  /*changes the artist name in Music object*/
  public void setArtist(String a) {
    artist = a;
  }

  /*changes the file path of the song in Music object*/
  public void setFilePath(String f) {
    filePath = f;
  }

  /*returns the name of the song from Music object*/
  public String getName() {
    return name;
  }

  /*returns the artist name from Music object*/
  public String getArtist() {
    return artist;
  }
  
  /*returns the file path from Music object*/
  public String getFilePath() {
    return filePath;
  }
}
