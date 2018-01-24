package pt.lsts.imc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IMCDefinitionTest {
    @Test
    public void create() throws Exception {
        IMCDefinition imcDefinition = IMCDefinition.getInstance();
        Announce message = (Announce)imcDefinition.create("Announce");

        message.setTimestamp(1.0);
        assertEquals(message.getTimestamp(), 1.0, 0.000000001);

        message.setSrc(10);
        assertEquals(message.getSrc(), 10);

        message.setSrcEnt(11);
        assertEquals(message.getSrcEnt(), 11);

        message.setDst(12);
        assertEquals(message.getDst(), 12);

        message.setDstEnt(13);
        assertEquals(message.getDstEnt(), 13);
    }
}
