package com.epam.newsmanagement.entity;

import java.sql.Timestamp;

public class Comment {
    private long id;
    private int articleID;
    private int accountId;
    private Timestamp date;
    private String content;

    public Comment() {

    }

    public Comment(int articleID, int accountId, Timestamp date, String content) {
        this.articleID = articleID;
        this.accountId = accountId;
        this.date = date;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment)) return false;
        Comment comment = (Comment) o;
        if (getArticleID() != comment.getArticleID()) return false;
        if (getAccountId() != comment.getAccountId()) return false;
        if (!getDate().equals(comment.getDate())) return false;
        return getContent().equals(comment.getContent());
    }

    @Override
    public int hashCode() {
        int result = getArticleID();
        result = 31 * result + getAccountId();
        result = 31 * result + getDate().hashCode();
        result = 31 * result + getContent().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "articleID=" + articleID +
                ", accountId='" + accountId + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }
}
