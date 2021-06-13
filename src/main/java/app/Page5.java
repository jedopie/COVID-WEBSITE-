package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Temporary HTML as an example page.
 * 
 * Based on the Project Workshop code examples.
 * This page currently:
 *  - Provides a link back to the index page
 *  - Displays the list of movies from the Movies Database using the JDBCConnection
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Page5 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page5.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Movies</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        // top banner
        html = html + "<div class='top'>";
        html = html + "<div class='covid'>COVID-19</div>";
        html = html + "</div>";

        html = html + "<div class='topnav'>";
         html = html + "<a href='/'>Home</a>";
         html = html + "<a href='page1.html'>Page 1</a>";
         html = html + "<a href='page2.html'>Page 2</a>";
         html = html + "<a href='page3.html'>Page 3</a>";
         html = html + "<a href='page4.html'>Page 4</a>";
         html = html + "<a class='active' href='page5.html'>Page 5</a>";
         html = html + "<a href='page6.html'>Page 6</a>";
         html = html + "</div>";
        // Add the body
        html = html + "<body>";

        html = html + "<div class='search_container'>";
        html = html + "<form>";
          html = html + "<div class='centered_div'>";
          html = html + "<input type='text' id='search' name='search' placeholder='Search for a Country...'>";
          html = html + "<input type='submit' value='Search' class='submit1'>";
          html = html + "</div>";
        html = html + "</form>";
        html = html + "<div class='clear'></div>";
      html = html + "</div>";



      html = html + "<div class='container7'>";
        html = html + "<div class='sim_climate'>";
            html = html + "<div class='grey'>";
            html = html + "<h4>See Covid data from countries in similar climates</h4>";
            html = html + "</div>";

            html = html + "<div class='filters'>";
                 html = html + "<p>Climate: load value</p>";
                 html = html + "<form>";
                    html = html + "<label for='date5'>Time Period: </label>";
                    html = html + "<input type='date' id='date5' name='date6' data-date-inline-picker='true'>";
                    html = html + "<label for='date6'>  to  </label>";
                    html = html + "<input type='date' id='date6' name='date6' data-date-inline-picker='true'>";
                html = html + "</form>";
            html = html + "</div>";

            html = html + "<table class='tbl'>";
            html = html + "<tr>";
            html = html + "<th>Country</th>";
            html = html + "<th>Transmission Rate</th>";
            html = html + "<th>Death Rate</th>";
            html = html + "</tr>";
            for(int i = 0; i <= 6; i++){
            html = html + "<tr>";
            html = html + "<td>COUNTRY</td>";
            html = html + "<td>0</td>";
            html = html + "<td>0</td>";
            html = html + "</tr>";
            }
        html = html + "</table>";

      html = html + "<br class='clear' />";
        html = html + "</div>";

        html = html + "<div class='sim_distance'>";  
            html = html + "<div class='grey'>";
                html = html + "<h4>See Covid data from surrounding countries</h4>";
                html = html + "</div>";

                html = html + "<div class='filters'>";
                    html = html + "<form>";
                        html = html + "<label for='distance_km'>See Surrounding Countries of distance  </label>";
                        html = html + "<input type='number' id='distance_km' name='distance_km'><span style='margin-left:10px;'>km</span>";
                    html = html + "</form>";
                html = html + "</div>"; 
        html = html + "<table class='tbl'>";
        html = html + "<tr>";
          html = html + "<th>Country</th>";
           html = html + "<th>Transmission Rate</th>";
           html = html + "<th>Death Rate</th>";
        html = html + "</tr>";
        for(int i = 0; i <= 6; i++){
            html = html + "<tr>";
            html = html + "<td>COUNTRY</td>";
            html = html + "<td>0</td>";
            html = html + "<td>0</td>";
            html = html + "</tr>";
            }
      html = html + "</table>";

      html = html + "<br class='clear' />";
        html = html + "</div>";   

        html = html + "<div class='clear'></div>";
      html = html + "</div>";     

      html = html + "<div class='container8'>";
        html = html + "<div class='grey'>";
        html = html + "<h4>See Deaths, Infections and Population Ratios for Covid-19</h4>";
        html = html + "</div>";
        html = html + "<table class='tbl'>";
        html = html + "<tr>";
            html = html + "<th>Country</th>";
            html = html + "<th>Infections</th>";
            html = html + "<th>Deaths</th>";
            html = html + "<th>Population</th>";
            html = html + "<th>Infection to Death Ratio</th>";
            html = html + "<th>Infections and Death to population Ratio</th>";
        html = html + "</tr>";
        for(int i = 0; i <= 6; i++){
            html = html + "<tr>";
            html = html + "<td>COUNTRY</td>";
            html = html + "<td>0</td>";
            html = html + "<td>0</td>";
            html = html + "<td>0</td>";
            html = html + "<td>0</td>";
            html = html + "<td>0</td>";
            html = html + "</tr>";
            }
        html = html + "</table>";

        html = html + "<br class='clear' />";

      html = html + "</div>";


        // Look up some information from JDBC
        // First we need to use your JDBCConnection class
        JDBCConnection jdbc = new JDBCConnection();


        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
