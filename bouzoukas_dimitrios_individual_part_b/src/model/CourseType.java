/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Dell
 */
public class CourseType {

  
    private int typeId;
    private String typeName;

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public CourseType(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    public CourseType() {
    }
    
      @Override
    public String toString() {
        return "CourseType{" + "typeId=" + typeId + ", typeName=" + typeName + '}';
    }
}
