package CitiesPackage;

import org.w3c.dom.*;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
//        saveCollectionToFile();
    }

    private void executeCommand(String text) {
        String[] parts = text.split(" ", 2);
        String command = parts[0];
        String element = parts.length > 1 ? parts[1] : "";
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
                System.out.println("count_by_car_code carCode : вывести количество элементов, значение поля carCode которых равно заданному ");
                System.out.println("count_greater_than_car_code carCode : вывести количество элементов, значение поля carCode которых больше заданного ");
                break;
            case "info":
                System.out.println("Тип: Cities");
                System.out.println("Дата инициализации: " + (cityCollection.get(0).getCreationDate()));
                System.out.println("Количество элементов: " + cityCollection.size());
                break;
            case "show":
                // Вывод всех элементов коллекции
                System.out.println("Элементы коллекции:" );
                for(int i = 0; i!= cityCollection.size(); i++ ) {
                    System.out.println(cityCollection.get(i));
                }
                break;
            case "add":



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
            for (int j = 0; j!= k+1; j++){
                NodeList cityNodes = doc.getElementsByTagName("city" + j);
                for (int i = 0; i < cityNodes.getLength(); i++) {
                    Node cityNode = cityNodes.item(i);
                    System.out.println("----------------------");
                    System.out.println("City:");
                    if (cityNode.getNodeType() == Node.ELEMENT_NODE) {
                        org.w3c.dom.Element cityElement = (org.w3c.dom.Element) cityNode;

                        String name = cityElement.getElementsByTagName("name").item(0).getTextContent();
                        String date = cityElement.getElementsByTagName("date").item(0).getTextContent();
                        int id = Integer.parseInt(cityElement.getElementsByTagName("id").item(0).getTextContent());
                        long telephoncode = Long.parseLong(cityElement.getElementsByTagName("telephonecode").item(0).getTextContent());
                        String standardOfLiving = cityElement.getElementsByTagName("standardOfLiving").item(0).getTextContent();
                        long carcode = Long.parseLong(cityElement.getElementsByTagName("carcode").item(0).getTextContent());
                        long population = Long.parseLong(cityElement.getElementsByTagName("population").item(0).getTextContent());
                        float area = Float.parseFloat(cityElement.getElementsByTagName("area").item(0).getTextContent());
                        double metersAboveSeaLevel = Double.parseDouble(cityElement.getElementsByTagName("metersAboveSeaLevel").item(0).getTextContent());
                        int age = Integer.parseInt(cityElement.getElementsByTagName("governor").item(0).getTextContent());
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
            }
        }
            catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create_document() {
        Scanner a = new Scanner(System.in);

        try {
            System.out.print("Введите количество городов: ");
            int citiesCount = a.nextInt();
            a.nextLine(); // очистка буфера после nextInt()

            StringBuilder xmlContent = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<cities>\n");

            for (int i = 1; i <= citiesCount; i++) {
                System.out.println("Город " + i + ":");
                System.out.print("Введите название: ");
                String name = a.nextLine();
                System.out.print("Введите телефонный код: ");
                long telephoneCode = a.nextLong();
                a.nextLine();
                System.out.print("Введите тип города(ULTRA_HIGH, HIGH, MEDIUM, ULTRA_LOW, NIGHTMARE):");
                String standardOfLiving = a.nextLine();
                System.out.print("Введите номер региона:");
                long carcode = a.nextInt();
                a.nextLine();
                System.out.print("Население:");
                long population = a.nextLong();
                a.nextLine();
                System.out.print("Введите area:");
                float area = a.nextFloat();
                a.nextLine();
                System.out.print("Высота над уровнем моря?");
                double metersAboveSeaLevel = a.nextDouble();
                a.nextLine();
                System.out.print("Введите возраст мэра:");
                int age = a.nextInt();
                a.nextLine();

                xmlContent.append("\t<city>\n")
                        .append("\t\t<name>").append(name).append("</name>\n")
                        .append("\t\t<telephoneCode>").append(telephoneCode).append("</telephoneCode>\n")
                        .append("\t\t<standardOfLiving>").append(standardOfLiving).append("</standardOfLiving>\n")
                        .append("\t\t<carcode>").append(carcode).append("</carcode>\n")
                        .append("\t\t<population>").append(population).append("</population>\n")
                        .append("\t\t<area>").append(area).append("</area>\n")
                        .append("\t\t<metersAboveSeaLevel>").append(metersAboveSeaLevel).append("</metersAboveSeaLevel>\n")
                        .append("\t\t<age>").append(age).append("</age>\n")

                        // Здесь добавьте запись остальных полей в XML
                        .append("\t</city>\n");
            }

            xmlContent.append("</cities>");

            // Сохраняем содержимое в файл
            File file = new File("city_collection.xml");
            FileWriter writer = new FileWriter(file);
            writer.write(xmlContent.toString());
            writer.close();

            System.out.println("Данные о городах успешно сохранены в XML файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

