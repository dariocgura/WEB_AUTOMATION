package com.nttdata.steps;

import com.nttdata.page.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.bidi.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.By.linkText;


public class LoginSteps {

    private WebDriver driver;

    //constructor
    public LoginSteps(WebDriver driver){
        this.driver = driver;
    }

    /**
     * Escribir el usuario
     * @param user el usuario
     */
    public void typeUser(String user){
        WebElement userInputElement = driver.findElement(LoginPage.userInput);
        userInputElement.sendKeys(user);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(444));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.submitLogin));


    }

    /**
     * Escribir el password
     * @param password el password del usuario
     */
    public void typePassword(String password){
        this.driver.findElement(LoginPage.passInput).sendKeys(password);
    }

    /**
     * Hacer click en el botón login
     */
    public void login(){
        this.driver.findElement(LoginPage.submitLogin).click();
    }

    public void navegocategoria(String categoria, String subcategoria){
        try {
            // Obtener los textos de las categorías
            String category1Text = driver.findElement(LoginPage.categoryClothes).getText();
            String category2Text = driver.findElement(LoginPage.categoryAcce).getText();
            String category3Text = driver.findElement(LoginPage.categoryArt).getText();

            // Comparar los textos con el parámetro `category`
            if (category1Text.equalsIgnoreCase(categoria)) {
                driver.findElement(LoginPage.categoryClothes).click();
            } else if (category2Text.equalsIgnoreCase(categoria)) {
                driver.findElement(LoginPage.categoryAcce).click();
            } else if  (category3Text.equalsIgnoreCase(categoria)) {
                driver.findElement(LoginPage.categoryArt).click();
            }else {

                System.out.println("Categoría no encontrada: " + categoria);
                throw new RuntimeException("La categoría no existe en la página.");
            }

        } catch (Exception e) {
            System.out.println("Error al navegar a la categoría: " + e.getMessage());
            throw e; // Re-lanzar la excepción para que el test falle
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.subcategoryMen));
        driver.findElement(LoginPage.subcategoryMen).click();

    }

    public void agregarProductos(){
        driver.findElement(LoginPage.primerProducto).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.aumento));
        driver.findElement(LoginPage.aumento).click();
        driver.findElement(LoginPage.addCarrito).click();


    }

    public void validarPopupVisible() {
        try {
            // Esperar a que el popup sea visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement popupElement = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.textoCar));

            // Verificar si el popup es visible
            if (popupElement.isDisplayed()) {
                System.out.println("El popup es visible.");
            } else {
                System.out.println("El popup no es visible.");
                throw new AssertionError("El popup no es visible en la página.");
            }

        } catch (Exception e) {
            System.out.println("Error al validar la visibilidad del popup: " + e.getMessage());
            throw e; // Re-lanzar la excepción para que el test falle
        }
    }

    public void validarTotalPopup()  {

            // Esperar a que el popup sea visible
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            // Obtener la cantidad de artículos desde el XPath
            WebElement cantidadElement = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.textoCantidad));
            int cantidadArticulos = Integer.parseInt(cantidadElement.getText());  // Convertir texto a entero

            // Obtener el valor de cada artículo desde el XPath
            WebElement valorElement = driver.findElement(LoginPage.textoPrecio);
            String textoValor = valorElement.getText();  // Obtener el texto del elemento

            // Limpiar el texto eliminando comas y cualquier otro carácter no numérico
            String textoValorLimpio = textoValor.replace(",", "").replace("S/", "").trim();

            // Convertir el texto limpio a un número
            double valorArticulo = Double.parseDouble(textoValorLimpio);  // Convertir a un número double


            // Obtener el total mostrado en el popup desde el XPath
            WebElement totalElement = driver.findElement(LoginPage.textoTotal);
            String totalPopuptxt = totalElement.getText().replace(",", "").replace("S/", "").trim();  // Eliminar comas y el símbolo de moneda

            // Usar una expresión regular para extraer solo los números (incluyendo decimales)
            Pattern pattern = Pattern.compile("[0-9.]+");
            Matcher matcher = pattern.matcher(totalPopuptxt);

            String valorNumerico = "";
            if (matcher.find()) {
                valorNumerico = matcher.group(0); // Extrae el primer valor numérico encontrado
                System.out.println("Valor numérico extraído: " + valorNumerico);
            } else {
                System.out.println("No se encontraron números en el texto.");

            }

            // Convertir el valor a un tipo numérico
            double totalPopup = Double.parseDouble(valorNumerico);  // Convertir el valor extraído a double
            System.out.println("Valor numérico como double: " + totalPopup);

            // Calcular el total esperado
            double totalEsperado = cantidadArticulos * valorArticulo;

            // Validar si el total calculado coincide con el total mostrado en el popup
            if (Math.abs(totalEsperado - totalPopup) < 0.01) {  // Comparación con un pequeño margen de error
                System.out.println("El total es correcto: " + totalPopup);
            } else {
                System.out.println("Error en el total. Esperado: " + totalEsperado + ", pero se obtuvo: " + totalPopup);
                throw new AssertionError("El total calculado no coincide con el total mostrado en el popup.");
            }


    }

    public void endCompra(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement botonCerrarPopup = wait.until(ExpectedConditions.elementToBeClickable(LoginPage.botonFinaliza));

        // Hacer clic en el botón
        botonCerrarPopup.click();
        System.out.println("Se ha hecho clic en el botón del popup.");
    }

    public void validarTitulo(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Localizar el título del popup (supongamos que el XPath es LoginPage.tituloPopup)
        WebElement tituloPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.carritoTitulo));

        // Validar que el título esté presente (solo verificamos que no sea null y esté visible)
        if (tituloPopup.isDisplayed()) {
            System.out.println("El título del popup es visible.");
        } else {
            System.out.println("El título del popup no es visible.");
            throw new AssertionError("El título del popup no es visible.");
        }
    }


    public void validarTotalPopupEnd() {

        // Esperar a que el popup sea visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement cantidadElement = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.finalCantidad));

        // Obtener el texto del elemento
        String cantidadTexto = cantidadElement.getText();

        // Usar una expresión regular para extraer los números
        Pattern pattern = Pattern.compile("\\d+"); // Encuentra secuencias de dígitos
        Matcher matcher = pattern.matcher(cantidadTexto);

        int cantidadArticulos = 0; // Valor por defecto si no hay números en el texto
        if (matcher.find()) {
            // Convertir el primer número encontrado a entero
            cantidadArticulos = Integer.parseInt(matcher.group());
            System.out.println("Cantidad de artículos: " + cantidadArticulos);
        } else {
            System.out.println("No se encontraron números en el texto: " + cantidadTexto);
        }






        // Obtener el valor de cada artículo desde LoginPage.finalPrecio
        WebElement valorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.finalPrecio));
        String textoValor = valorElement.getText();  // Obtener el texto del elemento

        // Limpiar el texto eliminando comas y cualquier otro carácter no numérico
        String textoValorLimpio = textoValor.replace(",", "").replace("S/", "").trim();

        // Convertir el texto limpio a un número
        double valorArticulo = 0;
        try {
            valorArticulo = Double.parseDouble(textoValorLimpio);  // Convertir a un número double
            System.out.println("Valor del artículo: " + valorArticulo);
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir el valor del artículo: " + textoValorLimpio);
            throw e;  // Lanza la excepción si no se puede convertir
        }

        // Obtener el total mostrado en el popup desde LoginPage.finalTotal
        WebElement totalElement = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.finalTotal));
        String totalPopuptxt = totalElement.getText().replace(",", "").replace("S/", "").trim();  // Eliminar comas y el símbolo de moneda

        // Usar una expresión regular para extraer solo los números (incluyendo decimales)
        pattern = Pattern.compile("[0-9.]+");
        matcher = pattern.matcher(totalPopuptxt);

        String valorNumerico = "";
        if (matcher.find()) {
            valorNumerico = matcher.group(0); // Extrae el primer valor numérico encontrado
            System.out.println("Valor numérico extraído: " + valorNumerico);
        } else {
            System.out.println("No se encontraron números en el texto.");
            throw new IllegalArgumentException("No se pudo extraer un valor numérico del total.");
        }

        // Convertir el valor a un tipo numérico
        double totalPopup = 0;
        try {
            totalPopup = Double.parseDouble(valorNumerico);  // Convertir el valor extraído a double
            System.out.println("Valor numérico como double: " + totalPopup);
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir el total mostrado en el popup: " + valorNumerico);
            throw e;  // Lanza la excepción si no se puede convertir
        }

        // Calcular el total esperado
        double totalEsperado = cantidadArticulos * valorArticulo;

        // Validar si el total calculado coincide con el total mostrado en el popup
        if (Math.abs(totalEsperado - totalPopup) < 0.01) {  // Comparación con un pequeño margen de error
            System.out.println("El total es correcto: " + totalPopup);
        } else {
            System.out.println("Error en el total. Esperado: " + totalEsperado + ", pero se obtuvo: " + totalPopup);
            throw new AssertionError("El total calculado no coincide con el total mostrado en el popup.");
        }
    }


    public void validarNOAUT(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            // Suponiendo que el mensaje de error tiene un XPath específico
            WebElement mensajeError = wait.until(ExpectedConditions.visibilityOfElementLocated(LoginPage.errAut));

            // Si llegamos aquí, el mensaje de error ha aparecido
            System.out.println("Se ha mostrado el mensaje de error.");

            // Opcionalmente, puedes obtener el texto del mensaje y validarlo
            String mensajeTexto = mensajeError.getText();
            System.out.println("Mensaje de error: " + mensajeTexto);

        } catch (Exception e) {
            // Si no se encuentra el mensaje de error
            System.out.println("No se mostró el mensaje de error.");
        }
    }








}
