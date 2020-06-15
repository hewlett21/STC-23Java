package ru.enikhov.lesson16;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AppZooLogging {
    private static final Logger logger = LogManager.getLogger(AppZooLogging.class);
    private static final String URL = "jdbc:postgresql://localhost:5432/jdbcDB";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "1";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            logger.info("Инициализация таблиц ...");
            DBUtil.recreateTable(conn);
            logger.info("Таблицы подготовлены ...");

            logger.info("Список всех животных в зоопарке:");
            listAnimal(conn, 0);
            System.out.println();
            logger.info("***********************************");
            logger.info("Список животных, весящих >= 300 кг.");
            listAnimal(conn, 300);
            System.out.println();
            logger.info("***********************************");
            logger.info("У животных, весящих >= 300 кг. и время первого кормления 7 часов,");
            logger.info("установить время первого кормления 7.30");
            updTime(conn, 300, 7.00, 7.30);
            listAnimal(conn, 300);
        } catch (SQLException | IOException sqle) {
            sqle.printStackTrace();
            logger.error(sqle.getMessage());
        }
    }

    /**
     * меняем дату первого кормления на 7.30
     * заполняем list значениями idfeed, которые далее проапдейтим в батче
     *
     * @param conn
     * @param w       - вес животных, у которых меняем время
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
                    logger.debug("Add list idfeed = " + resultSet.getInt("idfeed"));
                }
            }
            batchUpdate(conn, listIdFeed);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * по полученному list делаем батчапдейт
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
                logger.debug("update feed set hourfeed = 7.30 where idfeed = " + arg);
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
            logger.error(throwables.getMessage());
        }
    }

    /**
     * формируем список животных
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
                    String strInfo =
                            "id = " + resultSet.getInt("idAnimal")
                                    + "; животное = " + resultSet.getString("animal")
                                    + "; № клетки = " + resultSet.getInt("idcage")
                                    + "; смотритель = " + resultSet.getString("position")
                                    + "; вес = " + resultSet.getString("weight") + " кг."
                                    + "; время 1-го кормления = " + resultSet.getString("timefirst") + " ч."
                                    + "; чем кормят = " + resultSet.getString("typefirst")
                                    + "; кол-во = " + resultSet.getString("volumefirst") + " кг."
                                    + "; время 2-го кормления = " + resultSet.getString("timesecond") + " ч."
                                    + "; чем кормят = " + resultSet.getString("typesecond")
                                    + "; кол-во = " + resultSet.getString("volumesecond") + " кг.";
                    logger.info(strInfo);
                }
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                logger.error(sqle.getMessage());
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            logger.error(throwables.getMessage());
        }

    }
}
