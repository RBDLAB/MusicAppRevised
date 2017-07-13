/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfugue.Pattern;


/**
 *
 * @author Lidya
 */
public class Parser {
<<<<<<< HEAD

	//Pattern patternOrig = new Pattern();
	Parser(Pattern pattern){
		//Pattern patternOrig = pattern;
	}

	public static String parseToSmallestDuration(String origSTR, int smallestDuration){
		String note;
		double duration = 1;                                                        // Duration of each note

		String myList = "";									                        // List converted to int's, notes repeated according to the smallest duration
		String[] notesSTR = origSTR.split(" ");
		String firstNote;

		for (int j=0; j < notesSTR.length; j++){
			String[] charSTR = singleChars(notesSTR[j]);
			firstNote = charSTR[0];

			for (int i=0; i< notesSTR[j].length(); i++){
				note = charSTR[i];

				switch (note){
				// duration
				case "w": duration = 1;
				break;
				case "h": duration = 2;
				break;
				case "q": duration = 4;
				break;
				case "i": duration = 8;
				break;
				case "s": duration = 16;
				break;
				case "t": duration = 32;
				break;
				case "x": duration = 64;
				break;
				case "o": duration = 128;
				break;          

				}
			}
			for (double k = 0; k < (smallestDuration/duration); k++){
				myList = myList + (notesSTR[j]) +" ";

			} 
		}
		return myList;
	}

	public static List<Integer> parse(String origSTR, int smallestDuration){

		String note;
		int noteInt = 0;                                                        // The numerical value of the note
		int octave = 0;                                                         // The octave
		double duration = 1;                                                       // Duration of each note
		double finalNote;
		int charSTRLastChar=0;

		List<Double> myList = new ArrayList<Double>();                        // List converted to int's, notes repeated according to the smallest duration
		List<Integer> myListInt = new ArrayList<Integer>();
		String[] notesSTR = origSTR.split(" ");
		String firstNote;

		for (int j=1; j < notesSTR.length; j++){
			String[] charSTR = singleChars(notesSTR[j]);
			firstNote = charSTR[0];
			if ("V".equals(firstNote) || "I".equals(firstNote) || "L".equals(firstNote) || 
					"T".equals(firstNote) || "X".equals(firstNote) || "K".equals(firstNote)){}
			else {
				for (int i=0; i< notesSTR[j].length(); i++){
					note = charSTR[i];
					charSTRLastChar = Math.min(notesSTR[j].length()-1, i+1);

					switch (note){
					// Notes
					case "C": if ("#".equals(charSTR[charSTRLastChar])) noteInt = 1;
					else noteInt = 0;
					break;
					case "D": if ("b".equals(charSTR[charSTRLastChar])) noteInt = 1;
					else if ("#".equals(charSTR[charSTRLastChar])) noteInt = 3;
					else noteInt = 2;
					break;
					case "E": if ("b".equals(charSTR[charSTRLastChar])) noteInt = 3;
					else noteInt = 4;
					break;
					case "F": if ("#".equals(charSTR[charSTRLastChar])) noteInt = 6;
					else noteInt = 5;
					break;
					case "G": if ("b".equals(charSTR[charSTRLastChar])) noteInt = 6;
					else if ("#".equals(charSTR[charSTRLastChar])) noteInt = 8;
					else noteInt = 7;
					break;
					case "A": if ("b".equals(charSTR[charSTRLastChar])) noteInt = 8;
					else if ("#".equals(charSTR[charSTRLastChar])) noteInt = 10;
					else noteInt = 9;
					break;
					case "B": if ("b".equals(charSTR[charSTRLastChar])) noteInt = 10;
					else noteInt = 11;
					break;
					case "R": ;
					break;


					// duration
					case "w": duration = 1;
					break;
					case "h": duration = 2;
					break;
					case "q": duration = 4;
					break;
					case "i": duration = 8;
					break;
					case "s": duration = 16;
					break;
					case "t": duration = 32;
					break;
					case "x": duration = 64;
					break;
					case "o": duration = 128;
					break;          
					//					case "/": duration = 1/ Double.parseDouble(charSTR[i+1]);
					//					break;

					//Octaves
					case "0": if (i > 0 && !"/".equals(charSTR[i-1]))octave = 0;
					break;
					case "1": if (i > 0 && !"/".equals(charSTR[i-1])){
						if ("0".equals(charSTR[i+1])) octave = 10;
						else octave = 1;
					}
					break;
					case "2": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 2;
					break;
					case "3": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 3;
					break;
					case "4": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 4;
					break;
					case "5": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 5;
					break;
					case "6": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 6;
					break;
					case "7": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 7;
					break;
					case "8": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 8;
					break;
					case "9": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 9;
					break;         
					}
				}
				finalNote = noteInt + (12* octave);
				for (double k = 0; k < (smallestDuration/duration); k++){
					myList.add(finalNote);
				}
			} 
		}

		//***********************************************************************************//
		for (int j =0; j < myList.size(); j ++){
			myListInt.add(myList.get(j).intValue());
		}
		return myListInt;

	}

