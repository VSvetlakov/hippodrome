import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {

    @Test
    public void nullNameExeption(){
        assertThrows(IllegalArgumentException.class, () -> new Horse(null,1,1));
    }

    @Test
    public void nullNameMessage(){
        try{
            new Horse(null,1,1);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be null.",e.getMessage());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"","  ","\t\t","\n\n\n"})
    public void blankNameExeption(String name){
        assertThrows(IllegalArgumentException.class, () -> new Horse(name,1,1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"","  ","\t\t","\n\n\n"})
    public void blankNameMessage(String name){
        try{
            new Horse(name,1,1);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Name cannot be blank.",e.getMessage());
        }
    }

    @Test
    public void nullSpeedExeption(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("name",-1,1));
    }

    @Test
    public void nullSpeedMessage(){
        try{
            new Horse("name",-1,1);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Speed cannot be negative.",e.getMessage());
        }
    }

    @Test
    public void nullDistanceExeption(){
        assertThrows(IllegalArgumentException.class, () -> new Horse("name",1,-1));
    }

    @Test
    public void nullDistanceMessage(){
        try{
            new Horse("name",1,-1);
            fail();
        }catch (IllegalArgumentException e){
            assertEquals("Distance cannot be negative.",e.getMessage());
        }
    }

    @Test
    public void testGetName(){
        Horse horse = new Horse("name",1,1);

        assertEquals("name",horse.getName());

    }

    @Test
    public void testGetSpeed(){
        Horse horse = new Horse("name",1,1);

        assertEquals(1,horse.getSpeed());

    }

    @Test
    public void testGetDistance(){
        Horse horse = new Horse("name",1,1);

        assertEquals(1,horse.getDistance());

    }

    @Test
    public void testGetDistanceBalnk(){
        Horse horse = new Horse("name",1);

        assertEquals(0,horse.getDistance());

    }

    @Test
    public void moveUsesGetRandome(){
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("name",1,1).move();

            mockedStatic.verify(()->Horse.getRandomDouble(0.2,0.9));
        }
    }
}
