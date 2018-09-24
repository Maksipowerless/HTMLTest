package org.spbstu.telematics.factoryPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.spbstu.telematics.factoryPageObjects.entities.FieldData;
import java.util.List;

public class QuestionnairePage {

    @FindBy(id = "dataEmail")
    WebElement email;

    @FindBy(id = "dataName")
    WebElement name;

    @FindBy(id = "dataGender")
    WebElement sex;

    @FindAll({@FindBy(xpath = "//*[@type='checkbox']")})
    List<WebElement> listOfCheckButtons;

    @FindAll({@FindBy(xpath = "//*[@type='radio']")})
    List<WebElement> listOfRadioButtons;

    @FindBy(id = "dataSend")
    WebElement buttonAdd;

    @FindBy(xpath = "//*[@class='uk-button uk-button-primary uk-modal-close']")
    WebElement buttonOK;

    @FindAll({@FindBy(xpath = "//*[@id='dataTable']//tr[*]")})
    List<WebElement> listOfDataTableRows;

    public void fillForm(FieldData data){
        email.sendKeys(data.getEmail());
        name.sendKeys(data.getName());
        String sex = data.getSex();
        this.sex.click();
        this.sex.findElement(By.xpath("./option[contains(text(), '" + sex + "')]")).click();
    }

    public void clearForm(){
        email.clear();
        name.clear();
    }

    public void clickChekButtons(int num){
        String mask = String.format("%2s", Integer.toBinaryString(num)).replace(' ', '0');
        int counter = listOfCheckButtons.size() - 1;
        for (WebElement el: listOfCheckButtons) {
            if (mask.charAt(counter) == '1')
                el.click();
            counter--;
        }
    }

    public void clearCheckButtons(){
        for (WebElement el: listOfCheckButtons) {
            if(el.isSelected())
                el.click();
        }
    }

    public void clickRadioButton(int num){
        if(num > 0)
            listOfRadioButtons.get(num - 1).click();
    }

    public void submit(){
        buttonAdd.click();
    }

    public void clickOK(){
            buttonOK.click();
    }

    public boolean isExistAddedRow(FieldData data){
        List<WebElement> cells = listOfDataTableRows.get(listOfDataTableRows.size() - 1).findElements(By.tagName("td"));
        String choice1 = new String();
        for (WebElement el: listOfCheckButtons) {
            if (el.isSelected()) {
                el = el.findElement(By.xpath("./.."));
                int size = el.getText().length();
                if(size > 3)
                    choice1 += el.getText().substring(size - 3, size) + ", ";
            }
        }
        if (choice1.isEmpty())
            choice1 = "Нет";
        else
            choice1 = choice1.substring(0, choice1.length() - 2);

        String choice2 = new String();
        for (WebElement el: listOfRadioButtons) {
            if (el.isSelected()) {
                el = el.findElement(By.xpath("./.."));
                int size = el.getText().length();
                if(size > 3)
                    choice2 += el.getText().substring(size - 3, size);
                break;
            }
        }

        return cells.get(0).getText().equals(data.getEmail()) &&
                cells.get(1).getText().equals(data.getName()) &&
                cells.get(2).getText().equals(data.getSex()) &&
                cells.get(3).getText().equals(choice1) &&
                cells.get(4).getText().equals(choice2);
    }
}
