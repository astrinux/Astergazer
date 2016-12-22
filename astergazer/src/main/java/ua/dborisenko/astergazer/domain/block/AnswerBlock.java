package ua.dborisenko.astergazer.domain.block;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Answer")
public class AnswerBlock extends Block {
    
    public AnswerBlock() {
       application = "Answer";
       parametersCount = 1;
    }
    
}
