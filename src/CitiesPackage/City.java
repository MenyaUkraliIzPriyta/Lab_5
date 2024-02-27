package CitiesPackage;



public class City {
    private int id;
    private String name;
    private String creationDate;
    private float area;
    private long population;
    private double metersAboveSeaLevel;
    private long telephoneCode;
    private long carCode;
    private String standardOfLiving;
    private Human governor;

//    public City(String name, int id, Coordinates coordinates, java.util.Date creationDate, Float area, Long population, double metersAboveSeaLevel, long telephoneCode, Long carCode, StandardOfLiving standardOfLiving, Human governor ) {
//        this.name = name;
//        this.id = id;
//        this.coordinates = coordinates;
//        this.area = area;
//        this.creationDate = creationDate;
//        this.carCode = carCode;
//        this.population = population;
//        this.standardOfLiving = standardOfLiving;
//        this.metersAboveSeaLevel = metersAboveSeaLevel;
//        this.telephoneCode = telephoneCode;
//        this.governor = governor;
//    }
    public City(String name, int id,  long telephoneCode, long carCode, long population, float area,String creationDate, double metersAboveSeaLevel, String standardOfLiving, Human governor) {
        this.name = name;
        this.id = id;
        this.telephoneCode = telephoneCode;
        this.carCode = carCode;
        this.population = population;
        this.area = area;
        this.creationDate = creationDate;
        this.metersAboveSeaLevel = metersAboveSeaLevel;
        this.standardOfLiving = standardOfLiving;
        this.governor = governor;
    }
    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }





    public String getCreationDate() {

        return creationDate;
    }

    public void setCreationDate(String creationDate) {

        this.creationDate = creationDate;
    }

    public Float getArea() {

        return area;
    }

    public void setArea(Float area) {

        this.area = area;
    }

    public Long getPopulation() {

        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public double getMetersAboveSeaLevel() {
        return metersAboveSeaLevel;
    }

    public void setMetersAboveSeaLevel(double metersAboveSeaLevel) {
        this.metersAboveSeaLevel = metersAboveSeaLevel;
    }

    public long getTelephoneCode() {
        return telephoneCode;
    }

    public void setTelephoneCode(long telephoneCode) {
        this.telephoneCode = telephoneCode;
    }

    public Long getCarCode() {
        return carCode;
    }

    public void setCarCode(Long carCode) {
        this.carCode = carCode;
    }

    public String getStandardOfLiving() {
        return standardOfLiving;
    }

    public void setStandardOfLiving(String standardOfLiving) {
        this.standardOfLiving = standardOfLiving;
    }

    public Human getGovernor() {
        return governor;
    }

    public void setGovernor(Human governor) {
        this.governor = governor;
    }
    @Override
    public String toString() {
        return this.name ;
    }
}