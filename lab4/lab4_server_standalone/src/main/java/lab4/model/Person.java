package lab4.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String sex;
    private String city;

    public Person() {}

    public Person(int id, String name, String surname, int age, String sex, String city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.sex = sex;
        this.city = city;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public int getAge() {
        return age;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
