import java.util.ArrayList;
import java.util.List;

// Pizza class with nested Builder class
class Pizza {
    private String size;
    private List<String> toppings;
    private String pizzaChain;

    private Pizza(String size, List<String> toppings, String pizzaChain) {
        this.size = size;
        this.toppings = toppings;
        this.pizzaChain = pizzaChain;

    }

    public void eat() {
        if (pizzaChain == null){
            System.out.println("Eating a " + size + " with toppings: " + String.join(", ", toppings) + ".");
        } else {
            System.out.println("Eating a " + size + " pizza from " + pizzaChain + " with toppings: " + String.join(", ", toppings) + ".");
        }
    }

    // Static nested Builder class
    public static class PizzaBuilder {
        private String size;
        private List<String> toppings = new ArrayList<>();
        private String pizzaChain;

        public PizzaBuilder(){
        }

        public PizzaBuilder setSize(String size){
            this.size = size;
            return this;
        }

        public PizzaBuilder addTopping(String topping) {
            toppings.add(topping);
            return this;
        }

        public PizzaBuilder setPizzaChain(String pizzaChain){
            this.pizzaChain = pizzaChain;
            return this;
        }

        public PizzaBuilder addPepperoni(){
            toppings.add("Pepperoni");
            return this;
        }

        public PizzaBuilder addSausage(){
            toppings.add("Sausage");
            return this;
        }

        public PizzaBuilder addMushrooms(){
            toppings.add("Mushrooms");
            return this;
        }

        public PizzaBuilder addBacon(){
            toppings.add("Bacon");
            return this;
        }

        public PizzaBuilder addOnions(){
            toppings.add("Onions");
            return this;
        }

        public PizzaBuilder addExtraCheese(){
            toppings.add("Extra Cheese");
            return this;
        }

        public PizzaBuilder addPeppers(){
            toppings.add("Peppers");
            return this;
        }

        public PizzaBuilder addChicken(){
            toppings.add("Chicken");
            return this;
        }

        public PizzaBuilder addOlives(){
            toppings.add("Olives");
            return this;
        }

        public PizzaBuilder addSpinach(){
            toppings.add("Spinach");
            return this;
        }

        public PizzaBuilder addTomatoAndBasil(){
            toppings.add("Tomato and Basil");
            return this;
        }

        public PizzaBuilder addBeef(){
            toppings.add("Beef");
            return this;
        }

        public PizzaBuilder addHam(){
            toppings.add("Ham");
            return this;
        }

        public PizzaBuilder addPesto(){
            toppings.add("Pesto");
            return this;
        }

        public PizzaBuilder addSpicyPork(){
            toppings.add("Spicy Pork");
            return this;
        }

        public PizzaBuilder addHamAndPineapple(){
            toppings.add("Ham and Pineapple");
            return this;
        }


        public Pizza createPizza(){
            return new Pizza(size, toppings, pizzaChain);
        }
    }
}
public class PizzaAndMovies {
    public static void main(String[] args) {
        // Three pizzas with 3, 6, and 9 toppings
        Pizza p1 = new Pizza.PizzaBuilder().setSize("Small").addPepperoni().addSausage().addMushrooms().createPizza();
        Pizza p2 = new Pizza.PizzaBuilder().setSize("Medium").addBacon().addOnions().addExtraCheese().addPeppers().addChicken().addOlives().createPizza();
        Pizza p3 = new Pizza.PizzaBuilder().setSize("Large").addSpinach().addTomatoAndBasil().addBeef().addHam().addPesto().addSpicyPork().addHamAndPineapple().addPepperoni().addSausage().createPizza();

        // Eat Pizzas
        p1.eat();
        p2.eat();
        p3.eat();

        System.out.println();
        System.out.println();

        // Pizza Hut
        Pizza ph1 = new Pizza.PizzaBuilder().setSize("Large").setPizzaChain("Pizza Hut").addPepperoni().addSausage().addMushrooms().createPizza();
        Pizza ph2 = new Pizza.PizzaBuilder().setSize("Small").setPizzaChain("Pizza Hut").addBacon().addOnions().createPizza();

        ph1.eat();
        ph2.eat();

        System.out.println();
        System.out.println();

        // Little Caesars
        Pizza lt1 = new Pizza.PizzaBuilder().setSize("Medium").setPizzaChain("Little Caesars").addTomatoAndBasil().addBeef().addHam().addPesto().addSpicyPork().addHamAndPineapple().addPepperoni().addSausage().createPizza();
        Pizza lt2 = new Pizza.PizzaBuilder().setSize("Small").setPizzaChain("Little Caesars").addBacon().addOnions().addExtraCheese().addPeppers().addChicken().addOlives().createPizza();
        lt1.eat();
        lt2.eat();

        System.out.println();
        System.out.println();

        // Dominos
        Pizza d1 = new Pizza.PizzaBuilder().setSize("Small").setPizzaChain("Dominos").addSpinach().createPizza();
        Pizza d2 = new Pizza.PizzaBuilder().setSize("Large").setPizzaChain("Dominos").addPepperoni().addSausage().addMushrooms().createPizza();
        d1.eat();
        d2.eat();
    }
}
