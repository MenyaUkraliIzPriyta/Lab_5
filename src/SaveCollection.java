import CitiesPackage.CityManager;


import java.util.Scanner;

public class SaveCollection {
    public static void main(String[] args) {
        Scanner a = new Scanner(System.in);
        CityManager boss = new CityManager();
        boss.question();
        String answer1 = a.nextLine();
        if (answer1.equals("no")) {
            System.out.println("Ваша база пуста.Если хотите продолжить напишите yes, иначе no.");
            String answer2 = a.nextLine();
            if (answer2.equals("no")) {
                System.out.println("Работа прекращена.");
                System.exit(1);
            }
            else {
                boss.question();
                answer1 = a.nextLine();
                boss.createCollection(answer1, boss);
            }
        }

        else {
            boss.createCollection(answer1, boss);
        }

    }
}
