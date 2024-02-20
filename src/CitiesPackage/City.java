package CitiesPackage;



public class City {
    private int id;
    private String name;
    private Coordinates coordinates;
    private java.util.Date creationDate;
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
    public City(String name, int id,  long telephoneCode, String standardOfLiving) {
        this.name = name;
        this.id = id;
        this.telephoneCode = telephoneCode;
        this.standardOfLiving = standardOfLiving;
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

    public Coordinates getCoordinates()
    {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {

        this.coordinates = coordinates;
    }

    public java.util.Date getCreationDate() {

        return creationDate;
    }

    public void setCreationDate(java.util.Date creationDate) {

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