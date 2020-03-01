package interaction.customer;

// user-packages
import entity.*;
import interaction.customer.plants.OrganizationBuilder;
import interaction.instructions.extended.Indicator;
import interaction.sender.Prompter;

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
import java.util.Map;

public class TotalCommander extends Commander<Integer, Organization> {

  public TotalCommander(String envVar) {super(envVar);}
  // base methods
  @Override public void add(Organization element) { elements.put(element.getKey(), element);}
  @Override public void add(Integer key, Organization element) {elements.put(key, element);}
  public void add(Integer key, Organization element, Indicator sign) {
    if (sign.detect(lookFor(key)))
      add(key, element);
  }
  @Override public List<Organization> load() {
    Organizations companies = null; // TODO: eliminate "maybe null" remaining
    try(InputStream streamin = new FileInputStream(System.getProperty("user.dir") + "/" + System.getenv(envVar));
        InputStreamReader reader = new InputStreamReader(streamin);
    ) {
      JAXBContext loader = null;
      try {
        loader = JAXBContext.newInstance(Organizations.class);
        Unmarshaller subloader = loader.createUnmarshaller();
        companies = (Organizations) subloader.unmarshal(reader); // TODO: "maybe null"
      } catch (JAXBException e) {
        System.err.println(e.getErrorCode() + ": " + e.getMessage());
        System.err.println(e.getStackTrace());
        System.exit(1);
      }
    } catch (IOException e) {
      System.err.println(e.getMessage());
      System.err.println(e.getStackTrace());
      System.exit(1);
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
      writer.println(p);
  }
  // There is a facility magic
  @Override
  public Organization cook(Prompter.ParamsCollector committed) {
    return new OrganizationBuilder().make(committed);
  }

  @Override
  public Integer search(Integer id) {
    for (Map.Entry<Integer, Organization> enter : elements.entrySet())
      if (id.equals(enter.getValue().getID()))
        return enter.getKey();
    return null;
  }
  @Override
  public Organization lookFor(Integer key) {
    return elements.get(key);
  }
  @Override
  public void remove(Integer key) {
    elements.remove(key);
  }
  @Override
  public String survey(Indicator litmus) {
    StringBuilder blank = new StringBuilder("");
    for (Map.Entry<Integer, Organization> enter : elements.entrySet())
      if (litmus.detect(enter.getValue()))
        blank.append("key: " + enter.getKey() + "; value: " + enter.getValue() + "\n");
    return blank.toString();
  }
}