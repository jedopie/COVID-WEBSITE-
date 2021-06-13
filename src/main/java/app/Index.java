package app;

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
    public static final String URL = "/";

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        String html = "<html>";

        // Add some Header information
        html = html + "<head>" + 
               "<title>Homepage</title>";
               // Add some CSS (external file)
        html = html + "<link rel='stylesheet' type='text/css' href='common.css' />";
        // top banner
        html = html + "<div class='top'>";
        html = html + "<div class='covid'>COVID-19</div>";
        html = html + "</div>";


        // Link for each page
        html = html + "<div class='topnav'>";
        html = html + "<a href = \"/\"><img src=\"GLOBE Logo.png\" style = \"width: 5vh;\"></a>";
        html = html + "<a class='active' href='/'>Home</a>";
        html = html + "<a href='page1.html'>Page 1</a>";
        html = html + "<a href='page2.html'>Page 2</a>";
        html = html + "<a href='page3.html'>Page 3</a>";
        html = html + "<a href='page4.html'>Page 4</a>";
        html = html + "<a href='page5.html'>Page 5</a>";
        html = html + "<a href='page6.html'>Page 6</a>";
        html = html + "</div>";

        // Add the body
        html = html + "<body>";
        html = html + "<div class='covidbanner'>";
        html = html + "<img class='covidbannerpic' src='covid banner.jpeg' style = 'width:100%'></img>";
        html = html + "<div class='covid19head'>COVID-19</div>";
        html = html + "</div>";
        html = html + "</div>";
        
        html = html + "<div class='infobanner1'>";
        html = html + "<img class = 'banner1pic' src='Vaccine Background.jpeg'></img";
        html = html + "<div class='simplehead'>Broad Statistics</div>";
        html = html + "</div>";
        html = html + "</div>";

        html = html + "<div class='infobanner2'>";
        html = html + "<img class = 'banner2pic' src='Vaccine Background.jpeg'></img";
        html = html + "<div class = 'detailstats'>Detailed Statistics</div>";
        html = html + "</div>";
        html = html + "</div>";

        html = html + "<div class = 'infobanner3'>";
        html = html + "<img class = 'banner3pic' src='Vaccine Background.jpeg'></img";
        html = html + "<div class='maphead'>Interactive Map</div>";
        html = html + "</div>";

        html = html + "<div class = 'textboxleft'>";
        html = html + "<p> covid is scary </p>";
        html = html + "</div>";

        html = html + "<div class = 'textboxmiddle'>";
        html = html + "<p> covid is scary </p>";
        html = html + "</div>";

        html = html + "<div class = 'textboxright'>";
        html = html + "<p> covid is scary </p>";
        html = html + "</div>";
        html += "<form action='/shopping-cart' method='post'>";
		html += "<input name='secret' type='hidden' value='my hidden value'></input>";
		html += "<input name='product-id' type='hidden' value='1'></input>";
		html += "<input type='submit' value='Add this product to your cart'></input> </form>";



        // Finish the HTML webpage
        html = html + "</body>" + "</html>";


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.html(html);
    }

}
