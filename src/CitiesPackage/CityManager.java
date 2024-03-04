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
    private int col;
    int k = 1;
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
                System.out.println("info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
                System.out.println("show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
                System.out.println("add {element} : добавить новый элемент в коллекцию");
                System.out.println("update id {element} : обновить значение элемента коллекции, id которого равен заданному ");
                System.out.println("remove_by_id id : удалить элемент из коллекции по его id ");
                System.out.println("clear : очистить коллекцию ");
                System.out.println("save : сохранить коллекцию в файл");
                System.out.println("exit : завершить программу (без сохранения в файл)");
                System.out.println("insert_at index {element} : добавить новый элемент в заданную позицию ");
                System.out.println("remove_at index : удалить элемент, находящийся в заданной позиции коллекции (index) ");
                System.out.println("sum_of_meters_above_sea_level : вывести сумму значений поля metersAboveSeaLevel для всех элементов коллекции ");
                System.out.println("ount_by_car_code carCode : вывести количество элементов, значение поля carCode которых равно заданному ");
                System.out.println("count_greater_than_car_code carCode : вывести количество элементов, значение поля carCode которых больше заданного ");
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

    public void loadCollectionFromFile() {
        try {
            File inputFile = new File("city_collection.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

//            NodeList cityNodes = doc.getElementsByTagName("city1");
            NodeList cities = doc.getElementsByTagName("cities");

            for (int i = 0; i < cityNodes.getLength(); i++) {
                Node cityNode = cityNodes.item(i);
                System.out.println("----------------------");
                System.out.println("City:");
                if (cityNode.getNodeType() == Node.ELEMENT_NODE) {
                    org.w3c.dom.Element cityElement = (org.w3c.dom.Element) cityNode;

                    String name = cityElement.getElementsByTagName("name").item(0).getTextContent();
                    String date = cityElement.getElementsByTagName("date").item(0).getTextContent();
                    int id = Integer.parseInt (cityElement.getElementsByTagName("id").item(0).getTextContent());
                    long telephoncode = Long.parseLong(cityElement.getElementsByTagName("telephonecode").item(0).getTextContent());
                    String standardOfLiving = cityElement.getElementsByTagName("standardOfLiving").item(0).getTextContent();
                    long carcode = Long.parseLong(cityElement.getElementsByTagName("carcode").item(0).getTextContent());
                    long population = Long.parseLong(cityElement.getElementsByTagName("population").item(0).getTextContent());
                    float area = Float.parseFloat(cityElement.getElementsByTagName("area").item(0).getTextContent());
                    double metersAboveSeaLevel = Double.parseDouble(cityElement.getElementsByTagName("metersAboveSeaLevel").item(0).getTextContent());
                    int age =Integer.parseInt(cityElement.getElementsByTagName("governor").item(0).getTextContent());
                    Human governor = new Human();
                    governor.setAge(age);
                    City city = new City(name, id, telephoncode, carcode, population, area, date, metersAboveSeaLevel, standardOfLiving, governor);
                    System.out.println("Name: " + cityElement.getElementsByTagName("name").item(0).getTextContent());
                    System.out.println("Date: " + cityElement.getElementsByTagName("date").item(0).getTextContent());
                    System.out.println("ID: " + cityElement.getElementsByTagName("id").item(0).getTextContent());
                    System.out.println("Telephone Code: " + cityElement.getElementsByTagName("telephonecode").item(0).getTextContent());
                    System.out.println("Standard of Living: " + cityElement.getElementsByTagName("standardOfLiving").item(0).getTextContent());
                    System.out.println("Car Code: " + cityElement.getElementsByTagName("carcode").item(0).getTextContent());
                    System.out.println("Population: " + cityElement.getElementsByTagName("population").item(0).getTextContent());
                    System.out.println("Area: " + cityElement.getElementsByTagName("area").item(0).getTextContent());
                    System.out.println("Meters Above Sea Level: " + cityElement.getElementsByTagName("metersAboveSeaLevel").item(0).getTextContent());
                    System.out.println("Governor: " + cityElement.getElementsByTagName("governor").item(0).getTextContent());
                    cityCollection.add(city);

                }
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

            int k = 1;
            for (City city : cityCollection) {
                Element cityElement = document.createElement("city" + k);
                rootElement1.appendChild(cityElement);

                Element rootElementname = document.createElement("name");
                cityElement.appendChild(rootElementname);
                Text name = document.createTextNode(city.getName());
                rootElementname.appendChild(name);

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
                Date date1 = new Date();
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
                String date = String.valueOf(date1);

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

