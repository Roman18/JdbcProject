import DB.JdbcTemplate;
import DB.SetUpDB;
import Entity.Human;

import java.sql.SQLException;
import java.util.List;

public class Main {




    public static void main(String[] args) {
        SetUpDB setUpDB=new SetUpDB();
        JdbcTemplate jdbcTemplate=new JdbcTemplate(setUpDB.getDataSource());
        Object []o ={"Vasia", 25,19825.25};
        try {
            jdbcTemplate.update(
                    "INSERT INTO human(name,age,salary) VALUES (?,?,?)",
                    o
                    );
            jdbcTemplate.update("INSERT INTO human(name,age,salary) VALUES ('Pety',30,11111.11)");
            jdbcTemplate.update("INSERT INTO human(name,age,salary) VALUES ('Sasha',40,16090.12)");

            List<Human> result1=jdbcTemplate.queryOne("SELECT id, name, age, salary FROM human WHERE age > ?",
                    new Object[]{25},
                    rs -> {
                Human human=new Human();
                human.setId(rs.getLong("id"));
                human.setName(rs.getString("name"));
                human.setAge(rs.getInt("age"));
                human.setSalary(rs.getDouble("salary"));
                return human;
                    });
            System.out.println("Result1 - "+result1);
            List<Human> result2 = jdbcTemplate.query("SELECT id, name, age, salary FROM human WHERE salary > ?",
                    new Object[]{15000},
                    rs -> {
                        Human human=new Human();
                        human.setId(rs.getLong("id"));
                        human.setName(rs.getString("name"));
                        human.setAge(rs.getInt("age"));
                        human.setSalary(rs.getDouble("salary"));
                        return human;
                    });
            System.out.println("Result2 - "+result2);

            jdbcTemplate.update(
                    "DELETE FROM human WHERE name = ?"
                    ,new Object[]{"Sasha"}
                    );

            List<Human> result3 = jdbcTemplate.query("SELECT id, name, age, salary FROM human",
                    rs -> {
                        Human human=new Human();
                        human.setId(rs.getLong("id"));
                        human.setName(rs.getString("name"));
                        human.setAge(rs.getInt("age"));
                        human.setSalary(rs.getDouble("salary"));
                        return human;
                    });
            System.out.println("Result3 - "+result3);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
