package CitiesPackage;

import org.w3c.dom.*;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
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
            System.out.print("Введите команду (help для справки):");
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


            for (City city : cityCollection) {

                Element cityElement = document.createElement(city.getName());
                rootElement1.appendChild(cityElement);

                Element rootElement8= document.createElement("date");
                cityElement.appendChild(rootElement8);
                Text date = document.createTextNode(String.valueOf(city.getCreationDate()));
                rootElement8.appendChild(date);

                Element rootElement2 = document.createElement("id");
                cityElement.appendChild(rootElement2);
                Text element1 = document.createTextNode(String.valueOf(city.getId()));
                rootElement2.appendChild(element1);

                Element rootElement3 = document.createElement("telephonecode");
                cityElement.appendChild(rootElement3);
                Text telephonecode = document.createTextNode(String.valueOf(city.getTelephoneCode()));
                rootElement3.appendChild(telephonecode);

                Element rootElement4= document.createElement("standardOfLiving");
                cityElement.appendChild(rootElement4);
                Text standardOfLiving = document.createTextNode(String.valueOf(city.getStandardOfLiving()));
                rootElement4.appendChild(standardOfLiving);

                Element rootElement5= document.createElement("carcode");
                cityElement.appendChild(rootElement5);
                Text carcode = document.createTextNode(String.valueOf(city.getCarCode()));
                rootElement5.appendChild(carcode);

                Element rootElement6= document.createElement("population");
                cityElement.appendChild(rootElement6);
                Text population = document.createTextNode(String.valueOf(city.getPopulation()));
                rootElement6.appendChild(population);

                Element rootElement7= document.createElement("area");
                cityElement.appendChild(rootElement7);
                Text area = document.createTextNode(String.valueOf(city.getArea()));
                rootElement7.appendChild(area);


                Element rootElement9= document.createElement("metersAboveSeaLevel");
                cityElement.appendChild(rootElement9);
                Text metersAboveSeaLevel = document.createTextNode(String.valueOf(city.getMetersAboveSeaLevel()));
                rootElement9.appendChild(metersAboveSeaLevel);

                Element rootElement10= document.createElement("governor");
                cityElement.appendChild(rootElement10);
                Text governor = document.createTextNode(String.valueOf(city.getGovernor().getAge()));
                rootElement10.appendChild(governor);

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
    public  void question() {
        System.out.println("Хотите добавить новый элемент?(yes/no)");

    }
    public void createCollection(String answer, CityManager boss) {
        switch (answer) {
            case "yes":
                Date date = new Date();
                Scanner a = new Scanner(System.in);
                Scanner b = new Scanner(System.in);
                System.out.print("Введите название города:");
                String name = a.nextLine();
                System.out.print("Введите id:");
                int id = b.nextInt();
                System.out.print("Введите телефонный код:");
                long telephoncode= b.nextLong();
                System.out.print("Введите тип города(ULTRA_HIGH, HIGH, MEDIUM, ULTRA_LOW, NIGHTMARE):");
                String standardOfLiving = a.nextLine();
                System.out.print("Введите номер региона:");
                long carcode = b.nextInt();
                System.out.print("Население:");
                long population = b.nextLong();
                System.out.print("Введите area:");
                float area = b.nextFloat();
                System.out.print("Высота над уровнем моря?");
                double metersAboveSeaLevel = b.nextDouble();
                System.out.print("Введите возраст мэра:");
                int age = b.nextInt();
                Human governor = new Human();
                governor.setAge(age);

                City city = new City(name, id, telephoncode, carcode, population, area, date, metersAboveSeaLevel, standardOfLiving, governor);
                boss.addtoCol(city);
                System.out.println("Хотите добавить новый элемент снова?(yes/no)");
                answer = a.nextLine();
                createCollection(answer, boss);
                break;
            case "no":
                boss.saveCollectionToFile();
                System.out.println(boss.printy());
                break;
            default:
                a = new Scanner(System.in);
                System.out.println("Неизвестная команда. Введите 'yes/no'.");
                answer = a.nextLine();
                createCollection(answer, boss);
                break;
        }
    }

}

