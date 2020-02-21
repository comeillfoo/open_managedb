package interaction.customer;

// user-packages

import entity.Mappable;
import entity.Organization;
import entity.Organizations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;

// xml-packages
// io-packages
// input:
// output:
// other packages

public class TotalCommander<K, V extends Mappable> extends Commander {
  public TotalCommander(String envVar) {super(envVar);}
  @Override public void add(Mappable element) {
    elements.put(element.getKey(), element);
  }
  @Override public List<Organization> load() {
    Organizations companies = null; // TODO: eliminate maybe null remaining
    try(InputStream streamin = new FileInputStream(System.getenv(envVar));
        InputStreamReader reader = new InputStreamReader(streamin);
    ) {
      JAXBContext loader = null;
      try {
        JAXBContext.newInstance(Organizations.class);
        Unmarshaller subloader;
        subloader = loader.createUnmarshaller();
        companies = (Organizations) subloader.unmarshal(reader); // TODO: maybe null
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

  @Override
  public void man(String[] pages, PrintStream writer) {
    for (String p : pages)
      writer.println(p + "\n");
  }
}