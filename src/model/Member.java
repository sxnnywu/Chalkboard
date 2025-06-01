package model;

import java.util.List;

public class Member extends User{

//	FIELDS --------------------------------------------------------------------------------------------------------
	private String role;

//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Member(String firstName, String lastName, String userName, String email, String passwordHash,
			List<Club> joinedClubs, String role) {
		super(firstName, lastName, userName, email, passwordHash, joinedClubs);
		this.role = role;
	}
	
}
