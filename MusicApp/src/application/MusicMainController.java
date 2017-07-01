package application;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import com.google.gson.Gson;

import application.Engine;
import application.EntryState;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class MusicMainController implements Initializable {

	/***********************************************************************************/

	/***********************************************************************************/
	/*variables*/
	/***********************************************************************************/
	public static HashMap<String,Integer> currentAddition = new HashMap<String, Integer>();
	public static EntryState state = EntryState.NeitherEntered;
	public static String MelodyInserted;
	private static String scaleNote = "";
	private static String projectTitle;
	private static ProjectTransformations transformations;
	/***********************************************************************************/

	/***********************************************************************************/
	/*buttons for the UI declaration*/
	/***********************************************************************************/
	@FXML // fx:id="duration_1"
	private Button duration_1; // Value injected by FXMLLoader

	@FXML // fx:id="duration_2"
	private Button duration_2; // Value injected by FXMLLoader

	@FXML // fx:id="duration_4"
	private Button duration_4; // Value injected by FXMLLoader

	@FXML // fx:id="duration_8"
	private Button duration_8; // Value injected by FXMLLoader

	@FXML // fx:id="duration_16"
	private Button duration_16; // Value injected by FXMLLoader

	@FXML // fx:id="duration_32"
	private Button duration_32; // Value injected by FXMLLoader

	/***********************************************************************************/

	/***********************************************************************************/
	/*comboboxes for the UI declaration*/
	/***********************************************************************************/
	@FXML // fx:id="c_choicebox"
	private ComboBox<Integer> c_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="cSharp_choicebox"
	private ComboBox<Integer> cSharp_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="d_choicebox"
	private ComboBox<Integer> d_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="dSharp_choicebox"
	private ComboBox<Integer> dSharp_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="e_choicebox"
	private ComboBox<Integer> e_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="f_choicebox"
	private ComboBox<Integer> f_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="fSharp_choicebox"
	private ComboBox<Integer> fSharp_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="g_choicebox"
	private ComboBox<Integer> g_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="gSharp_choicebox"
	private ComboBox<Integer> gSharp_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="a_choicebox"
	private ComboBox<Integer> a_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="aSharp_choicebox"
	private ComboBox<Integer> aSharp_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="b_choicebox"
	private ComboBox<Integer> b_choicebox; // Value injected by FXMLLoader

	@FXML // fx:id="ScaleNote"
	private ComboBox<String> ScaleNote; // Value injected by FXMLLoader

	/***********************************************************************************/

	/***********************************************************************************/
	/*notes buttons for the UI declaration*/
	/***********************************************************************************/
	@FXML
	private Button DSharpBtn;

	@FXML
	private Button ABtn;

	@FXML
	private Button CBtn;

	@FXML
	private Button CSharpBtn;

	@FXML
	private Button FBtn;

	@FXML
	private Button ASharpBtn;

	@FXML
	private Button BBtn;

	@FXML
	private Button DBtn;

	@FXML
	private Button GBtn;

	@FXML
	private Button EBtn;

	@FXML
	private Button FSharpBtn;

	@FXML
	private Button GSharpBtn;

	@FXML
	private ToggleGroup octaveToggleGroup;

	@FXML // fx:id="Octave5Btn"
	private ToggleButton Octave5Btn; // Value injected by FXMLLoader

	@FXML // fx:id="Octave4Btn"
	private ToggleButton Octave4Btn; // Value injected by FXMLLoader

	@FXML // fx:id="Octave6Btn"
	private ToggleButton Octave6Btn; // Value injected by FXMLLoader
	/***********************************************************************************/

	/***********************************************************************************/
	/*comboboxes values for initialization*/
	/***********************************************************************************/
	ObservableList<Integer> c_list = FXCollections.observableArrayList(0,12,24,36,48,60,72,84,96,108,120);
	ObservableList<Integer> cSharp_list = FXCollections.observableArrayList(1,13,25,37,49,61,73,85,97,109,121);
	ObservableList<Integer> d_list = FXCollections.observableArrayList(2,13,26,38,50,62,74,86,98,110,122);
	ObservableList<Integer> dSharp_list = FXCollections.observableArrayList(3,15,27,39,51,63,75,87,99,111,123);
	ObservableList<Integer> e_list = FXCollections.observableArrayList(4,16,28,40,52,64,76,88,100,112,124);
	ObservableList<Integer> f_list = FXCollections.observableArrayList(5,17,29,41,53,65,77,89,101,113,125);
	ObservableList<Integer> fSharp_list = FXCollections.observableArrayList(6,18,30,42,54,66,78,90,102,114,126);
	ObservableList<Integer> g_list = FXCollections.observableArrayList(7,19,31,43,55,67,79,91,103,115,127);
	ObservableList<Integer> gSharp_list = FXCollections.observableArrayList(8,20,32,44,56,68,80,92,104,116);
	ObservableList<Integer> a_list = FXCollections.observableArrayList(9,21,33,45,57,69,81,93,105,117);
	ObservableList<Integer> aSharp_list = FXCollections.observableArrayList(10,22,34,46,58,70,82,94,106,118);
	ObservableList<Integer> b_list = FXCollections.observableArrayList(11,23,35,47,59,71,83,95,107,119);
	/***********************************************************************************/

	/***********************************************************************************/
	/*textfield and textarea for the UI declaration*/
	/***********************************************************************************/
	@FXML // fx:id="currentAdditionTextField"
	private TextField currentAdditionTextField; // Value injected by FXMLLoader

	@FXML // fx:id="melody"
	private TextArea melody; // Value injected by FXMLLoader
	/***********************************************************************************/

	/***********************************************************************************/
	/*all other buttons for the UI declaration*/
	/***********************************************************************************/
	@FXML // fx:id="eraseLastBtn"
	private Button eraseLastBtn; // Value injected by FXMLLoader

	@FXML // fx:id="eraseAllBtn"
	private Button eraseAllBtn; // Value injected by FXMLLoader

	@FXML // fx:id="MajorScaleBtn"
	private Button MajorScaleBtn; // Value injected by FXMLLoader

	@FXML // fx:id="MinorScaleBtn"
	private Button MinorScaleBtn; // Value injected by FXMLLoader

	@FXML
	private Button clear_btn;

	@FXML // fx:id="finished_btn"
	private Button finished_btn; // Value injected by FXMLLoader

	@FXML // fx:id="insert_btn"
	private Button insert_btn; // Value injected by FXMLLoader


	/***********************************************************************************/

	/***********************************************************************************/
	/*methods*/
	/***********************************************************************************/
	/*intialization methods*/
	/***********************************************************************************/
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		c_choicebox.setItems(c_list);
		cSharp_choicebox.setItems(cSharp_list);
		d_choicebox.setItems(d_list);
		dSharp_choicebox.setItems(dSharp_list);
		e_choicebox.setItems(e_list);
		f_choicebox.setItems(f_list);
		fSharp_choicebox.setItems(fSharp_list);
		g_choicebox.setItems(g_list);
		gSharp_choicebox.setItems(gSharp_list);
		a_choicebox.setItems(a_list);
		aSharp_choicebox.setItems(aSharp_list);
		b_choicebox.setItems(b_list);
		initializeCurrentAddition();
		currentAdditionTextField.clear();
		melody.clear();

		List<String> list = new ArrayList<String>();
		//		ScaleNote.setItems(FXCollections.observableArrayList(list));
		list.add("C");
		list.add("C#");
		list.add("D");
		list.add("D#");
		list.add("E");
		list.add("F");
		list.add("F#");
		list.add("G");
		list.add("G#");
		list.add("A");
		list.add("A#");
		list.add("B");
		ObservableList<String> options = FXCollections.observableArrayList(list);
		ScaleNote.setItems(options);
	}

	public void initializeCurrentAddition(){
		currentAddition.put("Note", -1);
		currentAddition.put("Duration", -1);
		currentAddition.put("Octave", 4);
	}
	/***********************************************************************************/

	/***********************************************************************************/
	/*duration button click events*/
	/***********************************************************************************/
	@FXML
	void setDuration0(ActionEvent event) {
		System.out.println("setDuration0 fired");
		currentAdditionUpdateDuration(0);
	}

	@FXML
	void setDuration1(ActionEvent event) {
		currentAdditionUpdateDuration(1);
	}

	@FXML
	void setDuration2(ActionEvent event) {
		currentAdditionUpdateDuration(2);
	}

	@FXML
	void setDuration4(ActionEvent event) {
		currentAdditionUpdateDuration(4);
	}

	@FXML
	void setDuration8(ActionEvent event) {
		currentAdditionUpdateDuration(8);
	}

	@FXML
	void setDuration16(ActionEvent event) {
		currentAdditionUpdateDuration(16);
	}

	@FXML
	void setDuration32(ActionEvent event) {
		currentAdditionUpdateDuration(32);
	}
	/***********************************************************************************/

	/***********************************************************************************/
	/*duration button click events helper methods*/
	/***********************************************************************************/
	public void currentAdditionUpdateDuration(int duration){
		currentAddition.put("Duration", duration);
		updateCurrentAdditionTextField();
	}
	/***********************************************************************************/

	/***********************************************************************************/
	/*comboboxes value select events*/
	/***********************************************************************************/
	@FXML
	void c_choiceEntered(ActionEvent event) {
		System.out.println("c_choiceEntered fired");
		currentAdditionUpdateNote(c_choicebox.getValue());
	}

	@FXML
	void cSharp_choiceEntered(ActionEvent event) {
		System.out.println("cSharp_choiceEntered fired");
		currentAdditionUpdateNote(cSharp_choicebox.getValue());
	}

	@FXML
	void d_choiceEntered(ActionEvent event) {
		System.out.println(" d_choiceEntered fired");
		currentAdditionUpdateNote(d_choicebox.getValue());
	}

	@FXML
	void dSharp_choiceEntered(ActionEvent event) {
		System.out.println("dSharp_choiceEntered fired");
		currentAdditionUpdateNote(dSharp_choicebox.getValue());
	}

	@FXML
	void e_choiceEntered(ActionEvent event) {
		System.out.println("e_choiceEntered fired");
		currentAdditionUpdateNote(e_choicebox.getValue());
	}

	@FXML
	void f_choiceEntered(ActionEvent event) {
		System.out.println("f_choiceEntered fired");
		currentAdditionUpdateNote(f_choicebox.getValue());
	}

	@FXML
	void fSharp_choiceEntered(ActionEvent event) {
		System.out.println("fSharp_choiceEntered fired");
		currentAdditionUpdateNote(fSharp_choicebox.getValue());
	}

	@FXML
	void g_choiceEntered(ActionEvent event) {
		System.out.println("g_choiceEntered fired");
		currentAdditionUpdateNote(g_choicebox.getValue());
	}

	@FXML
	void gSharp_choiceEntered(ActionEvent event) {
		System.out.println("gSharp_choiceEntered fired");
		currentAdditionUpdateNote(gSharp_choicebox.getValue());
	}

	@FXML
	void a_choiceEntered(ActionEvent event) {
		System.out.println("a_choiceEntered fired");
		currentAdditionUpdateNote(a_choicebox.getValue());
	}

	@FXML
	void aSharp_choiceEntered(ActionEvent event) {
		System.out.println("aSharp_choiceEntered fired");
		currentAdditionUpdateNote(aSharp_choicebox.getValue());
	}

	@FXML
	void b_choiceEntered(ActionEvent event) {
		System.out.println("b_choiceEntered fired");
		currentAdditionUpdateNote(b_choicebox.getValue());
	}


	@FXML
	void ScaleNoteSelect(ActionEvent event) {
		System.out.println("ScaleNoteSelect fired");
		MusicMainController.scaleNote  = ScaleNote.getValue();
		System.out.println("ScaleNoteSelect selected value: "+MusicMainController.scaleNote);
		transformations.setFinalis(scaleNote);
	}
	/***********************************************************************************/

	/***********************************************************************************/
	/*note button click events*/
	/***********************************************************************************/
	@FXML
	void CBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(1);
	}

	@FXML
	void CSharpBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(2);
	}

	@FXML
	void DBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(3);
	}

	@FXML
	void DSharpBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(4);
	}

	@FXML
	void EBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(5);
	}

	@FXML
	void FBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(6);
	}

	@FXML
	void FSharpBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(7);
	}	

	@FXML
	void GBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(8);
	}

	@FXML
	void GSharpBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(9);
	}

	@FXML
	void ABtnClick(ActionEvent event) {
		currentAdditionUpdateNote(10);
	}

	@FXML
	void ASharpBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(11);
	}

	@FXML
	void BBtnClick(ActionEvent event) {
		currentAdditionUpdateNote(12);
	}

	@FXML
	void Octave4BtnClick(ActionEvent event) {
		currentAddition.put("Octave",4);
	}

	@FXML
	void Octave5BtnClick(ActionEvent event) {
		currentAddition.put("Octave",5);
	}

	@FXML
	void Octave6BtnClick(ActionEvent event) {
		currentAddition.put("Octave",6);
	}
	/***********************************************************************************/

	/***********************************************************************************/
	/*comboboxes value select events helper methods*/
	/***********************************************************************************/
	//	public void currentAdditionUpdateNote(int note){
	//		System.out.println("currentAdditionUpdateNote fired with note "+note);
	//		currentAddition.put("Note",note);
	//		updateCurrentAdditionTextField();
	//	}
	public void currentAdditionUpdateNote(int note){
		System.out.println("currentAdditionUpdateNote fired with note "+note);
		currentAddition.put("Note",note);
		updateCurrentAdditionTextField();
	}
	/***********************************************************************************/

	/***********************************************************************************/
	/*other button click events methods*/
	/***********************************************************************************/
	@FXML
	void enterChoice(ActionEvent event) {

		//String[] notesToAddSplit = notesToAdd.split("/");
		//		System.out.println("notesToAddSplit.length is "+notesToAddSplit.length);
		//		for (String note: notesToAddSplit){
		//			System.out.println(note);
		//		}
		/*not accepting unfull selection of note*/
		System.out.println("currentAddition.get('Note')=="+currentAddition.get("Note")+" currentAddition.get('Duration')=="+currentAddition.get("Duration"));
		if (currentAddition.get("Note")==-1||(currentAddition.get("Duration")==-1)){
			return;
		}
		String notesToAdd = currentAdditionTextField.getText();
		String currentMelody = melody.getText();
		if (currentMelody.length() == 0)
			melody.setText(notesToAdd);
		else
			melody.setText(currentMelody+" "+notesToAdd);
		currentAdditionTextField.clear();
		initializeCurrentAddition();
	}

	@FXML
	void MajorScaleBtnClick(ActionEvent event) {
		/*getting text from textArea*/
		String currentMelody = melody.getText();
		/*splitting text from textArea by the character ','*/
		String[] currentMelodySplit = currentMelody.split(" ");
		/*minor scale is already set*/
		if (currentMelodySplit[0].equals("Major")){
			return;
		}

		/*current melody is empty*/
		if (currentMelodySplit.length == 1){
			if (currentMelodySplit[0].isEmpty() || currentMelodySplit[0].equals("Minor")){
				melody.setText("Major ");
				return;
			}
			/*there is a single note*/
			else{
				melody.setText("Major "+currentMelodySplit[0]);
				return;
			}
		}
		StringBuilder builder = new StringBuilder();
		/*current melody is not empty and we want to add it or replace major scale*/
		if (currentMelodySplit[0].equals("Minor")){
			currentMelodySplit[0] = "Major";
		}
		else builder.append("Major ");
		for (int i = 0;i<currentMelodySplit.length;i++){
			builder.append(currentMelodySplit[i]+" ");
			System.out.println("appended "+currentMelodySplit[i]);
		}
		melody.setText(builder.toString());
		transformations.setScale(currentMelodySplit[0]);
	}

	@FXML
	void MinorScaleBtnClick(ActionEvent event) {
		/*getting text from textArea*/
		String currentMelody = melody.getText();
		/*splitting text from textArea by the character ','*/
		String[] currentMelodySplit = currentMelody.split(" ");
		/*minor scale is already set*/
		if (currentMelodySplit[0].equals("Minor")){
			return;
		}

		/*current melody is empty*/
		if (currentMelodySplit.length == 1){
			if (currentMelodySplit[0].isEmpty() || currentMelodySplit[0].equals("Major")){
				melody.setText("Minor ");
				return;
			}
			/*there is a single note*/
			else{
				melody.setText("Minor "+currentMelodySplit[0]);
				return;
			}
		}
		StringBuilder builder = new StringBuilder();
		/*current melody is not empty and we want to add it or replace major scale*/
		if (currentMelodySplit[0].equals("Major")){
			currentMelodySplit[0] = "Minor";
		}
		else builder.append("Minor ");
		for (int i = 0;i<currentMelodySplit.length;i++){
			builder.append(currentMelodySplit[i]+" ");

		}
		melody.setText(builder.toString());
		transformations.setScale(currentMelodySplit[0]);
	}

	@FXML
	void eraseAll(ActionEvent event) {
		melody.clear();
		initializeCurrentAddition();
	}

	@FXML
	void clearCurrentAdditionTextField(ActionEvent event) {
		currentAdditionTextField.clear();
		currentAddition.put("Note", -1);
		currentAddition.put("Duration", -1);
		//currentAddition.remove()
	}

	@FXML
	void eraseLast(ActionEvent event) {

		StringBuilder builder = new StringBuilder();
		/*getting text from textArea*/
		String currentMelody = melody.getText();
		/*splitting text from textArea by the character ','*/
		String[] currentMelodySplit = currentMelody.split(" ");
		if (currentMelodySplit.length == 1){
			melody.clear();
			return;
		}
		for (int i = 0;i<currentMelodySplit.length;i++)
			System.out.println("currentMelodySplit["+i+"] is "+currentMelodySplit[i]);

		for (int i = 0;i<currentMelodySplit.length-2;i++){
			builder.append(currentMelodySplit[i]+" ");

		}
		/*creating the original text from textArea without last added element*/
		builder.append(currentMelodySplit[currentMelodySplit.length-2]);
		System.out.println("appended "+currentMelodySplit[currentMelodySplit.length-2]);
		melody.setText(builder.toString());
	}

	@FXML
	void finishedBtnClick(ActionEvent event) {
		MusicMainController.MelodyInserted = melody.getText();
		String[] melodySTR = melody.getText().split(" ");
		melody.clear();
		currentAdditionTextField.clear();
		currentAddition.put("Duration",-1);
		currentAddition.put("Note",-1);
		//currentAddition.put("Octave",4);
		System.out.println("melodySTR" + melodySTR);
		String melodyClean = String.join(" ", Arrays.copyOfRange(melodySTR, 1, melodySTR.length));
		System.out.println("melodyClean" + melodyClean);
		Engine.createBank(String.join(" ", melodyClean), melodySTR[0],projectTitle);
		Stage stage = (Stage)finished_btn.getScene().getWindow();
		PrintWriter writer = null;
		try {
			writer = new PrintWriter( projectTitle + ".json", "UTF-8");
			Gson gson = new Gson();
			String jsonTransformations = gson.toJson(transformations);
			writer.println(jsonTransformations);
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		writer.println(title);
//		writer.println(Parser.convertPatternToMidiStr(Arrays.toString(patAclean)));
//		writer.println(Parser.convertPatternToMidiStr((Arrays.toString(patBclean)).replaceAll("T120,", "").replaceFirst(",", "").replace("  ", "")));
//		writer.println(comparison);
		writer.close();
		stage.close();

	}

	/***********************************************************************************/

	/***********************************************************************************/
	/*helper methods*/
	/***********************************************************************************/
	public void updateCurrentAdditionTextField(){	
		int durationToShow = currentAddition.get("Duration");
		int originalNote = currentAddition.get("Note");
		int octaveSelected = currentAddition.get("Octave");
		String noteToShow = "";
		switch(originalNote){
		case 1:
			noteToShow="C";
			break;
		case 2:
			noteToShow="C#";
			break;
		case 3:
			noteToShow="D";
			break;
		case 4:
			noteToShow="D#";
			break;
		case 5:
			noteToShow="E";
			break;
		case 6:
			noteToShow="F";
			break;
		case 7:
			noteToShow="F#";
			break;
		case 8:
			noteToShow="G";
			break;
		case 9:
			noteToShow="G#";
			break;
		case 10:
			noteToShow="A";
			break;
		case 11:
			noteToShow="A#";
			break;
		case 12:
			noteToShow="B";
			break;
		}

		System.out.println("updateCurrentAdditionTextField fired! note "+noteToShow+" durationToshow "+durationToShow+" octave" + octaveSelected);
		if (durationToShow == -1)
			currentAdditionTextField.setText(""+"/"+octaveSelected+"/"+noteToShow);
		else if (originalNote == -1)
			currentAdditionTextField.setText(""+durationToShow+"/"+octaveSelected+"/");
		else currentAdditionTextField.setText(""+durationToShow+"/"+octaveSelected+"/"+noteToShow);
	}

	public void setTitle(String string) {
		projectTitle = string;

	}

	public void setTransformations(ProjectTransformations transformations) {
		this.transformations = transformations;
	}

	public static void addTransformation(TransformationInfo newTransformation){
		transformations.addTransformation(newTransformation);
	}

}
