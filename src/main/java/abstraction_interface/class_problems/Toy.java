package abstraction_interface.class_problems;

public abstract class Toy {
    private static int counter = 1000;
    private final String toyId;
    private final String name;

    public Toy(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        this.name = name;
        synchronized (Toy.class) {
            counter++;
            this.toyId = "TOY-" + counter;
        }
    }

    public String getName() {
        return name;
    }

    public String getToyId() {
        return toyId;
    }

    public abstract String makeSound();
}

class ToyCar extends Toy {
    public ToyCar(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return getName() + ": Vroom vroom!";
    }
}

class ToyRobot extends Toy {
    public ToyRobot(String name) {
        super(name);
    }

    @Override
    public String makeSound() {
        return getName() + ": Beep boop!";
    }
}