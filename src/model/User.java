package model;

import java.util.*;

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
		this.joinedClubs = (joinedClubs != null) ? joinedClubs : new ArrayList<>(); // Initialize if null
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
	
//		Check if user is already in the club
		if(joinedClubs.contains(club))
			throw new IllegalArgumentException("User already joined this club.");
		joinedClubs.add(club);
	}
	
//	REMOVE CLUB
	public void removeClub(Club club) {
		
//		Check if user is in club
		if(!joinedClubs.contains(club))
			throw new IllegalArgumentException("User is not a member of this club.");
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
