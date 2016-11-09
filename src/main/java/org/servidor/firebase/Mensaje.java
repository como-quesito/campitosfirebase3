package org.servidor.firebase;

/**
 * Created by campitos on 9/11/16.
 */
public class Mensaje {

    String to;

    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Mensaje() {
    }
}
