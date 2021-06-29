package ru.digital.league.x5.sign.bindings.data;

import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MarshallingData {

    private final static String CORRECT_INCOMING_MESSAGE = "/correct_message.xml";
    private final static String INCORRECT_INCOMING_MESSAGE = "/incorrect_message.xml";
    private final static String WITH_CLOSE_TAG_MESSAGE = "/with_close_tag_message.xml";
    private final static String COMMON_RESOURCE_PATH = "/file/";
    private final static String XML_STORE_ENTITY = "store";
    private final static String XML_EMPLOYEE_ENTITY = "employee";
    private final static String XML_CLUSTER_EMPLOYEE_ENTITY = "cl_employee";

    public static String getStoreInfoCorrectMessage() {
        return getIncomingMessage(XML_STORE_ENTITY, CORRECT_INCOMING_MESSAGE);
    }

    public static String getEmployeeCorrectMessage() {
        return getIncomingMessage(XML_EMPLOYEE_ENTITY, CORRECT_INCOMING_MESSAGE);
    }

    public static String getClusterEmployeeCorrectMessage() {
        return getIncomingMessage(XML_CLUSTER_EMPLOYEE_ENTITY, CORRECT_INCOMING_MESSAGE);
    }

    public static String getStoreInfoIncorrectMessage() {
        return getIncomingMessage(XML_STORE_ENTITY, INCORRECT_INCOMING_MESSAGE);
    }

    public static String getEmployeeIncorrectMessage() {
        return getIncomingMessage(XML_EMPLOYEE_ENTITY, INCORRECT_INCOMING_MESSAGE);
    }

    public static String getClusterEmployeeIncorrectMessage() {
        return getIncomingMessage(XML_CLUSTER_EMPLOYEE_ENTITY, INCORRECT_INCOMING_MESSAGE);
    }

    public static String getClusterEmployeeWithCloseTagMessage(){
        return getIncomingMessage(XML_CLUSTER_EMPLOYEE_ENTITY, WITH_CLOSE_TAG_MESSAGE);
    }

    public static String getEmployeeWithCloseTagMessage(){
        return getIncomingMessage(XML_EMPLOYEE_ENTITY, WITH_CLOSE_TAG_MESSAGE);
    }

    private static String getIncomingMessage(String entityName, String messageName){
        String message = null;
        try {
            message = IOUtils.toString(new ClassPathResource(COMMON_RESOURCE_PATH + entityName + messageName).getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
}
