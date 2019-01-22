package com.udacity.gradle.builditbigger.tests;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.udacity.gradle.builditbigger.EndpointAsync;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

/**
 * Created by denis on 01/01/19.
 */
@RunWith(AndroidJUnit4.class)
public class AsyncTaskTest {
    Context context;

    @Test
    public void jokeNotNull() throws InterruptedException{
        Assert.assertTrue(true);
        final CountDownLatch latch = new CountDownLatch(1);
        context = InstrumentationRegistry.getContext();
        EndpointAsync testAsync = new EndpointAsync(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Assert.assertNotNull(s);
                if (s != null){
                    Assert.assertTrue(s.length() > 0);
                    latch.countDown();
                }
            }
        };

        testAsync.execute(context);
        latch.await();
    }
}
