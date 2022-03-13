package com.projectjava.LACInventory;

/**
 * Class with stock the data of a product
 * @param idProduct   id of the product
 * @param nameProduct name of the product
 * @param costProduct price of the product
 */
public record Product(String idProduct, String nameProduct, Double costProduct) {

    /**
     * get the price of the product with add " $"
     *
     * @return price of the product
     */
    public String getCostProduct() {
        return costProduct + " $";
    }

    /**
     * get the id of the product
     *
     * @return id of the product
     */
    public String getIdProduct() {
        return idProduct;
    }

    /**
     * get the name of the product
     *
     * @return name of the product
     */
    public String getNameProduct() {
        return nameProduct;
    }
}
