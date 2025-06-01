package model;

import java.util.List;
import java.util.UUID;

public class User {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	private String userID;
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private String passwordHash;
	private List<Club> joinedClubs;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public User(String firstName, String lastName, String userName, String email, String passwordHash,
			List<Club> joinedClubs) {
		super();
		userID = UUID.randomUUID().toString();
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.email = email;
		this.passwordHash = passwordHash;
		this.joinedClubs = joinedClubs;
	}

//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getFirstName() {
		return firstName;
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
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public List<Club> getJoinedClubs() {
		return joinedClubs;
	}
	public void setJoinedClubs(List<Club> joinedClubs) {
		this.joinedClubs = joinedClubs;
	}

//	TO STRING -----------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "User [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", email=" + email + ", passwordHash=" + passwordHash + ", joinedClubs=" + joinedClubs
				+ "]";
	}
	
//	GET FULL NAME -------------------------------------------------------------------------------------------------
	public String getFullName() {
		
//		Concatenate first and last name 
		return firstName + " " + lastName;
	}
	
//	ADD CLUB
	public void addClub(Club club) {
		
//		Add the club if it isn't already in the list
		if(!joinedClubs.contains(club))
			joinedClubs.add(club);
	}
	
//	REMOVE CLUB
	public void removeClub(Club club) {
		
//		If the club is in the list, remove it
		if(joinedClubs.contains(club))
			joinedClubs.remove(club);
	}
	
//	IS MEMBER
	public boolean isMember(Club club) {
		
//		Check if the club is in the list of joined clubs
		if(joinedClubs.contains(club))
			return true;
		return false;
	}
}
