package code;


import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Scanner;

public class scramblebeater {
	
	public static Boolean isValid (String word) {
		int length = 0;
		
		for (int x = 0; x < word.length(); x++) {
			if (word.charAt(x) < 97 || word.charAt(x) > 122) 
				return (false);
			
				length++;	
		}
		return (true);
	}
		
	public static int hash (String word) {
		
		int hash = 0;
		int ascii;
		
		for (int x = 0; x < word.length(); x++) {
			ascii = word.charAt(x);
			hash += ascii*(x+1);
		}
		return (hash);
	}
	
	public static void hashin (String array[][], String word, int hash) {
		int y;
		if (hash > 20000) 
			hash = (hash%20000);
		
		
		for ( y = 0; array[hash][y] != null; y++) {
			if (array[hash][y].equals(word))
				break;
		}
		
		array [hash][y] = word;
		// System.out.println(word+ " stored at ( "+hash+", "+y+")");
		
		
	}
	
	public static Boolean anagram (String keyword, String word) {
		int [] lettertable = new int [123];
		
		
		for (int x = 0; x < keyword.length(); x++) {	
			lettertable [keyword.charAt(x)]++;
		}
		
		for (int x = 0; x < word.length(); x++) {
			if (--lettertable [word.charAt(x)]< 0)
				return (false);
		}
		return (true);
	}
	
	public static void type (String word) throws Exception{
		Robot player = new Robot();
		
		for (int x = 0; x < word.length(); x++) {
			player.keyPress(KeyEvent.VK_A + word.charAt(x) - 'a');
		}
		
		player.keyPress(KeyEvent.VK_ENTER);
	}
	
	public static void main(String[] args) throws Exception{
		
		String [][] hashmap = new String [20000][35];
		
		//Filling The Dictionary
		Robot player = new Robot();
		File data = new File ("popular.txt");
		Scanner reader1 = new Scanner (data);
		Scanner reader2 = new Scanner (System.in);
		String word;
		String keyword;
		int hash;
		
		while (reader1.hasNext()) {
			word = reader1.nextLine();
			
			if ((isValid(word) == true) && (word.length()>=3 && word.length() <= 6)) {
				hash = hash(word);
				hashin( hashmap, word, hash );
				
			}
		}
	
		player.keyPress(KeyEvent.VK_SPACE);
		System.out.println("enter the word:");
		keyword = reader2.next();
		
		Thread.sleep(5000);
		
		for (int x = 0; x < hashmap.length; x++) {
			for (int y = 0; y < 35; y++) {
				if (hashmap [x][y] != null) {
					if (anagram (keyword, hashmap[x][y]) == true) {
						type (hashmap [x][y]);
						
					}
				}		
			}
		}
		
	}
}
