package ua.dborisenko.astergazer.model.block;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Transfer")
public class TransferBlock extends Block {

    public TransferBlock() {
        application = "Transfer";
        parametersCount = 1;
    }
}
