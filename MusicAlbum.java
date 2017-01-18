/***************************************************
 ** Filename: MusicAlbum.java
 ** Author: Robert Silvan
 ** Student Number: 301118114
 ** Date: April 4, 2011
 ** Description: Contains info for the user's current instance
 ** of the Music Album
 ******************************************************/

import java.util.*;
import java.io.*;
public class MusicAlbum
{
   private String albumTitle;   //the title of the album
   private String artistName;	//the name of the artist
   private int numberOfSongs;	//the number of songs on the album
   private int yearOfRelease;	//the year of the albums release
   private String musicGenre;	//the genre of the album
   private String title;		//the title of a song
   private String dur;			//the duration of a song
   private String file;			//the filename of a song
   private int count = 0;		//keeps track of how many songs have been added
   Scanner keyboard = new Scanner(System.in);
   Songs[] songList;			//An array of the songs


   //************************************************************************
   //Creates a new Music Album with the specified information from user input
   //************************************************************************
   public MusicAlbum (String name, String singer, int numTracks, int year, String genre)
   {
      albumTitle = name;
      artistName = singer;
      numberOfSongs = numTracks;
      yearOfRelease = year;
      musicGenre = genre;
      songList = new Songs[numTracks];

		//adds songs based on how many songs the user specificed would be on the album
		for (int num = 1; num < numTracks+1; num++)
			{
	      	System.out.println("Enter the name of song " + num + ": ");
	      	title = keyboard.nextLine();
	      	System.out.println("Enter the filename/path of song "+ num + ": ");
	      	file = keyboard.nextLine();
	      	System.out.println("Enter the duration of song " + num + " in minutes: ");
	      	dur = keyboard.nextLine();
			addSong(title, dur, file);
			}

   }

   //************************************************************************************************
   //2nd constructor, Creates a new Music Album with the specified information if using a input FILE
   //************************************************************************************************
   public MusicAlbum(String TITLE, String ARTIST, int SONGNUM, String GENRE, int YEAR, Scanner scanner)
   	{
      albumTitle = TITLE;
      artistName = ARTIST;
      numberOfSongs = SONGNUM;
      yearOfRelease = YEAR;
      musicGenre = GENRE;
      songList = new Songs[SONGNUM];

		//adds songs based on how many songs the user specificed would be on the album
		for (int num = 1; num < SONGNUM+1; num++)
			{
	      	String SONGTITLE = scanner.nextLine();
	      	String FILE = scanner.nextLine();
	      	String DUR = scanner.nextLine();

			addSong(SONGTITLE, DUR, FILE);
			}

   }

    //*********************************************************
	//Returns the artist in the current music album as a string.
	//*********************************************************
	public String getArtist(){
    	return artistName;
   		}


   	//*************************************************************
	//Returns the # of songs in the current music album as a string.
	//*************************************************************
   	public String getNumberOfSongsStr(){
    	return Integer.toString(numberOfSongs);
   		}


   	//***************************************************************
	//Returns the # of songs in the current music album as an integer.
	//***************************************************************
   	public int getNumberOfSongsInt(){
    	return numberOfSongs;
   		}


   	//****************************************************************
	//Returns the release year of the current music album as a string.
	//****************************************************************
   	public String getReleaseYear(){
    	return Integer.toString(yearOfRelease);
   		}


   	//*********************************************************
	//Returns the genre of the current music album as a string.
	//*********************************************************
   	public String getGenre(){
    	return musicGenre;
   		}


   	//*********************************************************
   	//Returns the title of the current music album as a string.
   	//*********************************************************
    public String getTitle(){
    	return albumTitle;
    	}


    //************************************************************************
	//Returns the title, filename, and duration of the song at the num index.
	//************************************************************************
    public String getsongList(int num){
    		return songList[num].gettitle() + "\n" + songList[num].getfileName() + "\n" + songList[num].getduration();
    	}


	//***********************************************
	//Method which adds a song to the current album.
	//***********************************************
	public void addSong (String title, String dur, String file)
	{
		songList[count] = new Songs(title, dur, file);
		count++;
	}


   //***************************************************
   //Returns a string description of this Music Album.
   //***************************************************
   public String toString()
   {
      String description;
      description = "Artist: " + artistName + "  ||  " + "Album: " + albumTitle + "  ||  "
      	+ "Release Year: " + yearOfRelease + "  ||  " + "Genre: " + musicGenre + "  ||  " + "Number of Songs: " + numberOfSongs + "\n"
      		+ "\t\t****************List of Songs:****************\n";


      for (int songnum = 0; songnum < numberOfSongs; songnum++)
      	{
      	if (songList[songnum] != null){
         	description += songList[songnum] + "\n";}
      	}
      return description;


   }
}

