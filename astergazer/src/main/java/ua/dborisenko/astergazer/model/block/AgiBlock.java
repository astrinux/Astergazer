package ua.dborisenko.astergazer.model.block;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Agi")
public class AgiBlock extends Block {

    public AgiBlock() {
        application = "AGI";
        parametersCount = 1;
    }
}
