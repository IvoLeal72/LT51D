package trab1.grupo2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import trab1.grupo3.InvalidFormatException;

import java.rmi.UnexpectedException;

import static org.junit.jupiter.api.Assertions.*;

public class TestMultiScoreException {
    private static Score scoreTest;

    public static Score getScoreInstance() throws ScoreException{
        if ( scoreTest == null )
            scoreTest = new MultiScore( new SingleScore(150),
                   new SingleScore(2, 100),
                   new SingleScore(5, 80)
                                                 );
        return scoreTest;
    }

    @Test
    public void testGetters() {
        try {
            Score s = getScoreInstance();
            assertEquals(330, s.getPoints());
            assertEquals(5, s.getLevel());
        } catch ( ScoreException e ) {
            Assertions.fail("Unexpected exception: " + e.getMessage());
        }
    }

    @Test
    public void testToString( ) throws ScoreException {
         assertEquals( "5: 330(0: 150,2: 100,5: 80)", getScoreInstance().toString());
    }

    @Test
    public void testMultiLevel() throws ScoreException {
        Score s = getScoreInstance();
        assertTrue(  s instanceof  MultiLevel );
        MultiLevel ml = (MultiLevel) scoreTest;
        assertEquals(  5, ml.getLevel()  );
        assertEquals(  0, ml.getLowerLevel() );
    }

    @Test
    public void testExceptionLessTwoScore() {
        ScoreException ex = assertThrows(
                ScoreException.class,
                () -> new MultiScore(new SingleScore(100))
        );
        assertTrue(ex.getMessage().contains("invalid number of scores"));
    }

    @Test
    public void testExceptionEqualsLevels() {
        ScoreException ex = assertThrows(
                ScoreException.class,
                () -> new MultiScore(new SingleScore(100), new SingleScore(100))
        );
        assertTrue(ex.getMessage().contains("invalid sequence"));
    }

}
