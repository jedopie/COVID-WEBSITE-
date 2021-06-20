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
public class Page1 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page1.html";

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

        // top banner
        html = html + "<div class='top'>";
        html = html + "<div class='covid'>COVID-19</div>";
        html = html + "</div>";


         // Link for each page
         html = html + "<div class='topnav'>";
         html = html + "<a class='active' href='page1.html'>Home</a>";
         html = html + "<a href='page2.html'>Page 2</a>";
         html = html + "<a href='page3.html'>Page 3</a>";
         html = html + "<a href='page4.html'>Page 4</a>";
         html = html + "<a href='page5.html?distance_km=1000'>Page 5</a>";
         html = html + "<a href='page6.html?search=&sort_similar=per_mil'>Page 6</a>";
         html = html + "</div>";
         

         html = html + "</head>";

        // Add the body
        html = html + "<body>";

        html = html + "<div class='clear'></div>";

        // divs for layout
        html = html + "<div class='container1'>";
            html = html + "<img class='floatleft1' src='90.jpeg'>";

            html = html + "<div class='floatright1'>";
            html = html + "<ul>";
            html = html + "<li><a class='active' href='page1.html'>Home </a></li>";
            html = html + "<li><a href='page2.html'>COVID-19: Whats happening around the world?</a></li>";
            html = html + "<li><a href='page3.html'>Want to see more about Covid-19 infections?</a></li>";
            html = html + "<li><a href='page4.html'>Want to see more about Covid-19 Deaths?</a></li>";
            html = html + "<li><a href='page5.html'>See Cumulative Covid data from similar countries</a></li>";
            html = html + "<li><a href='page6.html'>Explore our data set with advanced search tools</a></li>";
            html = html + "</ul>";
            html = html + "</div>";

        html = html + "</div>";

        html = html + "<div class='clear'></div>";

        html = html + "<div class='wrapper'>";
            html = html + "<div class='left'>";
                html = html + "<h2>How do I use this<br>website?</h2>";
                html = html + "<p>Using the navigation above, find the page in which youd like to explore and press on it.";
                html = html + "After being redirected read all headings to find out what each statistic represents. Explore even further into our data by manipulating it using our tools provided on each page. </p>";
                
            html = html + "</div>";

            html = html + "<div class='middle'>";
            html = html + "<h2><a class='titlehelp' href='page1.html'>Need more<br>Help?</a></h2>";
            html = html + "<p>Need more Help? Well click <a class='help'href='page1.html'>'Help'</a> here and we'll take you through a short video on how to explore the website to get the most out of it. Listen to the intsrcutions carefully to ensure we can further your education regarding Covid-19</p>";
            html = html + "</div>";



            html = html + "<div class='right'>";
            html = html + "<h2>What can I explore about <br>Covid-19?</h2>";
            html = html + "<p>There is much to explore using our wide range of data. explore the impacts of Covid-19 using our tools such as set the time periods of the data, .";
            html = html + "see similar countries based on your preferred data, multiple ratios and more. For users wanting an overview, explore the first 4 pages in the menu's, however to dive deeper, explore the last 2 pages in the menu's</p>";

            html = html + "</div>";
        html = html + "</div>";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
