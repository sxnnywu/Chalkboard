package model;

import java.time.LocalDateTime;

public class Announcement {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
    private int announcementId;
    private String clubId;
    private Member member;
    private String title;
    private String text;
    private LocalDateTime createdAt;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Announcement(Member member, String title, String text) {
		super();
		this.member = member;
		this.title = title;
		this.text = text;
	}
	
//	DAO CONSTRUCTOR -----------------------------------------------------------------------------------------------
	public Announcement(int announcementId, String clubId, Member member, String title, String text, LocalDateTime createdAt) {
        this.announcementId = announcementId;
        this.clubId = clubId;
        this.member = member;
        this.title = title;
        this.text = text;
        this.createdAt = createdAt;
    }
	
//	GETTERS + SETTERS ---------------------------------------------------------------------------------------------
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Member getMember() {
		return member;
	}
	public void setMember(Member member) {
		this.member = member;
	}
	public int getAnnouncementId() {
		return announcementId;
	}
	public void setAnnouncementId(int announcementId) {
		this.announcementId = announcementId;
	}
	public String getClubId() {
		return clubId;
	}
	public void setClubId(String clubId) {
		this.clubId = clubId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	//	TO STRING -----------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Announcement [member=" + member + ", title=" + title + ", text=" + text + "]";
	}
}
