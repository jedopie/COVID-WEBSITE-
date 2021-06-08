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
public class Page2 implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/page2.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Movies</title>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

         html = html + "<div class='topnav'>";
         html = html + "<a href='/'>Home</a>";
         html = html + "<a href='page1.html'>Page 1</a>";
         html = html + "<a class='active' href='page2.html'>Page 2</a>";
         html = html + "<a href='page3.html'>Page 3</a>";
         html = html + "<a href='page4.html'>Page 4</a>";
         html = html + "<a href='page5.html'>Page 5</a>";
         html = html + "<a href='page6.html'>Page 6</a>";
         html = html + "</div>";
         
         html = html + "</head>";
         

        // Add the body
        html = html + "<body>";

        html = html + "<div class='container2'>";
            
        html = html + "</div>";

        html = html + "<div class='container3'>";
            html = html + "<img class='bigimg' src='covid.jpeg'></img>";
            html = html + "<div class=centered_text><a href=page1.html>Want to find out <br> more about covid-19?</a></div>";
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
