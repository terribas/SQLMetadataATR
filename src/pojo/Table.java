/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author alejandroterribasruiz
 */
public class Table {
    
    private String name;
    private ArrayList<Column> columns;
    
    
    public Table(String name) {
        this.name = name;
        this.columns = new ArrayList<>();
    }
    
    
    public void addColumn(Column column){
        this.columns.add(column);
    }
    
    
    
    public String getName() { return this.name; }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Table other = (Table) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String output = "";
        
        output += "================== TABLE " + name + " ==================\n";
        
        //con columns.toString() se incluyen [ , ] y queda feo
        for (int i = 0; i < columns.size(); i++) {
            output += columns.get(i).toString();
        }
        
        output += "\n\n\n";
        return output;
    }

    

    
    
    
    
    
}
