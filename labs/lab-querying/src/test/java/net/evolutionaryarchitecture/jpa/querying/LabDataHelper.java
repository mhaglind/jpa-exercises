package net.evolutionaryarchitecture.jpa.querying;

import java.sql.Date;

import net.evolutionaryarchitecture.jpa.querying.Conference;
import net.evolutionaryarchitecture.jpa.querying.EventRepository;
import net.evolutionaryarchitecture.jpa.querying.OnsiteCourse;
import net.evolutionaryarchitecture.jpa.querying.Person;
import net.evolutionaryarchitecture.jpa.querying.PublicCourse;

public class LabDataHelper {

	public static void createDefaultDataSet(EventRepository eventRepo) {
		
		// Add some events
		Long eventId1 = eventRepo.registerNewEvent(new Conference("JavaZone", Date
				.valueOf("2009-08-21"), "Stockholm Globe Arena"));
		Long eventId2 = eventRepo.registerNewEvent(new OnsiteCourse("Hibernate In Essence",
				Date.valueOf("2011-02-25"), "Marten Haglind", "Oracle"));
		Long eventId3 = eventRepo.registerNewEvent(new PublicCourse("Core Spring", Date
				.valueOf("2011-04-25"), "Christian Bale",
				"Geita, Programutvikling"));
		Long eventId4 = eventRepo.registerNewEvent(new OnsiteCourse("Spring and Housecleaning",
				Date.valueOf("2011-01-03"), "Tore Skogman", "Telenor"));
		Long eventId5 = eventRepo.registerNewEvent(new PublicCourse("Core Fall", Date
				.valueOf("2011-02-25"), "Nils Karlsson",
				"Holsa, Programutvikling"));
		Long eventId6 = eventRepo.registerNewEvent(new Conference("Scandinavian Developer Conference 2011", Date
				.valueOf("2011-03-03"), "Svenska Massan"));
		Long eventId7 = eventRepo.registerNewEvent(new Conference("JavaOne", Date
				.valueOf("2008-09-28"), "Moscone Center"));
		
		// Add some users
		Person user1 = eventRepo.registerNewUser("Kalle", "Karlsson");
		eventRepo.addNewAddressForUser(user1.getId(), "Andra Langgatan 1", "Goteborg", "123", "Sverige");
		eventRepo.addNewEmailForUser(user1.getId(), "kalle@karlsson.se");
		
		Person user2 = eventRepo.registerNewUser("Eva", "Larsson");
		eventRepo.addNewAddressForUser(user2.getId(), "Storveien 4", "Oslo", "456", "Norge");
		eventRepo.addNewEmailForUser(user2.getId(), "eva@larsson.no");
		eventRepo.addNewEmailForUser(user2.getId(), "nospam@google.com");
		
		Person user3 = eventRepo.registerNewUser("Erik", "Eriksson");
		eventRepo.addNewAddressForUser(user3.getId(), "Main Street 1", "Los Angeles", "1212", "USA");
		eventRepo.addNewAddressForUser(user3.getId(), "West Burbington Lane 43", "London", "754", "UK");
		eventRepo.addNewEmailForUser(user3.getId(), "erik@apple.com");
		
		
		// Register users for some events
		eventRepo.registerForEvent(user1.getId(), eventId1);
		eventRepo.registerForEvent(user1.getId(), eventId6);
		eventRepo.registerForEvent(user1.getId(), eventId5);
		
		eventRepo.registerForEvent(user2.getId(), eventId1);
		eventRepo.registerForEvent(user2.getId(), eventId2);
		eventRepo.registerForEvent(user2.getId(), eventId7);
		
		eventRepo.registerForEvent(user3.getId(), eventId2);
		eventRepo.registerForEvent(user3.getId(), eventId3);
		eventRepo.registerForEvent(user3.getId(), eventId4);
		eventRepo.registerForEvent(user3.getId(), eventId5);
		
	}

}
