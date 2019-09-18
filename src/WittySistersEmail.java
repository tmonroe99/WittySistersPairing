import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

public class WittySistersEmail {
	
	private static String fileName = "csv Fall 2019 WiTty Sisters Application (Responses).csv";
	static ArrayList<String[]> data;
	
	public static void getData() {
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			data = new ArrayList<String[]>(); // ArrayList used for data sets of different length.

			String line = br.readLine(); // Ignore Headers
			while ((line = br.readLine()) != null) {
				//System.out.println(line);
				
				String[] row = line.split(",");
				
				if (row.length!=0) {
					System.out.println(line);
					
					
					String[] restOfRow = new String[row.length - 1];
					for (int i = 1; i < row.length; i++) {
						try {
							restOfRow[i - 1] = (row[i]);
						} catch (NumberFormatException e) { // Missing Value
							restOfRow[i - 1] = null;
						}
					}
					data.add(restOfRow);
				}
				

			}

		} catch (FileNotFoundException e) {
			System.out.println(
					"Error Loading File. Ensure survey_data.csv " + "is located within the same folder as this file.");
		} catch (IOException e) {
			System.out.println("Error While Parsing Data From CSV:");
			e.printStackTrace();
		}
	}
	
	public static String buildEmail(String email, String[] params) {
		
		StringBuilder s = new StringBuilder(); 
		 
		int length = params.length;
		
		String role = (params[length-1].equals("Mentee")? "Mentee" : "Mentor");
				
		
		s.append("Hi " + params[length-2] +",");// Hi Taylor
		s.append("\n\nYou have been paired with "+ params[0]+ " as your " + role+ " for WiTty Sisters. Her information is below.\n");
		s.append("\nMajor: " + params[2]);
		s.append("\nYear: " + params[3]);
		s.append("\nCell: " + params[5]);
		s.append("\nEmail: " + params[4]);
		s.append("\nHobbies: ");
		for(int i = 6; i < length-3; i++) {
			
			if(i==length-4) {
				s.append(params[i].replace('"', ' '));
			} else {
				s.append(params[i].replace('"', ' ') +",");
			}
			
		}
		s.append("\nHopes to gain from the program: " + params[length -3] +"\n\n");

		s.append("The first meeting is this Thursday, 9/19 from 6:00-8:00pm in KNOY B019. Moe's will be served for dinner.\n");
		s.append("If you weren't added to the GroupMe, please respond to this email!\n\n");
		s.append("Best,\nTaylor Younkins\nVice President\nWomen in Technology/WiTty Sisters\nPurdue University\n\n");
		s.append("-----------------------------------------\n\n");
		
		return s.toString();
	}
	
	public static String getRecipient(String[] girl) {
		
		String recipientEmail = "";
		
		for(int i = 0; i < 62; i++) {
			
			for(int j = 0; j < girl.length; j++) {
				
				int length = girl[j].length();
				
			}
		}
		
		return recipientEmail;
		
	}

	public static void main(String[] args) {
		
		getData();
	
		
		try {
			File file = new File("emails.txt");
			FileWriter fw;
			
			if (!file.exists()) {
			     try {
					file.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  }

			
			fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			
			for(String[] girl : data) {
				

				String recipientEmail = getRecipient(girl);
				
				
				//write out the email here
				String email = buildEmail(recipientEmail, girl);
				
				try {
					bw.write(email);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				System.out.print(email);
				System.out.println();
			}
			bw.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		
		

		

		
		
		
	}

}
