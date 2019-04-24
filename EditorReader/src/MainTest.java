import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class MainTest {
	
	private static EditorReader r = new EditorReader();

	public static void main(String[] args) {
		JFrame window = new JFrame("ReaderTestMain");
		window.setSize(400,400);
		window.setDefaultCloseOperation(3);
		window.setLayout(null);
		
		JTextArea t = new JTextArea();
		window.getContentPane().add(t);
		t.setBounds(0, 100, 400, 300);
		t.setEditable(false);
		
		JButton btn1 = new JButton("Console");
		JButton btn2 = new JButton("GUI");
		
		btn1.setBounds(0, 0, 100, 50);
		btn2.setBounds(100, 0, 100, 50);
		
		window.getContentPane().add(btn1);
		window.getContentPane().add(btn2);
		
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String c = "";
				for(String s : r.lineReader(new File("Test.txt"))) {
					c += s += "\n";
					t.setText(c);
				}
			}
			
		});
		
		btn2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String c = "";
				for (String s : r.guiLineReader()) {
					c += s += "\n";
					t.setText(c);
				}
				
			}
			
		});
		
		JButton btn3 = new JButton("HIDDEN DOC");
		btn3.setBounds(200, 0, 100, 50);
		window.getContentPane().add(btn3);
		
		btn3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String c = "";
				t.setText(r.hiddenReader(new File("Test.txt")));
			}
			
		});
		
		JButton btn4 = new JButton("GUI DOC");
		btn4.setBounds(300,0,100,50);
		window.getContentPane().add(btn4);
		
		btn4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t.setText(r.guiDocumentReader());
				
			}
			
		});
		
		window.setVisible(true);
	}
}
