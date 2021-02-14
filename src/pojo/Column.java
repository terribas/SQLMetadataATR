/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;

/**
 *
 * @author alejandroterribasruiz
 */
public class Column {
    
    
    
    private boolean isPrimaryKey = false;
    private boolean isUnique = false;
    private boolean isNullable = false;
    
    // Accesos a final más rápidos.
    private final String name;
    private final String type;
    private final String defaultValue;
    
    private final String referencedTableName;
    private final String referencedColumnName;
    
    
    
    
    
    public Column(String columnName, String defaultValue, String isNullable, String type, String columnKey, String referencedTableName, String referencedColumnName){
        this.name = columnName;
        this.defaultValue = defaultValue;
        this.type = type;
        this.referencedTableName = referencedTableName;
        this.referencedColumnName = referencedColumnName;
        
        if(isNullable.equalsIgnoreCase("YES")){ this.isNullable = true; }
        if(columnKey.equalsIgnoreCase("PRI")){ this.isPrimaryKey = true; }
        if(columnKey.equalsIgnoreCase("UNI")){ this.isUnique = true; }
    }
    
    
    
    

    @Override
    public String toString() {
        String output = "";
        
        output += "* " + name + ": " + type;
        
        if(isPrimaryKey){
            output += "\n   = Primary key";
            
        }else if(!isNullable){
            //No es necesario mostrar not null si es primary key
            output += "\n   - Not null";
            
        }else{
            output += "\n   - Is Nullable";
        }
        
        
        if(isUnique){ output += "\n   - Is Unique"; }
        
        if(referencedTableName != null){
            output += "\n   - Foreign key, references table " + referencedTableName + " (column: " + referencedColumnName + ")";
        }
        
        if(defaultValue != null){ output += "\n   - Default value: " + defaultValue; }
        
        output += "\n\n";
        
        return output;
    }
    
    
    
    
    
}
