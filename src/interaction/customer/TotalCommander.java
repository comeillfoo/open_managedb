package interaction.customer;

// user-packages
import entity.Organization;
import entity.Organizations;
import interaction.sender.ConsolePrompter;

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

  private ConsolePrompter.ParamsCollector paramsCollector;
  private ConsolePrompter consolePrompter;

  public TotalCommander(String envVar) {super(envVar);}
  public void setParamsCollection(ConsolePrompter.ParamsCollector paramsCollector){
    this.paramsCollector = paramsCollector;
  }
  public void setConsolePrompter(ConsolePrompter consolePrompter){
    this.consolePrompter = consolePrompter;
  }

  // base methods
  @Override public void add(Organization element) { elements.put(element.getKey(), element);} //Script elements adding
  @Override public void add(Integer key, Organization element) {elements.put(key, element);} //Interactive elements adding

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

  @Override
  public void removeKey() throws NullPointerException{
    consolePrompter.getMainStream().println("Enter a key:");
    Integer key = consolePrompter.getInterrogater().nextInt();
    if (elements.containsKey(key)){
      elements.remove(key);
    }else{
      System.err.println("There is no such key:\""+key+"\" has been found!");
    }

  }

  @Override
  public void save() {
      unload();
  }

  @Override
  public void show() {
    for (Map.Entry<Integer, Organization> entry : elements.entrySet()){
        consolePrompter.getMainStream().println("Id:"+entry.getKey()+" Value:"+entry.getValue());
        consolePrompter.getMainStream().println();
        entry.getValue().toString();
        consolePrompter.getMainStream().println();
    }
  }

  @Override
  public void update(Organization element) {
    consolePrompter.getMainStream().println("Enter a key:");
    elements.replace(consolePrompter.getInterrogater().nextInt(),element);
  }

  @Override
  public void maxByCreationDate() {
    Organization  earliestOrganization = null;
    for(Map.Entry<Integer, Organization> entry : elements.entrySet()){
      if(earliestOrganization == null){
        earliestOrganization = elements.get(entry.getKey());
        continue;
      }
      if(earliestOrganization.getCreationDate().isAfter(elements.get(entry.getKey()).getCreationDate())){
        earliestOrganization = elements.get(entry.getKey());
      }
    }
    consolePrompter.getMainStream().println(earliestOrganization.toString());
  }

  @Override
  public void filterContaines() {
    consolePrompter.getMainStream().println("Enter the \"name\":");
    String tempName = consolePrompter.getInterrogater().nextLine().toLowerCase();
    for (Map.Entry<Integer, Organization> entry : elements.entrySet()){
      if(entry.getValue().getName().toLowerCase().contains(tempName)){
        consolePrompter.getMainStream().println(entry.getValue().toString());
      }
    }
  }

  @Override
  public void sumOfAnnual() {
    float annualSum = 0;
    for (Map.Entry<Integer, Organization> entry : elements.entrySet()){
        annualSum += entry.getValue().getAnnualTurnover();
    }
    consolePrompter.getMainStream().println(annualSum);
  }
}