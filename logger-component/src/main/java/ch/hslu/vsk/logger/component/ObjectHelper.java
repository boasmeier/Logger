/*
 * ObjectHelper.java
 * Created on 09.11.2020
 *
 * Copyright(c) 2020 Boas Meier.
 * This software is the proprietary information of Tobias Heller.
 */
package ch.hslu.vsk.logger.component;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Base64;
import java.util.logging.Logger;

/**
 * Serializes objects into strings and back.
 *
 * @author Boas Meier
 * @version JDK 12.0.2
 */
public class ObjectHelper {

    private static final Logger LOG = Logger.getLogger(ObjectHelper.class.getName());

    /**
     * Converts an object to string.
     *
     * @param object
     * @return Serialized object as string.
     */
    public static String objectToString(Serializable object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
        } catch (final IOException ex) {
            LOG.severe("IOException while serialize object to string: " + ex.getLocalizedMessage());
        }
        return Base64.getEncoder().encodeToString(baos.toByteArray());
    }

    /**
     * Converts a string to an object.
     *
     * @param objectAsString
     * @return Deserialized object from string.
     */
    public static Serializable stringToObject(String objectAsString) {
        final byte[] data = Base64.getDecoder().decode(objectAsString);
        Serializable object;
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            object = (Serializable) ois.readObject();
            return object;
        } catch (final ClassNotFoundException ex) {
            LOG.severe("ClassNotFoundException while deserialize object from string: " + ex.getLocalizedMessage());
        } catch (final IOException ex) {
            LOG.severe("IOException while deserialize object from string: " + ex.getLocalizedMessage());
        }
        return null;
    }
}
