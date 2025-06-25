public abstract class Animal {
    public Animal() {
        System.out.println("Animal constructor");
    }

    public void eat() {
        System.out.println("Animal is eating");
    }
    public abstract String getName();
}



