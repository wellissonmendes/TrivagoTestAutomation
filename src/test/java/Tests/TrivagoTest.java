package Tests;

import Pages.TrivagoHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;

import static org.asynchttpclient.util.Assertions.assertNotNull;

public class TrivagoTest {


    private WebDriver driver;
    private TrivagoHomePage trivagoHomePage;

    @BeforeEach
    public void setUp() {
        // Setup do WebDriver usando WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.trivago.com.br/");
        trivagoHomePage = new TrivagoHomePage(driver);
    }

    @AfterEach
    public void tearDown() {
        // Fechar o navegador após cada teste
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testHotelSearch() throws InterruptedException, IOException {
        String local = "Manaus";
        trivagoHomePage.pesquisar(local);
        Thread.sleep(1000);

        // Ordenar por "Avaliações e Sugestões"
        trivagoHomePage.ordenarMelhoresAvaliacoes();

        // Verificar o primeiro nome da lista
        String nomePrimeiroHotel = trivagoHomePage.obterNomePrimeiroHotel();
        assertNotNull(nomePrimeiroHotel, "O nome do primeiro hotel não deve ser nulo");
        System.out.println("Primeiro hotel listado: " + nomePrimeiroHotel);

        // Verificar a avaliação do primeiro hotel
        String AvaliacaoPrimeiroHotel = trivagoHomePage.obterAvaliacaoPrimeiroHotel();
        assertNotNull(AvaliacaoPrimeiroHotel, "A avaliação do primeiro hotel não deve ser nula");
        System.out.println("Avaliação do primeiro hotel: " + AvaliacaoPrimeiroHotel);

        // Verificar o valor do primeiro hotel
        String valorDiariaPrimeiroHotel = trivagoHomePage.obterPrecoPrimeiroHotelDiaria();
        assertNotNull(valorDiariaPrimeiroHotel, "O valor do primeiro hotel não deve ser nulo");
        System.out.println("Valor da diária do primeiro hotel: " + valorDiariaPrimeiroHotel);

        String valorTotalPrimeiroHotel = trivagoHomePage.obterPrecoPrimeiroHotelTotal();
        assertNotNull(valorTotalPrimeiroHotel, "O valor do primeiro hotel não deve ser nulo");
        System.out.println("Valor total do primeiro hotel: " + valorTotalPrimeiroHotel);
    }


}
