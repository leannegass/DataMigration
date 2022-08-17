package com.sparta.leanne;

import com.sparta.leanne.dao.EmployeeDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {

        //System.out.println(EmployeeDAO.getEmployees().get(0).getLastName());
        Connection postgresConnection = ConnectionManager.connectToDB();
        EmployeeDAO employeeDAO = new EmployeeDAO(postgresConnection);
        EmployeeDAO.populateArray("src/main/resources/EmployeeRecords.csv");
        System.out.println(EmployeeDAO.getEmployees().size());
        employeeDAO.truncateTable();
        //employeeDAO.createTable();
        employeeDAO.insertEmployee(EmployeeDAO.getEmployees());
        //employeeDAO.selectAll();
        ConnectionManager.closeConnection();
    }
}
