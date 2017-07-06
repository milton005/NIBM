package com.nibmglobal.nibm.ui.student_activities.studymaterial.detail;

import java.io.Serializable;

/**
 * Created by mindlabs on 12/3/15.
 */
public class SupportStudyMaterialChapter implements Serializable{

    private String lessonName;
    private String bookFile;

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getBookFile() {
        return bookFile;
    }

    public void setBookFile(String bookFile) {
        this.bookFile = bookFile;
    }
}
