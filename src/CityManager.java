import CitiesPackage.City;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CityManager {
    private ArrayList<City> cityCollection;

    public CityManager() {
        this.cityCollection = new ArrayList<>();
    }

    public void start(String fileName) {
        loadCollectionFromFile(fileName);
        Scanner scanner = new Scanner(System.in);
        String command;
        do {
            System.out.print("Введите команду (help для справки): ");
            command = scanner.nextLine();
            executeCommand(command);
        } while (!command.equals("exit"));
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

    private void loadCollectionFromFile(String fileName) {
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            cityCollection = (ArrayList<City>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Коллекция загружена из файла.");
        } catch (FileNotFoundException e) {
            System.out.println("Файл с коллекцией не найден. Создана пустая коллекция.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void saveCollectionToFile(String fileName) {
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(cityCollection);
            oos.close();
            fos.close();
            System.out.println("Коллекция сохранена в файл.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}