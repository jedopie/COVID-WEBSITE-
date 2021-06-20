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
        String query = "SELECT province_state, sum(cases) FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE province_state = '" + state + "' COLLATE NOCASE GROUP BY id" ;
        
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

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, max(deaths) FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE province_state = '" + country + "' COLLATE NOCASE Group BY id" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             max              = results.getInt("max(deaths)");

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
public String getClimateOfCountry(String country) {
    String climate = "";

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT climate FROM locations WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             climate             = results.getString("climate");
            
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

    // Finally we return all of the movies
    return climate;
}
public ArrayList<String> getCountriesByClimate(String climate) {
    ArrayList<String> countries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region,sum(cases) FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id WHERE climate = '" + climate + "' COLLATE NOCASE GROUP BY country_region ORDER BY sum(cases)";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

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
public int getInfectionRateCertainTime(String country, String date1, String date2) {
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
        String query = "SELECT sum(cases) / (julianday('" + date2 + "') -  julianday('" + date1 + "')) AS 'sum(cases)' FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE country_region = '" + country +"' COLLATE NOCASE AND date <= date('" + date2 + "') AND date >= date('" + date1 + "') GROUP BY country_region;" ;
        
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
public int getDeathRateCertainTime(String country, String date1, String date2) {
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
        String query = "SELECT sum(deaths) / (julianday('" + date2 + "') -  julianday('" + date1 + "')) AS 'sum(deaths)' FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE country_region = '" + country +"' COLLATE NOCASE AND date <= date('" + date2 + "') AND date >= date('" + date1 + "') GROUP BY country_region;" ;
        
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
public int getCountryPopulation(String country) {
    int pop = 0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT population FROM locations WHERE country_region = '" + country + "' COLLATE NOCASE AND province_state IS NULL" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             pop            = results.getInt("population");

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
    return pop;
}
public double getDeathToCaseRatio(String country) {
    double ratio = 0.0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT CAST(sum(deaths) AS FLOAT) / sum(cases) AS 'ratio' FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region;";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             ratio            = results.getInt("ratio");

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
    return ratio;
}
public double getLongByCountry(String country) {
    double longitude = 0.0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT longitude FROM locations WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             longitude           = results.getDouble("longitude");

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
    return longitude;
}
public double getLatByCountry(String country) {
    double latitude = 0.0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT latitude FROM locations WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
          latitude           = results.getDouble("latitude");

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
    return latitude;
}
public ArrayList<String> getCountriesByDistance(double longitude, double latitude, double longResult,  double latResult) {
    ArrayList<String> countries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region FROM locations JOIN casesdeaths ON casesdeaths.Location_id = locations.id WHERE longitude BETWEEN " + longResult + " and " + longitude + " AND latitude BETWEEN " + latitude + " and " + latResult + " GROUP BY country_region";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

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
public ArrayList<String> getSimCountriesByCasesPerMillion(double perMil) {
    ArrayList<String> countries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, sum(cases) / population AS permil FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id GROUP BY country_region HAVING permil > " + (0.8 * perMil) + " and permil < " + (2 * perMil) + " ORDER BY permil ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

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
public ArrayList<String> getSimilarCountriesByDeathsToCasesRatio(double deathsToCases) {
    ArrayList<String> simCountries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, CAST(sum(deaths) AS FLOAT) / sum(cases) AS dToC FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id GROUP BY country_region HAVING dToC > " + (0.8 * deathsToCases) + " and dToC < " + (1.05 * deathsToCases) + " ORDER BY dToC ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

            simCountries.add(country);
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

    return simCountries;
}
public ArrayList<String> getSimilarCountriesByMaxDeaths(int maxDeaths) {
    ArrayList<String> simCountries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, max(deaths) AS maxD FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id GROUP BY country_region HAVING maxD > " + (0.8 * maxDeaths) + " and maxD < " + (1.3 * maxDeaths) + " ORDER BY maxD ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

            simCountries.add(country);
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

    return simCountries;
}
public int getHighestDeathTallyDayByCountry(String country) {
    int max = 0;

    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT max(deaths) FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE country_region = '"+country+"' COLLATE NOCASE GROUP BY country_region;" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             max              = results.getInt("max(deaths)");

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
public ArrayList<String> getSimilarCountriesByMaxCases(int maxCases) {
    ArrayList<String> simCountries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, max(cases) AS maxC FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id GROUP BY country_region HAVING maxC > " + (0.8 * maxCases) + " and maxC < " + (1.3 * maxCases) + " ORDER BY maxC ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

            simCountries.add(country);
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

    return simCountries;
}
public double getDeathRateEntirePeriod(String country) {
    double sum = 0.0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT CAST(sum(deaths) AS FLOAT) / (julianday('2021-04-22') - julianday('2020-01-22')) AS sumdeaths, country_region FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region;" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getDouble("sumdeaths");

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
public double getTransmissionRateEntirePeriod(String country) {
    double sum = 0.0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT CAST(sum(cases) AS FLOAT) / (julianday('2021-04-22') - julianday('2020-01-22')) AS sumcases, country_region FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region;" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getDouble("sumcases");

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
public double getInfectionsPlusDeathsToPopulation(String country) {
    double sum = 0.0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT (sum(deaths) + sum(cases)) / population AS infectionrate, country_region FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE country_region = '" + country + "' COLLATE NOCASE GROUP BY country_region;" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getDouble("infectionrate");

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
public ArrayList<String> getStatesByCountry(String country) {
    ArrayList<String> states = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, CAST(sum(deaths) AS FLOAT) / sum(cases) AS RATE FROM Locations JOIN casesdeaths ON casesdeaths.location_id = locations.id WHERE country_region = '" + country + "' COLLATE NOCASE AND province_state IS NOT NULL GROUP BY id ORDER BY rate ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String state     = results.getString("province_state");
            

            states.add(state);
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

    return states;
}
public double getDeathInfectionRatioByStateTimePeriod(String state, String date1, String date2) {
    double sum = 0.0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, CAST(sum(deaths) AS FLOAT) / sum(cases) AS rate FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE province_state = '" + state + "' COLLATE NOCASE AND date < date('" + date2 + "') AND date > date('" + date1 + "') GROUP BY id" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getDouble("rate");

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
public double getDeathInfectionRatioByState(String state) {
    double sum = 0.0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT CAST(sum(deaths) AS FLOAT) / sum(cases) AS rate FROM casesdeaths JOIN locations ON locations.id = casesdeaths.Location_id WHERE province_state = '" + state + "' GROUP BY id" ;
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getDouble("rate");

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
public double getHighestDeathInfectionRatioByState(String state) {
    double sum = 0.0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, CAST(max(deaths) AS FLOAT) / max(cases) AS rate FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE province_state = '" + state + "' COLLATE NOCASE GROUP BY id;";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getDouble("rate");

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
public String getHighestDeathInfectionRatioDayByState(String state) {
    String sum = "";
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, CAST(max(deaths) AS FLOAT) / max(cases), date FROM casesdeaths JOIN locations ON locations.id = casesdeaths.location_id WHERE province_state = '" + state + "' COLLATE NOCASE GROUP BY id;";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             sum             = results.getString("date");

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
public ArrayList<String> getSimStatesByCasesPerMillion(double perMil) {
    ArrayList<String> countries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, sum(cases) / population AS permil FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id WHERE country_region = 'us' COLLATE NOCASE AND province_state IS NOT NULL GROUP BY id HAVING permil > " + (0.8 * perMil) + " and permil < " + (1.2 * perMil) + " ORDER BY permil ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("province_state");
            

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
public int getStatePopulation(String state) {
    int pop = 0;
    // Setup the variable for the JDBC connection
    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT population FROM locations WHERE province_state = '" + state + "' COLLATE NOCASE AND province_state IS NOT NULL";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
             pop             = results.getInt("population");

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
    return pop;
}
public ArrayList<String> getSimilarStatesByDeathsToCasesRatio(double deathsToCases) {
    ArrayList<String> simCountries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, CAST(sum(deaths) AS FLOAT) / sum(cases) AS dToC FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id WHERE province_state IS NOT NULL GROUP BY id HAVING dToC > " + (0.8 * deathsToCases) + " and dToC < " + (1.05 * deathsToCases) + " ORDER BY dToC ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("province_state");
            

            simCountries.add(country);
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

    return simCountries;
}
public int getTotalDeathsByState(String country) {
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
        String query = "SELECT country_region, sum(deaths) FROM casesdeaths JOIN Locations ON locations.ID = casesdeaths.Location_id WHERE province_state = '" + country + "' COLLATE NOCASE GROUP BY id" ;
        
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
public ArrayList<String> getSimilarStatesByMaxDeaths(int maxDeaths) {
    ArrayList<String> simCountries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, max(deaths) AS maxD FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id WHERE province_state IS NOT NULL GROUP BY id HAVING maxD > " + (0.8 * maxDeaths) + " and maxD < " + (1.3 * maxDeaths) + " ORDER BY maxD ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String state     = results.getString("province_state");
            

            simCountries.add(state);
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

    return simCountries;
}
public ArrayList<String> getSimilarStatesByMaxCases(int maxCases) {
    ArrayList<String> simCountries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT province_state, max(cases) AS maxC FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id WHERE province_state IS NOT NULL GROUP BY id HAVING maxC > " + (0.8 * maxCases) + " and maxC < " + (1.3 * maxCases) + " ORDER BY maxC ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("province_state");
            

            simCountries.add(country);
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

    return simCountries;
}
public ArrayList<String> getSimCountriesFromStateByCasesPerMillion(double perMil) {
    ArrayList<String> countries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, sum(cases) / population AS permil FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id WHERE province_state IS NULL GROUP BY id HAVING permil > " + (0.8 * perMil) + " and permil < " + (1.2 * perMil) + " ORDER BY permil ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

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
public ArrayList<String> getSimilarCountriesFromStateByDeathsToCasesRatio(double deathsToCases) {
    ArrayList<String> simCountries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, CAST(sum(deaths) AS FLOAT) / sum(cases) AS dToC FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id WHERE province_state IS NULL GROUP BY country_region HAVING dToC > " + (0.8 * deathsToCases) + " and dToC < " + (1.05 * deathsToCases) + " ORDER BY dToC ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

            simCountries.add(country);
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

    return simCountries;
}
public ArrayList<String> getSimilarCountriesFromStateByMaxDeaths(int maxDeaths) {
    ArrayList<String> simCountries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, max(deaths) AS maxD FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id  WHERE province_state IS NULL  GROUP BY country_region HAVING maxD > " + (0.8 * maxDeaths) + " and maxD < " + (1.3 * maxDeaths) + " ORDER BY maxD ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

            simCountries.add(country);
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

    return simCountries;
}
public ArrayList<String> getSimilarCountriesByMaxCasesFromState(int maxCases) {
    ArrayList<String> simCountries = new ArrayList<String>();

    Connection connection = null;

    try {
        // Connect to JDBC data base
        connection = DriverManager.getConnection(DATABASE);

        // Prepare a new SQL Query & Set a timeout
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30);

        // The Query
        String query = "SELECT country_region, max(cases) AS maxC FROM locations JOIN casesdeaths ON casesdeaths.location_id = locations.id WHERE province_state IS NULL GROUP BY country_region HAVING maxC > " + (0.8 * maxCases) + " and maxC < " + (1.3 * maxCases) + " ORDER BY maxC ASC";
        
        // Get Result
        ResultSet results = statement.executeQuery(query);

        while (results.next()) {
            String country     = results.getString("country_region");
            

            simCountries.add(country);
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

    return simCountries;
}
}
