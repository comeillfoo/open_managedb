package interaction.customer;

// user-packages
import entity.Organization;
import entity.Organizations;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.util.List;

// io package
import java.io.IOException;
import java.io.PrintStream;
// input:
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
// output:
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class TotalCommander extends Commander<Integer, Organization> {
  public TotalCommander(String envVar) {super(envVar);}
  // base methods
  @Override public void add(Organization element) { elements.put(element.getKey(), element);}
  @Override public void add(Integer key, Organization element) {elements.put(key, element);}

  @Override public List<Organization> load() {
    Organizations companies = null; // TODO: eliminate "maybe null" remaining
    try(InputStream streamin = new FileInputStream(System.getenv(envVar));
        InputStreamReader reader = new InputStreamReader(streamin);
    ) {
      JAXBContext loader = null;
      try {
        JAXBContext.newInstance(Organizations.class);
        Unmarshaller subloader;
        subloader = loader.createUnmarshaller();
        companies = (Organizations) subloader.unmarshal(reader); // TODO: "maybe null"
      } catch (JAXBException e) {
        System.err.println(e.getErrorCode() + ": " + e.getMessage());
        System.err.println(e.getStackTrace());
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.err.println(e.getStackTrace());
    }
    return companies.getCompanies();
  }

  @Override public void unload() {
    try(OutputStream streamout = new FileOutputStream(System.getenv(envVar));
        OutputStreamWriter writer = new OutputStreamWriter(streamout);
    ) {
      try {
        JAXBContext unloader = JAXBContext.newInstance(Organizations.class);
        Marshaller subunloader = unloader.createMarshaller();
        subunloader.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        subunloader.marshal(new Organizations(elements), writer);
      } catch (JAXBException e) {
        System.err.println(e.getErrorCode() + ": " + e.getMessage());
        System.err.println(e.getStackTrace());
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.err.println(e.getStackTrace());
    }
  }

  @Override public void clear() {elements.clear();}
  @Override
  public void man(String[] pages, PrintStream writer) {
    for (String p : pages)
      writer.println(p + "\n");
  }
}