/***************************************************
 ** Filename: SMOA.java
 ** Author: Robert Silvan
 ** Student Number: 301118114
 ** Date: April 4, 2011
 ** Description: Acts as the test driver for the Album
 ** Collection
 ******************************************************/

import java.util.*;

public class SMOA
{
   public static void main (String[] args)
   {

   	int Arraysizearg = 2;
		if (args.length > 0) {
    try {Arraysizearg = Integer.parseInt(args[0]);}				//Allows user to specify the size of the arrays as a cmd line arguement.
    catch (NumberFormatException e) {							//If no arguement is specified, the size will be default 2
        System.err.println("Argument must be an integer");
        System.exit(1);}
		}

	  int choice;											// Variable to be used to determine what the user wants to do next
	  int terminate = 0;                       		 		// Variable to be used to determine when to terminate the SMOA
	  String artistremove;                      	 		// Variable to be used to determine which artist the user wants to remove
	  AlbumCollection music = new AlbumCollection ();       // Creates an AlbumCollection Object where all albums will be stored
	  Scanner keyboard = new Scanner(System.in);


	  	music.input();							//Ask user whether he wants to read from a file

	  	music.setArraySize(Arraysizearg);		//Sets the size of the arrays

		// Program will run until the user wants to exit(where terminate wil be set to non 0)
		while (terminate == 0)
			{
		try{
		//User's Menu of Options
		System.out.println("To add an album to the SMOA, type 1");
		System.out.println("To remove an album from the SMOA, type 2");
		System.out.println("To display all your albums by increasing alphabetical sorted order of the name of the artist or group, type 3");
		System.out.println("To display the number of albums in the SMOA, type 4");
		System.out.println("To quit, type 5");
		choice = keyboard.nextInt();
		keyboard.nextLine();

			if (choice == 1){

				//Asks user for info and adds an album to the Album Collection through the addAlbum method
				System.out.println("Enter the name of the album: ");
				String aName = keyboard.nextLine();
				System.out.println("Enter the artist's name: ");
				String aArtist = keyboard.nextLine();
				System.out.println("Enter the year of release: ");
				int aYear = keyboard.nextInt();
				keyboard.nextLine();
				System.out.println("Enter the genre of music: ");
				String aGenre = keyboard.nextLine();
				System.out.println("Enter the number of songs on the album: ");
				int aSongnum = keyboard.nextInt();

				music.addAlbum (aName, aArtist, aSongnum, aYear, aGenre);
			}


			if (choice == 2){

				//asks user for artist which he wants to remove and runs the removeAlbum method based on his input
				System.out.println("\nEnter the name of the artist who's album you wish to remove: ");
				artistremove = keyboard.nextLine();
				artistremove = artistremove.toLowerCase();
				music.removeAlbum(artistremove);
			}


			if (choice == 3){
				//Sorts the Array alphabetically based on the artist name through the sortArray method,
				//and prints out the list of albums
				music.sortArray();
				//prints the album collection
				music.printOut();
			}


			if (choice == 4){
				//Reports how many albums are in the collection to the user
				System.out.println("\nThe SMOA is currently holding " + AlbumCollection.getNumOfAlbums() + " albums.\n");
			}

			if (choice == 5) {
				//terminates the SMOA
				terminate = 1;
				music.output();
			}

		}
			//Catch any possible errors
			catch( InputMismatchException theException ){
				System.out.println("\n***Your input is incorrect. A number is required for that entry. Returning to main menu.***\n");
				String garbage = keyboard.nextLine();
					}

		}
	}
}