	// Break a string to single characters
	public static String[] singleChars(String s) {
		return s.split("(?!^)");
	}

	// Find the smallest duration 
	public static int findSmallestDuration(String origSTR){
		String[] chars = singleChars(origSTR);
		int min = 1;
		int k = 0;
		for (int i = 0; i < chars.length; i++){
			String note = chars[i];
			if (i > 0)
				k = i-1;
			if ((chars[k]).matches("0|1|2|3|4|5|6|7|8|9")){
				switch(note){
				case "w": min = Math.max(min, 1);
				break;
				case "h": min = Math.max(min, 2);
				break;
				case "q": min = Math.max(min, 4);
				break;
				case "i": min = Math.max(min, 8);
				break;
				case "s": min = Math.max(min, 16);
				break;
				case "t": min = Math.max(min, 32);
				break;
				case "x": min = Math.max(min, 64);
				break;
				case "o": min = Math.max(min, 128);
				break;
				}
			}
		}
		return min;
	}

	static int[] addElement(int[] a, int e) {
		a  = Arrays.copyOf(a, a.length + 1);
		a[a.length - 1] = e;
		return a;
	}

	static void print(String s){
		System.out.println(s);
	}

	// Parse the input into a jfugue pattern
	public static String convertStringToPattern(String stringPattern){
		String pattern = "";
		String[] splitString = stringPattern.split(" ");
		String[] temp;
		for (int i = 1; i < splitString.length; i++){
			temp = splitString[i].split("/");
			// Note
			if (temp[2].matches("C|D|E|F|G|A|B|C#|D#|E#|F#|G#|A#|B#|Cb|Db|Eb|Fb|Gb|Ab|Bb")){
				if (i == 0)
					pattern = temp[2];
				else
					pattern = pattern + temp[2];
			}
			else
				System.out.println("Illegal Note value for the " + (i+1) + " note.");

			// Octave            
			if ("4".equals(temp[1]) || "5".equals(temp[1]) || "6".equals(temp[1]))
				pattern = pattern + temp[1];
			else 
				System.out.println("Illegal Octave value for the " + (i+1) + " note.");

			// Duration
			switch(temp[0]){
			case "0": pattern = pattern + "2.0 ";
			break;
			case "1": pattern = pattern + "w ";
			break;
			case "2": pattern = pattern + "h ";
			break;
			case "4": pattern = pattern + "q ";
			break;
			case "8": pattern = pattern + "i ";
			break;
			case "16": pattern = pattern + "s ";
			break;
			case "32": pattern = pattern + "t ";
			break;
			}
		}
		return pattern;
	}

