package app;

import java.util.ArrayList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database.
 * Allows SQL queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies Database
 * This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 */
public class JDBCConnection {

    // Name of database file (contained in database folder)
    private static final String DATABASE = "jdbc:sqlite:database/COVID FINAL DATABASE.db";

    public JDBCConnection() {
        System.out.println("Created JDBC Connection Object");
    }

    /**
     * Get all of the Movies in the database
     */
    // GLOBAL DEATH TALLY
    public int caseCount() {
        int cases = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT sum(cases) FROM casesdeaths";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            results.next();
            String sum = results.getString(1);
            System.out.println(cases);
            cases = (int) Double.parseDouble(sum);
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }

        // Finally we return all of the movies
        return cases;
    }
// GLOBAL DEATH TALLY
    public int deathCount() {
        int deaths = 0;

        // Setup the variable for the JDBC connection
        Connection connection = null;

        try {
            // Connect to JDBC data base
            connection = DriverManager.getConnection(DATABASE);

            // Prepare a new SQL Query & Set a timeout
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            // The Query
            String query = "SELECT sum(deaths) FROM casesdeaths";
            
            // Get Result
            ResultSet results = statement.executeQuery(query);
            results.next();
            String sum = results.getString(1);
            System.out.println(deaths);
            deaths = (int) Double.parseDouble(sum);
            

            // Close the statement because we are done with it
            statement.close();
        } catch (SQLException e) {
            // If there is an error, lets just pring the error
            System.err.println(e.getMessage());
        } finally {
            // Safety code to cleanup
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return deaths;
    }
    //MOST AFFECTED COUNTRIES BY CASES - ORDERED WORST - LEAST AFFECTED
    public ArrayList<String> getMostCases() {
    ArrayList<String> countries = new ArrayList<String>();
    ArrayList<int[]> countrycase = new ArrayList<int[]>(); 

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, SUM(cases) FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id GROUP BY country_region ORDER BY sum(cases) DESC LIMIT 5";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("Country_Region");
            

            countries.add(country);
        }

        statement.close();
    } catch (SQLException e) {
        System.err.println(e.getMessage());
    } finally {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    return countries;
}
// GIVES TOTAL CASES BY GIVEN COUNTRY
public int getTotalCasesByCountry(String country) {
    int sum = 0;

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, sum(cases) FROM casesdeaths JOIN Locations ON locations.ID = casesdeaths.Location_id WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum              = results.getInt("sum(cases)");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return sum;
}
//Total Deaths by Country
public int getTotalDeathsByCountry(String country) {
    int sum = 0;

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, sum(deaths) FROM casesdeaths JOIN Locations ON locations.ID = casesdeaths.Location_id WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum              = results.getInt("sum(deaths)");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return sum;
}
public int getHighestCaseTallyByDay(String country) {
    int max = 0;
    String date;

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, max(cases), date FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             max              = results.getInt("max(cases)");
             date             = results.getString("date");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return max;
}
public String getHighestCaseDay(String country) {
    String date = "";

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, max(cases), date FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             date             = results.getString("date");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return date;
}
public int getTotalCasesByState(String state) {
    int sum = 0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, sum(cases) FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE province_state = '" + state + "' GROUP BY id" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getInt("sum(cases)");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return sum;
}
public int getSumCasesTimePeriod(String country, String date1, String date2) {
    int sum = 0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, sum(cases) FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE country_region = '" + country + "' COLLATE NOCASE AND date < date('" + date2 + "') AND date > date('" +date1 + "') GROUP BY country_region;" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getInt("sum(cases)");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return sum;
}
public int getSumCasesTimePeriodByState(String state, String date1, String date2) {
    int sum = 0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, sum(cases) FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE province_state = '" + state + "' AND date < date('" + date2 + "') AND date > date('" + date1 + "') COLLATE NOCASE GROUP BY id;";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getInt("sum(cases)");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return sum;
}
public String getHighestCaseDayByState(String state) {
    String date = "";

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, max(cases), date FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE province_state = '" + state + "' COLLATE NOCASE GROUP BY id" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             date             = results.getString("date");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return date;
}
public int getHighestCaseTallyByDayState(String state) {
    int max = 0;
    String date;

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, max(cases), date FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE province_state = '" + state + "' COLLATE NOCASE GROUP BY id" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             max              = results.getInt("max(cases)");
             date             = results.getString("date");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return max;
}
public String getHighestDeathDay(String country) {
    String date = "";

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, max(deaths), date FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             date             = results.getString("date");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return date;
}
public int getHighestDeathTallyByDayState(String country) {
    int max = 0;
    String date;

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, max(deaths) FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE country_region = '" + country + "' COLLATE NOCASE Group BY country_region" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             max              = results.getInt("max(deaths)");
             date             = results.getString("date");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return max;
}
public int getSumDeathsTimePeriod(String country, String date1, String date2) {
    int sum = 0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, sum(deaths) FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE country_region = '" + country + "' COLLATE NOCASE AND date < date('" + date2 + "') AND date > date('" +date1 + "') GROUP BY country_region;" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getInt("sum(deaths)");

            // For now we will just store the movieName and ignore the id
            
        }

        // Close the statement because we are done with it
        statement.close();
    } catch (SQLException e) {
        // If there is an error, lets just pring the error
        System.err.println(e.getMessage());
    } finally {
        // Safety code to cleanup
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            // connection close failed.
            System.err.println(e.getMessage());
        }
    }

    // Finally we return all of the movies
    return sum;
}
}
