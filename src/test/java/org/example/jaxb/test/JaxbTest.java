package org.example.jaxb.test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import org.example.jaxb.Employee;
import org.example.jaxb.Serializer;
import org.jaxb.example.model.ObjectFactory;
import org.jaxb.example.model.Person;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class JaxbTest {

	@BeforeClass
	public static void setUp() {
		System.out.println("Starting tests ...");
	}

	@AfterClass
	public static void tearDown() {
		System.out.println("End tests ...");
	}

	@Test
	public void testSerializePerson() throws IOException, JAXBException {
		Person person = new ObjectFactory().createPerson();
		person.setId(1);
		person.setName("John Doe");

		FileWriter fw = new FileWriter("person.xml");
		Serializer.serialize(person, fw);

		FileReader fr = new FileReader("person.xml");
		Person p = Serializer.deserializePerson(fr);

		StringWriter expected = new StringWriter();
		Serializer.serialize(person, expected);

		StringWriter actual = new StringWriter();
		Serializer.serialize(p, actual);

		// Assert.assertEquals(person.getId(), p.getId());
		Assert.assertEquals(expected.toString(), actual.toString());
		System.out.println("\t Test Serialized passed");
	}

	@Test
	public void testSerializeGeneric() throws IOException, JAXBException {
		Person person = new ObjectFactory().createPerson();
		person.setId(1);
		person.setName("Jane Doe");

		StringWriter expected = new StringWriter();
		Serializer.serialize(person, expected);

		Person p = Serializer.deserialize(Person.class, new StringReader(
				expected.toString()));
		StringWriter actual = new StringWriter();
		Serializer.serialize(p, actual);

		// Assert.assertEquals(person.getId(), p.getId());
		Assert.assertEquals(expected.toString(), actual.toString());
		System.out.println("\t Test Serialized Generic passed");
	}

	@Test
	public void testInheritance() throws JAXBException {
		Employee employee = new Employee();
		employee.setId(1);
		employee.setName("John Doe");
		employee.setJob("Enginner");

		StringWriter expected = new StringWriter();
		Serializer.serialize(employee, expected);
		System.out.println(expected.toString());

		Employee empp = Serializer.deserialize(Employee.class,
				new StringReader(expected.toString()));
		StringWriter actual = new StringWriter();
		Serializer.serialize(empp, actual);

		// Assert.assertEquals(person.getId(), p.getId());
		Assert.assertEquals(expected.toString(), actual.toString());
		System.out.println("\t Test Serialized Inheritance passed");
	}

	@Test
	public void testArrayPerson() throws Exception {

		Person[] list = new Person[3];

		Person emp = new Person();
		emp.setId(1);
		emp.setName("John");
		list[0] = emp;

		emp = new Person();
		emp.setId(2);
		emp.setName("Jean");
		list[1] = emp;

		emp = new Person();
		emp.setId(3);
		emp.setName("Giovanni");
		list[2] = emp;

		System.out.println("List " + list);
		StringWriter sw = new StringWriter();
		Serializer.serializeArrayPerson(list, sw);
		System.out.println(sw);
		Assert.assertNotNull(sw.toString());

	}
}
