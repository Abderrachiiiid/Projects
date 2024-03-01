import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to convert an XML RSS (version 2.0) feed from a given URL into the
 * corresponding HTML output file.
 *
 * @author Abderrachid Sghir
 *
 */
public final class RSSAggregator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private RSSAggregator() {
    }

    /**
     * Outputs the "opening" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * <html> <head> <title>the channel tag title as the page title</title>
     * </head> <body>
     * <h1>the page title inside a link to the <channel> link</h1>
     * <p>
     * the channel description
     * </p>
     * <table border="1">
     * <tr>
     * <th>Date</th>
     * <th>Source</th>
     * <th>News</th>
     * </tr>
     *
     * @param channel
     *            the channel element XMLTree
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the root of channel is a <channel> tag] and out.is_open
     * @ensures out.content = #out.content * [the HTML "opening" tags]
     */
    private static void outputHeader(XMLTree channel, SimpleWriter out) {
        assert channel != null : "Violation of: channel is not null";
        assert out != null : "Violation of: out is not null";
        assert channel.isTag() && channel.label().equals("channel") : ""
                + "Violation of: the label root of channel is a <channel> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<html>");
        out.println("<head>");
        int title = getChildElement(channel, "title");
        if (channel.child(title).numberOfChildren() >= 1) {
            out.println("<title>" + channel.child(title).child(0).label()
                    + "</title>");
            out.println("</head>");
            out.println("<body>");
            int link = getChildElement(channel, "link");
            out.println("<h1><a href=\"" + channel.child(link).child(0).label()
                    + "\">" + channel.child(title).child(0).label()
                    + "</a></h1>");
        } else {
            out.println("<title>NO TITLE</title>");
            out.println("</head>");
            out.println("<body>");
            int link = getChildElement(channel, "link");
            out.println("<h1><a href=\"" + channel.child(link).child(0).label()
                    + "\">NO TITLE</a></h1>");
        }
        int description = getChildElement(channel, "description");
        if (channel.child(description).numberOfChildren() >= 1) {
            out.println("<p>" + channel.child(description).child(0).label()
                    + "</p>");
        } else {
            out.println("<title>NO DESCRIPTION</title>");
        }
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>Date</th>");
        out.println("<th>Source</th>");
        out.println("<th>News</th>");
        out.println("</tr>");
    }

    /**
     * Outputs the "closing" tags in the generated HTML file. These are the
     * expected elements generated by this method:
     *
     * </table>
     * </body> </html>
     *
     * @param out
     *            the output stream
     * @updates out.contents
     * @requires out.is_open
     * @ensures out.content = #out.content * [the HTML "closing" tags]
     */
    private static void outputFooter(SimpleWriter out) {
        assert out != null : "Violation of: out is not null";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }

    /**
     * Finds the first occurrence of the given tag among the children of the
     * given {@code XMLTree} and return its index; returns -1 if not found.
     *
     * @param xml
     *            the {@code XMLTree} to search
     * @param tag
     *            the tag to look for
     * @return the index of the first child of type tag of the {@code XMLTree}
     *         or -1 if not found
     * @requires [the label of the root of xml is a tag]
     * @ensures <pre>
     * getChildElement =
     *  [the index of the first child of type tag of the {@code XMLTree} or
     *   -1 if not found]
     * </pre>
     */
    private static int getChildElement(XMLTree xml, String tag) {
        assert xml != null : "Violation of: xml is not null";
        assert tag != null : "Violation of: tag is not null";
        assert xml.isTag() : "Violation of: the label root of xml is a tag";

        int total = -1;
        for (int i = 0; i < xml.numberOfChildren() && total == -1; i++) {
            if (xml.child(i).isTag() && xml.child(i).label().equals(tag)) {
                total = i;
            }
        }
        return total;
    }

    /**
     * Processes one news item and outputs one table row. The row contains three
     * elements: the publication date, the source, and the title (or
     * description) of the item.
     *
     * @param item
     *            the news item
     * @param out
     *            the output stream
     * @updates out.content
     * @requires [the label of the root of item is an <item> tag] and
     *           out.is_open
     * @ensures <pre>
     * out.content = #out.content *
     *   [an HTML table row with publication date, source, and title of news item]
     * </pre>
     */
    private static void processItem(XMLTree item, SimpleWriter out) {
        assert item != null : "Violation of: item is not null";
        assert out != null : "Violation of: out is not null";
        assert item.isTag() && item.label().equals("item") : ""
                + "Violation of: the label root of item is an <item> tag";
        assert out.isOpen() : "Violation of: out.is_open";

        out.println("<tr>");
        int pubDate = getChildElement(item, "pubDate");
        if (pubDate != -1) {
            out.println(
                    "<td>" + item.child(pubDate).child(0).label() + "</td>");
        } else {
            out.println("<td>No date available</td>");
        }
        int source = getChildElement(item, "source");
        if (source != -1) {
            out.println("<td><a href=\""
                    + item.child(source).attributeValue("url") + "\">"
                    + item.child(source).child(0).label() + "</td>");
        } else {
            out.println("<td>No source available</td>");
        }
        int title = getChildElement(item, "title");
        int description = getChildElement(item, "description");
        if (title != -1 && item.child(title).numberOfChildren() >= 1) {
            int link = getChildElement(item, "link");
            if (link != -1) {
                out.println("<td><a href=\"" + item.child(link).child(0).label()
                        + "\">" + item.child(title).child(0).label()
                        + "</a></td>");
            } else {
                out.println("<td>No title Available</td>");
            }
        } else if (description != -1
                && item.child(description).numberOfChildren() >= 1) {
            int link = getChildElement(item, "link");
            if (link != -1) {
                out.println("<td><a href=\"" + item.child(link).child(0).label()
                        + "\">" + item.child(description).child(0).label()
                        + "</a></td>");
            } else {
                out.println("<td>No Description Available</td>");
            }
        }
        out.println("</tr>");
    }

    /**
     * Processes one XML RSS (version 2.0) feed from a given URL converting it
     * into the corresponding HTML output file.
     *
     * @param url
     *            the URL of the RSS feed
     * @param file
     *            the name of the HTML output file
     * @param out
     *            the output stream to report progress or errors
     * @updates out.content
     * @requires out.is_open
     * @ensures <pre>
     * [reads RSS feed from url, saves HTML document with table of news items
     *   to file, appends to out.content any needed messages]
     * </pre>
     */
    private static void processFeed(String url, String file, SimpleWriter out) {
        XMLTree xml = new XMLTree1(url);
        SimpleWriter name = new SimpleWriter1L(file);
        XMLTree channel = xml.child(0);
        outputHeader(channel, name);
        int itemNumbers = channel.numberOfChildren();
        for (int i = 0; i < itemNumbers; i++) {
            if (channel.child(i).label().equals("item")) {
                processItem(channel.child(i), name);
            }
        }
        outputFooter(name);
        name.close();
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter o = new SimpleWriter1L();
        boolean isRSS = true;
        o.print("Enter valid RSS feeds: ");
        String userRSS = in.nextLine();
        XMLTree xml = new XMLTree1(userRSS);
        while (isRSS) {
            if (xml.label().equals("feeds")) {
                isRSS = false;
            } else {
                o.print("Enter valid RSS feeds: ");
                xml = new XMLTree1(in.nextLine());
            }
        }
        o.print("Enter a valid html file name for the major page: ");
        SimpleWriter out = new SimpleWriter1L(in.nextLine());
        out.println("<html>");
        out.println("<head>");
        out.println("<title>" + xml.attributeValue("title") + "</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>" + xml.attributeValue("title") + "</h1>");
        out.println("<ul>");
        int itemNumbers = xml.numberOfChildren();
        for (int i = 0; i < itemNumbers; i++) {
            if (xml.child(i).label().equals("feed")) {
                processFeed(xml.child(i).attributeValue("url"),
                        xml.child(i).attributeValue("file"), o);
                out.println("<li><a href=\""
                        + xml.child(i).attributeValue("file") + "\">"
                        + xml.child(i).attributeValue("name") + "</a></li>");
            }
        }
        out.println("</ul>");
        out.println("</body>");
        out.println("</html>");
        in.close();
        out.close();
    }

}
