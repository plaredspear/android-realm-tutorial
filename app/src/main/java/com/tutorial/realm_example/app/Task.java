package com.tutorial.realm_example.app;

import io.realm.RealmObject;

/**
 * Created by yslim on 4/12/16.
 */
public class Task extends RealmObject {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
