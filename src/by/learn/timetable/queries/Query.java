package by.learn.timetable.queries;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Properties;

public class Query {
    public static PreparedStatement getTeacherDateRoom(Connection connection, int date, int room) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("queries.properties"))) {
            properties.load(inputStream);
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("teacher_date_room"));
            statement.setInt(1, date);
            statement.setInt(2, room);
            return statement;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement getTeacherNotDate(Connection connection, int date) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("queries.properties"))) {
            properties.load(inputStream);
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("teacher_not_date"));
            statement.setInt(1, date);
            return statement;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement getDateLessonsNum(Connection connection, int lessonsNum) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("queries.properties"))) {
            properties.load(inputStream);
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("date_lessons_num"));
            statement.setInt(1, lessonsNum);
            return statement;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement getDateRoomBusy(Connection connection, int roomNum) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("queries.properties"))) {
            properties.load(inputStream);
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("date_room_busy"));
            statement.setInt(1, roomNum);
            return statement;
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void replaceLessons(Connection connection, int date) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get("queries.properties"))) {
            properties.load(inputStream);
            PreparedStatement statement = connection.prepareStatement(properties.getProperty("replace_lessons.0"));
            statement.setInt(1, date);
            statement.setInt(2, date);
            statement.addBatch();
            statement.executeBatch();
            statement.addBatch(properties.getProperty("replace_lessons.1"));
            statement.addBatch(properties.getProperty("replace_lessons.2"));
            statement.addBatch(properties.getProperty("replace_lessons.3"));
            int[] updateCounts = statement.executeBatch();
            System.out.println("Replaced: queries " + Arrays.toString(updateCounts));
            statement.close();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
