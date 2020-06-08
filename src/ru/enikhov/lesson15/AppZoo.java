package ru.enikhov.lesson15;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppZoo {
    private final static String URL = "jdbc:postgresql://localhost:5432/jdbcDB";
    private final static String USERNAME = "postgres";
    private final static String PASSWORD = "1";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            DBUtil.recreateTable(conn);

            System.out.println("Список всех животных в зоопарке:");
            listAnimal(conn, 0);
            System.out.println();
            System.out.println("***********************************");
            System.out.println("Список животных, весящих >= 300 кг.");
            listAnimal(conn, 300);
            System.out.println();
            System.out.println("***********************************");
            System.out.println("У животных, весящих >= 300 кг. и время первого кормления 7 часов,");
            System.out.println("установить время первого кормления 7.30");
            updTime(conn, 300, 7.00, 7.30);
            listAnimal(conn, 300);
        } catch (SQLException | IOException sqle) {
            sqle.printStackTrace();
        }
    }

    /** меняем дату первого кормления на 7.30
     * заполняем list значениями idfeed, которые далее проапдейтим в батче
     *
     * @param conn
     * @param w     - вес животных, у которых меняем время
     * @param oldTime - старое время
     * @param newTime - новое время
     */
    private static void updTime(Connection conn, int w, double oldTime, double newTime) {
        List<Integer> listIdFeed = new ArrayList<>();
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "select \n" +
                        "f.idfeed\n" +
                        "from animal a, med m, feed f\n" +
                        " where a.idmed = m.idmed\n" +
                        "\tand a.idanimal = f.idanimal\n" +
                        "\tand m.weight >= ?\n" +
                        "\tand f.hourfeed = ?;"
        )) {
            preparedStatement.setInt(1, w);
            preparedStatement.setDouble(2, oldTime);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    listIdFeed.add(resultSet.getInt("idfeed"));
                }
            }
            batchUpdate(conn, listIdFeed);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /** по полученному list делаем батчапдейт
     * для первой записи из таблицы feed пытаемся поменять время на 9.30
     * и через savepoint откатываем эту транзакцию
     *
     * @param conn
     * @param listIdFeed
     * @throws SQLException
     */
    private static void batchUpdate(Connection conn, List<Integer> listIdFeed) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "update feed set hourfeed = 7.30 where idfeed = ?;")
        ) {
            for (int arg : listIdFeed) {
                preparedStatement.setInt(1, arg);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            Savepoint savepoint = conn.setSavepoint();
            Statement statement = conn.createStatement();
            String str = "update feed set hourfeed = 9.30 where idfeed = 1";
            statement.executeUpdate(str);
            conn.rollback(savepoint);
            conn.commit();
        } catch (SQLException throwables) {
            conn.rollback();
            throwables.printStackTrace();
        }
    }

    /** формируем список животных
     *
     * @param conn
     * @param kg
     */
    private static void listAnimal(Connection conn, int kg) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                "with t as (\n" +
                        "select \n" +
                        "a.idanimal,\n" +
                        "a.animal,\n" +
                        "a.idcage,\n" +
                        "s.position,\n" +
                        "m.weight,\n" +
                        "f.hourfeed,\n" +
                        "f.typefood, \n" +
                        "f.volume,\n" +
                        "row_number() over (partition by a.idanimal order by f.hourfeed) rnk\n" +
                        "from animal a, cage c, staff s, med m, feed f\n" +
                        " where a.idcage = c.idcage\n" +
                        " \tand c.idstaff = s.idstaff\n" +
                        "\tand a.idmed = m.idmed\n" +
                        "\tand a.idanimal = f.idanimal and m.weight >= ?)\n" +
                        "select \n" +
                        "t.idanimal,\n" +
                        "t.animal,\n" +
                        "t.idcage,\n" +
                        "t.position,\n" +
                        "t.weight,\n" +
                        "max(case when t.rnk = 1 then t.hourfeed end) timeFirst,\n" +
                        "max(case when t.rnk = 1 then t.typefood end) typeFirst,\n" +
                        "max(case when t.rnk = 1 then t.volume end) volumeFirst,\n" +
                        "max(case when t.rnk = 2 then t.hourfeed end) timeSecond,\n" +
                        "max(case when t.rnk = 2 then t.typefood end) typeSecond,\n" +
                        "max(case when t.rnk = 2 then t.volume end) volumeSecond\n" +
                        "from t \n" +
                        "group by\n" +
                        "t.idanimal,\n" +
                        "t.animal,\n" +
                        "t.idcage,\n" +
                        "t.position,\n" +
                        "t.weight order by 1;"
        )) {
            preparedStatement.setInt(1, kg);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    System.out.print("id = " + resultSet.getInt("idAnimal"));
                    System.out.print("; животное = " + resultSet.getString("animal"));
                    System.out.print("; № клетки = " + resultSet.getInt("idcage"));
                    System.out.print("; смотритель = " + resultSet.getString("position"));
                    System.out.print("; вес = " + resultSet.getString("weight") + " кг.");
                    System.out.print("; время 1-го кормления = " + resultSet.getString("timefirst") + " ч.");
                    System.out.print("; чем кормят = " + resultSet.getString("typefirst"));
                    System.out.print("; кол-во = " + resultSet.getString("volumefirst") + " кг.");
                    System.out.print("; время 2-го кормления = " + resultSet.getString("timesecond") + " ч.");
                    System.out.print("; чем кормят = " + resultSet.getString("typesecond"));
                    System.out.println("; кол-во = " + resultSet.getString("volumesecond") + " кг.");
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
