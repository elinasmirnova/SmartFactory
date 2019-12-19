package impl;

public class Main {
    public static void main(String[] args) {
        FactoryConfiguration configurationDirector = new FactoryConfiguration();
        System.out.println("-----------The first configuration of the factory-----------");
        FactoryBuilder1 factory1 = new FactoryBuilder1();
        configurationDirector.startConfiguration(factory1);
        System.out.println("-----------The second configuration of the factory-----------");
        FactoryBuilder2 factory2 = new FactoryBuilder2();
        configurationDirector.startConfiguration(factory2);
    }
}
