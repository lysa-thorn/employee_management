package classess;

public class Employee {
	String first_name, last_name, gender, email;
	int department_id;
	int position_id;
	
	public Employee(String first_name, String last_name, String gender, String email, int department_id, int position_id) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.email = email;
		this.department_id = department_id;
		this.position_id = position_id;
	}
	
	public String toString() {
		return "Firstname: " + first_name + "Lastname:" + last_name + "Gender: " + gender;
	}
}
