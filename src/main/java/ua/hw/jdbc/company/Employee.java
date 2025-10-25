package ua.hw.jdbc.company;

public class Employee {
    private Integer id;
    private String name;
    private int age;
    private String position;
    private float salary;

    public Employee(Integer id, String name, int age, String position, float salary) {
        this.id = id; this.name = name; this.age = age; this.position = position; this.salary = salary;
    }
    public Employee(String name, int age, String position, float salary) {
        this(null, name, age, position, salary);
    }

    public Integer getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPosition() { return position; }
    public float getSalary() { return salary; }

    public void setId(Integer id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(float salary) { this.salary = salary; }

    @Override public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', age=" + age +
                ", position='" + position + "', salary=" + salary + '}';
    }
}