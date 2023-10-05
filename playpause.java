package application;
//Made by: Sushim Mishra, Brian Lee, and Miguel Velasco
//Texas Tech University 2023
import java.io.File;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**Class that allows for using JavaFX to run the program.*/
public class playpause extends Application {
	Scanner key = new Scanner(System.in);
	static volatile MediaPlayer mediaPlayer; //Variable that invokes the Media Player to play the songs.
	static volatile Slider slVolume; //Variable that allows for controlling volume.
	static volatile Slider slTime; //Variable that allows for seeking forward or backward within a song.
	static volatile Label lbTime; //Variable that allows to see the current music time and total music time.
	static volatile Playlist mediaPlaylist; //Variable that allows for using the Playlist class.
	
	static volatile Text line1; //JavaFX Text variable that frequently changes.
	static volatile Button playButton; //JavaFX Button variable that frequently changes.
  public void start(Stage primaryStage) {
	  
	mediaPlaylist = new Playlist();
	
	slVolume = new Slider();
	slTime = new Slider();
	lbTime = new Label();
	
	/*JavaFX Button variables that are constant.*/
    Button button1 = new Button("Add Song to Playlist"); 
    Button button2 = new Button("Remove Song From Playlist"); 
    Button button3 = new Button("Display Playlist"); 
    Button button4 = new Button("Shuffle Playlist"); 
    
    //Defining variable JavaFX elements.
    line1 = new Text("Welcome to the Music Player\n\n" + mediaPlaylist.displayPlaylist());
    playButton = new Button(">");
    
    /*Runs when the play button is clicked. The media will start playing the music, and the button will change to a pause icon.
     * The opposite happens vice versa. There is a try-catch clause that will output an exception if the music file path does
     * not exist.*/
    playButton.setOnAction(e -> {
      if (playButton.getText().equals(">")) {
    	mediaPlayer.play();
    	playButton.setText("||");
        line1.setText(mediaPlaylist.playMusic());
      } else {
        mediaPlayer.pause();
        playButton.setText(">");
        line1.setText(mediaPlaylist.pauseMusic());
      }
    });
    	    
    /*Sets design specifications for the volume range scroll.*/
    slVolume.setPrefWidth(100);
    slVolume.setMaxWidth(Region.USE_PREF_SIZE);
    slVolume.setMinWidth(25);
    slVolume.setValue(50);

    /*Sets design specifications for the time-seeking range scroll.*/
    slTime.setPrefWidth(200);
    slTime.setMaxWidth(Region.USE_PREF_SIZE);
    slTime.setMinWidth(50);
    slTime.setMin(0);
    
    /*Will be called every time the mediaPlayer changes because of a change in the song in the playlist*/
    changeMediaPlayer();
    
    /*Is called every time seeking occurs in the time-seeking range scroll. Makes the mediaPlayer sync its time
     * to match that of the range scroll.*/
    slTime.valueProperty().addListener(ov -> {
        if (slTime.isValueChanging()) {
          mediaPlayer.seek(new Duration(slTime.getValue()));
        }
      });

    /*Rewinds the song to the beginning, or seeks backward to the previous song in the playlist if already at the beginning 
     * of the song.*/
    Button rewindButton = new Button("<<");
    rewindButton.setOnAction(e -> {
    	if (mediaPlayer != null)
    	{
    		if (mediaPlayer.getCurrentTime().toMillis() > 2500)
        	{
            	mediaPlayer.seek(Duration.ZERO);
        	}
        	else
        	{
        		mediaPlayer.pause();
                playButton.setText(">");
        		mediaPlaylist.seek(-1);
                line1.setText(mediaPlaylist.pauseMusic());
                changeMediaPlayer();
        	}
    	}
    });
    
    /*Seeks forward to the next song in the playlist.*/
    Button forwardButton = new Button(">>");
    forwardButton.setOnAction(e -> {
    	if (mediaPlayer != null)
    	{
    		mediaPlayer.pause();
            playButton.setText(">");
    		mediaPlaylist.seek(1);
            line1.setText(mediaPlaylist.pauseMusic());
            changeMediaPlayer();
    	}
    });
    
    /*Sets design specifications for the bottom box containing the play/pause/seek buttons and range scrolls.*/
    HBox bottomBox = new HBox(10);
    bottomBox.setAlignment(Pos.CENTER);
    bottomBox.getChildren().addAll(rewindButton, playButton, forwardButton, new Label("Time"), slTime, lbTime,
    	      new Label("Volume"), slVolume);
    
    /*Sets design specifications for the center box that contains text that change during certain conditions. These
     * text changes can be found every time setText is called.
     */
    VBox centerBox = new VBox(10);
    centerBox.setAlignment(Pos.CENTER);
    centerBox.getChildren().add(line1);
    
    
    //Puts the center box inside a scroll pane.
    ScrollPane scroll = new ScrollPane();
    scroll.setContent(line1);
    scroll.setFitToWidth(true);
    scroll.setFitToHeight(true);
    
    /*Sets design specifications for the bottom box containing the add/remove/display/shuffle playlist buttons.*/
    HBox topBox = new HBox(10);
    topBox.setAlignment(Pos.CENTER);
    topBox.getChildren().addAll(button1, button2, button3, button4);

    /*Initiates the border pane window that contains the top, center, and bottom boxes.*/
    BorderPane pane = new BorderPane();
    pane.setCenter(scroll); 
    pane.setTop(topBox);
    pane.setBottom(bottomBox);

/*Initiates the scene of the border pane window with header title.*/
    Scene scene = new Scene(pane,600,200);
    primaryStage.setTitle("Music Player");
    primaryStage.setScene(scene);
    primaryStage.show();
    
    /*Is invoked every time the 'Add Song to Playlist' button is clicked. For the time being I am getting user input from
     * the console as getting input this through JavaFX will take some time to properly implement. I also allowed the user
     * to quit this function if they decide to do so at any time.
     */
    button1.setOnAction(e -> {
    	mediaPlayer.pause();
    	String name = "";
    	String artist = "";
    	String filePath = "";
        System.out.println("\nAdding Song to Playlist (press ~ to quit):");
        System.out.println("Enter name of music file: ");
        while (name.length() == 0)
        {
            name = key.nextLine();
            if (name.equals("~")) {
            	System.out.println("Exited");
            	return;
            }
        }
        System.out.println("Enter artist of music file: ");
        while (artist.length() == 0)
        {
            artist = key.nextLine();
            if (artist.equals("~")) {
            	System.out.println("Exited");
            	return;
            }
        }
        System.out.println("Enter file path of music file (copy directly from the directory): ");
        while (filePath.length() == 0)
        {
            filePath = key.nextLine();
            if (filePath.equals("~")) {
            	System.out.println("Exited");
            	return;
            }
        }
        filePath = filePath.replace("\"", "");
        
        /*There is a try-catch clause here to check if the file path the user inputs exists on the computer. An exception is thrown
        if the file path does not exist, since the mediaPlayer cannot be created for a null file.*/
        try {
        	mediaPlaylist.addSongToPlaylist(name,artist,filePath);
        	changeMediaPlayer();
        	System.out.println("Added song to playlist!");
        	line1.setText("Added song to playlist!\n" + mediaPlaylist.pauseMusic());
        }
    	catch (Exception f){
    		System.out.println("\nUnable to add song. Invalid File Input.");
    		mediaPlaylist.removeSongFromPlaylist();
    	}
        
    });
    
    /*Is invoked every time the 'Remove Song from Playlist' button is clicked. Asks for confirmation before calling the function
     * to actually remove the song.
     */
    button2.setOnAction(e -> {
    	mediaPlayer.pause();
    	String input;
    	if (mediaPlayer != null)
    	{
    		System.out.println("\nAre you sure you want to remove " + mediaPlaylist.stringMusic() + "? (Y for yes)");
    		input = key.nextLine();
    		if (input.equals("Y"))
        	{
        		String result = mediaPlaylist.removeSongFromPlaylist();
       	    	
       	    	if (result == "success")
       	    	{
       	    		changeMediaPlayer();
       	    		System.out.println("Removed song from playlist.");
       	    		line1.setText("Removed song from playlist.\n" + mediaPlaylist.pauseMusic());
       	    	}
       	    	else if (result == "fail")
       	    	{
       	    		System.out.println("Failed to delete song as there is only one song in the playlist.");
       	    		line1.setText("Failed to remove song.\n" + mediaPlaylist.pauseMusic());
       	    	}
        	    
        	}
    	}
    	
    });
    
    /*Is invoked every time the 'Display Playlist' button is clicked. Displays all of the songs in the playlist in the centet
     * box in the border pane.
     */
    button3.setOnAction(e -> {
    	mediaPlayer.pause();
    	line1.setText(mediaPlaylist.displayPlaylist());
    });
    
    /*Is invoked every time the 'Shuffle Playlist' button is clicked. Randomizes the order of the songs in the playlist.*/
    button4.setOnAction(e -> {
    	mediaPlayer.pause();
    	mediaPlaylist.shufflePlaylist();
    	changeMediaPlayer();
    	line1.setText("Shuffled playlist!\n" + mediaPlaylist.pauseMusic());
    });
  }

/*Every time a song is changed in the mediaPlayer, this function is called to reset the range scrolls for time and volume,
   * as well as to get the file name of the music that will be played by the mediaPlayer.
   */
  public static void changeMediaPlayer ()
  {
	mediaPlayer = new MediaPlayer(new Media(new File(mediaPlaylist.getCurrentFile()).toURI().toString()));
	
	  mediaPlayer.currentTimeProperty().addListener(ov -> {
          double total = mediaPlayer.getTotalDuration().toMillis();
          double current = mediaPlayer.getCurrentTime().toMillis();
          slTime.setMax(total);
          slTime.setValue(current);
          lbTime.setText(getTimeString(current) + "/" + getTimeString(total));
      });
	  
	  mediaPlayer.volumeProperty().bind(
		      slVolume.valueProperty().divide(100));
	  
	  /*Is called every time a song ends in the mediaPlayer. Automatically seeks forward to the next song, and automatically
	     * plays the song.*/
	  mediaPlayer.setOnEndOfMedia(() -> {
	    	if (playButton.getText().equals("||"))
		    {
	    		mediaPlayer.pause();
	        	mediaPlaylist.seek(1);
	            changeMediaPlayer();
	            line1.setText(mediaPlaylist.playMusic());
	            mediaPlayer.play();
		    }
	    });
  }
  
  /*Converts the time from milliseconds to minutes and seconds*/
  public static String getTimeString(double millis) {
	    millis /= 1000;
	    String s = formatTime(millis % 60);
	    millis /= 60;
	    String m = formatTime(millis);
	    return m + ":" + s;
	  }

  /*Formats the time to be displayed in String format.*/
	  public static String formatTime(double time) {
	    int t = (int)time;
	    if (t > 9) { return String.valueOf(t); }
	    return "0" + t;
	  }

	  
	  /**Main driver function that launches the JavaFX program.*/
  public static void main(String[] args) {
    launch(args);
  }
}
