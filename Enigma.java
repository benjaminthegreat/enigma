import java.util.*;
import java.io.*;

public class Enigma {
	//store rotors and their notch positions
	public static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String ROTORI = "EKMFLGDQVZNTOWYHXUSPAIBRCJ";
	public static final String ROTORII = "AJDKSIRUXBLHWTMCQGZNPYFVOE";
	public static final String ROTORIII = "BDFHJLCPRTXVZNYEIWGAKMUSQO";
	public static final String ROTORIV = "ESOVPZJAYQUIRHXLNFTGKDCMWB";
	public static final String ROTORV = "VZBRGITYUPSDNHLXAWMJQOFECK";
	public static final String[] MEGGAEGG = {ROTORI, ROTORII, ROTORIII, ROTORIV, ROTORV};
	/*public static HashMap<Character, Character> ROTORMAPI = new HashMap<Character, Character>();
	public static HashMap<Character, Character> ROTORMAPII = new HashMap<Character, Character>();
	public static HashMap<Character, Character> ROTORMAPIII = new HashMap<Character, Character>();
	public static HashMap<Character, Character> ROTORMAPIV = new HashMap<Character, Character>();
	public static HashMap<Character, Character> ROTORMAPV = new HashMap<Character, Character>();
	public static ArrayList<HashMap<Character, Character>> BIGEGG = new ArrayList<HashMap<Character, Character>>();
	public static HashMap<Character, Character> BROTORMAPI = new HashMap<Character, Character>();
	public static HashMap<Character, Character> BROTORMAPII = new HashMap<Character, Character>();
	public static HashMap<Character, Character> BROTORMAPIII = new HashMap<Character, Character>();
	public static HashMap<Character, Character> BROTORMAPIV = new HashMap<Character, Character>();
	public static HashMap<Character, Character> BROTORMAPV = new HashMap<Character, Character>();
	public static ArrayList<HashMap<Character, Character>> BIGGEGG = new ArrayList<HashMap<Character, Character>>();*/
	public static final char TURNI = 'R';
	public static final char TURNII = 'E';
	public static final char TURNIII = 'V';
	public static final char TURNIV = 'J' + 1;
	public static final char TURNV = 'A';
	public static final char[] 	TURNS = {TURNI, TURNII, TURNIII, TURNIV, TURNV};
	public static final String THINGTODECRYPT = "BNEHUDQNOGTDYQVKYICIT";
	//public static final String THINGTODECRYPT = "KJKRVCZFIQDUDHSBCGIKVJQEMBUXWBGOBEKMQIXFODK";
	//reflector
	public static final String REFLECTOR = "YRUHQSLDPXNGOKMIEBFZCWVJAT";
	public static final HashMap<Character, Character> REFLECTORMAP = new HashMap<Character, Character>();
	//global variables for final plugboard settings for cracking enigm
	//why
	public static HashMap<Character, Character> MAP1;
	public static HashMap<Character, Character> MAP2;
	public static long timeMaster = 0;
	public static long timeBoi = 0;
	//file for putting
	public static void main(String[] args) throws IOException {
		/*BIGEGG.add(ROTORMAPI);
		BIGEGG.add(ROTORMAPII);
		BIGEGG.add(ROTORMAPIII);
		BIGEGG.add(ROTORMAPIV);
		BIGEGG.add(ROTORMAPV);
		BIGGEGG.add(BROTORMAPI);
		BIGGEGG.add(BROTORMAPII);
		BIGGEGG.add(BROTORMAPIII);
		BIGGEGG.add(BROTORMAPIV);
		BIGGEGG.add(BROTORMAPV);
		//initialize the hashmaps
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 26; j++) {
				BIGEGG.get(i).put(ALPHABET.charAt(j), MEGGAEGG[i].charAt(j));
				BIGGEGG.get(i).put(MEGGAEGG[i].charAt(j), ALPHABET.charAt(j));
			}
		}
		//the final hashmaps
		for(int i = 0; i < 26; i++) {
			REFLECTORMAP.put(REFLECTOR.charAt(i), ALPHABET.charAt(i));
		}*/
		Scanner inputGetter = new Scanner(System.in);
		//printstream for solution file
		System.out.print("Start decrypting where?\n>");
		String childe = inputGetter.nextLine();
		if(isInteger(childe)) {
			File child = new File(childe + ".txt");
			PrintStream output;
			String initialSettings = "I II III A A A";
			String lastLine = "";
			if(child.isFile()) {
				//why is this the best way to do this
				//requires linux or mingw
				try {
					//run tail and grab the last line	
					Runtime r = Runtime.getRuntime();
					Process p = r.exec("tail " + childe + ".txt");
					p.waitFor();
					Scanner s = new Scanner(p.getInputStream());
					while(s.hasNextLine()) {
						lastLine = s.nextLine();
					}
					s.close();
				} catch(Exception e) {
					System.out.println("This program requires use of tail\nYour computer probably lacks this. Use MinGW or Linux");
				}
				child = new File(childe + "a.txt");
				if(lastLine.length() > 0) {
					initialSettings = lastLine.substring(0, lastLine.lastIndexOf(' '));
				}
			}
			output = new PrintStream(child);
			int startDecrypt = Integer.parseInt(childe);
			//long numbers = 0;
			//long totalTime = 0;
			boolean isValid = false;
			for(int i = startDecrypt; i < THINGTODECRYPT.length() - "COMPUTER".length() && !isValid; i++) {
				isValid = true;
				for(int j = i; j < "COMPUTER".length() + i; j++) {
					if(THINGTODECRYPT.charAt(j) == "COMPUTER".charAt(j - i)) {
						isValid = false;
						break;
					}
				}
				if(isValid) {
					//long time = System.currentTimeMillis();
					System.out.println(i);
					
					enigmaCracker(THINGTODECRYPT, "COMPUTER", i, output, initialSettings);
					//numbers++;
					//long boi = System.currentTimeMillis() - time;
					//totalTime += boi;
					//System.out.println(boi);
				}
			}
			//System.out.println(timeBoi);
			//System.out.println(timeMaster);
			//System.out.println((double) timeMaster / timeBoi);
			//System.out.println((double) totalTime / (double) numbers); 
			//System.out.println(totalTime);
		}
		else {
			/*else {
			inputGetter.next();
		}*/
			//enigma time
			//welcome
			//System.out.print(rotateRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ", 1) + "\n" + rotateRotor("ABCDEFGHIJKLMNOPQRSTUVWXYZ", -1) + "\n");
			System.out.print("Welcome to EnigmaBoi\n>");
			//get first input
			String input = inputGetter.nextLine();
			input = input.toUpperCase();
			//input loop
			while(!input.equalsIgnoreCase("quit")) {
				//make a string to store the result
				String result = "";
				Scanner inputScanner = new Scanner(input);
				//decide between encryption and decryption
				if(input.startsWith(">")) {
					//encryption
					if(input.startsWith(">C")) {
						//grab first
						String encryption = inputScanner.next();
						//decide between simple and advanced
						if(encryption.length() > 2) {
							//grab the encryption value as an int
							int enc = Integer.parseInt(encryption.substring(2));
							//and encrypt
							result = caesar(inputScanner.nextLine(), enc);
						}
						else {
							//simple encryption
							result = caesar(inputScanner.nextLine(), 1);
						}
					}
					//now do the affine cipher
					if(input.startsWith(">A")) {
						result = affine(input.substring(input.indexOf(" ")), "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
					}
					//and the rotor cipher
					if(input.startsWith(">R")) {
						//perform the first encryption
						result = affine(input.substring(input.indexOf(" ")), "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "EKMFLGDQVZNTOWYHXUSPAIBRCJ");
						//second
						result = affine(result, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "AJDKSIRUXBLHWTMCQGZNPYFVOE");
						//third rotor
						result = affine(result, "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "BDFHJLCPRTXVZNYEIWGAKMUSQO");
					}
				}
				else if(input.startsWith("<")) {
					//decryption
					if(input.startsWith("<C")) {
						//grab first
						String decryption = inputScanner.next();
						//decide between simple and advanced
						if(decryption.length() > 2) {
							//grab the decryption value as an int
							int dec = Integer.parseInt(decryption.substring(2));
							//and decrypt
							result = caesar(inputScanner.nextLine(), -dec);
						}
						else {
							//simple decryption
							result = caesar(inputScanner.nextLine(), -1);
						}

					}
					//now do the affine cipher
					if(input.startsWith("<A")) {
						result = affine(input.substring(input.indexOf(" ")), "EKMFLGDQVZNTOWYHXUSPAIBRCJ","ABCDEFGHIJKLMNOPQRSTUVWXYZ" );
					}
					//and the rotor cipher
					if(input.startsWith("<R")) {
						//perform the first decryption
						result = affine(input.substring(input.indexOf(" ")), "BDFHJLCPRTXVZNYEIWGAKMUSQO", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
						//second rotor
						result = affine(result, "AJDKSIRUXBLHWTMCQGZNPYFVOE", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
						//third rotor
						result = affine(result, "EKMFLGDQVZNTOWYHXUSPAIBRCJ", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
					}
				}
				//enigma
				//aaaaaaaaaaaaaaaaaa
				else {
					//make strings for the rotors and for their names
					String left, middle, right;
					String leftNumber, middleNumber, rightNumber;
					//store the rotor numbers
					leftNumber = inputScanner.next();
					middleNumber = inputScanner.next();
					rightNumber = inputScanner.next();
					left = getRotorFromRomans(leftNumber);
					middle = getRotorFromRomans(middleNumber);
					right = getRotorFromRomans(rightNumber);
					//left offset
					int loffset = inputScanner.next().charAt(0) - 65;
					//middle offset
					int moffset = inputScanner.next().charAt(0) - 65;
					//right offset
					int roffset = inputScanner.next().charAt(0) - 65;
					/*roffset = -roffset;
				moffset = -moffset;
				loffset = -loffset;*/
					//System.out.printf("%d %d %d\n", roffset, moffset, loffset);
					//beautiful code amazing on the internet beatuiful everybody lieks Programs
					String map1 = "", map2 = "";
					for(int i = 0; i < 10; i++) {
						String thing = inputScanner.next();
						map1 += thing.charAt(0);
						map2 += thing.charAt(1);
					}
					//grab the valune
					String valune = inputScanner.nextLine();
					//and apply the enigma method
					result = enigmaster(left, middle, right, leftNumber, middleNumber, rightNumber, loffset, moffset, roffset, map1, map2, valune);
				}
				System.out.print(result + "\n>");
				input = inputGetter.nextLine();
				input = input.toUpperCase();
				inputScanner.close();
			}
			inputGetter.close();
		}
	}
	//caesarean cipher method
	public static String caesar(String valune, int key) {
		//make a final string to return
		String toBeReturned = "";
		//and iterate through the valune
		for(int i = 0; i < valune.length(); i++) {
			//grab and check the current character to determine letterness
			char c = valune.charAt(i);
			if(c >= 'A' && c <= 'Z' ) {
				//encrypt the letter
				c += key;
				//and fix any encryption errors
				if(c < 'A') {
					c += 26;
				}
				if(c > 'Z') {
					c -= 26;
				}
				toBeReturned += c;
			}
			else {
				//simply add anything not a letter
				toBeReturned += c;
			}
		}
		return toBeReturned;
	}
	//affine cipher method
	public static String affine(String valune, String base, String key) {
		//make a final string to return
		String toBeReturned = "";
		//and iterate through this string
		for(int i = 0 ; i < valune.length(); i++) {
			//grab the char
			char c = valune.charAt(i);
			//check if it's in the base
			if(base.indexOf(c) != -1) {
				//store the index
				int index = base.indexOf(c);
				//and add the output char
				toBeReturned += key.charAt(index);
			}
			else {
				toBeReturned += c;
			}
		}
		return toBeReturned;
	}
	//rotate a string to the right by rotation
	public static String rotateString(String rotor, int rotation) {
		//sanitize rotation
		rotation = rotation % rotor.length();
		//initialize my string
		String rotated = "";
		//loop to perform rotation one char at a time
		for(int i = 0; i < rotor.length(); i++) {
			if(i + rotation >= rotor.length()) {
				rotation -= rotor.length();
			}
			if(i + rotation < 0) {
				rotation += rotor.length();
			}
			rotated += rotor.charAt(i + rotation);
		}
		return rotated;
	}
	//get rotor from roman numerals
	public static String getRotorFromRomans(String roman) {
		if(roman.equals("I")) {
			return ROTORI;
		}
		if(roman.equals("II")) {
			return ROTORII;
		}
		if(roman.equals("III")) {
			return ROTORIII;
		}
		if(roman.equals("IV")) {
			return ROTORIV;
		}
		if(roman.equals("V")) {
				return ROTORV;
		}
		return "";
	}
	//method to apply plugboards
	//map1 and 2 are characters in the same order
	public static String plugboard(String input, String map1, String map2) {
		//final string to return
		String plugged = "";
		//iterate through and map each character
		for(int i = 0; i < input.length(); i++) {
			if(map1.contains("" + input.charAt(i))) {
				plugged += map2.charAt(map1.indexOf(input.charAt(i)));
			}
			else if(map2.contains("" + input.charAt(i))) {
				plugged += map1.charAt(map2.indexOf(input.charAt(i)));
			}
			else {
				plugged += input.charAt(i);
			}
			
		}
		return plugged;
	}
	//method to apply plugboards
	//map1 and 2 are characters in the same order
	/*public static StringBuilder plugboard(String input, HashMap<Character, Character> map1, HashMap<Character, Character> map2) {
		//final string to return
		StringBuilder plugged = new StringBuilder();
		//iterate through and map each character
		for(Character c : input) {
			Character j = map1.get(c);
			Character i = map2.get(c);
			if(j != null) {
				plugged.append(j);
			}
			else if(i != null) {
				plugged.append(i);
			}
			else {
				plugged.append(c);
			}
		}
		return plugged;
	}*/
	//this one is in strings
	public static String plugboard(String input, HashMap<Character, Character> map1, HashMap<Character, Character> map2) {
		//final string to return
		StringBuilder plugged = new StringBuilder();
		//iterate through and map each character
		for(char c : input.toCharArray()) {
			Character j = map1.get(c);
			Character i = map2.get(c);
			if(j != null) {
				plugged.append(j);
			}
			else if(i != null) {
				plugged.append(i);
			}
			else {
				plugged.append(c);
			}
		}
		return plugged.toString();
	}
	//method to grab turnover points from roman numerals
	public static char turnaround(String roman) {
		if(roman.equals("I")) {
			return TURNI;
		}
		if(roman.equals("II")) {
			return TURNII;
		}
		if(roman.equals("III")) {
			return TURNIII;
		}
		if(roman.equals("IV")) {
			return TURNIV;
		}
		if(roman.equals("V")) {
				return TURNV;
		}
		return 0;
	}
	//enigma method
	public static String enigmaster(String left, String middle, String right, String leftNumber, String middleNumber, String rightNumber, int loffset, int moffset, int roffset, String map1, String map2, String valune) {
		String result = plugboard(valune, map1, map2);
		//String result = valune;
		//string to make a new version of the input
		String encrypted = "";
		boolean doubletpose = false;
		boolean doubledoubletpose = false;
		//loop to encrypt
		for(int i = 0; i < result.length(); i++) {
			//encrypt the text
			//only do it if it's a letter
			if(ALPHABET.contains("" + result.charAt(i))) {
				//spin the rotor
				roffset++;
				
				//and mod by 26
				roffset %= 26;
				//check turnaround
				//System.out.printf("%d ?= %d\n", 65 + roffset, (int)turnaround(rightNumber));
				if(65 + roffset == turnaround(rightNumber)) {
					moffset++;
					moffset %= 26;
					//check for doubletpose
					//System.out.printf("%d ?= %d\n", 65 + moffset, (int)turnaround(middleNumber) - 1);
					if(65 + moffset == turnaround(middleNumber) - 1) {
						doubletpose = true;
					}
					//check that turnaround
					/*if(65 + moffset == turnaround(middleNumber) - 1) {
						//rotate the left
						loffset++;
						loffset %= 26;
						//and check for doubletpose
						if(65 + moffset == turnaround(leftNumber) - 1) {
							doubledoubletpose = true;
						}
					}*/
				}
				
				//System.out.printf("%2d %2d %2d\n", roffset, moffset, loffset);
				//make the encrypted char
				char c = result.charAt(i);
				//System.out.println(c);
				//put it through the rotors
				//right rotor
				//apply shift
				c += roffset;
				if(c > 'Z') {
					c -= 26;
				}
				//System.out.println(c);
				//index into rotor
				c = right.charAt(ALPHABET.indexOf(c));
				//and reverse shift
				c -= roffset;
				if(c < 'A') {
					c += 26;
				}
				//System.out.println(c);
				//middle rotor
				//apply shift
				c += moffset;
				if(c > 'Z') {
					c -= 26;
				}
				//System.out.println(c);
				//index into rotor
				c = middle.charAt(ALPHABET.indexOf(c));
				//and reverse shift
				c -= moffset;
				if(c < 'A') {
					c += 26;
				}
				//left rotor
				//apply shift
				c += loffset;
				if(c > 'Z') {
					c -= 26;
				}
				//index into rotor
				c = left.charAt(ALPHABET.indexOf(c));
				//and reverse shift
				c -= loffset;
				if(c < 'A') {
					c += 26;
				}
				//reflector
				c = REFLECTOR.charAt(ALPHABET.indexOf(c));
				//System.out.println(c);
				//and back through the rotors
				//left rotor
				//apply shift
				c += loffset;
				if(c > 'Z') {
					c -= 26;
				}
				//index out of rotor
				c = ALPHABET.charAt(left.indexOf(c));
				//and reverse shift
				c -= loffset;
				if(c < 'A') {
					c += 26;
				}
				//System.out.println(c);
				//middle rotor
				//apply shift
				c += moffset;
				if(c > 'Z') {
					c -= 26;
				}
				//index out of rotor
				c = ALPHABET.charAt(middle.indexOf(c));
				//and reverse shift
				c -= moffset;
				if(c < 'A') {
					c += 26;
				}
				//System.out.println(c);
				//right rotor
				//apply shift
				c += roffset;
				if(c > 'Z') {
					c -= 26;
				}
				//index out of rotor
				c = ALPHABET.charAt(right.indexOf(c));
				//and reverse shift
				c -= roffset;
				if(c < 'A') {
					c += 26;
				}
				//and the left rotor doubletpose
				if(doubledoubletpose) {
					loffset++;
					loffset %= 26;
					doubledoubletpose = false;
				}
				//possibly doubletpose
				if(doubletpose) {
					moffset++;
					moffset %= 26;
					doubletpose = false;
					//rotate the left
					loffset++;
					loffset %= 26;
					//and check for doubledoubletpose
					if(65 + moffset == turnaround(leftNumber) - 1) {
						doubledoubletpose = true;
					}
				}
				
				
				//System.out.println(c);
				//and add it to the string, which gets plugboarded
				encrypted += c;
			}
			//not a letter
			else {
				encrypted += result.charAt(i);
			}
		}
		//back through the plugboard
		result = plugboard(encrypted, map1, map2);
		return result;
	}
	//enigma method
	public static String enigmaster(int bleft, int bmiddle, int bright, int loffset, int moffset, int roffset, HashMap<Character, Character> map1, HashMap<Character, Character> map2, String valune, int indexian) {
		String result = plugboard(valune, map1, map2);
		//String result = valune;
		//string to make a new version of the input
		StringBuilder encrypted = new StringBuilder();
		boolean doubletpose = false;
		boolean doubledoubletpose = false;
		String left = MEGGAEGG[bleft];
		String right = MEGGAEGG[bright];
		String middle = MEGGAEGG[bmiddle];
		//loop to encrypt
		for(int i = 0; i < result.length(); i++) {
			//encrypt the text
			//only do it if it's a letter
			if(ALPHABET.contains("" + result.charAt(i))) {
				//spin the rotor
				roffset++;

				//and mod by 26
				roffset %= 26;
				//check turnaround
				//System.out.printf("%d ?= %d\n", 65 + roffset, (int)turnaround(rightNumber));
				if(65 + roffset == TURNS[bright]) {
					moffset++;
					moffset %= 26;
					//check for doubletpose
					//System.out.printf("%d ?= %d\n", 65 + moffset, (int)turnaround(middleNumber) - 1);
					if(65 + moffset == TURNS[bmiddle] - 1) {
						doubletpose = true;
					}
					//check that turnaround
					/*if(65 + moffset == turnaround(middleNumber) - 1) {
							//rotate the left
							loffset++;
							loffset %= 26;
							//and check for doubletpose
							if(65 + moffset == turnaround(leftNumber) - 1) {
								doubledoubletpose = true;
							}
						}*/
				}
				if(indexian <= 0) {
					//System.out.printf("%2d %2d %2d\n", roffset, moffset, loffset);
					//make the encrypted char
					//make the encrypted char
					char c = result.charAt(i);
					//System.out.println(c);
					//put it through the rotors
					//right rotor
					//apply shift
					c += roffset;
					if(c > 'Z') {
						c -= 26;
					}
					//System.out.println(c);
					//index into rotor
					c = right.charAt(ALPHABET.indexOf(c));
					//and reverse shift
					c -= roffset;
					if(c < 'A') {
						c += 26;
					}
					//System.out.println(c);
					//middle rotor
					//apply shift
					c += moffset;
					if(c > 'Z') {
						c -= 26;
					}
					//System.out.println(c);
					//index into rotor
					c = middle.charAt(ALPHABET.indexOf(c));
					//and reverse shift
					c -= moffset;
					if(c < 'A') {
						c += 26;
					}
					//left rotor
					//apply shift
					c += loffset;
					if(c > 'Z') {
						c -= 26;
					}
					//index into rotor
					c = left.charAt(ALPHABET.indexOf(c));
					//and reverse shift
					c -= loffset;
					if(c < 'A') {
						c += 26;
					}
					//reflector
					c = REFLECTOR.charAt(ALPHABET.indexOf(c));
					//System.out.println(c);
					//and back through the rotors
					
					//left rotor
					//apply shift
					c += loffset;
					if(c > 'Z') {
						c -= 26;
					}
					//index out of rotor
					c = ALPHABET.charAt(left.indexOf(String.valueOf(c)));
					//and reverse shift
					c -= loffset;
					if(c < 'A') {
						c += 26;
					}
					//System.out.println(c);
					
					//middle rotor
					//apply shift
					c += moffset;
					if(c > 'Z') {
						c -= 26;
					}
					//index out of rotor
					c = ALPHABET.charAt(middle.indexOf(String.valueOf(c)));
					//and reverse shift
					c -= moffset;
					if(c < 'A') {
						c += 26;
					}
					//System.out.println(c);
					
					//right rotor
					//apply shift
					c += roffset;
					if(c > 'Z') {
						c -= 26;
					}
					//index out of rotor
					c = ALPHABET.charAt(right.indexOf(String.valueOf(c)));
					//and reverse shift
					c -= roffset;
					if(c < 'A') {
						c += 26;
					}
					//System.out.println(c);
					//and add it to the string, which gets plugboarded
					encrypted.append(c);
				} else {
					i--;
					indexian--;
				}
				//and the left rotor doubletpose
				if(doubledoubletpose) {
					loffset++;
					loffset %= 26;
					doubledoubletpose = false;
				}
				//possibly doubletpose
				if(doubletpose) {
					moffset++;
					moffset %= 26;
					doubletpose = false;
					//rotate the left
					loffset++;
					loffset %= 26;
					//and check for doubledoubletpose
					if(65 + moffset == TURNS[bleft] - 1) {
						doubledoubletpose = true;
					}
				}
			}
			//not a letter
			else {
				encrypted.append(result.charAt(i));
			}
		}
		//back through the plugboard
		result = plugboard(encrypted.toString(), map1, map2);
		return result;
	}
	//enigma cracker - brute force
	public static void enigmaCracker(String key, String startswith, int pos, PrintStream output, String initialSettings) {
		//these help with formatting
		String[] romans = {"I", "II", "III", "IV", "V"};
		List<String> romansIndexable = Arrays.asList(romans);
		//make initial values for settings
		//the i stands for initial
		int ii, ji, ki, li, mi, ni;
		Scanner boyo = new Scanner(initialSettings);
		ii = romansIndexable.indexOf(boyo.next());
		ji = romansIndexable.indexOf(boyo.next());
		ki = romansIndexable.indexOf(boyo.next());
		li = boyo.next().charAt(0) - 'A';
		mi = boyo.next().charAt(0) - 'A';
		ni = boyo.next().charAt(0) - 'A';
		boyo.close();
		//for loop
		//first rotor
		for(int i = ii; i < 5; i++) {
			//second rotor
			for(int j = ji; j < 5; j++) {
				if(j == i) continue;
				//third rotor
				for(int k = ki; k < 5; k++) {
					if(k == j || k == i) continue;
					System.out.printf("%s %s %s\n", romans[i], romans[j], romans[k]);
					//rotor settings
					for(int l = li; l < 26; l++) {
						for(int m = mi; m < 26; m++) {
							for(int n = ni; n < 26; n++) {								//plugboard
								//herd
								//arraylist for wrong plugboard settings here
								ArrayList<String> wrongs = new ArrayList<String>();
								//call recursive method to make plugboard assumptions
								//long timeChild = System.currentTimeMillis();
								if(plugboard(wrongs, i, j, k, l, m, n, key, startswith, 0, new HashMap<Character, Character>(), new HashMap<Character, Character>(), pos, 0)) {
									String s = enigmaster(i, j, k, l, m, n, MAP1, MAP2, key, 0);
									if(s.contains(startswith)) {
										output.printf("%s %s %s %c %c %c ", romans[i], romans[j], romans[k], l + 'A', m + 'A', n + 'A');
										for(char c : MAP1.keySet()) {
											output.print(c + "" + MAP1.get(c) + " ");
										}
										output.println(s);
									}
									//return true;
									output.flush();
								}
								//timeMaster += System.currentTimeMillis() - timeChild;
								//System.out.println(timeBoi);
							}
						}
					}
				}
			}
		}
		//return false;
	}
	//method to call enigma easily
	/*public static String enigma(int left, int middle, int right, int loffset, int moffset, int roffset, HashMap<Character, Character> map1, HashMap<Character, Character> map2, String valune) {
		//initialize two arrays to grab the correct rotors
		String[] rotors = {ROTORI, ROTORII, ROTORIII, ROTORIV, ROTORV};
		String[] romans = {"I", "II", "III", "IV", "V"};
		//and grab those to call enigma
		return enigmaster(rotors[left], rotors[middle], rotors[right], romans[left], romans[middle], romans[right], loffset, moffset, roffset, map1, map2, valune.toUpperCase()).toString();
	}*/
	//recursive plugboard method
	public static boolean plugboard(ArrayList<String> oldwrongs, int left, int middle, int right, int loffset, int moffset, int roffset, String key, String crib, int n, HashMap<Character, Character> map1, HashMap<Character, Character> map2, int l, int numDups) {
		ArrayList<String> wrongs = new ArrayList<String>(oldwrongs);
		//System.out.println(map1 + " " + map2);
		/*if(map2.equals("LNOEZHUDTQ")) {
			System.out.print("nice job");
		}*/
		//check if we are done
		if(map1.size() - numDups == 10) {
			if(enigmaster(left, middle, right, loffset, moffset, roffset, map1, map2, key, 0).contains(crib)) {
				MAP1 = map1;
				MAP2 = map2;
				return true;
			}
			else {
				return false;
			}
		}
		if(n == crib.length()) {
			return false;
		}
		//and grab the current char
		char c = crib.charAt(n);
		//make fresh maps with which to perform a recursive call
		HashMap<Character, Character> map3 = new HashMap<Character, Character>(map1);
		HashMap<Character, Character> map4 = new HashMap<Character, Character>(map2);
		//check if it's in the plugboard
		if(!(map3.containsKey(c) || map4.containsKey(c))) {
			/*if(map3.contains("" + c)) {
				c = map4.charAt(map3.indexOf(c));
			}
			else {
				c = map3.charAt(map4.indexOf(c));
			}
		}
		//and the guess case
		else {*/
			//make some fresh plugboard settings
			for(char i = 'A'; i <= 'Z'; i++) {
				//check for wrongs
				if(wrongs.contains("" + c + i) || wrongs.contains("" + i + c)) {
					continue;
				}
				//and check if it's not in the plugboard
				if(!(map3.containsKey(i) || map4.containsKey(i))) {
					//check duplicates
					if(c == i) {
						if(numDups >= 6) {
							return false;
						}
						numDups++;
					}
					//and check if there is enough room to continue
					if(map3.size() - numDups >= 10) {
						continue;
					}
					map3.put(c, i);
					map4.put(i, c);
					if(plugboard(wrongs, left, middle, right, loffset, moffset, roffset, key, crib, n, map3, map4, l, numDups)) {
						return true;
					} else {
						map3 = new HashMap<Character, Character>(map1);
						map4 = new HashMap<Character, Character>(map2);
						//correct numDups
						numDups = 0;
						for(char k : map3.keySet()) {
							if(map3.get(k) == k) {
								numDups++;
							}
						}
					}
				}
				else if(i == 'Z') {
					wrongs.add("" + c + i);
					return false;
				}
			}
		}
		//now put it through the rotors to make an inference
		//add the os to fix the rotation
		//time it
		//long timeChild = System.currentTimeMillis();
		c = enigmaster(left, middle, right, loffset, moffset, roffset, map3, map4, crib, l).charAt(n);
		//timeMaster += System.currentTimeMillis() - timeChild;
		//timeBoi++;
		//check if more plugboard settings need to be added
		//store the current char in the key
		char k = key.charAt(n + l);
		if(c != k) {
			//check if the new plugboard would be wrong fast
			if(wrongs.contains("" + c + k) || wrongs.contains("" + k + c)) {
				return false;
			}
			//check if they are nonconflicting
			if(!(map3.containsKey(c) || map4.containsKey(c) || map3.containsKey(k) || map4.containsKey(k))) {
				//check duplicates
				if(c == k) {
					if(numDups >= 6) {
						return false;
					}
					numDups++;
				}
				map3.put(c, k);
				map4.put(k, c);
			}
			//if they conflict
			else {
				wrongs.add("" + c + k);
				return false;
			}
		} else {
			//check if these two are nonconflicting
			if(!(map3.containsKey(c) || map4.containsKey(c) || map3.containsKey(k) || map4.containsKey(k))) {
				numDups++;
				map3.put(c, c);
				map4.put(c, c);
			}
		}
		return plugboard(wrongs, left, middle, right, loffset, moffset, roffset, key, crib, n + 1, map3, map4, l, numDups);
	}
	//arrrr
	public static boolean isInteger(String child) {
		try {
			Integer.parseInt(child);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
