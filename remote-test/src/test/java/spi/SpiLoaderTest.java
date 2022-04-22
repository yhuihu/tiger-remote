package spi;

import com.yhhu.remote.spi.SpiDirector;
import org.junit.Test;

import java.io.IOException;

/**
 * @author yhhu
 * @date 2022/4/22
 * @description
 */
public class SpiLoaderTest {
    @Test
    public void testSpiDirector() throws IOException {
        SpiDirector spiDirector = SpiDirector.getInstance();
    }
}
