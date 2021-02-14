package database;

import java.awt.List;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import pojo.Column;
import pojo.Table;

public class DBConnection {
    
    static final String DDBC_DRIVER = "com.mysql.jdbc.Driver";
    static String DB_URL_PREFIX = "jdbc:mysql://";
    
    static Statement stmt;
    static private ResultSet resultSet;
    static private ResultSetMetaData resultSetMetaData;
    static private Connection connection;
    


    public DBConnection() { }
    
    
    public static void connect(String server, String user, String pass) throws Exception {
        
        Class.forName(DDBC_DRIVER);
        connection = DriverManager.getConnection(
                DB_URL_PREFIX + server, user, pass);


        stmt=connection.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

    }
    
    
    public static void closeConnection(){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
    public static String[] getDatabases(){
        
        String[] dbs = null;
        
        try{
            
            //Podría usar SHOW DATABASES pero me saca broza
            final String query="SELECT SCHEMA_NAME "
                            + "FROM information_schema.SCHEMATA "
                            + "WHERE SCHEMA_NAME NOT IN ( 'information_schema', 'mysql', 'performance_schema', 'sys' )";

            stmt=connection.createStatement();
            
            ResultSet res=stmt.executeQuery(query);
            
            //numero de filas de la consulta
            res.last();
            int numRows=res.getRow();
            
            dbs=new String[numRows];
            
            
            //contenido
            int index=0;
            res.beforeFirst();
            
            String dbName = "";
            while(res.next()){
                dbName = res.getString(1);
                
                dbs[index++] = dbName;
            }
            
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        
        return dbs;
    }
    
    
    
    public static ArrayList<Table> getMetadataOf(String tableName){
        ArrayList<Table> tables = null;
        
        try{
            // describe no saca si es clave foránea ni a qué tabla referencia.
            // entonces hay que sacar todo de las bds internas de mySQL
            final String query = 
                "SELECT C.TABLE_NAME, C.COLUMN_NAME, C.COLUMN_DEFAULT, C.IS_NULLABLE, C.COLUMN_TYPE, C.COLUMN_KEY, K.REFERENCED_TABLE_NAME, K.REFERENCED_COLUMN_NAME "
                + "FROM information_schema.SCHEMATA S "
                + "INNER JOIN information_schema.COLUMNS C "
                +     "ON S.SCHEMA_NAME = C.TABLE_SCHEMA "
                + "LEFT JOIN information_schema.KEY_COLUMN_USAGE K "
                +     "ON C.TABLE_NAME = K.TABLE_NAME "
                +     "AND C.COLUMN_NAME = K.COLUMN_NAME "
                +     "AND C.TABLE_SCHEMA = K.TABLE_SCHEMA "
                + "WHERE S.SCHEMA_NAME = '" + tableName + "' "
                + "ORDER BY C.TABLE_NAME, C.ORDINAL_POSITION";
            
            
            stmt = connection.createStatement();
            
            ResultSet res = stmt.executeQuery(query);
            
            tables = new ArrayList<>();
            
            
            res.last();
            int numRows = res.getRow();

            
            
              
            String name = "";
            Table lastTable = new Table("");
            Column column;
            
            res.first();
            for (int i = 0; i < numRows; i++) {
                name = res.getString("TABLE_NAME");
                
                if(!name.equals(lastTable.getName())){
                    lastTable = new Table(name);
                    tables.add(lastTable);
                }
                
                
                column = new Column(
                        res.getString("COLUMN_NAME"),
                        res.getString("COLUMN_DEFAULT"),
                        res.getString("IS_NULLABLE"),
                        res.getString("COLUMN_TYPE"),
                        res.getString("COLUMN_KEY"),
                        res.getString("REFERENCED_TABLE_NAME"),
                        res.getString("REFERENCED_COLUMN_NAME")
                );
                
                lastTable.addColumn(column);
                
                res.next();
                                
            }
            
            
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        return tables;
    }
    
    

}

