package ua.dborisenko.astergazer.model.block;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Ringing")
public class RingingBlock extends Block {

    public RingingBlock() {
        application = "Ringing";
    }
}
