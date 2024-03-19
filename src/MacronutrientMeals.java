import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Macronutrient interface
interface Macronutrient {
    String getName();
}

// Carb options
class Cheese implements Macronutrient {
    @Override
    public String getName() { return "Cheese"; }
}

class Bread implements Macronutrient {
    @Override
    public String getName() { return "Bread"; }
}

class Lentils implements Macronutrient {
    @Override
    public String getName() { return "Lentils"; }
}

class Pistachio implements Macronutrient {
    @Override
    public String getName() { return "Pistachio"; }
}

// Protein options
class Fish implements Macronutrient {
    @Override
    public String getName() { return "Fish"; }
}

class Chicken implements Macronutrient {
    @Override
    public String getName() { return "Chicken"; }
}

class Beef implements Macronutrient {
    @Override
    public String getName() { return "Beef"; }
}

class Tofu implements Macronutrient {
    @Override
    public String getName() { return "Tofu"; }
}

// Fat options
class Avocado implements Macronutrient {
    @Override
    public String getName() { return "Avocado"; }
}

class SourCream implements Macronutrient {
    @Override
    public String getName() { return "Sour Cream"; }
}

class Tuna implements Macronutrient {
    @Override
    public String getName() { return "Tuna"; }
}

class Peanuts implements Macronutrient {
    @Override
    public String getName() { return "Peanuts"; }
}

// Factory interfaces
interface CarbsFactory {
    Macronutrient createCarb();
}

interface ProteinFactory {
    Macronutrient createProtein();
}

interface FatFactory {
    Macronutrient createFat();
}

// Concrete factories
class CarbsFactoryImpl implements CarbsFactory {
    private static CarbsFactoryImpl instance;
    private static List<Macronutrient> carbsList = new ArrayList<>();

    static {
        carbsList.add(new Cheese());
        carbsList.add(new Bread());
        carbsList.add(new Lentils());
        carbsList.add(new Pistachio());
    }

    private CarbsFactoryImpl() {}

    public static CarbsFactoryImpl getInstance() {
        if (instance == null) {
            instance = new CarbsFactoryImpl();
        }
        return instance;
    }

    @Override
    public Macronutrient createCarb() {
        Random random = new Random();
        int index = random.nextInt(carbsList.size());
        return carbsList.get(index);
    }
}

class ProteinFactoryImpl implements ProteinFactory {
    private static ProteinFactoryImpl instance;
    private static List<Macronutrient> proteinsList = new ArrayList<>();

    static {
        proteinsList.add(new Fish());
        proteinsList.add(new Chicken());
        proteinsList.add(new Beef());
        proteinsList.add(new Tofu());
    }

    private ProteinFactoryImpl() {}

    public static ProteinFactoryImpl getInstance() {
        if (instance == null) {
            instance = new ProteinFactoryImpl();
        }
        return instance;
    }

    @Override
    public Macronutrient createProtein() {
        Random random = new Random();
        int index = random.nextInt(proteinsList.size());
        return proteinsList.get(index);
    }
}

class FatFactoryImpl implements FatFactory {
    private static FatFactoryImpl instance;
    private static List<Macronutrient> fatsList = new ArrayList<>();

    static {
        fatsList.add(new Avocado());
        fatsList.add(new SourCream());
        fatsList.add(new Tuna());
        fatsList.add(new Peanuts());
    }

    private FatFactoryImpl() {}

    public static FatFactoryImpl getInstance() {
        if (instance == null) {
            instance = new FatFactoryImpl();
        }
        return instance;
    }

    @Override
    public Macronutrient createFat() {
        Random random = new Random();
        int index = random.nextInt(fatsList.size());
        return fatsList.get(index);
    }
}

// Abstract Factory
interface MacronutrientMealFactory {
    CarbsFactory createCarbsFactory();
    ProteinFactory createProteinFactory();
    FatFactory createFatFactory();
}

class MacronutrientMealFactoryImpl implements MacronutrientMealFactory {
    private static MacronutrientMealFactoryImpl instance;

    private MacronutrientMealFactoryImpl() {}

    public static MacronutrientMealFactoryImpl getInstance() {
        if (instance == null) {
            instance = new MacronutrientMealFactoryImpl();
        }
        return instance;
    }

    @Override
    public CarbsFactory createCarbsFactory() {
        return CarbsFactoryImpl.getInstance();
    }

    @Override
    public ProteinFactory createProteinFactory() {
        return ProteinFactoryImpl.getInstance();
    }

    @Override
    public FatFactory createFatFactory() {
        return FatFactoryImpl.getInstance();
    }
}

enum DietPlan {
    NO_RESTRICTION,
    PALEO,
    VEGAN,
    NUT_ALLERGY
}

// Customer class
class Customer {
    private String name;
    private DietPlan dietPlan;

    public Customer(String name, DietPlan dietPlan) {
        this.name = name;
        this.dietPlan = dietPlan;
    }

    public String getName() {
        return name;
    }

