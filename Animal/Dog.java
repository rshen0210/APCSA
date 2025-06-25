class Dog extends Animal {
    private String name = "";
    public Dog(String n) {
        // Compiler implicitly adds super();
        super.eat();
        name = n;
        System.out.println("Dog constructor");
    }

    @Override
    public void eat() {
        super.eat(); // Calling the superclass method
        System.out.println("Dog is eating");
    }
    public String getName() {
        return name;
    }
}