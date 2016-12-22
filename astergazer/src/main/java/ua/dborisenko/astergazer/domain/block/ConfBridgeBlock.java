package ua.dborisenko.astergazer.domain.block;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ConfBridge")
public class ConfBridgeBlock extends Block {
    
    public ConfBridgeBlock() {
        application = "ConfBridge";
        parametersCount = 4;
    }
}
