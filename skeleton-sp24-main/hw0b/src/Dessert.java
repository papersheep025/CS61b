public class Dessert {
    private static int numDesserts = 0;

    private String flavor;
    private int price;

    public Dessert (String flavor, int price) {
        this.flavor = flavor;
        this.price = price;
        Dessert.numDesserts++;
    }

    public void printDessert() {
        System.out.println(flavor + " " + price + " " + Dessert.numDesserts);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
        
        Dessert dessert1 = new Dessert("1", 2);
        Dessert dessert2 = new Dessert("3", 4);

        dessert1.printDessert();
        dessert2.printDessert();
    }
}
