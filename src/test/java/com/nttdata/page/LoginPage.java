package com.nttdata.page;

import org.openqa.selenium.By;

public class LoginPage {

    //Localizadores de elementos
    public static By loginBotton = By.xpath("//*[@id=\"_desktop_user_info\"]/div");
    public static By userInput = By.xpath("//*[@id=\"field-email\"]");
    public static By passInput = By.xpath("//*[@id=\"field-password\"]");
    public static By submitLogin = By.xpath("//*[@id=\"submit-login\"]");

    public static By categoryClothes = By.xpath("//*[@id=\"category-3\"]/a");
    public static By categoryAcce = By.xpath("//*[@id=\"category-6\"]/a");
    public static By categoryArt = By.xpath("//*[@id=\"category-9\"]/a");

    public static By subcategoryMen = By.xpath("//*[@id=\"subcategories\"]/ul/li[1]/div[1]");

    public static By primerProducto = By.xpath("//*[@id=\"js-product-list\"]/div[1]/div/article/div/div[1]/a/picture/img");

    public static By aumento = By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[1]");

    public static By addCarrito = By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[2]/button");


    public static By textoCar = By.xpath("//*[@id=\"myModalLabel\"]");

    public static By textoCantidad = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/span[3]/strong");

    public static By textoPrecio = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[1]/div/div[2]/p");

    public static By textoTotal = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/p[4]/span[2]");

    public static By botonFinaliza = By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a");

    public static By carritoTitulo = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[1]/h1");

    public static By finalCantidad = By.xpath("//*[@id=\"cart-subtotal-products\"]/span[1]");

    public static By finalPrecio = By.xpath("//*[@id=\"main\"]/div/div[1]/div/div[2]/ul/li/div/div[2]/div[2]/div[2]/span");

    public static By finalTotal = By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[1]/div[2]/div[2]/span[2]");

    public static By errAut = By.xpath("//*[@id=\"content\"]/section/div/ul/li");




}
