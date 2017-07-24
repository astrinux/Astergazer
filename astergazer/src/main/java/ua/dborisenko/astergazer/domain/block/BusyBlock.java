package ua.dborisenko.astergazer.domain.block;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Busy")
public class BusyBlock extends Block {

    public BusyBlock() {
        application = "Busy";
        parametersCount = 1;
    }
}
