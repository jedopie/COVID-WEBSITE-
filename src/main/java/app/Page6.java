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
public class Page6 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page6.html";

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
         html = html + "<a href='page5.html'>Page 5</a>";
         html = html + "<a class='active' href='page6.html'>Page 6</a>";
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
            html = html + "<h4>See Covid-19 data from countries facing similar impacts</h4>";
            html = html + "</div>";

            html = html + "<div class='filters'>";
            html = html + "<form class='worst'>";
            html = html + "<label for='sort_similar'>See Similar Countries based on </label>";
            html = html + "<select name='sort_similar' id='sort_similar'>";
            html = html + "<option value='per_mil'>Total infections/1 million people</option>";
            html = html + "<option value='death_inf_ratio'>Deaths to infections Ratio</option>";
            html = html + "<option value='max_deaths'>Maximum daily deaths</option>";
            html = html + "<option value='max_infection'>Maximum daily infections</option>";
            html = html + "</select>";
        html = html + "</form>";
    html = html + "</div>"; 

            html = html + "<table class='tbl'>";
            html = html + "<tr>";
                html = html + "<th>Country</th>";
                html = html + "<th>Total Infections</th>";
                html = html + "<th>Infections/1 million people</th>";
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
                html = html + "<h4>See Covid data from American States facing similar impacts</h4>";
                html = html + "</div>";
 
                html = html + "<div class='US_SEARCH'>";
                    html = html + "<form>";
                    html = html + "<div class='centered_div'>";
                    html = html + "<input type='text' id='search_US' name='search_US' placeholder='Search for a US State...'>";
                    html = html + "<input type='submit' value='Search' class='submit1'>";
                    html = html + "</div>";
                    html = html + "</form>";
                html = html + "</div>";

                html = html + "<div class='filters'>";
                        html = html + "<form class='worst'>";
                        html = html + "<label for='sort_similar'>See Similar US States based on </label>";
                        html = html + "<select name='sort_similar2' id='sort_similar2'>";
                        html = html + "<option value='per_mil2'>Total infections/1 million people</option>";
                        html = html + "<option value='death_inf_ratio2'>Deaths to infections Ratio</option>";
                        html = html + "<option value='max_deaths2'>Maximum daily deaths</option>";
                        html = html + "<option value='max_infection2'>Maximum daily infections</option>";
                        html = html + "</select>";
                    html = html + "</form>";
                html = html + "</div>"; 
        html = html + "<table class='tbl'>";
        html = html + "<tr>";
          html = html + "<th>Country</th>";
           html = html + "<th>Total Infections</th>";
           html = html + "<th>Infections/1 million people</th>";
        html = html + "</tr>";
        for(int i = 0; i <= 5; i++){
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
        html = html + "<h4>See Covid data from countries facing similar impacts to your US State</h4>";
        html = html + "</div>";
        html = html + "<div class='filters'>";
        html = html + "<form class='worst'>";
        html = html + "<label for='sort_similar'>See Similar Countries to your US State based on </label>";
        html = html + "<select name='sort_similar2' id='sort_similar2'>";
        html = html + "<option value='per_mil2'>Total infections/1 million people</option>";
        html = html + "<option value='death_inf_ratio2'>Deaths to infections Ratio</option>";
        html = html + "<option value='max_deaths2'>Maximum daily deaths</option>";
        html = html + "<option value='max_infection2'>Maximum daily infections</option>";
        html = html + "</select>";
    html = html + "</form>";
html = html + "</div>"; 
        html = html + "<table class='tbl'>";
        html = html + "<tr>";
            html = html + "<th>Country</th>";
           html = html + "<th>Total Infections</th>";
           html = html + "<th>Infections/1 million people</th>";
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
