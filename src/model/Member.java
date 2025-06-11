package model;

public class Member extends User{

//	FIELDS --------------------------------------------------------------------------------------------------------
	private String role;

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Member(String firstName, String lastName, String userName, String email, String passwordHash, String role) {
		super(firstName, lastName, userName, email, passwordHash);
		this.role = role;
	}

//	CONSTRUCTOR 2 -------------------------------------------------------------------------------------------------
	public Member(User user, String role) {
		super(user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail(), user.getPasswordHash());
		this.role = role;
	}
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}

	
//	TO STRING -----------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Member [role=" + role + "]";
	}
}
