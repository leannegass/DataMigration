package com.sparta.leanne;

public interface SQLQueries {


    String CREATE_TABLE = "CREATE TABLE public.employees_db (" +
            "empID VARCHAR(10), \n" +
            "title VARCHAR(255),\n" +
            "firstName VARCHAR(255),\n" +
            "middleInitial VARCHAR(10),\n" +
            "lastName VARCHAR(255),\n" +
            "gender VARCHAR(10),\n" +
            "email VARCHAR(50),\n" +
            "dateOfBirth DATE,\n" +
            "dateOfJoining DATE,\n" +
            "salary INTEGER" +
            ");";

    String DROP_TABLE = "DROP TABLE public.employees_db;";
    String SELECT_ALL = "SELECT * from public.employees_db";


    String INSERT_INTO = "INSERT INTO public.employees_db " +
            "(empID, title, firstName, middleInitial, lastName, gender, email, dateOfBirth, dateOfJoining, salary) " +
            "VALUES(?,?,?,?,?,?,?,?,?,?);";

    String DELETE_INTO_DB = "DELETE FROM public.employees_db WHERE id = ?";
    String TRUNCATE_TABLE = "TRUNCATE public.employees_db";
}
