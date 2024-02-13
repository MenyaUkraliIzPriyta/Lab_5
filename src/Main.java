import CitiesPackage.City;
import CitiesPackage.CityManager;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        CityManager boss = new CityManager();
        System.out.println("Хотите добавить новый элемент?(yes/no)");
        String answer = a.nextLine();
        executeCommand(answer, boss);

//        while (answer.equals("yes")) {
//            City spb = new City("SPb");
//            boss.addtoCol(spb);
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
    private static void executeCommand(String answer, CityManager boss) {
        Scanner a = new Scanner(System.in);
        switch (answer) {
            case "yes":
                City spb = new City("SPb");
                boss.addtoCol(spb);
                System.out.println("Хотите добавить новый элемент снова?(yes/no)");
                answer = a.nextLine();
                executeCommand(answer, boss);
                break;
            case "no":
                boss.saveCollectionToFile();
                System.out.println(boss.printy().get(0).toString());
                break;
            default:
                System.out.println("Неизвестная команда. Введите 'yes/no'.");
                answer = a.nextLine();
                executeCommand(answer, boss);
                break;
        }
    }

}