	public static String convertPatternToMidiStr(String pattern){
		String[] splitPattern = pattern.split(" ");
		String midiSTR = "";
		String note;
		int noteInt = 0;                                                        // The numerical value of the note
		int octave = 0;                                                         // The octave
		double duration = 1;                                                    // Duration of each note
		double finalNote;
		String[] charSTR = singleChars(splitPattern[0]); 


		for (int j = 0; j < splitPattern.length; j++){
			charSTR = singleChars(splitPattern[j]); 
			for (int i=splitPattern[j].length() - 1; i >= 0; i--){
				note = charSTR[i];


				switch (note){

				// duration
				case "w": midiSTR = midiSTR + "1/";
				break;
				case "h": midiSTR = midiSTR + "2/";
				break;
				case "q": midiSTR = midiSTR + "4/";
				break;
				case "i": midiSTR = midiSTR + "8/";
				break;
				case "s": midiSTR = midiSTR + "16/";
				break;
				case "t": midiSTR = midiSTR + "32/";
				break;
				case "x": midiSTR = midiSTR + "64/";
				break;
				case "o": midiSTR = midiSTR + "128/";
				break;          
				case "/": midiSTR = midiSTR + "0/";
				break;

				//Octaves
				case "0": midiSTR = midiSTR + "0/";
				break;
				case "1": if (i > 0 && !"/".equals(charSTR[i-1])){
					if ("0".equals(charSTR[i+1])) midiSTR = midiSTR + "10/";
					else midiSTR = midiSTR + "1/";
				}
				break;
				case "2": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "2/";
				break;
				case "3": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "3/";
				break;
				case "4": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "4/";
				break;
				case "5": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "5/";
				break;
				case "6": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "6/";
				break;
				case "7": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "7/";
				break;
				case "8": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "8/";
				break;
				case "9": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "9/";
				break;       

				// Notes
				case "C": if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "C#";
				else midiSTR = midiSTR + "C";
				break;
				case "D": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Db";
				else if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "D#";
				else midiSTR = midiSTR + "D";
				break;
				case "E": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Eb";
				else midiSTR = midiSTR + "E";
				break;
				case "F": if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "F#";
				else midiSTR = midiSTR + "F";
				break;
				case "G": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Gb";
				else if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "G#";
				else midiSTR = midiSTR + "G";
				break;
				case "A": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Ab";
				else if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "A#";
				else midiSTR = midiSTR + "A";
				break;
				case "B": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Bb";
				else midiSTR = midiSTR + "B";
				break;
				case "R": ;
				break;
				}
			}
			if (j < splitPattern.length-1)
				midiSTR = midiSTR + ", ";
		}
		return midiSTR;
	} 

	public static String splitPatternToSmallestDurations(int smallestDuration, String pattern){
		String[] splitPattern = pattern.split(" ");
		String midiSTR = "";
		String note;
		int noteInt = 0;                                                        // The numerical value of the note
		int octave = 0;                                                         // The octave
		double duration = 1;                                                    // Duration of each note
		double finalNote;
		String[] charSTR = singleChars(splitPattern[0]);

		for (int j = 0; j < splitPattern.length; j++){
			//charSTR
		}

		return pattern;
	}

	public static String removeTitle(String patternStr){
		String[] splitPattern = patternStr.split(" ");
		String cleanPattern = String.join(" ", Arrays.copyOfRange(splitPattern, 1, splitPattern.length));
		return cleanPattern;
	}

	public static String extractTitle(String patternStr){
		String[] splitPattern = patternStr.split(" ");
		return splitPattern[0];
	}
=======
    
    //Pattern patternOrig = new Pattern();
    Parser(Pattern pattern){
        //Pattern patternOrig = pattern;
    }
    
