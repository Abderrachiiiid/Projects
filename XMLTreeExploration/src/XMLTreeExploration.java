import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class XMLTreeExploration {

    /**
     * No argument constructor--private to prevent instantiation.
     */
    private XMLTreeExploration() {
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        XMLTree xml = new XMLTree1(
                "http://web.cse.ohio-state.edu/software/2221/web-sw1/"
                        + "extras/instructions/xmltree-model/columbus-weather.xml");
        xml.display();
        out.println(xml.isTag());
        out.println(xml.label());
        XMLTree results = xml.child(0);
        results.display("Results");
        XMLTree channel = results.child(0);
        channel.display("Channel");
        out.println(channel.numberOfChildren());
        XMLTree title = channel.child(1);
        title.display("Title");
        XMLTree titleText = title.child(0);
        titleText.display("Title Label");
        out.println(xml.child(0).child(0).child(1).child(0).toString());
        XMLTree astronomy = channel.child(10);
        if (astronomy.isTag()) {
            out.println("Sunrise val: " + astronomy.attributeValue("sunrise"));
            out.println("Midday val: " + astronomy.attributeValue("midday"));
        } else {
            out.println("Sunrise is not a tag it can't have attributes");
        }
        /*
         * Put your main program code here
         */
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
