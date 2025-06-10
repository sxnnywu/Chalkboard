package model;

public class Announcement {
	
//	FIELDS --------------------------------------------------------------------------------------------------------
	private Member member;
	private String title;
	private String text;
	
//	CONSTRUCTOR ---------------------------------------------------------------------------------------------------
	public Announcement(Member member, String title, String text) {
		super();
		this.member = member;
		this.title = title;
		this.text = text;
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
	
//	TO STRING -----------------------------------------------------------------------------------------------------
	@Override
	public String toString() {
		return "Announcement [member=" + member + ", title=" + title + ", text=" + text + "]";
	}
}
