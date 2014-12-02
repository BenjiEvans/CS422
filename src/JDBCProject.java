import javax.swing.*;

public class JDBCProject {
	public static void main(String args[])
	{
		DBPanel db = new DBPanel();
		JFrame frame = new JFrame();
		
		frame.setTitle("CS 422 Project");
		frame.setSize(600,600);
		frame.getContentPane().add(db);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
