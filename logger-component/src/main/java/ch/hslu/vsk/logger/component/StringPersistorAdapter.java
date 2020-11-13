/*
 * StringPersistorAdapter.java
 * Created on 09.11.2020
 *
 * Copyright(c) 2020 Boas Meier.
 * This software is the proprietary information of Boas Meier.
 */
package ch.hslu.vsk.logger.component;

import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.stringpersistor.api.PersistedString;
import ch.hslu.vsk.stringpersistor.api.StringPersistor;
import ch.hslu.vsk.stringpersistor.impl.StringPersistorFile;
import java.io.File;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Code of Class StringPersistorAdapter.
 *
 * @author Boas Meier
 */
public final class StringPersistorAdapter implements LogPersistor {

    private final StringPersistor persistor;
    private final FileSelector selector;
    private final Queue<File> files;

    public StringPersistorAdapter() {
        this.persistor = new StringPersistorFile();
        this.selector = new FileSelector();
        this.files = new LinkedList<>();
    }

    /**
     * Store a message to the StringPersistor.
     *
     * @param message LogMessage Object to store.
     */
    @Override
    public final void save(final LogMessage message) {
        File file = selector.select(message);
        if (!files.contains(file)) {
            files.offer(file);
        }
        persistor.setFile(file);
        System.out.println("Persist to file: " + file);
        String objectAsString = ObjectHelper.objectToString(message);
        persistor.save(Instant.now(), objectAsString);
    }

    @Override
    public Queue<LogMessage> get() {
        Queue<LogMessage> strings = new LinkedList<>();
        while (!files.isEmpty()) {
            File file = files.remove();
            persistor.setFile(file);
            System.out.println("Persist to file: " + file);
            List<PersistedString> tmp = persistor.get(10);
            System.out.println("AAAAAAAAAAAAAAAAAAA" + tmp.get(0));
            while (!tmp.isEmpty()) {
                strings.offer((LogMessage) ObjectHelper.stringToObject(tmp.remove(0).getPayload()));
            }
            //file.delete();
        }
        return strings;
    }
}
