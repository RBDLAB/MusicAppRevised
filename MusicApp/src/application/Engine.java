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
        int bankSize = 0;
        String title = "";
        
        // Transpositions:
        for ( i = -14; i <= 14; i++){
            if (i != 0){
                manipulated = (transposition(pattern, i)).toString();
                patternBank.add("Transposition_by_" + i + " " + removeTempo(manipulated));
            }
        }
        
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
        notes = Parser.singleChars(tonalPattern);
        String firstNote = notes[0];
        if (notes[1].matches("b|#"))
        	firstNote = notes[0] + notes[1];
        int firstIndex = Arrays.asList(baseNotes).indexOf(firstNote);
        int index;
        tonalPattern = tonalPattern + " ";
        
        for(int j=1; j < splitPattern.length; j++){
            notes = Parser.singleChars(splitPattern[j]);
            firstNote = notes[0];
            if (notes[1].matches("b|#"))
            	firstNote = notes[0] + notes[1];
            index = Arrays.asList(baseNotes).indexOf(firstNote);
            tonalPattern = tonalPattern + baseNotes[(16+firstIndex + (firstIndex - index))%16] + notes[splitPattern[j].length()-2] + notes[splitPattern[j].length()-1] + " ";
        }
        return tonalPattern;
    }
    
 // Diatonic Transposition
    public static String diatonicTransposition(String pattern, String scale, int i){
        String[] splitPattern = pattern.split(" ");
        String[] thisScale = MINOR;
        String[] notes;
        String diatonicStr = "";
        String firstNote="";
        int noteIndex = -1;
        
        switch (scale){
            case "MAJOR": thisScale = MAJOR;
                break;
            case "MINOR": thisScale = MINOR;
                break;
        }
        
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
    }
    
    public static String removeTempo(String pattern){
        String noCommas = pattern.replaceAll(",", "");
        noCommas = noCommas.replace("[", "");
        noCommas = noCommas.replace("]", "");
        noCommas = (noCommas.replace("T120,", "")).trim();
        noCommas = (noCommas.replace("T120", "")).trim();
        noCommas = noCommas.replaceAll("  ", " ");
        String[] splitPattern = noCommas.split(" ");
        for(int i = 0; i < splitPattern.length; i++){
            if ("T".equals(splitPattern[i])){
                splitPattern = Arrays.copyOfRange(splitPattern, 1, splitPattern.length);
            }
        }
        return noCommas;
    }
    
    
}
