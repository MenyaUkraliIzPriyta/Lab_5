package CitiesPackage;

import org.w3c.dom.*;
import java.util.Random;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

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
                System.out.println("update_id {element} : обновить значение элемента коллекции, id которого равен заданному ");
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
                System.out.println("Элементы коллекции:");
                for (int i = 0; i != cityCollection.size(); i++) {
                    System.out.println(cityCollection.get(i));
                }
                break;

            case "add":
                Date date = new Date();
                Scanner a = new Scanner(System.in);
                Random random = new Random();
                int id = random.nextInt(10000000);
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

                Human governor = new Human();
                governor.setAge(age);
                // Создаем объект City на основе считанных данных
                City city = new City();
                city.setName(element);
                city.setId(id);
                city.setTelephoneCode(telephoneCode);
                city.setCarCode(carcode);
                city.setPopulation(population);
                city.setArea(area);
                city.setMetersAboveSeaLevel(metersAboveSeaLevel);
                city.setGovernor(governor);
                city.setStandardOfLiving(standardOfLiving);
                city.setCreationDate(String.valueOf(date));
                cityCollection.add(city);
                System.out.println(cityCollection);
                break;

            case "update_id":
                for (City cities : cityCollection) {
                    if (cities.getName().equals(element) ) {
                        Random random_ = new Random();
                        int id_ = random_.nextInt(10000000);
                        cities.setId(id_);
                        System.out.println("id изменен");
                        break;
                    }
                }

            case "remove_by_id":
                for (int i = 0; i != cityCollection.size(); i++) {
                    if (cityCollection.get(i).getName().equals(element)) {
                        City delete = cityCollection.get(i);
                        cityCollection.remove(delete);
                    }
                    System.out.println("Элемент удален");
                    break;
                }

            case "clear":
               cityCollection.clear();
                System.out.println("Ваша коллекция стала пустой");
                break;

            case "exit":
                System.out.println("Работа программы завершена без принудительного сохранения");
                System.exit(0);

            case "save":
                saveCollection();
                break;

            default:
                System.out.println("Неизвестная команда. Введите 'help' для справки.");
        }
    }

    public void loadCollectionFromFile() {
        try {
            // Загружаем XML файл в память в виде DOM дерева
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("city_collection.xml"));

            // Получаем корневой элемент "cities"
            Element rootElement = document.getDocumentElement();

            // Получаем список всех элементов "city"
            NodeList cityNodes = rootElement.getElementsByTagName("city");

            // Создаем пустой список для хранения объектов City

            // Перебираем все элементы "city"
            for (int i = 0; i < cityNodes.getLength(); i++) {
                Element cityElement = (Element) cityNodes.item(i);

                // Получаем значения полей элемента "city"
                String name = cityElement.getElementsByTagName("name").item(0).getTextContent();
                int id = Integer.parseInt(cityElement.getElementsByTagName("id").item(0).getTextContent());
                long telephoneCode = Long.parseLong(cityElement.getElementsByTagName("telephoneCode").item(0).getTextContent());
                long carcode = Long.parseLong(cityElement.getElementsByTagName("carcode").item(0).getTextContent());
                long population = Long.parseLong(cityElement.getElementsByTagName("population").item(0).getTextContent());
                float area = Float.parseFloat(cityElement.getElementsByTagName("area").item(0).getTextContent());
                double metersAboveSeaLevel = Double.parseDouble(cityElement.getElementsByTagName("metersAboveSeaLevel").item(0).getTextContent());
                int age = Integer.parseInt(cityElement.getElementsByTagName("age").item(0).getTextContent());
                String standardOfLiving = cityElement.getElementsByTagName("standardOfLiving").item(0).getTextContent();
                String creationDate = cityElement.getElementsByTagName("creationDate").item(0).getTextContent();
                Human governor = new Human();
                governor.setAge(age);
                // Создаем объект City на основе считанных данных
                City city = new City();
                city.setName(name);
                city.setId(id);
                city.setTelephoneCode(telephoneCode);
                city.setCarCode(carcode);
                city.setPopulation(population);
                city.setArea(area);
                city.setMetersAboveSeaLevel(metersAboveSeaLevel);
                city.setGovernor(governor);
                city.setStandardOfLiving(standardOfLiving);
                city.setCreationDate(creationDate);
                cityCollection.add(city);
            }
            System.out.println(cityCollection);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void create_document() {
        Scanner a = new Scanner(System.in);
        Random random = new Random();
        int id = random.nextInt(10000000);
        try {
            System.out.print("Введите количество городов: ");
            int citiesCount = a.nextInt();
            a.nextLine(); // очистка буфера после nextInt()
            StringBuilder xmlContent = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<cities>\n");

            for (int i = 1; i <= citiesCount; i++) {
                Date date = new Date();
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
                        .append("\t\t<id>").append(id).append("</id>\n")
                        .append("\t\t<telephoneCode>").append(telephoneCode).append("</telephoneCode>\n")
                        .append("\t\t<standardOfLiving>").append(standardOfLiving).append("</standardOfLiving>\n")
                        .append("\t\t<carcode>").append(carcode).append("</carcode>\n")
                        .append("\t\t<population>").append(population).append("</population>\n")
                        .append("\t\t<area>").append(area).append("</area>\n")
                        .append("\t\t<metersAboveSeaLevel>").append(metersAboveSeaLevel).append("</metersAboveSeaLevel>\n")
                        .append("\t\t<age>").append(age).append("</age>\n")
                        .append("\t\t<creationDate>").append(date).append("</creationDate>\n")

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
    private void saveCollection() {
        try {
            // Создаем новый документ XML
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();

            // Создаем корневой элемент "cities"
            Element rootElement = document.createElement("cities");
            document.appendChild(rootElement);

            // Для каждого города в коллекции создаем элемент "city" и добавляем его в корневой элемент
            for (City city : cityCollection) {
                Element cityElement = document.createElement("city");
                rootElement.appendChild(cityElement);

                // Добавляем дочерние элементы для каждого поля города
                addElement(document, cityElement, "name", city.getName());
                addElement(document, cityElement, "id", String.valueOf(city.getId()));
                addElement(document, cityElement, "telephoneCode", String.valueOf(city.getTelephoneCode()));
                addElement(document, cityElement, "standardOfLiving", city.getStandardOfLiving());
                addElement(document, cityElement, "carcode", String.valueOf(city.getCarCode()));
                addElement(document, cityElement, "population", String.valueOf(city.getPopulation()));
                addElement(document, cityElement, "area", String.valueOf(city.getArea()));
                addElement(document, cityElement, "metersAboveSeaLevel", String.valueOf(city.getMetersAboveSeaLevel()));
                addElement(document, cityElement, "age", String.valueOf(city.getGovernor().getAge()));
                addElement(document, cityElement, "creationDate", String.valueOf(city.getCreationDate()));
            }

            // Сохраняем измененное DOM дерево обратно в XML файл
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("city_collection.xml"));
            transformer.transform(source, result);

            System.out.println("XML файл успешно сохранен.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Вспомогательный метод для добавления элемента в DOM дерево
    private static void addElement(Document document, Element parentElement, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(textContent));
        parentElement.appendChild(element);
    }
}



