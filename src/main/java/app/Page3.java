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
         html = html + "<a href='/'>Home</a>";
         html = html + "<a href='page1.html'>Page 1</a>";
         html = html + "<a href='page2.html'>Page 2</a>";
         html = html + "<a class='active' href='page3.html'>Page 3</a>";
         html = html + "<a href='page4.html'>Page 4</a>";
         html = html + "<a href='page5.html'>Page 5</a>";
         html = html + "<a href='page6.html'>Page 6</a>";
         html = html + "</div>";
        // Add the body
        html = html + "<body>";


        html = html + "<div class='search_container'>";
          html = html + "<form>";
            html = html + "<div class='centered_div'>";
            html = html + "<input type='text' id='search' name='search' value='"+country+"' placeholder='Search for a Country...'>";
            html = html + "<input type='submit' id='submit1' value='Search' class='submit1'>";
            html = html + "</div>";
        html = html + "</div>";
        
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

        if (jdbc.getTotalCasesByCountry(country) < 1) {
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
          html = html + "<label for='date1'> from </label>";
          html = html + "<input type='date' min='2020-01-01' max='2021-04-30' value='2020-01-01' id='date1' name='date1' data-date-inline-picker='true'>";
          html = html + "<label for='date2'>to </label>";
          html = html + "<input type='date' min='2020-01-01' max='2021-04-30' id='date2' name='date2' data-date-inline-picker='true'>";
          html = html + "<input type='submit' id='submit2' value='Search' class='submit2'>";
         html = html + "</form>";
         html = html + "<h2>" + myFormat.format(jdbc.getSumCasesTimePeriod(country, date1, date2)) + " Cases</h2>";
         html = html + "</div>";

         if  (jdbc.getHighestCaseTallyByDay(country) <1) {
          html = html + "<div class='max_infection'>";
          html = html + "<p>Highest Infections in 1 Day</p>";
          html = html + "<h2>COUNTRY COULD NOT BE FOUND</h2>";

         }
         else if (jdbc.getHighestCaseTallyByDay(country) <2){
          html = html + "<div class='max_infection'>";
          html = html + "<p>Highest Infections in 1 Day</p>";
          html = html + "<h2>" + myFormat.format(jdbc.getHighestCaseTallyByDay(country)) + " Case</h2>";
          html = html + "<h2>" + jdbc.getHighestCaseDay(country) + "</h2>";
          }
         else {
         html = html + "<div class='max_infection'>";
         html = html + "<p>Highest Infections in 1 Day</p>";
         html = html + "<h2>" + myFormat.format(jdbc.getHighestCaseTallyByDay(country)) + " Cases</h2>";
         html = html + "<h2>" + jdbc.getHighestCaseDay(country) + "</h2>";
         }
         
         html = html + "</div>";

         html = html + "<br class='clear' />";

        html = html + "</div>";

        html = html + "<div class='button_cont'>";
        html = html + "<form>";
        html = html + "<div class='center_button'>";
          html = html + "<button class='btn_block' name='search' value='australia' type='submit'>Australian States</button>";
          html = html + "</div>";
        html = html + "</form>";
        html = html + "<br class='clear' />";
        html = html + "</div>";


        html = html + "<div class='container6'>";
            html = html + "<form class='worst'>";
            html = html + "<label for='sort_aus'>Sort Data  </label>";
            html = html + "<select name='sort_aus' id='sort_aus'>";
            html = html + "<option value='worst'>Worst Affected</option>";
            html = html + "<option value='least'>Least Affected</option>";
            html = html + "</select>";
            html = html + "<input type='submit' value='Search' class='submit2'>";
            html = html + "</form>";

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
              html = html + "<input type='submit' value='Search' class='submit3'>";
              html = html + "</form>";
              html = html + "</th>";

          html = html + "<th>Highest Infections in 1 day</th>";
          html = html + "</tr>";
          html = html + "<tr>";
            html = html + "<td>VIC</td>";
            html = html + "<td>" + myFormat.format(jdbc.getTotalCasesByState("Victoria")) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getSumCasesTimePeriodByState("Victoria", date3, date4)) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getHighestCaseTallyByDayState("Victoria")) + " Cases ON " + jdbc.getHighestCaseDayByState("Australian Capital Territory") + "</td>";
          html = html + "</tr>";
          html = html + "<tr>";
            html = html + "<td>NSW</td>";
            html = html + "<td>" + myFormat.format(jdbc.getTotalCasesByState("New South Wales")) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getSumCasesTimePeriodByState("New South Wales", date3, date4)) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getHighestCaseTallyByDayState("New South Wales")) + " Cases ON " + jdbc.getHighestCaseDayByState("Australian Capital Territory") + "</td>";
          html = html + "</tr>";
          html = html + "<tr>";
            html = html + "<td>QLD</td>";
            html = html + "<td>" + myFormat.format(jdbc.getTotalCasesByState("Queensland")) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getSumCasesTimePeriodByState("Queensland", date3, date4)) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getHighestCaseTallyByDayState("Queensland")) + " Cases ON " + jdbc.getHighestCaseDayByState("Australian Capital Territory") + "</td>";
          html = html + "</tr>";
          html = html + "<tr>";
            html = html + "<td>WA</td>";
            html = html + "<td>" + myFormat.format(jdbc.getTotalCasesByState("Western Australia")) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getSumCasesTimePeriodByState("Western Australia", date3, date4)) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getHighestCaseTallyByDayState("Western Australia")) + " Cases ON " + jdbc.getHighestCaseDayByState("Australian Capital Territory") + "</td>";
          html = html + "</tr>";
          html = html + "<tr>";
            html = html + "<td>SA</td>";
            html = html + "<td>" + myFormat.format(jdbc.getTotalCasesByState("South Australia")) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getSumCasesTimePeriodByState("South Australia", date3, date4)) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getHighestCaseTallyByDayState("South Australia")) + " Cases ON " + jdbc.getHighestCaseDayByState("Australian Capital Territory") + "</td>";
          html = html + "</tr>";
          html = html + "<tr>";
            html = html + "<td>TAS</td>";
            html = html + "<td>" + myFormat.format(jdbc.getTotalCasesByState("Tasmania")) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getSumCasesTimePeriodByState("Tasmania", date3, date4)) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getHighestCaseTallyByDayState("Tasmania")) + " Cases ON " + jdbc.getHighestCaseDayByState("Australian Capital Territory") + "</td>";
          html = html + "</tr>";
          html = html + "<tr>";
            html = html + "<td>NT</td>";
            html = html + "<td>" + myFormat.format(jdbc.getTotalCasesByState("Northern Territory")) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getSumCasesTimePeriodByState("Northern Territory", date3, date4)) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getHighestCaseTallyByDayState("Northern Territory")) + " Cases ON " + jdbc.getHighestCaseDayByState("Australian Capital Territory") + "</td>";
            html = html + "<tr>";
            html = html + "<td>ACT</td>";
            html = html + "<td>" + myFormat.format(jdbc.getTotalCasesByState("Australian Capital Territory")) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getSumCasesTimePeriodByState("Australian Capital Territory", date3, date4)) + "</td>";
            html = html + "<td>" + myFormat.format(jdbc.getHighestCaseTallyByDayState("Australian Capital Territory")) + " Cases ON " + jdbc.getHighestCaseDayByState("Australian Capital Territory") + "</td>";
          html = html + "</tr>";
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
