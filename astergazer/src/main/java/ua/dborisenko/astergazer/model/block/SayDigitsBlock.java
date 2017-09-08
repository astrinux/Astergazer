package ua.dborisenko.astergazer.model.block;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SayDigits")
public class SayDigitsBlock extends Block {

    public SayDigitsBlock() {
        application = "SayDigits";
        parametersCount = 1;
    }
}
