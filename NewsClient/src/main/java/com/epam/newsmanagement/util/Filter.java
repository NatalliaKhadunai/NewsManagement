package com.epam.newsmanagement.util;

/**
 * Class-container where filter parameters are hold
 */

public class Filter {
    String authorIdStr;
    String[] tagIdArray;

    public String getAuthorIdStr() {
        return authorIdStr;
    }

    public void setAuthorIdStr(String authorIdStr) {
        this.authorIdStr = authorIdStr;
    }

    public String[] getTagIdArray() {
        return tagIdArray;
    }

    public void setTagIdArray(String[] tagIdArray) {
        this.tagIdArray = tagIdArray;
    }
}
