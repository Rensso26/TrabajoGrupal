package uce.gruapal.shared.model;


public class User {
    private Long id;
    private String name;
    private String facialData;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacialData() {
        return facialData;
    }

    public void setFacialData(String facialData) {
        this.facialData = facialData;
    }
}