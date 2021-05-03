package com.fzu.stop.pojo;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;


/**
 * @author 武雍易
 */
public class NoticeDO {
    @NotNull(message = "id不能为空")
    @Min(value = 1,message = "错误的id")
    Integer id;
    String title;
    String content;
    Boolean top;
    Timestamp publishTime;
    Timestamp updateTime;
    @Range(min = 0,max = 1,message = "非法的状态")
    Integer status;

    public NoticeDO() {
    }

    public NoticeDO(String title, String content, Boolean top, Integer status) {
        this.title = title;
        this.content = content;
        this.top = top;
        this.status = status;
    }

    public NoticeDO(Integer id, String title, String content, Boolean top, Integer status) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.top = top;
        this.status = status;
    }

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

    public Boolean getTop() {
        return top;
    }

    public void setTop(Boolean top) {
        this.top = top;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NoticeDO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", top=" + top +
                ", publishTime=" + publishTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                '}';
    }
}
