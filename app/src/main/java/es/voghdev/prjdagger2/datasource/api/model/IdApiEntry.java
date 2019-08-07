
package es.voghdev.prjdagger2.datasource.api.model;


class IdApiEntry {
    private String name = "";
    private String value = "";

    String parseId() {
        return String.format("%s%s", name != null ? name : "", value != null ? value : "");
    }
}
