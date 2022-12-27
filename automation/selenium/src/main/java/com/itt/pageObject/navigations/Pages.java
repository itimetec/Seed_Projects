package com.itt.pageObject.navigations;

/***
 * Please make sure all the value should be of 'href' tag in anchor tag
 * Example:
 * ON UI: <a ..... href="create_import_set.do">......</a>
 * IN Framework: LOAD_DATA("create_import_set.do")
 */
public enum Pages {

    LOGIN("openmrs/referenceapplication/home.page");

    private String name;

    Pages(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
