import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class WittySistersPairing {

	private static String fileName = "scrubbed data.csv";
	static ArrayList<String[]> data;
	static HashMap myTable = new HashMap();
	static ArrayList<Girl> girls;
	static ArrayList<Girl> mentors = new ArrayList<Girl>();
	static ArrayList<Girl> mentees = new ArrayList<Girl>();

	public static void makeGirls() {

		/*
		 * 1. sort the data based on major. 2. collect the unsorted and sort+pair based
		 * on department. if necessary 3. pair based on mentee and mentor status. print
		 * out all left overs for individual evaluation
		 * 
		 * pair: Taylor Younkins(Cybersecurity)(Mentor)(tyounkin@purdue.edu) Mia
		 * Stoffel(Cybersecurity)(Mentee)(stoffelm@purdue.edu)
		 * 
		 */
		System.out.println("hi");
		girls = new ArrayList<Girl>();

		for (String line[] : data) {

			for (int i = 0; i < line.length; i++) {
				line[i] = trimLeadingTrailingSpaces(line[i]);
			}

			String secondMajor = (line[3].equals("")) ? "None" : line[3];
			String allergies = (line[6].equals("")) ? "None" : line[6];

			String hobbies = "";
			for (int i = 9; i < line.length - 5; i++) {
				hobbies += line[i];
			}

			// first name, last name, 1st major2, 2nd major3, year4 , email5, cell6,
			// allergies7, mRole8, hobbies9, gain10
			Girl g = new Girl(line[0], line[1], line[2], secondMajor, line[4], line[5], allergies, line[7], line[8],
					hobbies, line[line.length - 5], line[line.length - 1]);

			if (g.getmRole().equals("Mentor")) {
				mentors.add(g);
			} else {
				mentees.add(g);
			}

			girls.add(g);

		}

	}

	public static String trimLeadingTrailingSpaces(String str) {

		if (str.isEmpty()) {
			return "";
		}

		if (str.substring(0).equals(" "))
			str = str.substring(1, str.length());

		if (str.substring(str.length() - 1).equals(" "))
			str = str.substring(0, str.length() - 1);

		return str;
	}

	public static void pair() {

		ArrayList<Girl> unpaired = new ArrayList<Girl>();
		ArrayList<Girl> totalPaired = new ArrayList<Girl>();

		for (int i = 0; i < mentees.size(); i++) {

			for (int j = 0; j < mentors.size(); j++) {

				Girl mentor = mentors.get(j);
				Girl mentee = mentees.get(i);
				
				ArrayList<Girl> menteesList = new ArrayList<Girl>();
				menteesList.add(mentee);

				//if majors match
				if (mentor.getMajor().equalsIgnoreCase(mentee.getMajor()) || mentor.getMajor().equalsIgnoreCase(mentee.getSecondMajor())) {
					//if they both dont exist
					if (myTable.containsKey(mentor) == false && myTable.containsValue(menteesList) == false) {
						
//						System.out.println("Mytable doesnt contain this mentor or this mentee. I put " + mentor.getFirstName() + " with " + mentee.getFirstName());

						totalPaired.add(mentor);
						totalPaired.add(mentee);
						myTable.put(mentor, menteesList);
					}
				//if departments match
				} else if (mentor.getDepartment().equalsIgnoreCase(mentee.getDepartment())) {
					
					//if they both dont exist
					if (myTable.containsKey(mentor) == false && myTable.containsValue(menteesList) == false) {
//						System.out.println("\nDepartments: I put " + mentor.getFirstName() + " with " + mentee.getFirstName() + mentee.getLastName());
						
						totalPaired.add(mentor);
						totalPaired.add(mentee);
						myTable.put(mentor, menteesList);
					}

				}
			}

		} //end first round pairing
		
		//2nd round pairing
		
		
		for (Girl g : girls) {

			if (!totalPaired.contains(g)) {
//				System.out.println(g.getFirstName() + " is unpaired, major: " + g.getMajor() + " role: " + g.getmRole());
			}

		}
		
		System.out.println("2ND ROUND -- PAIR MATCHING MENTEES TO MENTORS---------------------------------------------------------");
		
		for (int i = 0; i < mentees.size(); i++) {

			for (int j = 0; j < mentors.size(); j++) {

				Girl mentor = mentors.get(j);
				Girl mentee = mentees.get(i);
				
				ArrayList<Girl> menteesList = new ArrayList<Girl>();
				menteesList.add(mentee);
				
				//if majors match
				if (mentor.getMajor().equalsIgnoreCase(mentee.getMajor()) || mentor.getMajor().equalsIgnoreCase(mentee.getSecondMajor())) {
					//if they both don't exist
					
					ArrayList<Girl> thisMentorsList = (ArrayList<Girl>)myTable.get(mentor);
					
					
					if (myTable.containsKey(mentor) == true && !totalPaired.contains(mentee) && thisMentorsList.size() <2 ) {
						
//						System.out.println("Mytable contains this mentor but not this mentee. I put " + mentor.getFirstName() + " with " + mentee.getFirstName());
						thisMentorsList.add(mentee);
//						System.out.println("this mentor's list is: " + thisMentorsList.size());

						totalPaired.add(mentee);
						myTable.put(mentor, thisMentorsList);
					}
				//if departments match
				} else if (mentor.getDepartment().equalsIgnoreCase(mentee.getDepartment())) {
					
					ArrayList<Girl> thisMentorsList = (ArrayList<Girl>)myTable.get(mentor);
					
					
					//if they both dont exist
					if (myTable.containsKey(mentor) == true && !totalPaired.contains(mentee)  && thisMentorsList.size() <2) {
//						System.out.println("Departments: I put " + mentor.getFirstName() + " with " + mentee.getFirstName() + mentee.getLastName());
						thisMentorsList.add(mentee);
//						System.out.println("this mentor's list is: " + thisMentorsList.size());

						totalPaired.add(mentee);
						myTable.put(mentor, thisMentorsList);
					}

				}
			}

		} //end 2nd round pairing


		for (Girl g : girls) {

			if (!totalPaired.contains(g)) {
//				System.out.println(g.getFirstName() + " is unpaired, major: " + g.getMajor() + " role: " + g.getmRole());
			}

		}
		
		Set<Entry> entries = myTable.entrySet();
		
		StringBuilder sb = new StringBuilder();
		
		for(Entry entry : entries) {
			Girl key = (Girl)entry.getKey();
			ArrayList<Girl> values = (ArrayList<Girl>)entry.getValue();
			
			String s = key.getFirstName() + " major/department:   "  + key.getMajor() + "/" + key.getDepartment();
			System.out.println(s);
			sb.append(s);
			
			
			for(Girl value : values) {
				
				String mt = "   " + value.getFirstName() +"   " +value.getMajor() + "/" + value.getDepartment();
				System.out.println(mt);
				sb.append(mt);
				
			}
			
		}

	}

	public static void main(String[] args) {

		data = WittySistersEmail.getData();
		makeGirls();
		pair();

	}

}
