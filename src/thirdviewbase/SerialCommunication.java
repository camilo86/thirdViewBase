package thirdviewbase;

import gnu.io.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.TooManyListenersException;

/**
 *
 * @author Camilo
 */
public class SerialCommunication implements SerialPortEventListener {
    
    private Enumeration ports = null;
    private HashMap portMap = new HashMap();
    
    private CommPortIdentifier selectedPortIdentifier = null;
    private SerialPort serialPort = null;
    
    private InputStream input = null;
    private OutputStream output = null;
    
    private boolean isConnected = false;
    
    final static int TIMEOUT = 2000;
    
    final static int SPACE_ASCII = 32;
    final static int DASH_ASCII = 45;
    final static int NEW_LINE_ASCII = 10;
    
    String logText = "";

    public void searchForPorts() {
        ports = CommPortIdentifier.getPortIdentifiers();
        
        while(ports.hasMoreElements()) {
            CommPortIdentifier currentPort = (CommPortIdentifier)ports.nextElement();
            
            if(currentPort.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                System.out.println(currentPort.getName());
            }
        }
    }
    
    public void connect(String portName, CommPortIdentifier portIdentifier) {
        String chosenPort = portName;
        selectedPortIdentifier = portIdentifier;
        
        CommPort commPort = null;
        
        try {
            commPort = selectedPortIdentifier.open(portName, TIMEOUT);
            serialPort = (SerialPort)commPort;
            System.out.println("Opened port successfully.");
        }catch(PortInUseException e) {
            System.out.println(portName + " is in use. (" + e.toString() + ")");
        }catch(Exception e) {
            System.out.println("Failed epicaly while trying to connect to " + portName + " (" + e.toString() + ")" );
        }
    }
    
}