    public DietPlan getDietPlan() {
        return dietPlan;
    }
}

// Meal class
class Meal {
    private Macronutrient carb;
    private Macronutrient protein;
    private Macronutrient fat;

    public Meal(Customer customer) {
        MacronutrientMealFactory factory = MacronutrientMealFactoryImpl.getInstance();
        CarbsFactory carbsFactory = factory.createCarbsFactory();
        ProteinFactory proteinFactory = factory.createProteinFactory();
        FatFactory fatFactory = factory.createFatFactory();

        DietPlan dietPlan = customer.getDietPlan();

        switch (dietPlan) {
            case NO_RESTRICTION:
                carb = carbsFactory.createCarb();
                protein = proteinFactory.createProtein();
                fat = fatFactory.createFat();
                break;
            case PALEO:
                List<Macronutrient> paleoCarbsList = new ArrayList<>();
                paleoCarbsList.add(new Pistachio());
                carb = paleoCarbsList.get(new Random().nextInt(paleoCarbsList.size()));

                List<Macronutrient> paleoProteinsList = new ArrayList<>();
                paleoProteinsList.add(new Fish());
                paleoProteinsList.add(new Chicken());
                paleoProteinsList.add(new Beef());
                protein = paleoProteinsList.get(new Random().nextInt(paleoProteinsList.size()));

                List<Macronutrient> paleoFatsList = new ArrayList<>();
                paleoFatsList.add(new Avocado());
                paleoFatsList.add(new Peanuts());
                paleoFatsList.add(new Tuna());
                fat = paleoFatsList.get(new Random().nextInt(paleoFatsList.size()));
                break;
            case VEGAN:
                List<Macronutrient> veganCarbsList = new ArrayList<>();
                veganCarbsList.add(new Bread());
                veganCarbsList.add(new Lentils());
                veganCarbsList.add(new Pistachio());
                carb = veganCarbsList.get(new Random().nextInt(veganCarbsList.size()));

                List<Macronutrient> veganProteinsList = new ArrayList<>();
                veganProteinsList.add(new Tofu());
                protein = veganProteinsList.get(new Random().nextInt(veganProteinsList.size()));

                List<Macronutrient> veganFatsList = new ArrayList<>();
                veganFatsList.add(new Avocado());
                veganFatsList.add(new Peanuts());
                fat = veganFatsList.get(new Random().nextInt(veganFatsList.size()));
                break;
            case NUT_ALLERGY:
                List<Macronutrient> nutAllergyList = new ArrayList<>();
                nutAllergyList.add(new Cheese());
                nutAllergyList.add(new Bread());
                nutAllergyList.add(new Lentils());
                carb = nutAllergyList.get(new Random().nextInt(nutAllergyList.size()));

                protein = proteinFactory.createProtein();

                List<Macronutrient> nutAllergyFatsList = new ArrayList<>();
                nutAllergyFatsList.add(new Avocado());
                nutAllergyFatsList.add(new SourCream());
                nutAllergyFatsList.add(new Tuna());
                fat = nutAllergyFatsList.get(new Random().nextInt(nutAllergyFatsList.size()));
                break;
        }
    }

    public void printMeal() {
        System.out.println("Carb: " + carb.getName());
        System.out.println("Protein: " + protein.getName());
        System.out.println("Fat: " + fat.getName());
    }
}

// Driver
public class MacronutrientMeals {
    public static void main(String[] args) {
        // Customers and their diet plans
        Customer customer1 = new Customer("Billy Bronco", DietPlan.NO_RESTRICTION);
        Customer customer2 = new Customer("Wolverine", DietPlan.PALEO);
        Customer customer3 = new Customer("Goku", DietPlan.VEGAN);
        Customer customer4 = new Customer("Storm", DietPlan.NUT_ALLERGY);
        Customer customer5 = new Customer("Achilles", DietPlan.NO_RESTRICTION);
        Customer customer6 = new Customer("Jean", DietPlan.PALEO);

        // Creating a customer's meal for their respective diet
        Meal meal1 = new Meal(customer1);
        Meal meal2 = new Meal(customer2);
        Meal meal3 = new Meal(customer3);
        Meal meal4 = new Meal(customer4);
        Meal meal5 = new Meal(customer5);
        Meal meal6 = new Meal(customer6);

        // Meal plans for 6 customers
        System.out.println("A no restriction meal for " + customer1.getName() + ".");
        meal1.printMeal();
        System.out.println();
        System.out.println("A paleo meal for " + customer2.getName() + ".");
        meal2.printMeal();
        System.out.println();
        System.out.println("A vegan meal for " + customer3.getName() + ".");
        meal3.printMeal();
        System.out.println();
        System.out.println("A nut allergy meal for " + customer4.getName() + ".");
        meal4.printMeal();
        System.out.println();
        System.out.println("A no restriction meal for " + customer5.getName() + ".");
        meal5.printMeal();
        System.out.println();
        System.out.println("A paleo meal for " + customer6.getName() + ".");
        meal6.printMeal();
    }
}