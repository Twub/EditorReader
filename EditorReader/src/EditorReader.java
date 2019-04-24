import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

@SuppressWarnings("ucd")
public class EditorReader {
	
	private ArrayList<String> content = new ArrayList<>(); // line by line array
	private JFileChooser jChooser;
	private FileReader fr;
	private BufferedReader br;
	
	/*
	 * Reader methods
	 */
	// Read line by line
	@SuppressWarnings("ucd")
	public ArrayList<String> lineReader(File file) {
		return reader(file);
	}
	
	// GUI - Line by line reader
	@SuppressWarnings("ucd")
	public ArrayList<String> guiLineReader(){
		jChooserSetup();
		if(jChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File getFile = jChooser.getSelectedFile();
			if(isFileTypeValid(getFile.getName())) {
				return reader(getFile);
			}else {
				content.add(">>>>>>>Error: Not A Valid File Type or it dosen´t end with .txt.>>>>>>>>>>>>>>>>>>>>>");
			}
			
		}
		return content;
	}
	
	// non-gui document reader.
	@SuppressWarnings("ucd")
	public String hiddenReader(File file) {
		String c = "";
		ArrayList<String> tmp = new ArrayList<>(reader(file));
		StringBuilder builder = new StringBuilder();
		for(int i=0; i < tmp.size(); i++) {
			builder.append(tmp.get(i) + "\n");
		}
		c = builder.toString();
		if(jChooser != null)jChooser = null;
		if(c == null || c == "") return "Error";
		return c;
	}
	
	// GUI DOCUMENT READER
	@SuppressWarnings("ucd")
	public String guiDocumentReader() {
		jChooserSetup();
		if(jChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File getFile = jChooser.getSelectedFile();
			if(isFileTypeValid(getFile.getName())) {
				return hiddenReader(getFile);
			}else {
				return ">>>>>>>Error: Not A Valid File Type or it dosen´t end with .txt.>>>>>>>>>>>>>>>>>>>>>";
			}
		}
		return "";
	}
	
	/*
	 * Setup METHODS
	 */
	private void jChooserSetup() {
		jChooser = new JFileChooser();
		jChooser.setApproveButtonText("Open");
		jChooser.setDialogTitle("Open");
		jChooser.setCurrentDirectory(new java.io.File("C:"));
		jChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	}
	
	private boolean isFileTypeValid(String name) {
		if(name.toLowerCase().endsWith(".txt")) return true;
		return false;
	}
	
	/*
	 * FILE READER
	 */
	private ArrayList<String> reader(File f) {
		content.clear();
		try {
			fr = new FileReader(f);
			br = new BufferedReader(fr);
			String s = br.readLine();
			while(s != null) {
				content.add(s);
				s = br.readLine(); // ERROR MESSAGE STARTS HERE.
			}
			br.close();
		}catch(IOException e) {
			print("Reader method test: " + e.getStackTrace().toString());
		}finally {
			fr = null;
			br = null;
		}
		if(content.isEmpty() == false)return content;
		return null;
	}
	
	/*
	 * Test Methods
	 */
	private void print(String txt) {
		System.out.println(txt);
	}
}