    public static List<Integer> parse(String origSTR, int smallestDuration){
        
        String note;
        int noteInt = 0;                                                        // The numerical value of the note
        int octave = 0;                                                         // The octave
        double duration = 1;                                                       // Duration of each note
        double finalNote;
        
        List<Double> myList = new ArrayList<Double>();                        // List converted to int's, notes repeated according to the smallest duration
        List<Integer> myListInt = new ArrayList<Integer>();
        String[] notesSTR = origSTR.split(" ");
        String firstNote;
        
        for (int j=1; j < notesSTR.length; j++){
            String[] charSTR = singleChars(notesSTR[j]);
            //System.out.println(notesSTR[j]);
            firstNote = charSTR[0];
            if ("V".equals(firstNote) || "I".equals(firstNote) || "L".equals(firstNote) || 
                    "T".equals(firstNote) || "X".equals(firstNote) || "K".equals(firstNote)){}
            else {
                for (int i=0; i< notesSTR[j].length(); i++){
                    note = charSTR[i];
                    
                    switch (note){
                        // Notes
                        case "C": if ("#".equals(charSTR[i+1])) noteInt = 1;
                                  else noteInt = 0;
                                  break;
                        case "D": if ("b".equals(charSTR[i+1])) noteInt = 1;
                                  else if ("#".equals(charSTR[i+1])) noteInt = 3;
                                  else noteInt = 2;
                                  break;
                        case "E": if ("b".equals(charSTR[i+1])) noteInt = 3;
                                  else noteInt = 4;
                                  break;
                        case "F": if ("#".equals(charSTR[i+1])) noteInt = 6;
                                  else noteInt = 5;
                                  break;
                        case "G": if ("b".equals(charSTR[i+1])) noteInt = 6;
                                  else if ("#".equals(charSTR[i+1])) noteInt = 8;
                                  else noteInt = 7;
                                  break;
                        case "A": if ("b".equals(charSTR[i+1])) noteInt = 8;
                                  else if ("#".equals(charSTR[i+1])) noteInt = 10;
                                  else noteInt = 9;
                                  break;
                        case "B": if ("b".equals(charSTR[i+1])) noteInt = 10;
                                  else noteInt = 11;
                                  break;
                        case "R": ;
                                  break;
                        
                        
                        // duration
                        case "w": duration = 1;
                                  break;
                        case "h": duration = 2;
                                  break;
                        case "q": duration = 4;
                                  break;
                        case "i": duration = 8;
                                  break;
                        case "s": duration = 16;
                                  break;
                        case "t": duration = 32;
                                  break;
                        case "x": duration = 64;
                                  break;
                        case "o": duration = 128;
                                  break;          
                        case "/": duration = 1/ Double.parseDouble(charSTR[i+1]);
                                  break;
                        
                        //Octaves
                        case "0": if (i > 0 && !"/".equals(charSTR[i-1]))octave = 0;
                                  break;
                        case "1": if (i > 0 && !"/".equals(charSTR[i-1])){
                                    if ("0".equals(charSTR[i+1])) octave = 10;
                                    else octave = 1;
                                  }
                                  break;
                        case "2": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 2;
                                  break;
                        case "3": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 3;
                                  break;
                        case "4": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 4;
                                  break;
                        case "5": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 5;
                                  break;
                        case "6": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 6;
                                  break;
                        case "7": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 7;
                                  break;
                        case "8": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 8;
                                  break;
                        case "9": if (i > 0 && !"/".equals(charSTR[i-1])) octave = 9;
                                  break;         
                    }
                }
                finalNote = noteInt + (12* octave);
                for (double k = 0; k < (smallestDuration/duration); k++){
                    myList.add(finalNote);
                }
            } 
        }
        //System.out.println("My numeric String" + myList);
        
        //***********************************************************************************//
        for (int j =0; j < myList.size(); j ++){
            myListInt.add(myList.get(j).intValue());
        }
        return myListInt;
        
    }
    
    // Break a string to single characters
    public static String[] singleChars(String s) {
        return s.split("(?!^)");
    }
    
    // Find the smallest duration 
    public static int findSmallestDuration(String origSTR){
        String[] chars = singleChars(origSTR);
        int min = 1;
        int k = 0;
        for (int i = 0; i < chars.length; i++){
            String note = chars[i];
            if (i > 0)
                k = i-1;
            if ((chars[k]).matches("0|1|2|3|4|5|6|7|8|9")){
                switch(note){
                    case "w": min = Math.max(min, 1);
                              break;
                    case "h": min = Math.max(min, 2);
                              break;
                    case "q": min = Math.max(min, 4);
                              break;
                    case "i": min = Math.max(min, 8);
                              break;
                    case "s": min = Math.max(min, 16);
                              break;
                    case "t": min = Math.max(min, 32);
                              break;
                    case "x": min = Math.max(min, 64);
                              break;
                    case "o": min = Math.max(min, 128);
                              break;
                }
            }
        }
        return min;
    }
    
    static int[] addElement(int[] a, int e) {
        a  = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }
    
    static void print(String s){
        System.out.println(s);
    }
    
