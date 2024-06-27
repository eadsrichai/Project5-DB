package model;

public class Student {
	private String id;
	private String fname;
	private String lname;
	private String tel;
	
	public Student() {}
	
	public Student(String id, String fname, String lname, String tel) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.tel = tel;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	

}
