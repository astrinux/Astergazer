package ua.dborisenko.astergazer.domain.block;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
public class AgiBlockTest {

    private BlockTestUtil blockTestUtil = new BlockTestUtil();
    
    @Test
    public void translateTest() {
        String caption = "caption";
        String[] parameters = {"command"};
        String expectedResult = Block.COMMAND_PREFIX + caption + "),AGI(command)\n";
        AgiBlock block = new AgiBlock();
        blockTestUtil.setBlockParameters(block, caption, parameters);
        
        assertThat(block.translate(), is(expectedResult));
    }

}
