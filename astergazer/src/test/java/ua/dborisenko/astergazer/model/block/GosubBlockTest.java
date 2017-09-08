package ua.dborisenko.astergazer.model.block;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class GosubBlockTest {

    private BlockTestUtil blockTestUtil = new BlockTestUtil();

    @Test
    public void translateTest() {
        String caption = "caption";
        String[] parameters = { "context", "extension", "priority", "arguments" };
        String expectedResult = Block.COMMAND_PREFIX + caption + "),Gosub(context,extension,priority(arguments))\n";
        GosubBlock block = new GosubBlock();
        blockTestUtil.setBlockParameters(block, caption, parameters);

        assertThat(block.translate(), is(expectedResult));
    }

    @Test
    public void translateNoArgumentsTest() {
        String caption = "caption";
        String[] parameters = { "context", "extension", "priority" };
        String expectedResult = Block.COMMAND_PREFIX + caption + "),Gosub(context,extension,priority)\n";
        GosubBlock block = new GosubBlock();
        blockTestUtil.setBlockParameters(block, caption, parameters);

        assertThat(block.translate(), is(expectedResult));
    }
}
