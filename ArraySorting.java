/************************************************************************
 ** Filename: ArraySorting.java
 ** Author: Robert Silvan
 ** Student Number: 301118114
 ** Date: April 4, 2011
 ** Description:  Makes a Comparator argument for Arrays.sort to sort any
 ** array objects of MusicAlbum based on the artist's name.
 ************************************************************************/

import java.util.*;

//The Array class implements the Comparator Interface
public class ArraySorting implements Comparator<MusicAlbum> {

    //overrides the compare method
    public int compare(MusicAlbum p1, MusicAlbum p2) {
			if (p1 != null && p2 != null){
				return p1.getArtist().compareTo(p2.getArtist());}
			else
				return 0;

    }

}