package com.mingshashan.learn;

import org.junit.Test;

public class Common_Test {

    @Test
    public void test_01() {
        final int COUNT_BITS = Integer.SIZE - 3;
        // runState is stored in the high-order bits
        final int RUNNING1 = -1 << COUNT_BITS;

        System.out.println(Integer.toBinaryString(-1 << COUNT_BITS));
        System.out.println(Integer.toBinaryString(RUNNING1));
        System.out.println(Integer.toBinaryString(RUNNING1 | 0));

        final int RUNNING = -1 << COUNT_BITS;
        final int SHUTDOWN = 0 << COUNT_BITS;
        final int STOP = 1 << COUNT_BITS;
        final int TIDYING = 2 << COUNT_BITS;
        final int TERMINATED = 3 << COUNT_BITS;

        System.out.printf("RUNNING: %s \nSHUTDOWN: %s \nSTOP: %s \nTIDYING: %s \nTERMINATED: %s \n",
                Integer.toBinaryString(RUNNING),
                Integer.toBinaryString(SHUTDOWN),
                Integer.toBinaryString(STOP),
                Integer.toBinaryString(TIDYING),
                Integer.toBinaryString(TERMINATED));

    }
}
