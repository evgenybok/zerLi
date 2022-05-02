package controllers;

import java.util.Objects;

import ocsf.server.ConnectionToClient;

public class ClientDoc {
    private String ipAddress;

    private String hostName;

    private String status;
    @SuppressWarnings("unused")
	private ConnectionToClient client;

    public ClientDoc(ConnectionToClient client) {

        hostName = client.getInetAddress().getHostName();
        ipAddress = client.getInetAddress().getHostAddress();
        status = client.isAlive() == true ? "Connected" : "Disconnected";
        this.client = client;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getHostName() {
        return this.hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String toString() {
        return String.format("%s %s %s", hostName, ipAddress, status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientDoc clientDoc = (ClientDoc) o;
        return Objects.equals(ipAddress, clientDoc.ipAddress) && Objects.equals(hostName, clientDoc.hostName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ipAddress, hostName);
    }

}

