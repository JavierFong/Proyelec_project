/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author Work
 */
public class DBManager {
    Connection con; 
    Statement stm; 
    
    public void DBManager() {      
    }
    
    public Statement getStatement() {
        return stm; 
    }
    public Connection getConnection(){
        return con; 
    }
    public boolean conectar(String ip,String db, String user, String pw) {
        boolean estado; 
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String conexion = "jdbc:mysql://"+ip+"/"+db+"?user="+user+"&password="+pw; 
            con = DriverManager.getConnection(conexion); 
            stm = con.createStatement(); 
            estado = true; 
        } catch (Exception e) {estado = false; System.out.println("Error de conexion");} 
        return estado; 
    }
    
    public void insertar(String table, String values) {
        String insertar = "INSERT INTO "+table+" VALUES "+values;
        try { 
            stm.executeUpdate(insertar); 
        } catch (Exception e) {System.out.println("ERROR de inserción, en "+table);}
    }
    
    public void borrar(String table, String clave, String id) {
        String borrar = "DELETE FROM "+table+" WHERE "+clave+"='"+id+"';";
        try {
            stm.executeUpdate(borrar); 
        } catch (Exception e) {}
    }
    
    public void revisarConexion(boolean estado){
        if (estado) {
            System.out.println("Conexion Exitosa");
        } else {
            System.out.println("Conexion Fallida");
        }
    }
    
    public int getTableLength(String tabla) {
        int len = 0; 
        try {
            ResultSet rs = stm.executeQuery("SELECT * FROM "+tabla);
            while (rs.next()){
                len++; 
            }
        } catch (Exception e){}
        return len; 
    }
}
            
            
      