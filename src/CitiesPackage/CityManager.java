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
    private ArrayList<City> cityCollection;
    private final String XML_FILE_NAME = "city_collection.xml"; // Имя файла для сохранения и загрузки коллекции

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

            Element rootElement = document.createElement("cities");
            document.appendChild(rootElement);
//
            for (City city : cityCollection) {
                Element cityElement = document.createElement(city.toString());
                rootElement.appendChild(cityElement);
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

    private Element createCityElement(Document document, City city) {
        // Реализация создания XML-элемента на основе объекта City
        return null;
    }

}
