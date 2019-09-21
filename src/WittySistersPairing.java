import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class WittySistersPairing {
	
	private static String fileName = "scrubbed data.csv";
	static ArrayList<String[]> data;
	static Hashtable myTable = new Hashtable();
	static ArrayList<Girl> girls;
	static ArrayList<Girl> mentors = new ArrayList<Girl>();
	static ArrayList<Girl> mentees = new ArrayList<Girl>();

	public static void makeGirls() {
		
		/*
		 * 1. sort the data based on major.
		 * 2. collect the unsorted and sort+pair based on department. if necessary 
		 * 3. pair based on mentee and mentor status.
		 * print out all left overs for individual evaluation
		 * 
		 * pair: Taylor Younkins(Cybersecurity)(Mentor)(tyounkin@purdue.edu)
		 * Mia Stoffel(Cybersecurity)(Mentee)(stoffelm@purdue.edu) 
		 * 
		 */
		System.out.println("hi");
		girls = new ArrayList<Girl>();
		
			for(String line[] : data) {
				
				for(int i = 0; i < line.length; i++) {
					line[i] = trimLeadingTrailingSpaces(line[i]);
				}
			
				String secondMajor = (line[3].equals("")) ? "None" : line[3];
				String allergies = (line[6].equals("")) ? "None" : line[6];
				
				String hobbies = "";
				for(int i = 9; i < line.length-5; i++) {
					hobbies+=line[i];
				}
				
				
				//first name, last name, 1st major2, 2nd major3, year4 , email5, cell6, allergies7, mRole8, hobbies9, gain10
				Girl g = new Girl(line[0], line[1], line[2], secondMajor, line[4], line[5], allergies, line[7], line[8], hobbies, line[line.length-5], line[line.length-1]);
				
				if(g.getmRole().equals("Mentor")) {
					mentors.add(g);
				} else {
					mentees.add(g);
				}
				
				//System.out.println(g.toString());
				girls.add(g);
				
			}
		
	}

	public static String trimLeadingTrailingSpaces(String str) {
		
		if(str.isEmpty()) {
			return "";
		}
		
		if(str.substring(0).equals(" "))
			str = str.substring(1, str.length());
		
		if(str.substring(str.length()-1).equals(" "))
			str = str.substring(0, str.length()-1);
			
		
		return str;
	}
	
	public static void pair() {
		
		ArrayList<Girl> unpaired = new ArrayList<Girl>();
		
		for(int i = 0; i < mentees.size(); i++) {
			
			for(int j = 0; j < mentors.size(); j++) {
				
				Girl mentor = mentors.get(j);
				Girl mentee = mentees.get(i);

				
				if(mentor.getMajor().equalsIgnoreCase(mentee.getMajor()) || mentor.getMajor().equalsIgnoreCase(mentee.getSecondMajor())){
					
					 if(myTable.containsKey(mentor) == false && myTable.containsValue(mentee) == false) {
						System.out.println("I put " + mentor.getFirstName() + " with " +mentee.getFirstName());
						myTable.put(mentor, mentee);
					} 
					
				} else if(mentor.getDepartment().equalsIgnoreCase(mentee.getDepartment())){
					
					if(myTable.containsKey(mentor) == false && myTable.containsValue(mentee) == false) {
						System.out.println("I put " + mentor.getFirstName() + " with " +mentee.getFirstName());
						myTable.put(mentor, mentee);
					}
					
				} 
				

				
			}
			
		}
		
		for(Girl g : girls) {
			
			if(!myTable.containsKey(g) && !myTable.containsValue(g)) {
				unpaired.add(g);
				System.out.println(g.getFirstName() + " is unpaired, major: " + g.getMajor() + " role: " + g.getmRole());
			}
			
		}
		
		
		
		System.out.println(myTable.toString());
		
		
		
		
	}
	
	public static void main(String[] args) {
		
		data = WittySistersEmail.getData();
		makeGirls();
		pair();
		
	}

}
