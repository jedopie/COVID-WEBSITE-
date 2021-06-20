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
         html = html + "<a href='page2.html'>Overview</a>";
         html = html + "<a href='page3.html'>Infection data</a>";
         html = html + "<a href='page4.html'>Death data</a>";
         html = html + "<a href='page5.html?distance_km=1000'>Similar Countries</a>";
         html = html + "<a href='page6.html?search=&sort_similar=per_mil&search_US=&sort_similar2=per_mil2&sort_similar3=max_infection2'>Similar impacts</a>";
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
            html = html + "<li><a href='page5.html?distance_km=1000'>See Covid data from similar located countries</a></li>";
            html = html + "<li><a href='page6.html?search=&sort_similar=per_mil&search_US=&sort_similar2=per_mil2&sort_similar3=max_infection2'>Explore countries facing similar impacts</a></li>";
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
            html = html + "<p>Need more Help? Well click <a class='help'href='#desc'>'Help'</a> here and we'll take you below to an outline of each page and what you can explore about Covid-19 to ensure you get the most about our website and gather the information about Covid-19 you intended to. It all starts with finding the right page so have a look through the descriptions and see what suits you.</p>";
            html = html + "</div>";



            html = html + "<div class='right'>";
            html = html + "<h2>What can I explore about <br>Covid-19?</h2>";
            html = html + "<p>There is much to explore using our wide range of data. explore the impacts of Covid-19 using our tools such as set the time periods of the data, .";
            html = html + "see similar countries based on your preferred data, multiple ratios and more. For users wanting an overview, explore the first 4 pages in the menu's, however to dive deeper, explore the last 2 pages in the menu's.</p>";

            html = html + "</div>";
        html = html + "</div>";

        html = html + "<div class='question'>";
        html = html + "<h1>Don't know which page your<br>looking for?</h1>";
        html = html + "<p>Below is a description of each to help you find the right page as quick as possible!</p>";
        html = html + "</div>";

        html = html + "<div class='page_desc'>";

            html = html + "<div class='desc'>";
                html = html + "<h2>An Overview of Covid-19</h2>";
                html = html + "<p>The second page of this website is for our users to have a vast look at the main facts of covid 19 such as the total global cases, deaths and 5 worst affected countries. To have a look at this overview click <a href='page2.html'>'here'</a></p>";
            html = html + "</div>";

            html = html + "<div class='desc'>";
                html = html + "<h2>Statistics on Covid-19 Infections</h2>";
                html = html + "<p>The third page of this website is for our users to delve deeper into the infection statistics of Covid-19. With a simple search of your country you can find the total infections over your set time period, see maximum infections in a day, see the infections states of the searched country and more. To explore further click<a href='page3.html'>'here'</a> </p>";
                html = html + "</div>";

            html = html + "<div class='desc'>";
                html = html + "<h2>Statistics on Covid-19 Deaths</h2>";
                html = html + "<p>The third page of this website is for our users to delve deeper into the Death statistics of Covid-19. With a simple search of your country you can find the total deaths over your set time period, see maximum deaths in a day, see the death ratios of states of the searched country and more. To explore further click<a href='page4.html'>'here'</a> </p>";
            html = html + "</div>";

            html = html + "<div class='desc'>";
                html = html + "<h2>Covid-19 Data from similar Countries</h2>";
                html = html + "<p>The fourth page of this website is for our users to explore Covid-19 data from similar countries of your search, based on their climates or countries in close proximities. You can also see the population, death and infection ratios for Covid-19 in these similar countries. To explore further click<a href='page5.html'>'here'</a></p>";
                 html = html + "</div>";

            html = html + "<div class='last_desc'>";
                html = html + "<h2>Covid-19 Data from countries facing similar impacts</h2>";
                html = html + "<p>The final page of this website is for our users to to explore Covid-19 data from countries facing similar impacts from Covid-19 to the country you searched. These similarly impacted countries can be based off the impact of your choice such as infections per million people, Death ratio's or maximum daily deaths and infections. You also have the option to find Similar US States to a US state of your search. To explore further click<a href='page6.html'>'here'</a></p>";
                html = html + "</div>";
        html = html + "</div>";
        html = html + "<div id='desc'></div>";



        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
