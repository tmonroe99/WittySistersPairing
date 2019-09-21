
public class Girl {
	
	private String firstName;
	private String lastName;
	private String major;
	private String secondMajor;
	private String year;
	private String department;
	private String email;
	private String cell;
	private String mRole;
	

	private String hobbies;
	private String hopetoGain;
	

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public String getHobbies() {
		return hobbies;
	}

	public void setHobbies(String hobbies) {
		this.hobbies = hobbies;
	}

	public String getHopetoGain() {
		return hopetoGain;
	}

	public void setHopetoGain(String hopetoGain) {
		this.hopetoGain = hopetoGain;
	}
	
	//first name, last name, 1st major2, 2nd major3, email4, cell5, allergies6, mRole7, hobbies8, gain9 
	//Girl g = new Girl(line[0], line[1], line[2], secondMajor, line[4], line[5], line[7], line[8], line[9]);
	public Girl(String firstName, String lastName, String major, String secondMajor, String year, String email,
			String cell, String allergies, String mRole, String hobbies, String hopeToGain, String department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.major = major;
		this.secondMajor = secondMajor;
		this.year = year;
		this.department = department;
		this.email = email;
		this.cell =  cell;
		this.mRole = mRole;
		this.hobbies = hobbies;
		this.hopetoGain = hopeToGain;
	}

	public String getFirstName() {
		return firstName;
	}

	@Override
	public String toString() {
		return "Girl [firstName=" + firstName + ", lastName=" + lastName + ", major=" + major + ", secondMajor="
				+ secondMajor + ", year=" + year + ", department=" + department + ", email=" + email + ", cell=" + cell
				+ ", mRole=" + mRole + ", hobbies=" + hobbies + ", hopetoGain=" + hopetoGain + "]";
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getMajor() {
		return major;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getSecondMajor() {
		return secondMajor;
	}

	public void setSecondMajor(String secondMajor) {
		this.secondMajor = secondMajor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getmRole() {
		return mRole;
	}

	public void setmRole(String mRole) {
		this.mRole = mRole;
	}

}
