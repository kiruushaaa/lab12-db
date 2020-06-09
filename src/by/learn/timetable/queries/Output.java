package by.learn.timetable.queries;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Output {
    public static void showTeacher(PreparedStatement statement) {
        try (ResultSet result = statement.executeQuery()) {
            while (result.next()) {
                System.out.println(result.getString("firstName") + " " + result.getString("lastName"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void showDay(PreparedStatement statement) {
        try (ResultSet result = statement.executeQuery()) {
            List<DayOfWeek> results = new ArrayList<>();
            while (result.next()) {
                results.add(DayOfWeek.of(result.getInt("date")));
            }
            Collections.sort(results);
            System.out.println(results);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
