package io.github.nicepay.service.webview;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import io.github.nicepay.utils.LoggerPrint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WebViewService {

    public static final Logger LOGGER = LoggerFactory.getLogger("[Web View Service]");


    public static void openHtmlInBrowser(String htmlContent) {
        if (htmlContent == null || htmlContent.isEmpty()) {
            LOGGER.info(LoggerPrint.LOG_ERROR, "HTML content is null or empty.");
            return;
        }

        try {
            String encodedHtml = URLEncoder.encode(htmlContent, StandardCharsets.UTF_8)
                    .replace("+", "%20")
                    .replace("*", "%2A")
                    .replace("%7E", "~")
                    .replace("%21", "!")
                    .replace("%27", "'")
                    .replace("%28", "(")
                    .replace("%29", ")")
                    .replace("%2C", ",")
                    .replace("%2F", "/");

            // Create data URI
            String dataUri = "data:text/html;charset=utf-8," + encodedHtml;
            URI data = new URI(dataUri);
            LOGGER.info("uri : {}", dataUri);

            // Open data URI in default browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(data);
            } else {
                LOGGER.info(LoggerPrint.LOG_ERROR, "Desktop or browse action not supported.");
            }
        } catch (IOException e) {
            LOGGER.info(LoggerPrint.LOG_ERROR, "IOException occurred: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.info(LoggerPrint.LOG_ERROR, "Exception occurred: " + e.getMessage());
        }
    }

    public static void serveHtml(String htmlContent, String baseUrl, String txId) {
        try {
            // Parse the base URL to extract the host and port
            URI uri = new URI(baseUrl+"/"+txId);
            String host = uri.getHost();
            int port = uri.getPort() == -1 ? (uri.getScheme().equals("https") ? 443 : 80) : uri.getPort();
            String protocol = uri.getScheme();

            // Create and start the HTTP server
            InetSocketAddress address = new InetSocketAddress(host, port);
            HttpServer server = HttpServer.create(address, 0);
            server.createContext("/"+txId, new HttpHandler() {
                @Override
                public void handle(HttpExchange exchange) throws IOException {
                    exchange.sendResponseHeaders(200, htmlContent.getBytes().length);
                    OutputStream os = exchange.getResponseBody();
                    os.write(htmlContent.getBytes());
                    os.close();
                }
            });
            server.setExecutor(null);
            server.start();
            // Open the URL in the default browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(uri);
            } else {
                System.out.println("Desktop or browse action not supported.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void openHtmlInBrowser(String resClient, String tXid) throws IOException {

        //Save the HTML content to a temporary file

        File tempFile = File.createTempFile("Payment-"+tXid+"-" , ".html");
        FileWriter writer = new FileWriter(tempFile);
        writer.write(resClient);
        writer.close();

        // Open page on browser
        Desktop.getDesktop().browse(tempFile.toURI());

    }
}
