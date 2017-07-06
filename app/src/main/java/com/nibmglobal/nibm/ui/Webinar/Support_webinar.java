package com.nibmglobal.nibm.ui.Webinar;

import java.io.Serializable;

/**
 * Created by user on 8/11/2016.
 */
public class Support_webinar implements Serializable{
    private String YoutubeId;
    private String name;
    private String description;

    public String getYoutubeId() {
        return YoutubeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setYoutubeId(String youtubeId) {
        YoutubeId = youtubeId;
    }


    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }
}
