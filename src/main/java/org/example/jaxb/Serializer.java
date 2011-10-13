package org.example.jaxb;

import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.jaxb.example.model.Person;

public class Serializer {

	/**
	 * Serialize an object
	 * 
	 * @param o
	 * @throws JAXBException
	 */
	public static void serialize(Object o) throws JAXBException {

		// write it out as XML
		final JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());
		StringWriter writer = new StringWriter();

		// for cool output
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(o, writer);

		System.out.println(writer.toString());
	}

	/**
	 * Serialize an array of persons
	 * 
	 * In order to work properly, this method need that the XMLType annotation
	 * (if exists) in Person class to have a non empty name
	 * 
	 * @param o
	 * @param writer
	 * @throws JAXBException
	 */
	public static void serializeArrayPerson(Person[] o, Writer writer)
			throws JAXBException {

		// write it out as XML
		final JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());

		// for cool output
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(new JAXBElement<Person[]>(new QName("persons"),
				Person[].class, o), writer);
	}

	/**
	 * Serialize an object
	 * 
	 * @param o
	 * @param writer
	 * @throws JAXBException
	 */
	public static void serialize(Object o, Writer writer) throws JAXBException {

		// write it out as XML
		final JAXBContext jaxbContext = JAXBContext.newInstance(o.getClass());

		// jaxbContext.createMarshaller().marshal(john, writer);

		// for cool output
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(o, writer);

		// System.out.println(writer.toString());
	}

	/**
	 * Deserialze
	 * 
	 * @param reader
	 * @return
	 * @throws JAXBException
	 */
	public static Person deserializePerson(Reader reader) throws JAXBException {
		final JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
		final Person emp = (Person) jaxbContext.createUnmarshaller().unmarshal(
				reader);
		return emp;
	}

	/**
	 * Generic deserialization
	 * 
	 * @param <O>
	 * @param clazz
	 * @param reader
	 * @return
	 * @throws JAXBException
	 */
	@SuppressWarnings("unchecked")
	public static <O extends Object> O deserialize(Class<O> clazz, Reader reader)
			throws JAXBException {

		final JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
		final O object = (O) jaxbContext.createUnmarshaller().unmarshal(reader);
		return object;
	}

}