    // Parse the input into a jfugue pattern
    public static String convertStringToPattern(String stringPattern){
        String pattern = "";
        String[] splitString = stringPattern.split(" ");
        String[] temp;
        for (int i = 1; i < splitString.length; i++){
            temp = splitString[i].split("/");
            // Note
            System.out.println("splitString[i] = " + splitString[i]);
            if (temp[2].matches("C|D|E|F|G|A|B|C#|D#|E#|F#|G#|A#|B#|Cb|Db|Eb|Fb|Gb|Ab|Bb")){
                if (i == 0)
                    pattern = temp[2];
                else
                    pattern = pattern + temp[2];
            }
            else
                System.out.println("Illegal Note value for the " + (i+1) + " note.");
            
            // Octave            
            if ("4".equals(temp[1]) || "5".equals(temp[1]) || "6".equals(temp[1]))
                pattern = pattern + temp[1];
            else 
                System.out.println("Illegal Octave value for the " + (i+1) + " note.");
            
            // Duration
            switch(temp[0]){
                case "0": pattern = pattern + "2.0 ";
                    break;
                case "1": pattern = pattern + "w ";
                    break;
                case "2": pattern = pattern + "h ";
                    break;
                case "4": pattern = pattern + "q ";
                    break;
                case "8": pattern = pattern + "i ";
                    break;
                case "16": pattern = pattern + "s ";
                    break;
                case "32": pattern = pattern + "t ";
                    break;
            }
        }
        System.out.println("Final pattern " + pattern);
        return pattern;
    }
    
    public static String convertPatternToMidiStr(String pattern){
        String[] splitPattern = pattern.split(" ");
        String midiSTR = "";
        String note;
        int noteInt = 0;                                                        // The numerical value of the note
        int octave = 0;                                                         // The octave
        double duration = 1;                                                    // Duration of each note
        double finalNote;
        
        
        for (int j = 0; j < splitPattern.length; j++){
           String[] charSTR = singleChars(splitPattern[j]); 
           for (int i=splitPattern[j].length() - 1; i >= 0; i--){
                note = charSTR[i];
                System.out.println("midiSTR = " + midiSTR);

                switch (note){
                    
                    // duration
                    case "w": midiSTR = midiSTR + "1/";
                              break;
                    case "h": midiSTR = midiSTR + "2/";
                              break;
                    case "q": midiSTR = midiSTR + "4/";
                              break;
                    case "i": midiSTR = midiSTR + "8/";
                              break;
                    case "s": midiSTR = midiSTR + "16/";
                              break;
                    case "t": midiSTR = midiSTR + "32/";
                              break;
                    case "x": midiSTR = midiSTR + "64/";
                              break;
                    case "o": midiSTR = midiSTR + "128/";
                              break;          
                    case "/": midiSTR = midiSTR + "0/";
                              break;
                              
                    //Octaves
                    case "0": midiSTR = midiSTR + "0/";
                              break;
                    case "1": if (i > 0 && !"/".equals(charSTR[i-1])){
                                if ("0".equals(charSTR[i+1])) midiSTR = midiSTR + "10/";
                                else midiSTR = midiSTR + "1/";
                              }
                              break;
                    case "2": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "2/";
                              break;
                    case "3": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "3/";
                              break;
                    case "4": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "4/";
                              break;
                    case "5": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "5/";
                              break;
                    case "6": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "6/";
                              break;
                    case "7": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "7/";
                              break;
                    case "8": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "8/";
                              break;
                    case "9": if (i > 0 && !"/".equals(charSTR[i-1])) midiSTR = midiSTR + "9/";
                              break;       
                              
                    // Notes
                    case "C": if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "C#";
                              else midiSTR = midiSTR + "C";
                              break;
                    case "D": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Db";
                              else if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "D#";
                              else midiSTR = midiSTR + "D";
                              break;
                    case "E": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Eb";
                              else midiSTR = midiSTR + "E";
                              break;
                    case "F": if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "F#";
                              else midiSTR = midiSTR + "F";
                              break;
                    case "G": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Gb";
                              else if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "G#";
                              else midiSTR = midiSTR + "G";
                              break;
                    case "A": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Ab";
                              else if ("#".equals(charSTR[i+1])) midiSTR = midiSTR + "A#";
                              else midiSTR = midiSTR + "A";
                              break;
                    case "B": if ("b".equals(charSTR[i+1])) midiSTR = midiSTR + "Bb";
                              else midiSTR = midiSTR + "B";
                              break;
                    case "R": ;
                              break;
                }
           }
           if (j < splitPattern.length-1)
                midiSTR = midiSTR + ", ";
        }
        return midiSTR;
    } 
>>>>>>> f9a5b33e7d5d26d447a0e8c4d6c53c07eb40be85
}
