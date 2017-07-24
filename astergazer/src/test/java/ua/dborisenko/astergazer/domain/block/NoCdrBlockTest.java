package ua.dborisenko.astergazer.domain.block;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class NoCdrBlockTest {

    @Test
    public void translateTest() {
        String caption = "caption";
        String expectedResult = Block.COMMAND_PREFIX + caption + "),NoCDR()\n";
        NoCdrBlock block = new NoCdrBlock();
        block.setCaption(caption);

        assertThat(block.translate(), is(expectedResult));
    }
}
