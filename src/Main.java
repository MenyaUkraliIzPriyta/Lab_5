import CitiesPackage.CityManager;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        CityManager boss = new CityManager();
        boss.loadCollectionFromFile();
        System.out.println(boss.printy());
    }
}
