package com.vuclip.ubs.common;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

public class ObjectMapperUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Json file to java pojo conversion
     *
     * @param filepath
     * @param returnType
     * @param <T>
     * @return
     */
    public static <T> T readValue(String filepath, Class<T> returnType) {
        System.out.println("*** filepath: " + filepath);
        try {
            File f = new File(filepath);
            System.out.println("Is File object is Null : " + (f == null) + "");
            return objectMapper.readValue(f, returnType);
        } catch (UnrecognizedPropertyException e) {
            System.out.println("UnrecognizedPropertyException occurs : In JSON  ");
            System.out.println(e.getMessage());
        } catch (JsonParseException e) {
            System.out.println("JsonParseException occurs ");
            System.out.println(e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("JsonMappingException occurs ");
            System.out.println(e.getMessage());
        } catch (IOException e) {

            System.out.println("IOException occurs ");
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Json String to Java pojo conversion
     *
     * @param jsonString
     * @param returnType
     * @param <T>
     * @return
     */
    public static <T> T readValueFromString(String jsonString, Class<T> returnType) {
        try {
            return objectMapper.readValue(jsonString, returnType);
        } catch (UnrecognizedPropertyException e) {
            System.out.println("UnrecognizedPropertyException occurs  in JSON " + jsonString);
            System.out.println(e.getMessage());
        } catch (JsonParseException e) {
            System.out.println("JsonParseException occurs In JSON " + jsonString);
            System.out.println(e.getMessage());
        } catch (JsonMappingException e) {
            System.out.println("JsonMappingException occurs In JSON" + jsonString);
            System.out.println(e.getMessage());
        } catch (IOException e) {

            System.out.println("IOException occurs   " + jsonString);
            System.out.println(e.getMessage());
        }

        return null;
    }

    /**
     * Object to JSON as String
     *
     * @param value
     * @return
     */
    public static String writeValueAsString(Object value) {
        String s = null;
        try {
            s = objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    /**
     * @param value
     * @param filepath
     * @return
     */
    public static boolean writeValueInFile(Object value, String filepath) {

        File f = new File(filepath);

        System.out.println("Is File object is Null : " + (f == null) + "");
        try {

            objectMapper.writeValue(f, value);
            return true;
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * POJO to soap
     *
     * @param obj
     * @return
     */
    public static String marshall(Object obj) {
        String output = null;
        try {
            StringWriter sw = new StringWriter();
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
            jaxbMarshaller.marshal(obj, sw);
            output = sw.toString();
        } catch (JAXBException jb) {
            System.out.println("Error occured while marshalling object to xml {}" + jb.getMessage());
        }

        return output;
    }
}