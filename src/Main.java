import CitiesPackage.City;
import CitiesPackage.CityManager;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        CityManager boss = new CityManager();
        System.out.println("Хотите добавить новый элемент?(yes/no)");
        String answer = a.nextLine();
        executeCommand_1(answer, boss);
        System.out.println(boss.city_data);

//        if (answer.equals("no")) {
//            System.out.println("В ваша база пуста. Хотите завершить рабту?(yes/no)");
//            answer = a.nextLine();
//            if (answer.equals("yes")) {
//                System.out.println("Работа завершена");
//                System.exit(1);
//            }
//            else {
//                System.out.println("Хотите добавить новый элемент?(yes/no)");
//                answer = a.nextLine();
//                executeCommand_1(answer, boss);
//            }
//        }
//        else {
//            executeCommand_1(answer, boss);
//        }

//        while (answer.equals("yes")) {
//            String name = a.nextLine();
//            int id = a.nextInt();
//            City city = new City(name, id);
//            boss.addtoCol(city);
//            System.out.println("Хотите добавить новый элемент?(yes/no)");
//            answer = a.nextLine();
//            if (answer.equals("no")) {
//                break;
//            }
//
//        }
//        boss.saveCollectionToFile();
//        System.out.println(boss.printy().get(0).toString());
    }
    private static void executeCommand_1(String answer, CityManager boss) {
        switch (answer) {
            case "yes":
                Scanner a = new Scanner(System.in);
                Scanner b = new Scanner(System.in);
                System.out.print("Введите название города:");
                String name = a.nextLine();
                System.out.print("Введите id:");
                int id = b.nextInt();
                boss.addXML_id(id);
                boss.add_data(id + "");
                System.out.print("Введите телефонный код:");
                long telephoncode= b.nextLong();
                boss.addXML_telephoncode(telephoncode);
                boss.add_data( telephoncode + "");
                System.out.print("Введите тип города(ULTRA_HIGH, HIGH, MEDIUM, ULTRA_LOW, NIGHTMARE):");
                String standardOfLiving = a.nextLine();
                boss.add_data(standardOfLiving);
                City city = new City(name, id, telephoncode, standardOfLiving);
                boss.addtoCol(city);
                System.out.println("Хотите добавить новый элемент снова?(yes/no)");
                answer = a.nextLine();
                executeCommand_1(answer, boss);
                break;
            case "no":
                boss.saveCollectionToFile();
                System.out.println(boss.printy());
                break;
            default:
                a = new Scanner(System.in);
                System.out.println("Неизвестная команда. Введите 'yes/no'.");
                answer = a.nextLine();
                executeCommand_1(answer, boss);
                break;
        }
    }
}
