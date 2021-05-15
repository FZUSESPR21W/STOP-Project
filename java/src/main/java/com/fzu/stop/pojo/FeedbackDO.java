package com.fzu.stop.pojo;


/**
 * @author 宋家锐
 */
public class FeedbackDO {
    Integer id;
    String openId;
    String title;
    String content;
    String username;
    /**
     * 待解决2，已解决3
     */
    String status;
    String submitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
                "\topID = " + openId + "\n" +
                "\ttitle = " + title + "\n" +
                "\tcontent = " + content + "\n" +
                "\tname = " + username + "\n" +
                "\tstatus = " + status + "\n" +
                "\tsubmit_time = " + submitTime + "\n" +
                "}\n";
    }
}
