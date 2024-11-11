package dataBase;

// -- Selecting all rows from a table
// SELECT * FROM table_name;

// -- Selecting specific columns
// SELECT column1, column2 FROM table_name;

// -- Filtering with WHERE
// SELECT * FROM table_name WHERE condition;

// -- Sorting with ORDER BY
// SELECT * FROM table_name ORDER BY column1 ASC;
// SELECT * FROM table_name ORDER BY column1 DESC;

// -- Equal to
// SELECT * FROM table_name WHERE column_name = 'value';

// -- Not equal to
// SELECT * FROM table_name WHERE column_name != 'value';
// SELECT * FROM table_name WHERE column_name <> 'value';

// -- Greater than, less than
// SELECT * FROM table_name WHERE column_name > 10;
// SELECT * FROM table_name WHERE column_name < 10;

// -- Greater than or equal, less than or equal
// SELECT * FROM table_name WHERE column_name >= 10;
// SELECT * FROM table_name WHERE column_name <= 10;

// -- LIKE (for pattern matching)
// For Dates TO_CHAR(col, 'YYYY')
// SELECT * FROM table_name WHERE column_name LIKE 'A%';  -- Starts with 'A' 
// SELECT * FROM table_name WHERE column_name LIKE '%A';  -- Ends with 'A'
// SELECT * FROM table_name WHERE column_name LIKE '%A%'; -- Contains 'A'

// -- IN (for multiple values)
// SELECT * FROM table_name WHERE column_name IN ('value1', 'value2', 'value3');

// -- BETWEEN (for range)
// SELECT * FROM table_name WHERE column_name BETWEEN 10 AND 20;

// -- IS NULL / IS NOT NULL
// SELECT * FROM table_name WHERE column_name IS NULL;
// SELECT * FROM table_name WHERE column_name IS NOT NULL;

// -- Sort results in ascending order (default)
// SELECT * FROM table_name ORDER BY column_name;

// -- Sort results in descending order
// SELECT * FROM table_name ORDER BY column_name DESC;

// -- GROUP BY to aggregate data
// SELECT column_name, COUNT(*) FROM table_name GROUP BY column_name;

// -- GROUP BY with HAVING (for filtering grouped data)
// SELECT column_name, COUNT(*) FROM table_name GROUP BY column_name HAVING COUNT(*) > 10;

// -- GROUP BY with multiple columns
// SELECT column1, column2, AVG(column3) FROM table_name GROUP BY column1, column2;

// -- INNER JOIN (returns rows with matching values in both tables)
// SELECT * FROM table1 INNER JOIN table2 ON table1.id = table2.id;

// -- LEFT JOIN (returns all rows from the left table, and matching rows from the right table)
// SELECT * FROM table1 LEFT JOIN table2 ON table1.id = table2.id;

// -- RIGHT JOIN (returns all rows from the right table, and matching rows from the left table)
// SELECT * FROM table1 RIGHT JOIN table2 ON table1.id = table2.id;

// -- FULL JOIN (returns rows when there is a match in one of the tables)
// SELECT * FROM table1 FULL JOIN table2 ON table1.id = table2.id;

// -- Insert a single row
// INSERT INTO table_name (column1, column2, column3) VALUES ('value1', 'value2', 'value3');

// -- Insert multiple rows
// INSERT INTO table_name (column1, column2) VALUES 
// ('value1', 'value2'),
// ('value3', 'value4'),
// ('value5', 'value6');

// -- Update existing data
// UPDATE table_name SET column_name = 'new_value' WHERE condition;

// -- Update multiple columns
// UPDATE table_name SET column1 = 'new_value1', column2 = 'new_value2' WHERE condition;

// -- Delete specific rows
// DELETE FROM table_name WHERE condition;

// -- Delete all rows in a table (be cautious!)
// DELETE FROM table_name;

// -- Drop a table (permanently removes it)
// DROP TABLE table_name;

// -- Create a new table
// CREATE TABLE table_name (
//     column1 datatype,
//     column2 datatype,
//     column3 datatype,
//     ...
// );

// -- Create a table with a primary key
// CREATE TABLE table_name (
//     id SERIAL PRIMARY KEY,
//     name VARCHAR(100),
//     age INT
// );

// -- Add a new column to a table
// ALTER TABLE table_name ADD column_name datatype;

// -- Modify an existing column
// ALTER TABLE table_name MODIFY column_name datatype;

// -- Drop a column
// ALTER TABLE table_name DROP COLUMN column_name;

