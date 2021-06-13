package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.text.NumberFormat;


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
public class Page4 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page4.html";

    @Override
    public void handle(Context context) throws Exception {
      JDBCConnection jdbc = new JDBCConnection();
      NumberFormat myFormat = NumberFormat.getInstance();
      final String country = context.queryParam("search");
        final String date1 = context.queryParam("date1");
        final String date2 = context.queryParam("date2");
        final String sort = context.queryParam("sort_aus");
        final String date3 = context.queryParam("date3");
        final String date4 = context.queryParam("date4");
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
         html = html + "<a class='active' href='page4.html'>Page 4</a>";
         html = html + "<a href='page5.html'>Page 5</a>";
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
      html = html + "</div>";

      html = html + "<div class='clear'></div>";

      html = html + "<div class='container4'>";
       html = html + "<div class='country_title'>the searched country goes here</div>";
      html = html + "</div>";

      html = html + "<div class='container5'>";

       html = html + "<div class='Tot_infection'>";
       html = html + "<p>Total Deaths</p>";
       html = html + "<h2>" + myFormat.format(jdbc.getTotalDeathsByCountry(country)) + " Deaths</h2>";

       html = html + "</div>";

       html = html + "<div class='infection_date'>";
        html = html + "<p>Total Deaths<br><p>";
        html = html + "<label for='date1'> from </label>";
        html = html + "<input type='date' min='2020-01-01' max='2021-04-30' id='date1' name='date1' data-date-inline-picker='true'>";
        html = html + "<label for='date2'>to </label>";
        html = html + "<input type='date' min='2020-01-01' max='2021-04-30' id='date2' name='date2' data-date-inline-picker='true'>";
        html = html + "<input type='submit' value='Search' class='submit1'>";

        html = html + "<h2>" + myFormat.format(jdbc.getSumDeathsTimePeriod(country, date1, date2)) + " Deaths</h2>";
       html = html + "</form>";
       html = html + "</div>";

       html = html + "<div class='max_infection'>";
       html = html + "<p>Highest Deaths in 1 day</p>";
       html = html + "<h2>" + myFormat.format(jdbc.getHighestDeathTallyByDayState(country)) + " Deaths </h2>";
       html = html + "<h2>" + jdbc.getHighestDeathDay(country) + "</h2";
       
       html = html + "</div>";

       html = html + "<br class='clear' />";

      html = html + "</div>";


      html = html + "<div class='container6'>";
        html = html + "<form class='worst'>";
        html = html + "<label for='sort_ratio'>Sort Data  </label>";
        html = html + "<select name='sort_ratio' id='sort_ratio'>";
        html = html + "<option value='worst'>Worst Affected</option>";
        html = html + "<option value='least'>Least Affected</option>";
        html = html + "</select>";
      html = html + "</form>";
        html = html + "<table class='tbl'>";
        html = html + "<tr>";
          html = html + "<th>State</th>";
          html = html + "<th>Total Deaths/Total infections Ratio</th>";
          // date selector tbl header
            html = html + "<th>";
            html = html + "<form>";
            html = html + "<label for='date3'>Total Deaths/Total infections Ratio</label>";
            html = html + "<input type='date' id='date3' name='date3' data-date-inline-picker='true'>";
            html = html + "<label for='date4'> to </label>";
            html = html + "<input type='date' id='date4' name='date4' data-date-inline-picker='true'>";
            html = html + "</form>";
            html = html + "</th>";

          html = html + "<th>Highest Deaths/Highest infections in 1 Day Ratio</th>";
        html = html + "</tr>";
        html = html + "<tr>";
          html = html + "<td>java forloop display for all countries</td>";
          html = html + "<td></td>";
          html = html + "<td></td>";
          html = html + "<td></td>";
        html = html + "</tr>";
      html = html + "</table>";

      html = html + "<br class='clear' />";
    html = html + "</div>";



        // Look up some information from JDBC
        // First we need to use your JDBCConnection class


        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
