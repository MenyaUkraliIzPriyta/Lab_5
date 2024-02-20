package CitiesPackage;

import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CityManager {
    private int id;
    private long telephonecode;
    private ArrayList<City> cityCollection;
    public ArrayList<String> city_data = new ArrayList<>();
    private final String XML_FILE_NAME = "city_collection.xml"; // Имя файла для сохранения и загрузки коллекции

    public void add_data(String a) {
        city_data.add(a);
    }
    public CityManager() {
        this.cityCollection = new ArrayList<>();
    }
    /////////////
    public void addtoCol(City a) {
        cityCollection.add(a);
    }
    /////////////
    public ArrayList<City> printy() {
        return cityCollection;
    }

    public void start() {
        loadCollectionFromFile();
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.print("Введите команду (help для справки): ");
            command = scanner.nextLine();
            executeCommand(command);
        } while (!command.equals("exit"));
        saveCollectionToFile();
    }

    private void executeCommand(String command) {
        // Реализация обработки команд
        switch (command) {
            case "help":
                // Вывод справки по доступным командам
                break;
            case "info":
                // Вывод информации о коллекции
                break;
            case "show":
                // Вывод всех элементов коллекции
                break;
            // Реализация остальных команд
            default:
                System.out.println("Неизвестная команда. Введите 'help' для справки.");
        }
    }

    private void loadCollectionFromFile() {
        try {
            File file = new File(XML_FILE_NAME);
            if (file.exists()) {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document document = builder.parse(file);
                NodeList cityNodes = document.getElementsByTagName("city");
                for (int i = 0; i < cityNodes.getLength(); i++) {
                    Node cityNode = cityNodes.item(i);
                    if (cityNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element cityElement = (Element) cityNode;
                        // Прочитать данные из XML и создать объект City
                        City city = parseCityElement(cityElement);
                        cityCollection.add(city);
                    }
                }
                System.out.println("Коллекция загружена из файла.");
            } else {
                System.out.println("Файл с коллекцией не найден. Создана пустая коллекция.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private City parseCityElement(Element cityElement) {
        // Реализация парсинга XML-элемента в объект City
        return null;
    }

    public void saveCollectionToFile() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            Element rootElement1 = document.createElement("cities");
            document.appendChild(rootElement1);
            int k = 0;
            for (City city : cityCollection) {
                Element cityElement = document.createElement(String.valueOf(city));
                rootElement1.appendChild(cityElement);
                Element rootElement2 = document.createElement("id");
                cityElement.appendChild(rootElement2);
                Text element1 = document.createTextNode(city_data.get(k));
                rootElement2.appendChild(element1);
                k++;
                Element rootElement3 = document.createElement("telephonecode");
                cityElement.appendChild(rootElement3);
                Text telephonecode = document.createTextNode(city_data.get(k));
                rootElement3.appendChild(telephonecode);
                k++;


            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File(XML_FILE_NAME));
            transformer.transform(source, result);

            System.out.println("Коллекция сохранена в файл.");
        } catch (Exception e) {
            System.err.println(e);
        }
    }
public int addXML_id(int id) {
        return this.id = id;
}
public long addXML_telephoncode( long telephonecode) {
        return this.telephonecode = telephonecode;
}
private Element createCityElement(Document document, City city) {
        // Реализация создания XML-элемента на основе объекта City
        return null;
    }

}