// -- Drop a table
// DROP TABLE table_name;

// -- Drop a column constraint
// ALTER TABLE table_name DROP CONSTRAINT constraint_name;

// -- Create an index
// CREATE INDEX index_name ON table_name (column_name);

// -- Drop an index
// DROP INDEX index_name;

// -- Subquery in the SELECT clause
// SELECT name, (SELECT COUNT(*) FROM orders WHERE customer_id = customers.id) AS order_count FROM customers;

// -- Subquery in the WHERE clause
// SELECT * FROM products WHERE price > (SELECT AVG(price) FROM products);

// -- Begin a transaction
// BEGIN;

// -- Commit the transaction (make changes permanent)
// COMMIT;

// -- Rollback the transaction (undo changes)
// ROLLBACK;

// -- Adding a primary key constraint
// ALTER TABLE table_name ADD PRIMARY KEY (column_name);

// -- Adding a foreign key constraint
// ALTER TABLE table_name ADD CONSTRAINT fk_name FOREIGN KEY (column_name) REFERENCES other_table (other_column);

// -- Adding a unique constraint
// ALTER TABLE table_name ADD CONSTRAINT unique_constraint_name UNIQUE (column_name);

// -- Adding a check constraint
// ALTER TABLE table_name ADD CONSTRAINT check_name CHECK (column_name > 0);

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class main {
    public static void main(String[] args) {
        Game bmw = new Game(1L,"BlackMythWukong",new Date(System.currentTimeMillis()),"Action");
        Game er = new Game(1L,"EldenRing",new Date(System.currentTimeMillis()),"Action");
        Game lol = new Game(1L,"LeagueOfLegends",new Date(System.currentTimeMillis()),"MOBA");
        Game gta = new Game(1L,"GTA5",new Date(System.currentTimeMillis()),"OpenWorld");
        Game ac = new Game(1L,"AnimalCrossing",new Date(System.currentTimeMillis()),"Action");
        Game zd = new Game(1L,"Zelda",new Date(System.currentTimeMillis()),"Action");
        Game ww = new Game(1L,"WutheringWaves",new Date(System.currentTimeMillis()),"Action");
        List<Game> games = Arrays.asList(bmw,er,lol,gta,ac,zd,ww);

        
        try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5433/template1","derek","password")) {
                // Queries
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM games");
                
                // Adding
                String st = "INSERT INTO games (name, release_date, genre) VALUES (?, ?, ?)";
                for (Game game : games) {
                    // Use PreparedStatement with parameterized values
                    try (PreparedStatement is = conn.prepareStatement(st)) {
                        is.setString(1, game.name); // Set the 'name' value
                        is.setDate(2, game.releaseDate); // Set the 'release_date' value
                        is.setString(3, game.genre); // Set the 'genre' value
                        
                        // Execute the insert statement
                        is.executeUpdate();
                    } catch (SQLException e) {
                        System.err.println("Error inserting data: " + e.getMessage());
                    }
                }
                String dt = "DELETE FROM games WHERE name=?";
                //Delete
                for (Game game : games) {
                    // Use PreparedStatement with parameterized values
                    try (PreparedStatement ds = conn.prepareStatement(dt)) {
                        if(game.name.equals("BlackMythWukong")) {
                            ds.setString(1, game.name);
                            ds.executeUpdate();
                        }
                    } catch (SQLException e) {
                        System.err.println("Error inserting data: " + e.getMessage());
                    }
                }

                //filtering
                PreparedStatement filter = conn.prepareStatement("SELECT * FROM games WHERE length(name)>10 AND genre='MOBA' ");
                ResultSet res_fil = filter.executeQuery();
                while (res_fil.next()) {
                    System.out.println("ID: " + res_fil.getLong("id"));
                    System.out.println("Name: " + res_fil.getString("name"));
                    System.out.println("Release Date: " + res_fil.getDate("release_date"));
                    System.out.println("Genre: " + res_fil.getString("genre"));
                    System.out.println("-------------------------------");
                }
                
                //Query
                ResultSet res = ps.executeQuery();
                // while (res.next()) {
                //     System.out.println("ID: " + res.getLong("id"));
                //     System.out.println("Name: " + res.getString("name"));
                //     System.out.println("Release Date: " + res.getDate("release_date"));
                //     System.out.println("Genre: " + res.getString("genre"));
                //     System.out.println("-------------------------------");
                // }

                System.out.println(res);

                
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
