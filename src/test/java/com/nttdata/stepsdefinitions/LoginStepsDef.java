package com.nttdata.stepsdefinitions;

import com.nttdata.page.LoginPage;
import com.nttdata.steps.LoginSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;


public class LoginStepsDef {


    private static final Logger log = LoggerFactory.getLogger(LoginStepsDef.class);
    private WebDriver driver;




    @Given("estoy en la página de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {
        driver = getDriver();
        driver.get("https://qalab.bensg.com/store/pe/");
        driver.findElement(LoginPage.loginBotton).click();
        screenShot();

    }

    @And("me logueo con mi usuario {string} y clave {string}")
    public void meLogueoConMiUsuarioYClave(String user, String password) {
        LoginSteps loginSteps = new LoginSteps(driver);
        loginSteps.typeUser(user);
        loginSteps.typePassword(password);
        loginSteps.login();
        screenShot();
    }

    @When("navego a la categoria {string} y subcategoria {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        LoginSteps loginSteps =new LoginSteps(driver);
        loginSteps.navegocategoria(categoria,subcategoria);
        screenShot();
    }

    @And("agrego unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito() {
        LoginSteps loginSteps =new LoginSteps(driver);

        loginSteps.agregarProductos();
        screenShot();
    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmacionDelProductoAgregado() {
        LoginSteps loginSteps =new LoginSteps(driver);
        loginSteps.validarPopupVisible();
        screenShot();

    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        LoginSteps loginSteps =new LoginSteps(driver);
        loginSteps.validarTotalPopup();
        screenShot();
    }





    @When("finalizo la compra")
    public void finalizoLaCompra() {
        LoginSteps loginSteps =new LoginSteps(driver);
        loginSteps.endCompra();

    }


    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        LoginSteps loginSteps =new LoginSteps(driver);
        loginSteps.validarTitulo();

    }


    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        LoginSteps loginSteps =new LoginSteps(driver);
        loginSteps.validarTotalPopupEnd();

    }

    @Then("valido autentificacion")
    public void validoAutentificacion() {
        LoginSteps loginSteps =new LoginSteps(driver);
        loginSteps.validarNOAUT();

    }
}
