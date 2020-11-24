/**
 * LoggerViewer.java
 * Created on 24.11.2020
 *
 * Copyright(c) 2020 Thomas Goldenberger.
 * This software is the proprietary information of Thomas Goldenberger.
 */
package ch.hslu.vsk.logger.viewer;

import ch.hslu.vsk.logger.common.LogMessage;
import ch.hslu.vsk.logger.viewer.remote.LoggerClient;
import java.awt.Dimension;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Code of Class LoggerViewer.
 *
 * @author Thomas Goldenberger
 */
@SuppressWarnings("serial")
public class LoggerViewer extends JFrame {

    private static final Logger LOGGER = Logger.getLogger(LoggerClient.class.getName());
    private final JTable table;
    private final DefaultTableModel model;
    private String[] columnNames = {"LogLevel", "LogMessage", "Source", "Time-created", "Time-ReceivedServer"};
    private static ArrayList<LogMessage> data = new ArrayList<LogMessage>();
    private static boolean flag = true;

    public LoggerViewer() {
        this.model = new DefaultTableModel();
        this.model.setColumnIdentifiers(columnNames);
        this.table = new JTable();
        this.table.setModel(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 100));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public static void main(final String[] args) throws RemoteException {
        final LoggerClient client = new LoggerClient();
        LoggerViewer gui = new LoggerViewer();
        gui.setSize(1200, 700);
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setResizable(true);
        gui.setTitle("g01-LoggerViewer");

        while (true) {
            while (flag) {
                gui.updateTable();
                gui.setVisible(true);
                flag = false;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                LOGGER.info(ex.getMessage());
            }
        }
    }

    /**
     * Adds a LogMessage to ArrayList.
     *
     * @param message LogMessage Object to add.
     */
    public final void add(LogMessage message) {
        data.add(message);
        flag = true;
    }

    /**
     * Updates Table with new LogMessage.
     */
    private void updateTable() {
        String[] tempData = new String[5];
        if (data.size() == 0) {

        } else {
            int index = data.size() - 1;
            tempData[0] = String.valueOf(data.get(index).getLogLevel());
            tempData[1] = data.get(index).getMessage();
            tempData[2] = data.get(index).getLoggerName();
            tempData[3] = data.get(index).getCreatedAt().toString();
            tempData[4] = data.get(index).getReceivedAt().toString();
            this.model.addRow(tempData);
        }
    }
}
