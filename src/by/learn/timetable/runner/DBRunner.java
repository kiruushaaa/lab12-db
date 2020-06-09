package by.learn.timetable.runner;

import by.learn.timetable.connector.Connector;
import by.learn.timetable.queries.Output;
import by.learn.timetable.queries.Query;

import java.io.IOException;
import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class DBRunner {
    
    public static void main(String[] args) {
        try (Connection connection = Connector.getConnection()) {
            List<PreparedStatement> statements = new ArrayList<>();

            statements.add(Query.getTeacherDateRoom(connection, DayOfWeek.MONDAY.getValue(), 433));
            statements.add(Query.getTeacherNotDate(connection, DayOfWeek.MONDAY.getValue()));
            statements.add(Query.getDateLessonsNum(connection, 4));
            statements.add(Query.getDateRoomBusy(connection, 4));
//            Query.replaceLessons(connection, DayOfWeek.MONDAY.getValue());

            Output.showTeacher(statements.get(0));
//            Output.showTeacher(statements.get(1));
//            Output.showDay(statements.get(2));
//            Output.showDay(statements.get(3));

            //close statements
            statements.forEach(c -> {
                try {
                    c.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (SQLException | IOException ex) {
            ex.printStackTrace();
        }
    }
}
