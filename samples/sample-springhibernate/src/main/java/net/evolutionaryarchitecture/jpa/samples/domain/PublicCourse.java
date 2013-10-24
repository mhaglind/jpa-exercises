package net.evolutionaryarchitecture.jpa.samples.domain;

public class PublicCourse extends Course {
	String classRoom;

	public String getClassRoom() {
		return classRoom;
	}

	public void setClassRoom(String classRoom) {
		this.classRoom = classRoom;
	}

	public String toString() {
		return "Public Course held in " + getClassRoom() + ": " + getTitle();
	}
}
