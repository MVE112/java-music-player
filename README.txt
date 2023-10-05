README.txt

To run the Music Player, make sure the Java package you are using has "music.java", "player.java", "playlist.java", and "playpause.java" in it. 

"music.java" has the Music and MusicObject classes. "player.java" has the Player class. "playlist.java" has the Playlist class. "playpause.java" has the JavaFX implementation and public static void main().

You can replace "package application" at the top of each Java file with the package you are using.



This program uses JavaFX, and it requires these dependencies listed here:
	javafx.application.Application
	javafx.geometry.Pos
	javafx.scene.Scene
	javafx.scene.control.Button
	javafx.scene.control.Label
	javafx.scene.control.ScrollPane
	javafx.scene.control.Slider
	javafx.scene.layout.BorderPane
	javafx.scene.layout.HBox
	javafx.scene.layout.Region
	javafx.scene.layout.VBox
	javafx.scene.media.Media
	javafx.scene.media.MediaPlayer
	javafx.scene.text.Text
	javafx.stage.Stage
	javafx.util.Duration

Additionally, these dependencies are required:
	java.io.File
	java.util.ArrayList
	java.util.Scanner



Before running the program, there needs to be one music object in the playlist. Go to "player.java", and find this line: 	

	add(new MusicObject("[insert name]","[insert artist]","[insert file name, but replace slashes with double slashes]"));


Replace the code with the respective tokens for the music object. This is an example of what the code should look like after inputting the proper information:

	add(new MusicObject("Blinding Lights","The Weeknd","C:\\Users\\Adam\\Music\\BlindingLights.mp3"));


For the file name, slashes should be replaced with double slashes to properly initiate a file object from the string. Note that when the program asks for user input to add more music files, double slashes are not required as there is code to handle that.

The reason there needs to be one music file in the playlist at the start of the program is to avoid the mediaPlayer from pointing to a null object and crashing the program.



It is preferential to use Eclipse IDE to run the program.

This program was created using Java Version 19.0.2 in Windows. Recent earlier versions of Java may be able to run the program.

