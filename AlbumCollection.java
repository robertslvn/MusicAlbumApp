/***************************************************
 ** Filename: AlbumCollection.java
 ** Author: Robert Silvan
 ** Student Number: 301118114
 ** Date: April 4, 2011
 ** Description: Contains all the music albums
 ** which the user has.
 ******************************************************/

import java.util.*;
import java.io.*;

public class AlbumCollection extends SMOA
{
   Scanner keyboard = new Scanner(System.in);
   protected static int numOfAlbums;						  // variable to hold the total number of albums in the collection
   private int arraysize = 2;								  // variable which holds the user's choice for the size of the array
   private int character;									  // variable used to hold the index(in the alphabet) of the first letter of the current artist's name.
   private String alphabett = "abcdefghijklmnopqrstuvwxyz";	  // string of the alphabet, will be used to idenfity the index of the first letter of the current artist's name.


   //array used to determine how many artists starting with a certain letter of the alphabet have been added
   private int[] alphabetcount = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};

   //2D Array which contains all the albums and songs. Each of the 26 elements of the first dimension represents a letter
   //of the alphabet, thus only albums whos artist names start with that letter are stored in the 2nd dimension of that array.
   MusicAlbum[][] Alphabet = new MusicAlbum[26][arraysize];


   //***************************************************
   //Constructor: Creates an initially empty collection.
   //***************************************************
   public AlbumCollection ()
   {
      numOfAlbums = 0;
   }


   //*************************************************
   //Adds a Album to the collection, increasing the size of the
   //collection if necessary.
   //*************************************************
   public void addAlbum (String title, String artist, int tracks, int year, String genre)
   {
   	  int place = 99;						//variable used to hold the index(in the alphabet) of the first letter of the current artist's name.
	  artist = artist.toLowerCase(); 		//converts artist to lower case to be able to compare letters properly

	  for (int num = 0; num < 26; num++)
	  	{
	  	//finds the index(in the alphabet) of the first letter of the artist's name
	  	if (artist.charAt(0) == alphabett.charAt(num))
	  		{
	  		place = num;
	  		alphabetcount[num] = alphabetcount[num] + 1;
	  		}

	  	//checks if the array's size is big enough
	  	if (alphabetcount[num] -1 == arraysize)
			increaseSize();
	 	 }

	  //creates a new music album in the next empty space in the appropriate array
      Alphabet[place][alphabetcount[place]-1] = new MusicAlbum(title, artist, tracks, year, genre);
      numOfAlbums++;

   }


   //*********************************************
   //Method to remove an album from the collection
   //*********************************************
   public void removeAlbum(String artistremove)
   	{
   	int removecount = 0;		//variable used to tell if the user entered an artist who is in his collection
   	int removedconfirm = 0;		//variable used to tell if the user entered a valid album to remove
   	String removedigits = "";	//string which stores the index(s) of where the artist to be removed is stored in the array

	//finds the index(in the alphabet) of the first letter of the artist's name
	for (int num = 0; num < 26; num++)
		{
	  	if (artistremove.charAt(0) == alphabett.charAt(num))
	  		character = num;
	  	}

	//checks the array belonging to that letter of the alphabet
	//(2nd dimension of Alphabet[][])to find all the places where that artist is stored
   	for (int num = 0; num < alphabetcount[character]; num++)
   		{
   		if (Alphabet[character][num].getArtist().equals(artistremove))
   			{
   			removedigits = removedigits + Integer.toString(num);
   			removecount++;
   			}
   		}

	//if the above does not find any artists, tells the user he did not enter a proper artist
   	if (removecount == 0)
   		System.out.println("\nYou entered an artist who is not in your collection\n");

   	//if only found 1 album by the artist, remove that artist from the collection by setting its array cell to null
   	else if (removecount == 1)
   		{
   		Alphabet[character][Integer.parseInt(removedigits)] = null;
   		System.out.println("\n" + artistremove + " has been removed from your collection.\n");
   		numOfAlbums--;
   		alphabetcount[character] = alphabetcount[character] - 1;

   		//sort the array by shifting all its content to the left (starting at the cell which was set to null)
   		//this removes any null cells in between cells containing objects
   		for (int i = Integer.parseInt(removedigits); i < arraysize-1; i++)
				Alphabet[character][i] = Alphabet[character][i+1];
   		}

   		//if we found multiple artists, ask which album by the artist to remove
   		else
   			{
   			//print out the albums by the artist
   			for (int num = 0; num < removedigits.length(); num++)
   				System.out.println("Album by this artist: "+ Alphabet[character][num].getTitle());

   			System.out.println("Which album do you want to remove?");
   			String removethis = keyboard.nextLine();
   			removethis = removethis.toLowerCase();

			//if the album and artist are valid, find their array cell and remove them by setting their array cell to null
   			for (int num = 0; num < alphabetcount[character]; num++)
   				{
   				if (Alphabet[character][num].getArtist().toLowerCase().equals(artistremove) && Alphabet[character][num].getTitle().toLowerCase().equals(removethis))
   					{
   					Alphabet[character][num] = null;
   					System.out.println("\n" + removethis + " has been removed from your collection.\n");
   					numOfAlbums--;
   					alphabetcount[character] = alphabetcount[character] - 1;
   					removedconfirm = 1;

					//sort the array by shifting all its content to the left (starting at the cell which was set to null)
   					//this removes any null cells in between cells containing objects
					for (int temp = num; temp < arraysize-1; temp++)
							Alphabet[character][temp] = Alphabet[character][temp+1];

					}
				}

			//if the user's input for the album is not found
			if (removedconfirm == 0)
				System.out.println("\nThat album does not exist\n");

			}
		}


   //***************************************************************************
   //Method which sorts the Array in alphabetical order based on the artist's name
   //(using the ArraySorting class).
   //***************************************************************************
   public void sortArray()
   	{
	for (int num = 0; num < 26; num++)
   	Arrays.sort(Alphabet[num], new ArraySorting());
	}


   //******************************************************************
   //Method which returns the total number of albums in the collection
   //******************************************************************
   public static int getNumOfAlbums()
   {
   	return numOfAlbums;
   }

   //*********************************************************************************
   //Method which sets the size of the aray (based on the cmd line arguement supplied)
   //*********************************************************************************
   public void setArraySize(int sizearg){
   	arraysize = sizearg;
   }


   //************************************************************************
   //Method which print out all the albums and songs in the album collection
   //***********************************************************************
   public void printOut()
   	{
   	System.out.println("Album Collection: \n\n");
   	for (int alphaprint = 0; alphaprint < 26; alphaprint++){
      	for (int albumprint = 0; albumprint < arraysize; albumprint++){
      		if (Alphabet[alphaprint][albumprint] != null){
      			System.out.println(Alphabet[alphaprint][albumprint]);}}}
   	}


   //************************************************************************
   //Method which outputs the contents of the Album Collection to a text file
   //************************************************************************
	public void output(){
		String Choice = "";						//Tracks whether or not the user wants to output to a file
		FileOutputStream fos;
    	DataOutputStream dos;
    	boolean inputTypeBad = true;			// Tracks whether the filename/path entered is valid

		while (inputTypeBad == true)
			{
		//Asks if user wants to save collection or not
		System.out.print("If you want to save your collection, please type yes. Otherwise press enter or type anything to quit.");
		Choice = keyboard.nextLine();
		Choice = Choice.toLowerCase();

		try
			{
		//if user wants to save collection, the following is run
		if (Choice.equals("yes"))
			{
		//asks for the filename + path of where to write to
		System.out.print("Please enter the name of a file + path to write to in the following format: (ex. C:\\\\Users\\\\Guest\\\\Desktop\\\\myfile.txt)");
		String aFileName = keyboard.nextLine();
		aFileName.toLowerCase();

			//creates a new file based on user's input
			File file= new File(aFileName);
			fos = new FileOutputStream(file);
      		dos = new DataOutputStream(fos);

      		//input is good if we get here, so while loop need not run next time
      		inputTypeBad = false;

			//writes each attribute of each object contained in the Alphabet 2d array (each on a seperate line)
			for (int alphaprint = 0; alphaprint < 26; alphaprint++){
      			for (int albumprint = 0; albumprint < arraysize; albumprint++){
      				if (Alphabet[alphaprint][albumprint] != null){
      					dos.writeBytes(Alphabet[alphaprint][albumprint].getArtist());
      					dos.writeBytes("\r\n");
      					dos.writeBytes(Alphabet[alphaprint][albumprint].getTitle());
      					dos.writeBytes("\r\n");
      					dos.writeBytes(Alphabet[alphaprint][albumprint].getReleaseYear());
      					dos.writeBytes("\r\n");
      					dos.writeBytes(Alphabet[alphaprint][albumprint].getGenre());
      					dos.writeBytes("\r\n");
      					dos.writeBytes(Alphabet[alphaprint][albumprint].getNumberOfSongsStr());
      					dos.writeBytes("\r\n");
						for (int song = 0; song < Alphabet[alphaprint][albumprint].getNumberOfSongsInt(); song++){
      						dos.writeBytes(Alphabet[alphaprint][albumprint].getsongList(song));
      						dos.writeBytes("\r\n");
						}}}}

            dos.flush();
			}
			else
				//if user doesnt want to write to a file, exit the loop by setting variable
				inputTypeBad = false;
		}
	catch (IOException e) {System.out.println("\nError, you cannot write to that location.\n");}		//catch any exceptions
		}
	System.out.println("See you next time!");
	}


   //************************************************************************
   //Method which inputs the contents of a text file into the SMOA
   //************************************************************************
	public void input(){
		String input = "";						// Tracks whether user wants to input data from a file
		boolean inputTypeBad = true;			// Tracks whether the filename/path entered is valid

	while (inputTypeBad == true)
		{
	  //asks if user wants to input from a file
	  System.out.println("Would you like to input data from a file? (Type yes if you want to, otherwise press enter or type anything)");
	  input = keyboard.nextLine();
	  input = input.toLowerCase();

	  //if user wants to input, the following is run
	  if (input.equals("yes"))
	  	{
		//asks for file name/path to import from, stores it
		System.out.print("Please enter the name of the file + path to write from in the following format: (ex. C:\\\\Users\\\\Guest\\\\Desktop\\\\myfile.txt) ");
		String aFile = keyboard.nextLine();
		aFile.toLowerCase();

		File file = null;
		try {
		file = new File(aFile);
		//takes the first 5 lines of the file as the 5 attributes of the album
		Scanner scanner = new Scanner(file);
		inputTypeBad = false;
		while (scanner.hasNextLine())
			{
        		String ARTIST = scanner.nextLine();
        		String TITLE = scanner.nextLine();
        		int YEAR = Integer.parseInt(scanner.nextLine());
        		String GENRE = scanner.nextLine();
        		int SONGNUM = Integer.parseInt(scanner.nextLine());

		//goes through the same code as for adding an album,
		//except now it uses the strings found in the file instead of user input
	  	int place = 50;
	 	ARTIST = ARTIST.toLowerCase();

	  	for (int num = 0; num < 26; num++)
	  	{
	  	//finds the index(in the alphabet) of the first letter of the artist's name
	  	if (ARTIST.charAt(0) == alphabett.charAt(num))
	  		{
	  		place = num;
	  		alphabetcount[num] = alphabetcount[num] + 1;
	  		}
		//checks if the array's size is big enough
	  	if (alphabetcount[num] -1 == arraysize)
			increaseSize();
	 	 }

	  //creates a new music album in the next empty space in the appropriate array, also passes scanner as parameter
      Alphabet[place][alphabetcount[place]-1] = new MusicAlbum(TITLE, ARTIST, SONGNUM, GENRE, YEAR, scanner);
      numOfAlbums++;
		}

    	}
    catch(FileNotFoundException caughtException) {System.out.println("\nThat file cannot be found.\n"); }
    catch(IOException caughtException) {System.out.println("\nError.\n");}
	}

	//if user doesnt want to input from file, exit the while loop by setting variable
	else
		inputTypeBad = false;
		}
		}


   //*************************************************
   //Returns a report describing the Album collection.
   //*************************************************
   public String toString()
   {
	  String output = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
      output += "My Album Collection\n\n";
	  output += "\nAlbum List:\n\n";

      for (int alpha = 0; alpha < 26; alpha++){
      	for (int album = 0; album < arraysize; album++){
      		if (Alphabet[alpha][album] != null){
         		output += Alphabet[alpha][album] + "\n";}}}
      return output;
   }

   //*******************************************************
   //Increases the capacity of the collection by creating a
   //larger array and copying the existing collection into it.
   //*********************************************************
   private void increaseSize ()
   {
		MusicAlbum[][] temparray = new MusicAlbum[26][arraysize+10];

      			for (int alpha = 0; alpha < 26; alpha++){
      				for (int album = 0; album < arraysize; album++){
         					temparray[alpha][album] = Alphabet[alpha][album];}}

     	Alphabet = temparray;
        arraysize = arraysize+10;
	}


}
