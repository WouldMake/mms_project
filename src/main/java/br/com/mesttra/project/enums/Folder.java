package br.com.mesttra.project.enums;

public enum Folder {

    HEALTH("HEALTH"), EDUCATION("EDUCATION"), SPORTS("SPORTS"), INFRASTRUCTURE("INFRASTRUCTURE");

    private String value;

    private Folder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
