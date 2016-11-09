package org.servidor.firebase;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * Created by campitos on 9/11/16.
 */

public class Data {

    String body;
    String title;

    public Data() {
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
