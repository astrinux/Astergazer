package ua.dborisenko.astergazer.domain.block;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SipAddHeaderlBlockTest {
    
    private BlockTestUtil blockTestUtil = new BlockTestUtil();
    
    @Test
    public void translateTest() {
        String caption = "caption";
        String[] parameters = { "header", "content" };
        String expectedResult = Block.COMMAND_PREFIX + caption + "),SIPAddHeader(header:content)\n";
        SipAddHeaderBlock block = new SipAddHeaderBlock();
        blockTestUtil.setBlockParameters(block, caption, parameters);
        
        assertThat(block.translate(), is(expectedResult));
    }
}
