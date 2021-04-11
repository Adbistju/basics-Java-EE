import java.util.ArrayList;

public class ListProd {
    ArrayList<Product> productList = new ArrayList<Product>();

    public ListProd() {
        productList.add(new Product(0,"Банан", 11));
        productList.add(new Product(1,"Арбуз", 33));
        productList.add(new Product(2,"Гранат", 22));
        productList.add(new Product(3,"Виноград", 7));
        productList.add(new Product(4,"Яблоко", 9));
        productList.add(new Product(5,"f1", 111));
        productList.add(new Product(6,"f2", 222));
        productList.add(new Product(7,"f3", 333));
        productList.add(new Product(8,"f4", 444));
        productList.add(new Product(9,"f5", 555));
    }

    public Product getSearchProductIndex(String name){
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getTitle().equals(name)){
                return productList.get(i);
            }
        }
        return new Product(0, "NULL",0);
    }

    public Product getProductIndex(int number){
        return productList.get(number);
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
