/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jfugue.Note;
import org.jfugue.Pattern;
//import org.jfugue.*;
import org.jfugue.PatternTransformer;
import org.jfugue.extras.DurationPatternTransformer;
import org.jfugue.extras.ReversePatternTransformer;




public class Engine {
    public static final String[] MAJOR = {"C", "D", "E", "F", "G", "A", "B"};
    public static final String[] MINOR = {"C", "D", "Eb", "F", "G", "Ab", "Bb"};
    public static final String[] NOTES = {"C", "C#", "Db", "D", "D#", "Eb", "E", "F", "F#", "Gb", "G", "G#", "Ab", "A", "A#", "Bb", "B"};
    
    public static final String TRANSPOSITION = "Transposition";
    public static final String DIATONIC_TRANSPOSITION = "Diatonic Transposition";
    public static final String AUGMANTATION = "Augmantation";
    public static final String DIMINUTION = "Diminution";
    public static final String INVERSION = "Inversion";
    
    Engine(){}
    
    static List<String> patternBank = new ArrayList<String>();
    
    public static void createBank (String strPattern, String scale, String projectTitle){
        Pattern pattern = new Pattern(Parser.convertStringToPattern(strPattern));
        Pattern bankPattern;
        patternBank.add("Original " + pattern);
        double i = 0;
        int j =0;
        String manipulated;
<<<<<<< HEAD
        int bankSize = 0;
        String title = "";
=======
>>>>>>> f9a5b33e7d5d26d447a0e8c4d6c53c07eb40be85
        
        // Transpositions:
        for ( i = -14; i <= 14; i++){
            if (i != 0){
                manipulated = (transposition(pattern, i)).toString();
                patternBank.add("Transposition_by_" + i + " " + removeTempo(manipulated));
            }
        }
        
<<<<<<< HEAD
     // Augmantation
        bankSize = patternBank.size();
        for (j = 0; j < bankSize; j++){
        	bankPattern = new Pattern(Parser.removeTitle(patternBank.get(j)));
        	title = Parser.extractTitle(patternBank.get(j));
	        for (i = 2; i < 5; i=i+2){
	            DurationPatternTransformer durationTransformer = new DurationPatternTransformer(i);
	            manipulated = (durationTransformer.transform(bankPattern)).toString();
	            patternBank.add(title + "&Augmantation_by_" + i + " " + removeTempo(manipulated));
	        }
        }
        
     // Diminution
        bankSize = patternBank.size();
        for (j = 0; j < bankSize; j++){
        	bankPattern = new Pattern(Parser.removeTitle(patternBank.get(j)));
        	title = Parser.extractTitle(patternBank.get(j));        
	        for (i = 2; i < 5; i=i+2){
	            DurationPatternTransformer durationTransformer = new DurationPatternTransformer(1/i);
	            manipulated = (durationTransformer.transform(bankPattern)).toString();
	            patternBank.add(title + "&Diminution_by_" + 1/i + " " + removeTempo(manipulated));
	        }
        }
        
     // Inversion
        bankSize = patternBank.size();
        for (j = 0; j < bankSize; j++){
        	bankPattern = new Pattern(Parser.removeTitle(patternBank.get(j)));
        	title = Parser.extractTitle(patternBank.get(j));        
	        ReversePatternTransformer rpt = new ReversePatternTransformer();
	        manipulated = (rpt.transform(bankPattern)).toString();
	        patternBank.add(title + "&Inversion " + removeTempo(manipulated));
        }
        
        // Diatonic Transposition
        bankSize = patternBank.size();
        for (j = 0; j < bankSize; j++){
        	bankPattern = new Pattern(Parser.removeTitle(patternBank.get(j)));
        	title = Parser.extractTitle(patternBank.get(j));        
        	patternBank.add(title + "&Diatonic " + diatonicTransposition(bankPattern.toString(), scale, 3));
        }
        
        // Tonal Inversion
        bankSize = patternBank.size();
        for (j = 0; j < bankSize; j++){
        	bankPattern = new Pattern(Parser.removeTitle(patternBank.get(j)));
        	title = Parser.extractTitle(patternBank.get(j));
        	patternBank.add(title + "&TonalInversion " + tonalInversion(bankPattern.toString()));
        }
=======
        // Augmantation
        //for (j = 0; j < patternBank.size(); j++){
        	//bankPattern = new Pattern(Parser.convertStringToPattern(patternBank.get(j)));
	        for (i = 2; i < 5; i=i+2){
	            DurationPatternTransformer durationTransformer = new DurationPatternTransformer(i);
	            manipulated = (durationTransformer.transform(pattern)).toString();
	            patternBank.add("Augmantation_by_" + i + " " + removeTempo(manipulated));
	        }
        //}
        
        // Diminution
        for (i = 2; i < 5; i=i+2){
            DurationPatternTransformer durationTransformer = new DurationPatternTransformer(1/i);
            manipulated = (durationTransformer.transform(pattern)).toString();
            System.out.println("manipulated.tostring = " + manipulated);
            System.out.println("removeTempo(manipulated) = " + removeTempo(manipulated));
            patternBank.add("Diminution_by_" + 1/i + " " + removeTempo(manipulated));
        }
        
        // Inversion
        ReversePatternTransformer rpt = new ReversePatternTransformer();
        manipulated = (rpt.transform(pattern)).toString();
        patternBank.add("Inversion " + removeTempo(manipulated));
        
        // Diatonic
        patternBank.add("Diatonic " + diatonicTransposition(strPattern, scale, 3));
        
        // Tonal Inversion
        //patternBank.add("TonalInversion" + tonalInversion(strPattern));
>>>>>>> f9a5b33e7d5d26d447a0e8c4d6c53c07eb40be85
        
        System.out.println("End of bank");
        
        for (int k = 0; k < patternBank.size(); k++){
            Compare.doCompare(patternBank.get(0), patternBank.get(k),projectTitle);
        }
    }
    
    
    
