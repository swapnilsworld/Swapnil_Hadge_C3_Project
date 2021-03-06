import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");
    Restaurant r = new Restaurant("swapnil","pune",openingTime , closingTime);

    Restaurant rspied = Mockito.spy(r);

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime mockCurrTime = LocalTime.parse("11:00");
        Mockito.when(rspied.getCurrentTime()).thenReturn(mockCurrTime);
        Assertions.assertTrue(rspied.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        LocalTime mockCurrTime = LocalTime.parse("23:59");
        Mockito.when(rspied.getCurrentTime()).thenReturn(mockCurrTime);
        Assertions.assertFalse(rspied.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void order_value_should_get_cumulative_total_when_collection_of_items_selected(){
        getTestRestaurant();
        List<String> selectedMenu = new ArrayList<String>();
        //Test1
        selectedMenu.add(restaurant.getMenu().get(0).getName());
        selectedMenu.add(restaurant.getMenu().get(2).getName());
        int totalOrderValue = restaurant.getOrderValue(selectedMenu);
        Assertions.assertEquals(totalOrderValue,50);

        // Test2
        selectedMenu.add(restaurant.getMenu().get(1).getName());
        totalOrderValue = restaurant.getOrderValue(selectedMenu);
        Assertions.assertEquals(totalOrderValue,72);
    }

    private void getTestRestaurant() {
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("poha",30);
        restaurant.addToMenu("kachori", 22);
        restaurant.addToMenu("samosa", 20);
        restaurant.addToMenu("wada pav", 25);
    }
}