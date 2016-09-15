package VoxspellPrototype;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class WordList extends HashMap<String, HashMap<String, int[]>> {

	private static WordList _instance = null;
	
	private WordList() {
		super();
	}
	
	public static WordList GetWordList() {
		if (_instance == null) {
			_instance = initialiseNathansAwesomeDataStructure("NZCER-spelling-lists.txt");
		}
		
		return _instance;
	}
	
	public WordList ReloadWordList() {
		_instance = initialiseNathansAwesomeDataStructure("NZCER-spelling-lists.txt");
		
		return _instance;
	}
	
	private static WordList initialiseNathansAwesomeDataStructure(String fileName) {
		//Creating the file to read from
		File wordList = new File(fileName);

		String line;
		int lvlCounter = 1;
		String levelKey = "";
		
		//Initialising the data structures
		WordList nathansAwesomeDataStructure = new WordList();
		HashMap<String, int[]> levelHashMap = new HashMap<String, int[]>();
		
		try {
			//Creating the reader to loop through each line in the text file
			BufferedReader textFileReader = new BufferedReader(new FileReader(wordList));

			while((line = textFileReader.readLine()) != null) {
	
				//If the first char is % then its the name of the level
				if(line.charAt(0) == '%') {
					
					//Set the level name and increase the counter by 1
					levelKey = "Level " + (lvlCounter - 1);
					lvlCounter++;
					
					//Create the hashmap for that level
					levelHashMap = new HashMap<String, int[]>();

				} else {
					
					//Hashing each word to the level hashmap
					levelHashMap.put(line, new int[3]);
					
					//Hashing the level hashmap to the overall hashmap
					nathansAwesomeDataStructure.put(levelKey, levelHashMap);
					
				}
			}
			textFileReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return nathansAwesomeDataStructure;
	}
	
	/**
	 * Returns random selection of words from the wordlist specified.
	 * @param wordlistName Name of the list to select random words
	 * @param listCount Number of words to return
	 * @return
	 */
	public List<String> GetRandomWords(String wordlistName, int listCount) {
		// Get list of words from named wordlist
		Collection<String> wordset = this.get(wordlistName).keySet();
		List<String> wordlist = new ArrayList<String>(wordset);
		
		// Shuffle list
		java.util.Collections.shuffle(wordlist);
		
		// Ensure we don't try to return more elements than exist in the list
		listCount = Math.min(listCount, wordlist.size() - 1);
		
		// Return first n elements from shuffled list (essentially n random elements)
		return wordlist.subList(0, listCount);
	}
}
