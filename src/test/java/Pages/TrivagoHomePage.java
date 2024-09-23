package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrivagoHomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores
    private By searchInput = By.xpath("//*[@id='input-auto-complete']");
    private By selecionarProximoFinalSemana = By.xpath("//*[text()='Próximo fim de semana']");
    private By btnConfirmarHospedes = By.xpath("//*[text()='Confirmar']");

    private By textSugestoes = By.xpath("//*[contains(text(),'Sugestões')]");

    private By textAvaliacaoSugestoes = By.xpath("//*[contains(text(),'Avaliação e sugestões')]");
    private By searchButton = By.xpath("//*[text()='Pesquisar']");
    private By nomePrimeiroHotel = By.xpath("//li[1]//h2//span");
    private By avaliacaoPrimeiroHotel = By.xpath("(//li[1]//button[2]//span)[1]");
    private By valorPrimeiroHotelDiaria = By.xpath("//li[1]//div[2]//div[1]//div[2]//div//div/span");
    private By valorPrimeiroHotelTotal = By.xpath("//*[@data-testid='price-per-stay']");

    public TrivagoHomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Ação: Pesquisar por um destino
    public void pesquisar(String location) throws InterruptedException {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys(location);
        Thread.sleep(500);
        WebElement localText = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'" + location + "')]")));
        localText.click();
        WebElement selecionarProximoFinalSemana = wait.until(ExpectedConditions.elementToBeClickable(this.selecionarProximoFinalSemana));
        selecionarProximoFinalSemana.click();

        WebElement clicarConfirmarHospedes = wait.until(ExpectedConditions.elementToBeClickable(btnConfirmarHospedes));
        clicarConfirmarHospedes.click();

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(searchButton));
        button.click();
    }

    // Ação: Ordenar por "Avaliações e Sugestões"
    public void ordenarMelhoresAvaliacoes() throws InterruptedException {
        WebElement clickOrdenar = wait.until(ExpectedConditions.elementToBeClickable(textSugestoes));
        clickOrdenar.click();
        Thread.sleep(5000);
        WebElement clickAvaliacaoSugestoes = wait.until(ExpectedConditions.elementToBeClickable(textAvaliacaoSugestoes));
        clickAvaliacaoSugestoes.click();
        Thread.sleep(5000);
    }

    // Verificação: Nome do primeiro hotel
    public String obterNomePrimeiroHotel() {
        WebElement titulo = wait.until(ExpectedConditions.visibilityOfElementLocated(nomePrimeiroHotel));
        return titulo.getText();
    }

    // Verificação: Avaliação do primeiro hotel
    public String obterAvaliacaoPrimeiroHotel() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(avaliacaoPrimeiroHotel)).getText();
    }

    // Verificação: Preço do primeiro hotel
    public String obterPrecoPrimeiroHotelDiaria() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(valorPrimeiroHotelDiaria)).getText();
    }

    public String obterPrecoPrimeiroHotelTotal() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(valorPrimeiroHotelTotal)).getText();
    }


}
