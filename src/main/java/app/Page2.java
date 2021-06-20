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
public class Page2 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page2.html";

    @Override
    public void handle(Context context) throws Exception {
        JDBCConnection jdbc = new JDBCConnection();
        int sumCases = jdbc.caseCount();
        int sumDeaths = jdbc.deathCount();
        ArrayList<String> countries = jdbc.getMostCases();
        NumberFormat myFormat = NumberFormat.getInstance();

        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Movies</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        html = html + "<script src='javascript.js'></script>";

        // top banner
        html = html + "<div class='top'>";
        html = html + "<div class='covid'>COVID-19</div>";
        html = html + "</div>";

         html = html + "<div class='topnav'>";
         html = html + "<a href='page1.html'>Home</a>";
         html = html + "<a class='active' href='page2.html'>Page 2</a>";
         html = html + "<a href='page3.html'>Page 3</a>";
         html = html + "<a href='page4.html'>Page 4</a>";
         html = html + "<a href='page5.html?distance_km=1000'>Page 5</a>";
         html = html + "<a href='page6.html?search=&sort_similar=per_mil'>Page 6</a>";
         html = html + "</div>";
         
         html = html + "</head>";
         

        // Add the body
        html = html + "<body>";


        html = html + "<div class=container2>";
            // TOTAL CASES WORLDWIDE
            html = html + "<div class='sumcases'>";
                html = html + "<p>TOTAL GLOBAL CASES</p>";
                html = html + "<h2>" + myFormat.format(sumCases) + " <br>Cases</h2>";
            html = html + "</div>";

            // TOTAL DEATHS WORLDWIDE
            html = html + "<div class='sumdeaths'>";
                html = html + "<p>TOTAL GLOBAL DEATHS</p>";
                html = html + "<h2>" + myFormat.format(sumDeaths) + " <br>Deaths</h2>";
             html = html + "</div>";

             //WORST AFFECTED COUNTRIES BY CASES
             html = html + "<div class='covid_country'>";
                html = html + "<p>TOP 5 COUNTRIES</p>";
                for (String country : countries) {
                    html = html + "<br>";
                    html = html + "<h4>" + country + " - " + myFormat.format(jdbc.getTotalCasesByCountry(country)) + " <br>Cases</h4>";
                    html = html + "<br>";
                }
            html = html + "</div>";

        html = html + "<div class='container3'>";
            html = html + "<img class='bigimg' src='covid.jpeg'></img>";
                 html = html + "<br class='clear' />";
            html = html + "<div class=centered_text>";
            html = html + "<a href='page3.html'>Want to find out<br>more about Covid-19?</a>";
            html = html + "</div>";
        html = html + "</div>";

    html = html + "</div>";

        
        // find out more image


        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
