package com.spacecomplexity.longboilife.headless;

import com.spacecomplexity.longboilife.game.utils.Timer;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;

public class TimerTests extends AbstractHeadlessGdxTest {

    @Test
    public void testPauseTimer() {
        Timer timer = new Timer();
        timer.setTimer(1000);
        timer.pauseTimer();
        assertTrue(timer.isPaused());
    }

    @Test
    public void testResumeTimerIfPaused() {
        Timer timer = new Timer();
        timer.setTimer(1000);
        timer.pauseTimer();
        timer.resumeTimer();
        assertFalse(timer.isPaused());
    }

    @Test
    public void testResumeTimerIfNotPaused() {
        Timer timer = new Timer();
        timer.setTimer(1000);
        assertThrows(IllegalStateException.class, timer::resumeTimer);
    }

    @Test
    public void testGetTimeLeftIfPaused() {
        Timer timer = new Timer();
        timer.setTimer(1000);
        timer.pauseTimer();
        long t1 = timer.getTimeLeft();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long t2 = timer.getTimeLeft();
        assertEquals(t1, t2);
    }

    @Test
    public void testGetTimeLeftIfNotPaused() {
        Timer timer = new Timer();
        timer.setTimer(1000);
        long t1 = timer.getTimeLeft();
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long t2 = timer.getTimeLeft();
        assertTrue(t1 > t2);
    }

    @Test
    public void testPollIfNotFinished() {
        Timer timer = new Timer();
        AtomicBoolean called = new AtomicBoolean(false);
        timer.setEvent(() -> {
            called.set(true);
        });
        timer.setTimer(100);
        assertFalse(timer.poll());
        assertFalse(called.get());
    }

    @Test
    public void testPollIfFinished() {
        Timer timer = new Timer();
        AtomicBoolean called = new AtomicBoolean(false);
        timer.setEvent(() -> {
            called.set(true);
        });
        timer.setTimer(100);
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertTrue(timer.poll());
        assertTrue(called.get());
    }
}
