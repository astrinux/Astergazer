package ua.dborisenko.astergazer.model.block;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ProgressBlockTest {

    @Test
    public void translateTest() {
        String caption = "caption";
        String expectedResult = Block.COMMAND_PREFIX + caption + "),Progress()\n";
        ProgressBlock block = new ProgressBlock();
        block.setCaption(caption);

        assertThat(block.translate(), is(expectedResult));
    }
}
