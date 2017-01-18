/***************************************************
 ** Filename: Songs.java
 ** Author: Robert Silvan
 ** Student Number: 301118114
 ** Date: April 4, 2011
 ** Description: Contains info for the user's current instance
 ** of the Song
 ******************************************************/

public class Songs
{
	private String title;			//title of the song
	private String durationMinutes;    //duration of the song in minutes
	private String fileNameandPath; //file name/path of the song


   //***************************************************
   //Creates a new Song with the specified information.
   //***************************************************
    public Songs(String SongTitle, String durationMin, String fileName)
    	{
    	title = SongTitle;
    	durationMinutes = durationMin;
    	fileNameandPath = fileName;
    	}


    //***************************************************
    //Returns the title of the current song as a string.
    //***************************************************
    public String gettitle(){
    	return title;
   		}


   	//***************************************************
    //Returns the duration of the current song as an int.
    //***************************************************
   	public String getduration(){
    	return durationMinutes;
   		}


   	//******************************************************
    //Returns the filename of the current song as a string.
    //******************************************************
   	public String getfileName(){
    	return fileNameandPath;
   		}


   //********************************************
   //Returns a string description of this Song.
   //********************************************
	public String toString()
		{
      String description;
      description = "\t\tSong: " + title + "  ||||||  " + "Duration: " + durationMinutes + " minutes" + "  ||||||  " + "File: " + fileNameandPath;

      return description;
   		}

}