package ua.dborisenko.astergazer.domain.block;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ReturnBlockTest {

    private BlockTestUtil blockTestUtil = new BlockTestUtil();

    @Test
    public void translateTest() {
        String caption = "caption";
        String[] parameters = { "value" };
        String expectedResult = Block.COMMAND_PREFIX + caption + "),Return(value)\n";
        ReturnBlock block = new ReturnBlock();
        blockTestUtil.setBlockParameters(block, caption, parameters);

        assertThat(block.translate(), is(expectedResult));
    }
}
