package org.example.model;

public class RDFEdge {

    private long id;
    private String from;
    private String to;

    public long getId() {
        return id;
    }

    public String getFrom() {
        return from;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getTo() {
        return to;
    }

    public String getLabel() {
        return label;
    }

    public RDFEdge(long id, String from, String to, String label) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.label = label;
    }

    private String label;
}
