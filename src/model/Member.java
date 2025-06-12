package model;

public class Member extends User{

//	FIELDS --------------------------------------------------------------------------------------------------------
	private String role;
	private String clubID;

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Member(String firstName, String lastName, String userName, String email, String passwordHash, String role,
			 String clubID) {
		super(firstName, lastName, userName, email, passwordHash);
		this.role = role;
		this.clubID = clubID;
	}

//	CONSTRUCTOR 2 -------------------------------------------------------------------------------------------------
	public Member(User user, String role, String clubID) {
		super(user.getFirstName(), user.getLastName(), user.getUserName(), user.getEmail(), user.getPasswordHash());
		this.role = role;
		this.clubID = clubID;
	}
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getClubID() {
	    return clubID;
	}
	public void setClubID(String clubID) {
	    this.clubID = clubID;
	}

//	TO STRING -----------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Member [role=" + role + ", clubID=" + clubID + "]";
	}
}
