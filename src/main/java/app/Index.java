package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class Index implements Handler {

    // URL of this page relative to http://localhost:7000/
    public static final String URL = "/index.html";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";


        // Link for each page
        html = html + "<div class='topnav'";
        html = html + "<a class='active' href='index.html'>Home</a>";
        html = html + "<a href='page1.html'>Page 1</a>";
        html = html + "<a href='page2.html'>Page 2</a>";
        html = html + "<a href='page3.html'>Page 3</a>";
        html = html + "<a href='page4.html'>Page 4</a>";
        html = html + "<a href='page5.html'>Page 5</a>";
        html = html + "<a href='page6.html'>Page 6</a>";
        html = html + "</div>";

        // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";

        // Add the body
        html = html + "<body>";

        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
