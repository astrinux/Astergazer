package ua.dborisenko.astergazer.domain.block;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class SendDtmfBlockTest {

    private BlockTestUtil blockTestUtil = new BlockTestUtil();

    @Test
    public void translateTest() {
        String caption = "caption";
        String[] parameters = { "digits", "timeout", "duration", "channel" };
        String expectedResult = Block.COMMAND_PREFIX + caption + "),SendDTMF(digits,timeout,duration,channel)\n";
        SendDtmfBlock block = new SendDtmfBlock();
        blockTestUtil.setBlockParameters(block, caption, parameters);

        assertThat(block.translate(), is(expectedResult));
    }
}
