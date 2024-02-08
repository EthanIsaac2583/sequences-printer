package kz.ruanjian;

import kz.ruanjian.loopcontrol.LoopControl;
import kz.ruanjian.runner.SleepAfterRunner;
import kz.ruanjian.threaded.SafeRandom;
import kz.ruanjian.threaded.SafeSleeper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;

@ExtendWith(MockitoExtension.class)
class SleepAfterRunnerTest {

    @Mock
    Runnable runnable;

    @Mock
    LoopControl loopControl;

    @Mock
    SafeSleeper sleeper;

    @Mock
    SafeRandom random;

    @InjectMocks
    SleepAfterRunner sleepAfterRunner;

    @Test
    void run() {
        doReturn(true).doReturn(true).doReturn(true).doReturn(false).when(loopControl).canExecute();
        doReturn(100).doReturn(200).doReturn(300).when(random).get();

        InOrder inOrder = inOrder(sleeper, runnable, random, loopControl);

        sleepAfterRunner.run();

        inOrder.verify(loopControl).canExecute();
        inOrder.verify(random).get();
        inOrder.verify(sleeper).sleep(100);
        inOrder.verify(runnable).run();
        inOrder.verify(loopControl).canExecute();
        inOrder.verify(random).get();
        inOrder.verify(sleeper).sleep(200);
        inOrder.verify(runnable).run();
        inOrder.verify(loopControl).canExecute();
        inOrder.verify(random).get();
        inOrder.verify(sleeper).sleep(300);
        inOrder.verify(runnable).run();
    }
}