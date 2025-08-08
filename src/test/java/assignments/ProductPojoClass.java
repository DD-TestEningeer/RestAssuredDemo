package assignments;

public class ProductPojoClass {
    private String name;
    private DataPojoClass data;

    public ProductPojoClass(String name, DataPojoClass data) {
        this.name = name;
        this.data = data;
    }

    // Getters & setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public DataPojoClass getData() { return data; }
    public void setData(DataPojoClass data) { this.data = data; }
}
