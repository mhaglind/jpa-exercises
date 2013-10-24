package net.evolutionaryarchitecture.jpa.samples;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import net.evolutionaryarchitecture.jpa.samples.domain.Address;
import net.evolutionaryarchitecture.jpa.samples.domain.Conference;
import net.evolutionaryarchitecture.jpa.samples.domain.Country;
import net.evolutionaryarchitecture.jpa.samples.domain.Name;
import net.evolutionaryarchitecture.jpa.samples.domain.OnsiteCourse;
import net.evolutionaryarchitecture.jpa.samples.domain.Person;
import net.evolutionaryarchitecture.jpa.samples.domain.PublicCourse;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Setup {

	public static void main(String[] args) throws Exception {

		Session session = new Configuration().configure().buildSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		//
		// Countries
		//
		Country sweden = new Country("se", "Sverige");
		session.save(sweden);
		Country norway = new Country("no", "Norway");
		session.save(norway);
		Country england = new Country("uk", "England");
		session.save(england);
		Country us = new Country("us", "USA");
		session.save(us);
		
		//
		// Persons
		// 
		Set<Address> addresses = new HashSet<Address>();
		addresses.add(new Address("Street 1", "City", "123-45", "Finland"));
		Set<String> emails = new HashSet<String>();
		emails.add("freddy@krueger.com");
		emails.add("bob@bob.com");
		Person p1 = new Person(new Name("Karl", "Karlsson"), addresses, emails);
		session.save(p1);

		Set<Address> addresses2 = new HashSet<Address>();
		addresses2.add(new Address("Gatan 4", "Oslo", "123-22", "Norge"));
		Set<String> emails2 = new HashSet<String>();
		emails2.add("larry@larry.com");
		Person p2 = new Person(new Name("Nils", "Nilsson"), addresses2, emails2);
		session.save(p2);

		Set<Address> addresses3 = new HashSet<Address>();
		addresses3.add(new Address("Storgatan 47", "Goteborg", "123-22",
				"Sverige"));
		Set<String> emails3 = new HashSet<String>();
		emails2.add("erik@eriksson.com");
		Person p3 = new Person(new Name("Erik", "Eriksson"), addresses3,
				emails3);
		session.save(p3);

		//
		// Courses
		// 

		PublicCourse course1 = new PublicCourse();
		course1.setTitle("Spring and Hibernate in Essence");
		course1.setInstructor("MH");
		course1.setClassRoom("Geita");
		course1.setDate(df.parse("2008-01-02"));
		session.save(course1);

		OnsiteCourse course2 = new OnsiteCourse();
		course2.setTitle("Spring and Housekeeping");
		course2.setCompanyName("Acme");
		course2.setDate(df.parse("2008-01-02"));
		course2.setContact(p1);
		session.save(course2);

		OnsiteCourse course3 = new OnsiteCourse();
		course3.setTitle("Fall Framework");
		course3.setCompanyName("Foocorp");
		course2.setDate(df.parse("2008-07-02"));
		course3.setContact(p2);
		session.save(course3);

		//
		// Conferences
		// 
		Conference conf1 = new Conference();
		conf1.setTitle("JavaZone 2008");
		conf1.setDate(df.parse("2008-01-02"));
		conf1.setVenue("Moscone Center");
		conf1.setCountry(norway);
		session.save(conf1);
		
		Conference conf2 = new Conference();
		conf2.setTitle("JavaOne 2009");
		conf2.setDate(df.parse("2009-01-02"));
		conf2.setVenue("Moscone Center");
		conf2.setCountry(us);
		session.save(conf2);		

		//
		// Connections
		//
		Set<Person> persons1 = new HashSet<Person>();
		persons1.add(p1);
		persons1.add(p2);
		persons1.add(p3);
		conf1.setParticipants(persons1);

		Set<Person> persons2 = new HashSet<Person>();
		persons2.add(p1);
		persons2.add(p2);
		course1.setParticipants(persons2);
		course3.setParticipants(persons2);

		Set<Person> persons3 = new HashSet<Person>();
		persons3.add(p3);
		course2.setParticipants(persons3);

		tx.commit();
		session.close();

	}

}
