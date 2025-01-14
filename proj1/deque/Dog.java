package deque;

public class Dog {

    private int age;
    private String name;

    public Dog(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dog) {
            return name.equals(((Dog) obj).name);
        }

        return false;
    }
}
