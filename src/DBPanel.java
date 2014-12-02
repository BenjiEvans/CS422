import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DBPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	//addProject
	private JButton addProjectButton;
	private JPanel addFaculty, addProject, deleteFaculty, deleteProject, displayInfo, boxes, labels;
	private JLabel l_projnum, l_startdate, l_enddate, l_budget, l_sponsor, l_principal;
	private JTextField t_projnum, t_startdate, t_enddate, t_budget, t_sponsor, t_principal;
	private JTextArea addProjectStatusBox;
	
	//addFaculty
	private JButton addFacultyButton;
	private JTextArea addFacultyBox;
	private JPanel addF_boxes, addF_labels;
	private JLabel l_ssn, l_name, l_specialty, l_rank, l_age, l_gender, l_worksIn;
	private JTextField t_ssn, t_name, t_specialty, t_rank, t_age, t_gender, t_worksIn;
	
	//deleteProject
	private JButton deleteProjectButton;
	private JLabel delete_l_id;
	private JTextField delete_t_id;
	private JTextArea deleteProjectBox;
	
	//deleteFaculty
	private JButton deleteFacultyButton;
	private JLabel delete_l_fid;
	private JTextField delete_t_fid;
	private JTextArea deleteFacultyBox;
	
	//displayInfo
	private JButton displayButton, clearButton;
	private JTextArea displayInfoBox;
	private JComboBox displayOptions;
	
	JTabbedPane jtp = new JTabbedPane();
	
	private String url = "jdbc:postgresql://cs1.calstatela.edu/cs422s21";
	private String username = "cs422s21";
	private String password = "beap422";
	
	public DBPanel()
	{
		Dimension d = new Dimension(600,400);
		setLayout(new BorderLayout(5,0));
		
		deleteProjectBox = new JTextArea(10, 40);
		addFacultyBox = new JTextArea(10, 40);
		addProjectStatusBox = new JTextArea(10, 40);
		deleteFacultyBox = new JTextArea(10, 40);
		deleteFacultyBox.setLineWrap(true);
		displayInfoBox = new JTextArea(10,60);
		displayInfoBox.setFont(new Font("monospaced", Font.PLAIN, 12));
		JScrollPane deleteFacultyScrollPane, deleteProjectScrollPane, addFacultyScrollPane, addProjectScrollPane, displayInfoPane;
		deleteFacultyScrollPane = new JScrollPane(deleteFacultyBox);
		deleteProjectScrollPane = new JScrollPane(deleteProjectBox);
		addFacultyScrollPane = new JScrollPane(addFacultyBox);
		addProjectScrollPane = new JScrollPane(addProjectStatusBox);
		displayInfoPane = new JScrollPane(displayInfoBox);
		deleteFacultyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		deleteProjectScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		addFacultyScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		addProjectScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		displayInfoPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		addProjectButton = new JButton("Add Project");
		deleteProjectButton = new JButton("Delete Project");
		addFacultyButton = new JButton("Add Faculty");
		deleteFacultyButton = new JButton("Delete Faculty");
		displayButton = new JButton("Display");
		clearButton = new JButton("Clear");
		
		OptionListener listener = new OptionListener();
		addProjectButton.addActionListener(listener);
		deleteProjectButton.addActionListener(listener);
		addFacultyButton.addActionListener(listener);
		deleteFacultyButton.addActionListener(listener);
		displayButton.addActionListener(listener);
		clearButton.addActionListener(listener);
		
		//addFaculty
		addF_boxes = new JPanel(new GridLayout(7,1,5,10));
		addF_labels = new JPanel(new GridLayout(7,1,5,15));
		l_ssn = new JLabel("SSN: ", JLabel.RIGHT);
		l_name = new JLabel("Name: ", JLabel.RIGHT);
		l_specialty = new JLabel("Specialty: ", JLabel.RIGHT);
		l_rank = new JLabel("Rank: ", JLabel.RIGHT);
		l_age = new JLabel("Age: ", JLabel.RIGHT);
		l_gender = new JLabel("Gender[M/F]: ", JLabel.RIGHT);
		l_worksIn = new JLabel("Department: ", JLabel.RIGHT);
		
		t_ssn = new JTextField(20);
		t_name = new JTextField(20);
		t_specialty = new JTextField(20);
		t_rank = new JTextField(20);
		t_age = new JTextField(20);
		t_gender = new JTextField(20);
		t_worksIn = new JTextField(20);
		addF_boxes.add(t_ssn); addF_boxes.add(t_name); addF_boxes.add(t_specialty);
		addF_boxes.add(t_rank); addF_boxes.add(t_age); addF_boxes.add(t_gender);
		addF_boxes.add(t_worksIn);
		
		addF_labels.add(l_ssn); addF_labels.add(l_name); addF_labels.add(l_specialty);
		addF_labels.add(l_rank); addF_labels.add(l_age); addF_labels.add(l_gender);
		addF_labels.add(l_worksIn);
		
		//addProject		
		boxes = new JPanel(new GridLayout(6,1,5,10));
		labels = new JPanel(new GridLayout(6,1,5,15));
		l_projnum = new JLabel("Project Number: ", JLabel.RIGHT);
		l_startdate = new JLabel("Start Date(YYYY-MM-DD): ", JLabel.RIGHT);
		l_enddate = new JLabel("End Date(YYYY-MM-DD): ", JLabel.RIGHT);
		l_budget = new JLabel("Budget: ", JLabel.RIGHT);
		l_sponsor = new JLabel("Sponsor: ", JLabel.RIGHT);
		l_principal = new JLabel("Principal Investigator: ", JLabel.RIGHT);

		t_projnum = new JTextField(20);
		t_startdate = new JTextField(20);
		t_enddate = new JTextField(20);
		t_budget = new JTextField(20);
		t_sponsor = new JTextField(20);
		t_principal = new JTextField(20);
		boxes.add(t_projnum); boxes.add(t_startdate);
		boxes.add(t_enddate); boxes.add(t_budget); boxes.add(t_sponsor);
		boxes.add(t_principal);
		
		labels.add(l_projnum); labels.add(l_startdate);
		labels.add(l_enddate); labels.add(l_budget); labels.add(l_sponsor);
		labels.add(l_principal);
		
		//deleteProject
		delete_l_id = new JLabel("Project ID: ", JLabel.RIGHT);
		delete_t_id = new JTextField(20);
		
		//deleteFaculty
		delete_l_fid = new JLabel("Faculty ID: ", JLabel.RIGHT);
		delete_t_fid = new JTextField(20);
		String[] options = {"Professor", "Grad_Student", "Project", "Department", "Works_On", "Grad_Works"};

		//displayInfo
		displayOptions = new JComboBox(options);
		displayOptions.addActionListener(listener);
		
		addProject = new JPanel(new FlowLayout());
		addProject.add(labels);
		addProject.add(boxes);
		addProject.add(addProjectButton);
		addProject.add(addProjectScrollPane);
		
		addFaculty = new JPanel(new FlowLayout());
		addFaculty.add(addF_labels);
		addFaculty.add(addF_boxes);
		addFaculty.add(addFacultyButton);
		addFaculty.add(addFacultyScrollPane);
		
		deleteProject = new JPanel(new FlowLayout());
		deleteProject.add(delete_l_id);
		deleteProject.add(delete_t_id);
		deleteProject.add(deleteProjectButton);
		deleteProject.add(deleteProjectScrollPane);
		
		deleteFaculty = new JPanel(new FlowLayout());
		deleteFaculty.add(delete_l_fid); deleteFaculty.add(delete_t_fid);
		deleteFaculty.add(deleteFacultyButton);
		deleteFaculty.add(deleteFacultyScrollPane);
		
		displayInfo = new JPanel(new FlowLayout());
		displayInfo.add(displayOptions);
		displayInfo.add(displayButton);
		displayInfo.add(clearButton);
		displayInfo.add(displayInfoPane);
		
		jtp.setPreferredSize(d);
		jtp.add(addProject, "Add Project");
		jtp.add(deleteProject, "Delete Project");
		jtp.add(addFaculty, "Add Faculty");
		jtp.add(deleteFaculty, "Delete Faculty");
		jtp.add(displayInfo, "Display Information");
		add(jtp);
	}
	
	private class OptionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			Object source = event.getSource();
			if(source.equals(addProjectButton))
			{
				String add = "";
				try
				{
					Connection connection = null;
					addProjectStatusBox.append("Connecting to database...\n");
					connection = DriverManager.getConnection(url, username, password);
					Statement stmt = connection.createStatement();
					add = "INSERT INTO project values(" +
							t_projnum.getText() + ",'" + t_startdate.getText() + "','" +
							t_enddate.getText() + "'," + t_budget.getText() + ",'" + t_sponsor.getText() +
							"',"+ t_principal.getText() + ")";
					stmt.executeUpdate(add);
					
					addProjectStatusBox.append("\nTable updated.");
					connection.close();
					addProjectStatusBox.append("\nConnection closed.\n");
				}
				catch(SQLException s)
				{
					addProjectStatusBox.append("\nCannot insert: \n");
					addProjectStatusBox.append(add + "\n");
					addProjectStatusBox.append(s.getMessage() + "\n");
				}
				
			}
			else if(source.equals(deleteProjectButton))
			{
				try
				{
					Connection connection = null;
					deleteProjectBox.append("Connecting to database...\n");
					connection = DriverManager.getConnection(url, username, password);
					Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					String query = "SELECT * FROM project WHERE project_num =" + delete_t_id.getText();
					ResultSet rs = stmt.executeQuery(query);
					if(rs.first() == false)
					{
						deleteProjectBox.append("\nNo result found that matched Project number " + 
									delete_t_id.getText());
					}
					else
					{
						//delete that record from the database
						Statement delQuery = connection.createStatement();
						String del = "DELETE FROM project WHERE project_num = " + delete_t_id.getText();
						delQuery.executeUpdate(del);
						deleteProjectBox.append("\nProject " + delete_t_id.getText() + " deleted.");
					}
					connection.close();
					deleteProjectBox.append("\nConnection closed.\n");
				}
				catch(SQLException s)
				{
					deleteProjectBox.append("\nDelete Project Error.\n");
					deleteProjectBox.append(s.getMessage() + "\n");
				}
			}
			else if(source.equals(addFacultyButton))
			{
				String add = "";
				try
				{
					Connection connection = null;
					addFacultyBox.append("Connecting to database...\n");
					connection = DriverManager.getConnection(url, username, password);
					Statement stmt = connection.createStatement();
					add = "INSERT INTO professor values(" +
							t_ssn.getText() + ",'" + t_name.getText() + "','" +
							t_specialty.getText() + "','" + t_rank.getText() + "'," + t_age.getText() +
							",'" + t_gender.getText() + "', "+ t_worksIn.getText() + ")";
					stmt.executeUpdate(add);
					
					addFacultyBox.append("\nTable updated.");
					connection.close();
					addFacultyBox.append("\nConnection closed.\n");
				}
				catch(SQLException s)
				{
					addFacultyBox.append("\nCannot insert: \n");
					addFacultyBox.append(add + "\n");
					addFacultyBox.append(s.getMessage() + "\n");
				}
				
			} 
			else if(source.equals(deleteFacultyButton))
			{
				try
				{
					Connection connection = null;
					deleteFacultyBox.append("Connecting to database...\n");
					connection = DriverManager.getConnection(url, username, password);
					Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					String query = "SELECT * FROM professor WHERE ssn =" + delete_t_fid.getText();
					ResultSet rs = stmt.executeQuery(query);
					if(rs.first() == false)
					{
						deleteFacultyBox.append("\nNo result found that matched Professor SSN " + 
								delete_t_fid.getText());
					}
					else
					{
						//delete that record from the database
						Statement delQuery = connection.createStatement();
						String del = "DELETE FROM professor WHERE ssn = " + delete_t_fid.getText();
						delQuery.executeUpdate(del);
						deleteFacultyBox.append("\nProfessor " + delete_t_fid.getText() + " deleted.");
					}
					connection.close();
					deleteFacultyBox.append("\nConnection closed.\n");
				}
				catch(SQLException s)
				{
					deleteFacultyBox.append("\nDelete Faculty Error.\n");
					deleteFacultyBox.append(s.getMessage() + "\n");
				}

			}
			else if(source.equals(displayButton))
			{
				try
				{
					Connection connection = null;
					displayInfoBox.append("Connecting to database...\n");
					connection = DriverManager.getConnection(url, username, password);
					Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
					String query = "select * from " + String.valueOf(displayOptions.getSelectedItem());
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData rsmd = rs.getMetaData();
					while(rs.next())
					{
						for(int i = 1; i <= rsmd.getColumnCount(); i++)
						{
							if(rs.getObject(i) == null)
							{
								displayInfoBox.append("null ");
							}
							else
								displayInfoBox.append(rs.getObject(i).toString() + " ");
						}
						displayInfoBox.append("\n");
						
					}


					connection.close();
					displayInfoBox.append("\nConnection closed.\n");
				}
				catch(SQLException s)
				{
					displayInfoBox.append("\nDisplay Info Error.\n");
					displayInfoBox.append(s.getMessage() + "\n");
				}
			}
			else if(source.equals(clearButton))
			{
				displayInfoBox.setText("");
			}
		}
	}
}
