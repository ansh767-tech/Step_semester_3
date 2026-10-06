package abstraction_interface.class_problems;

public abstract class Instrument {
    public abstract String play();
}

class StringInstrument extends Instrument {
    @Override
    public String play() {
        return "Strumming the strings";
    }
}

class Violin extends StringInstrument {
    @Override
    public String play() {
        return super.play() + ", with a bow drawn across four strings";
    }
}