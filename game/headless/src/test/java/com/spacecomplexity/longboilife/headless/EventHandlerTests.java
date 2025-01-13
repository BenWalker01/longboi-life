// NEW FOR PART 2

package com.spacecomplexity.longboilife.headless;

import java.util.function.Function;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.spacecomplexity.longboilife.game.utils.EventHandler;
import com.spacecomplexity.longboilife.game.utils.EventHandler.Event;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EventHandlerTests extends AbstractHeadlessGdxTest {
    private EventHandler eventHandler;
    private Function<Object[], Object> testFunction;

    @BeforeEach
    public void setup() {
        eventHandler = new EventHandler();
        testFunction = (params) -> {
            return new Object();
        };
    }

    @Test
    public void testCreateEventAndCallEvent() {
        testFunction = mock();
        eventHandler.createEvent(Event.BUILD, testFunction);
        eventHandler.callEvent(Event.BUILD);
        verify(testFunction).apply(new Object[0]);
    }

    @Test
    public void testCreateEventFailure() {
        assertThrows(IllegalArgumentException.class, () -> eventHandler.createEvent(null, testFunction));
    }

    @Test
    public void testCallEventFailureNoCallbackDefined() {
        assertThrows(IllegalArgumentException.class, () -> eventHandler.callEvent(Event.BUILD));
    }

    @Test
    public void testCallEventFailureEventIsNull() {
        assertThrows(IllegalArgumentException.class, () -> eventHandler.callEvent(null));
    }

    @AfterEach
    public void destroy() {
        testFunction = null;
    }
}
