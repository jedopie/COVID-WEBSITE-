package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.text.NumberFormat;
import java.text.DecimalFormat;



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
        DecimalFormat df = new DecimalFormat("#.####");


        ArrayList<String> states = jdbc.getStatesByCountry(country);


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
        html = html + "<a href='page1.html'>Home</a>";
        html = html + "<a href='page2.html'>Overview</a>";
        html = html + "<a href='page3.html'>Infection data</a>";
        html = html + "<a class='active' href='page4.html'>Death data</a>";
        html = html + "<a href='page5.html?distance_km=1000'>Similar Countries</a>";
        html = html + "<a href='page6.html?search=&sort_similar=per_mil&search_US=&sort_similar2=per_mil2&sort_similar3=max_infection2'>Similar impacts</a>";
        html = html + "</div>";

        // Add the body
        html = html + "<body>";

        if (country == null) {
          html = html + "<div class='search_container'>";
          html = html + "<form>";
            html = html + "<div class='centered_div'>";
            html = html + "<input type='text' id='search' name='search' placeholder='Search for a Country...'>";
            html = html + "<input type='submit' id='submit1' value='Search' class='submit1'>";
            html = html + "</div>";
        html = html + "</div>";
        }
        else{
  
          html = html + "<div class='search_container'>";
            html = html + "<form>";
              html = html + "<div class='centered_div'>";
              html = html + "<input type='text' id='search' name='search' value='"+country+"' placeholder='Search for a Country...'>";
              html = html + "<input type='submit' id='submit1' value='Search' class='submit1'>";
              html = html + "</div>";
          html = html + "</div>";
        }
      html = html + "<div class='clear'></div>";

      if (country == null) {
        html = html + "<div class='container4'>";
        html = html + "<div class='country_title' id='countryID'>Please Enter a Country</div>";
       html = html + "</div>";
       }
       else {
        html = html + "<div class='container4'>";
         html = html + "<div class='country_title' id='countryID'>" + country.toUpperCase() + "</div>";
        html = html + "</div>";
       }

      html = html + "<div class='container5'>";
       if (country == null) {
       html = html + "<div class='Tot_infection'>";
       html = html + "<p>Total Deaths</p>";
       }
       else if (jdbc.getTotalDeathsByCountry(country) < 1) {
        html = html + "<div class='Tot_infection'>";
        html = html + "<p>Total Deaths</p>";
        html = html + "<h2>NO DATA FOUND</h2>";
       }
       else {
        html = html + "<div class='Tot_infection'>";
        html = html + "<p>Total Deaths</p>";
        html = html + "<h2>" + myFormat.format(jdbc.getTotalDeathsByCountry(country)) + " Deaths</h2>";
       }

       html = html + "</div>";

       html = html + "<div class='infection_date'>";
        html = html + "<p>Total Deaths<br><p>";
        html = html + "<input type='date' min='2020-01-01' max='2021-04-30' value='2020-01-01'id='date1' name='date1' data-date-inline-picker='true'>";
        html = html + "<label for='date2'>to </label>";
        html = html + "<input type='date' min='2020-01-01' max='2021-04-30' id='date2' name='date2' data-date-inline-picker='true'>";
        html = html + "<input type='submit' value='Go' class='submit1'>";
       if (country == null) {
       html = html + "</div>";
       }
       else if (date2.equals("")){
        html = html + "</div>";
       }
       else {
        html = html + "<h2>" + myFormat.format(jdbc.getSumDeathsTimePeriod(country, date1, date2)) + " Deaths</h2>";
        html = html + "</div>";
       }
       if (country == null) {
       html = html + "<div class='max_infection'>";
       html = html + "<p>Highest Deaths in 1 day</p>";
       }
       else if (jdbc.getHighestCaseTallyByDay(country) < 1) {
        html = html + "<div class='max_infection'>";
        html = html + "<p>Highest Deaths in 1 day</p>";
        html = html + "<h2> NO DATA FOUND </h2>";
       }
       else {
        html = html + "<div class='max_infection'>";
        html = html + "<p>Highest Deaths in 1 day</p>";
        html = html + "<h2>" + myFormat.format(jdbc.getHighestCaseTallyByDay(country)) + " Deaths </h2>";
        html = html + "<h2>" + jdbc.getHighestDeathDay(country) + "</h2";
       }
       
       html = html + "</div>";

       html = html + "<br class='clear' />";

      html = html + "</div>";

      html = html + "<div class='question'>";
      html = html + "<h1>State Death Statistics</h1>";
      html = html + "<p>See below the Death statistics for each state including the total deaths to infection ratios <br>and the option to choose a time period for which this ratio occured, and the highest of this ratio in 1 day</p>";
      html = html + "</div>";


      html = html + "<div class='container6'>";
        html = html + "<form class='worst'>";
        html = html + "<label for='sort_ratio'>Sort Data  </label>";
        html = html + "<select name='sort_ratio' id='sort_ratio'>";
        html = html + "<option value='worst'>Worst Affected</option>";
        html = html + "<option value='least'>Least Affected</option>";
        html = html + "</select>";
        html = html + "<input type='submit' value='Go' class='submit1'>";
        html = html + "<table class='tbl'>";
        html = html + "<tr>";
          html = html + "<th>State</th>";
          html = html + "<th>Total Deaths/Total infections Ratio</th>";
          // date selector tbl header
            html = html + "<th>";
            html = html + "<form>";
            html = html + "<label for='date3'>Total Deaths/Total infections Ratio</label>";
            html = html + "<input type='date' id='date3'min=' 2020-01-01' max='2021-04-30' value='2020-01-01' name='date3' data-date-inline-picker='true'>";
            html = html + "<label for='date4'> to </label>";
            html = html + "<input type='date' min='2020-01-01' max='2021-04-30' id='date4' name='date4' data-date-inline-picker='true'>";
            html = html + "<input type='submit' value='Go' class='submit1'>";
            html = html + "</form>";
            html = html + "</th>";

          html = html + "<th>Highest Deaths/Highest infections in 1 Day Ratio</th>";
        html = html + "</tr>";
        for (String state : states) {

        html = html + "<tr>";
          html = html + "<td>" + state + " </td>";
          html = html + "<td>" + df.format(jdbc.getDeathInfectionRatioByState(state)) + "</td>";
          html = html + "<td>" + df.format(jdbc.getDeathInfectionRatioByStateTimePeriod(state, date3, date4)) + " Deaths Per Case</td>";
          html = html + "<td>" + df.format(jdbc.getHighestDeathInfectionRatioByState(state)) + " ON " + jdbc.getHighestDeathInfectionRatioDayByState(state) + "</td>";
      
        html = html + "</tr>";
        }
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
