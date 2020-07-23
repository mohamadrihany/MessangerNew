package org.example.model;

public class RDFNode {

    private String id;
    private String name;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RDFNode(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    /*
    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            RDFNode node = (RDFNode) object;
            if (this.id.equals(node.id)) //&& this.designation.equals(employee.getDesignation())   && this.age == employee.getAge()) {
                result = true;
        }
        return result;
    }

*/
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RDFNode))
            return false;
        if (obj == this)
            return true;
        return this.id.equals(((RDFNode) obj).id);
    }

    @Override
    public int hashCode(){
        return id.hashCode();//for simplicity reason
    }
}