    // Creates a transposition of the pattern
    public static Pattern transposition(Pattern pattern, double i){
        PatternTransformer lowerOctaveTransformer = new PatternTransformer() {    
            public void noteEvent(Note note){
                byte currentValue = note.getValue();
                note.setValue((byte)(currentValue - i));
                getReturnPattern().addElement(note);
                }
            };
            
        Pattern octavePattern = lowerOctaveTransformer.transform(pattern);
        return octavePattern;
    }
    
    // Tonal Inversion
    public static String tonalInversion(String pattern){
        String[] splitPattern = pattern.split(" ");
        String[] baseNotes = NOTES;
        String tonalPattern = splitPattern[0];
        String[] notes;
<<<<<<< HEAD
        notes = Parser.singleChars(tonalPattern);
        String firstNote = notes[0];
        if (notes[1].matches("b|#"))
        	firstNote = notes[0] + notes[1];
=======
        notes = tonalPattern.split("/");
        String firstNote = notes[2];
>>>>>>> f9a5b33e7d5d26d447a0e8c4d6c53c07eb40be85
        int firstIndex = Arrays.asList(baseNotes).indexOf(firstNote);
        int index;
        tonalPattern = tonalPattern + " ";
        
        for(int j=1; j < splitPattern.length; j++){
<<<<<<< HEAD
            notes = Parser.singleChars(splitPattern[j]);
            firstNote = notes[0];
            if (notes[1].matches("b|#"))
            	firstNote = notes[0] + notes[1];
            index = Arrays.asList(baseNotes).indexOf(firstNote);
            tonalPattern = tonalPattern + baseNotes[(16+firstIndex + (firstIndex - index))%16] + notes[splitPattern[j].length()-2] + notes[splitPattern[j].length()-1] + " ";
=======
            notes = splitPattern[j].split("/");
            tonalPattern = tonalPattern + notes[0] + "/" + notes[1] + "/";
            index = Arrays.asList(baseNotes).indexOf(notes[2]);
            tonalPattern = tonalPattern + baseNotes[(firstIndex - (firstIndex - index) + 1)%7] + " ";
>>>>>>> f9a5b33e7d5d26d447a0e8c4d6c53c07eb40be85
        }
        return tonalPattern;
    }
    
<<<<<<< HEAD
 // Diatonic Transposition
=======
    // Diatonic Transposition
>>>>>>> f9a5b33e7d5d26d447a0e8c4d6c53c07eb40be85
    public static String diatonicTransposition(String pattern, String scale, int i){
        String[] splitPattern = pattern.split(" ");
        String[] thisScale = MINOR;
        String[] notes;
        String diatonicStr = "";
<<<<<<< HEAD
        String firstNote="";
=======
>>>>>>> f9a5b33e7d5d26d447a0e8c4d6c53c07eb40be85
        int noteIndex = -1;
        
        switch (scale){
            case "MAJOR": thisScale = MAJOR;
                break;
            case "MINOR": thisScale = MINOR;
                break;
        }
        
<<<<<<< HEAD
        for(int j=0; j < splitPattern.length; j++){
            notes = Parser.singleChars(splitPattern[j]);
            firstNote = notes[0];
            if (notes[1].matches("b|#"))
            	firstNote = notes[0] + notes[1];
            noteIndex = Arrays.asList(thisScale).indexOf(firstNote);
            if (j == 0)
                diatonicStr = firstNote + notes[splitPattern[j].length()-2]+ notes[splitPattern[j].length()-1] + " ";
            else 
                diatonicStr = diatonicStr + thisScale[(noteIndex+i-1)%7] + notes[splitPattern[j].length()-2] + notes[splitPattern[j].length()-1] + " ";
        }
        return diatonicStr;
=======
        for(int j=1; j < splitPattern.length; j++){
            notes = splitPattern[j].split("/");
            noteIndex = Arrays.asList(thisScale).indexOf(notes[2]);
            if (j == 0)
                diatonicStr = notes[0]+ "/" + notes[1] + "/";
            else 
                diatonicStr = diatonicStr + notes[0] + "/" + notes[1] + "/";

            diatonicStr = diatonicStr + thisScale[(noteIndex+i-1)%7] + " ";
        }
        return Parser.convertStringToPattern(diatonicStr);
>>>>>>> f9a5b33e7d5d26d447a0e8c4d6c53c07eb40be85
    }
    
    public static String removeTempo(String pattern){
        String noCommas = pattern.replaceAll(",", "");
        noCommas = noCommas.replace("[", "");
        noCommas = noCommas.replace("]", "");
<<<<<<< HEAD
        noCommas = (noCommas.replace("T120,", "")).trim();
        noCommas = (noCommas.replace("T120", "")).trim();
        noCommas = noCommas.replaceAll("  ", " ");
        String[] splitPattern = noCommas.split(" ");
        for(int i = 0; i < splitPattern.length; i++){
=======
        //System.out.println("noCommas , :" + noCommas);
        noCommas = (noCommas.replace("T120,", "")).trim();
        //System.out.println("noCommas T120 :" + noCommas);
        noCommas = pattern.replaceAll("  ", " ");
        String[] splitPattern = noCommas.split(" ");
        for(int i = 0; i < splitPattern.length; i++){
            //System.out.println("splitPattern[" + i + "] = " + splitPattern[i]);
>>>>>>> f9a5b33e7d5d26d447a0e8c4d6c53c07eb40be85
            if ("T".equals(splitPattern[i])){
                splitPattern = Arrays.copyOfRange(splitPattern, 1, splitPattern.length);
            }
        }
        return noCommas;
    }
    
    
}
