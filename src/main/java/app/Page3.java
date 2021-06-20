package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.text.NumberFormat;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.Date;

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
public class Page3 implements Handler {


    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page3.html";

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
        final String ausButton = context.queryParam("ausbutton");
        ArrayList<String> states = jdbc.getStatesByCountry(country);
        DecimalFormat df = new DecimalFormat("#.####");





        System.out.println(country);

        System.out.println(context.queryParamMap());

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
        html = html + "<a class='active' href='page3.html'>Infection data</a>";
        html = html + "<a href='page4.html'>Death data</a>";
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
          html = html + "<p>Total Infections</p>";
        }
        else if (jdbc.getTotalCasesByCountry(country) < 1) {
          html = html + "<div class='Tot_infection'>";
          html = html + "<p>Total Infections</p>";
          html = html + "<h2>COUNTRY COULD NOT BE FOUND</h2>";
        }
        else {

         html = html + "<div class='Tot_infection'>";
         html = html + "<p>Total Infections</p>";
         html = html + "<h2>" + myFormat.format(jdbc.getTotalCasesByCountry(country)) + " Cases</h2>";
        }

         html = html + "</div>";

         html = html + "<div class='infection_date'>";
          html = html + "<p>Total Infections<br><p>";
          html = html + "<input type='date' min='2020-01-01' max='2021-04-30' value='2020-01-01' id='date1' name='date1' data-date-inline-picker='true'>";
          html = html + "<label for='date2'>to </label>";
          html = html + "<input type='date' min='2020-01-01' max='2021-04-30' id='date2' name='date2' data-date-inline-picker='true'>";
          html = html + "<input type='submit' id='submit2' value='Go' class='submit2'>";
         if (country==null) {
          html = html + "<h2></h2>";

         }
         else if (date2.equals("")) {
          html = html + "<h2></h2>";
         }
         else {
         html = html + "<h2>" + myFormat.format(jdbc.getSumCasesTimePeriod(country, date1, date2)) + " Cases</h2>";
         }
         html = html + "</div>";

         html = html + "<div class='max_infection'>";

         if (country==null) {
          html = html + "<p>Highest Infections in 1 Day</p>";
         }
         else if  (jdbc.getHighestCaseTallyByDay(country) <1) {
          html = html + "<p>Highest Infections in 1 Day</p>";
          html = html + "<h2>COUNTRY COULD NOT BE FOUND</h2>";

         }
         else if (jdbc.getHighestCaseTallyByDay(country) <2){
          html = html + "<p>Highest Infections in 1 Day</p>";
          html = html + "<h2>" + myFormat.format(jdbc.getHighestCaseTallyByDay(country)) + " Case</h2>";
          html = html + "<h2>" + jdbc.getHighestCaseDay(country) + "</h2>";
          }
         else {
         html = html + "<p>Highest Infections in 1 Day</p>";
         html = html + "<h2>" + myFormat.format(jdbc.getHighestCaseTallyByDay(country)) + " Cases</h2>";
         html = html + "<h2>" + jdbc.getHighestCaseDay(country) + "</h2>";
         }
         
         html = html + "</div>";

         html = html + "<br class='clear' />";
         html = html + "</div>";


         html = html + "<div class='question'>";
        html = html + "<h1>State Infection Statistics</h1>";
        html = html + "<p>See below the infection statistics for each state including Total infections, highest infections in a day<br> and total infections over a time period. Use the calendar provided to choose these dates</p>";
        html = html + "</div>";

        html = html + "<div class='container6'>";
            html = html + "<form class='worst'>";
            html = html + "<label for='sort_aus'>Sort Data  </label>";
            html = html + "<select name='sort_aus' id='sort_aus'>";
            html = html + "<option value='worst'>Worst Affected</option>";
            html = html + "<option value='least'>Least Affected</option>";
            html = html + "</select>";
            html = html + "<input type='submit' value='Go' class='submit2'>";

          html = html + "<table class='tbl'>";
          html = html + "<tr>";
            html = html + "<th>State</th>";
            html = html + "<th>Total Infections</th>";
            // date selector tbl header
              html = html + "<th>";
              html = html + "<form>";
              html = html + "<label for='date3'>Total Infections from </label>";
              html = html + "<input type='date' min='2020-01-01' max='2021-04-30' id='date3' name='date3' data-date-inline-picker='true'>";
              html = html + "<label for='date4'> to </label>";
              html = html + "<input type='date' min='2020-01-01' max='2021-04-30' id='date4' name='date4' data-date-inline-picker='true'>";
              html = html + "<input type='submit' value='Go' class='submit3'>";
              html = html + "</th>";

          html = html + "<th>Highest Infections in 1 day</th>";
          html = html + "</tr>";
          for (String state : states) {

            html = html + "<tr>";
              html = html + "<td>" + state + " </td>";
              html = html + "<td>" + myFormat.format(jdbc.getTotalCasesByState(state)) + "</td>";
              html = html + "<td>" + myFormat.format(jdbc.getSumCasesTimePeriodByState(state, date3, date4)) + "</td>";
              html = html + "<td>" + myFormat.format(jdbc.getHighestCaseTallyByDayState(state)) + " ON " + jdbc.getHighestCaseDayByState(state) + "</td>";
          
            html = html + "</tr>";
            }
        html = html + "</table>";

        html = html + "<br class='clear' />";
      html = html + "</div>";
           

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
