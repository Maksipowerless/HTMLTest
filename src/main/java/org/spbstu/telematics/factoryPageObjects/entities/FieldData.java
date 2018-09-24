package org.spbstu.telematics.factoryPageObjects.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.flotsam.xeger.Xeger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FieldData {
    String regularEmail;
    String regularName;
    String regularSex;
    String email;
    String name;
    String sex;

    public void setRandomStrings(){
        Xeger genetation = new Xeger(regularEmail);
        email = genetation.generate();

        genetation = new Xeger(regularName);
        name = genetation.generate();

        genetation = new Xeger(regularSex);
        sex = genetation.generate();
    }
}
