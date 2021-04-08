package algs.seventask;

class Item {
    public  String name;
    public  double weigth;
    public  double price;

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", weigth=" + weigth +
                ", price=" + price +
                '}';
    }

    public Item(String name, double weight, double price)
    {
        this.name = name;
        this.weigth = weight;
        this.price = price;
    }
}
