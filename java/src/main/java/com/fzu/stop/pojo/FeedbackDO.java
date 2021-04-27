package com.fzu.stop.pojo;


/**
 * @author 宋家锐
 */
public class FeedbackDO {
    Integer id;
    String title;
    String content;
    String name;
    String status;
    String submitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "FeedbackDO{" + "\n" +
                "\tid = " + id + "\n" +
                "\ttitle = " + title + "\n" +
                "\tcontent = " + content + "\n" +
                "\tname = " + name + "\n" +
                "\tstatus = " + status + "\n" +
                "\tsubmit_time = " + submitTime + "\n" +
                "}\n";
    }
}
