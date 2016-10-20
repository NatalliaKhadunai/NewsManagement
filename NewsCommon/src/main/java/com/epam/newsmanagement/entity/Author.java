package com.epam.newsmanagement.entity;

import java.util.Set;

public class Author {
    private int id;
    private String first_name;
    private String last_name;
    private Set<Article> articleSet;
    private boolean isWorking;

    public Author() {

    }

    public Author(String first_name, String last_name, boolean isWorking) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.isWorking = isWorking;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setWorking(boolean working) {
        isWorking = working;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Article> getArticleSet() {
        return articleSet;
    }

    public void setArticleSet(Set<Article> articleSet) {
        this.articleSet = articleSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Author)) return false;
        Author author = (Author) o;
        if (!getFirst_name().equals(author.getFirst_name())) return false;
        return getLast_name().equals(author.getLast_name());
    }

    @Override
    public int hashCode() {
        int result = getFirst_name().hashCode();
        result = 31 * result + getLast_name().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", articleSet=" + articleSet +
                '}';
    }
}
