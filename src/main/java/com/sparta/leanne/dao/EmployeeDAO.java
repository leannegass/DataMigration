package com.sparta.leanne.dao;

import com.sparta.leanne.ConnectionManager;
import com.sparta.leanne.SQLQueries;
import com.sparta.leanne.dto.EmployeeDTO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class EmployeeDAO {

    private final Connection postgresConnection;

    private Statement statement;

    private static ArrayList<EmployeeDTO> employees = new ArrayList<>();
    private static BufferedReader bufferedReader;

    public EmployeeDAO(Connection postgresConnection) {
        this.postgresConnection = postgresConnection;
        try {
            statement = postgresConnection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<EmployeeDTO> getEmployees() {

        return employees;
    }

    public static void populateArray(String filename) throws SQLException {
        try {
            FileReader fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
            bufferedReader.readLine();
            for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
                String[] records = line.split(",");
                EmployeeDTO employeeDTO = new EmployeeDTO(records);
                employees.add(employeeDTO);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void createTable() {
        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.CREATE_TABLE);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void selectAll() throws SQLException {
        if (postgresConnection == null || postgresConnection.isClosed()) {
            ConnectionManager.connectToDB();
        }

        try {


            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.SELECT_ALL);

            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void insertEmployee(ArrayList<EmployeeDTO> employees) throws SQLException {


        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.INSERT_INTO);
            for (int i = 0; i < EmployeeDAO.getEmployees().size(); i++) {
                EmployeeDTO employee = EmployeeDAO.getEmployees().get(i);
                queryToInsertEmp(employee, preparedStatement);


            }
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void queryToInsertEmp(EmployeeDTO employee, PreparedStatement preparedStatement) {

        try {
            preparedStatement.setString(1, employee.getEmpID());

            preparedStatement.setString(2, employee.getNamePrefix());
            preparedStatement.setString(3, employee.getFirstName());
            preparedStatement.setString(4, employee.getMiddleInitial());
            preparedStatement.setString(5, employee.getLastName());
            preparedStatement.setString(6, String.valueOf(employee.getGender()));
            preparedStatement.setString(7, employee.getEmail());
            preparedStatement.setDate(8, Date.valueOf(employee.getDateOfBirth()));
            preparedStatement.setDate(9, Date.valueOf(employee.getDateOfJoining()));
            preparedStatement.setFloat(10, employee.getSalary());
            preparedStatement.execute();
        }
     catch(
    SQLException e)

    {
        e.printStackTrace();
    }

}
   public void deleteEmployee(int id){
        try {
            PreparedStatement preparedStatement = postgresConnection.prepareStatement(SQLQueries.DELETE_INTO_DB);
            preparedStatement.setInt(1,id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public  void deleteTable(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = postgresConnection.prepareStatement(SQLQueries.DROP_TABLE);

        preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public  void truncateTable(){
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = postgresConnection.prepareStatement(SQLQueries.TRUNCATE_TABLE);

            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

