package aulas.aula_19_10;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPerson {
    @Test
    public void testIncAge(){
        Person p = new Person();
        p.name="AA";
        p.age=13;
        assertEquals(13, p.age);
        p.incAge();
        assertEquals(14, p.age);
    }
    @Test
    public void testToString() {
        Person p = new Person();
        p.name="AA";
        p.age=13;
        assertEquals("AA - 13 years", p.toString());
    }
}
