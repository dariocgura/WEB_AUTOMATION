@test1
Feature: Product-Store
  Scenario Outline: SuccesLogin
    Given estoy en la página de la tienda
    And me logueo con mi usuario "dario.s.ricardo@gmail.com" y clave "Laesperanza2017"
    When navego a la categoria "<category>" y subcategoria "Men"
    And agrego unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito
    Examples:
      | category |
      | Clothes  |
    | Autos    |


  @test2
    Scenario: FailLogin
      Given estoy en la página de la tienda
      When me logueo con mi usuario "dagggg@gmail.com" y clave "Laespera"
      Then valido autentificacion

