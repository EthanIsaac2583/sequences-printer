package kz.ruanjian.runner;

import kz.ruanjian.loopcontrol.LoopControl;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

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
    void run_shouldNeverRun_whenLoopControlNotAllows() {
        doReturn(false).when(loopControl).canExecute();

        sleepAfterRunner.run();

        verify(runnable, never()).run();
    }

    @Test
    void run_shouldRunAndSleep_whenLoopControlAllows() {
        doReturn(true).doReturn(true).doReturn(true).doReturn(false).when(loopControl).canExecute();
        doReturn(100).doReturn(200).doReturn(300).when(random).get();

        InOrder inOrder = inOrder(runnable, loopControl, sleeper, random);

        sleepAfterRunner.run();

        inOrder.verify(loopControl).canExecute();
        inOrder.verify(runnable).run();
        inOrder.verify(random).get();
        inOrder.verify(sleeper).sleep(100);

        inOrder.verify(loopControl).canExecute();
        inOrder.verify(runnable).run();
        inOrder.verify(random).get();
        inOrder.verify(sleeper).sleep(200);


        inOrder.verify(loopControl).canExecute();
        inOrder.verify(runnable).run();
        inOrder.verify(random).get();
        inOrder.verify(sleeper).sleep(300);
    }
}