package assignments;

public class DataPojoClass {
    private int year;
    private double price;
    private String CPUmodel;
    private String Harddisksize;

    public DataPojoClass(int year, double price, String CPUmodel, String Harddisksize) {
        this.year = year;
        this.price = price;
        this.CPUmodel = CPUmodel;
        this.Harddisksize = Harddisksize;
    }

    // Getters & setters
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCPUmodel() { return CPUmodel; }
    public void setCPUmodel(String CPUmodel) { this.CPUmodel = CPUmodel; }

    public String getHarddisksize() { return Harddisksize; }
    public void setHarddisksize(String harddisksize) { Harddisksize = harddisksize; }
}
