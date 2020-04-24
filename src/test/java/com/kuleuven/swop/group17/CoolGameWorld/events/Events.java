package com.kuleuven.swop.group17.CoolGameWorld.events;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ ElementAddedEventTest.class, ElementClearedEventTest.class, EventFactoryTest.class,
		BoatChangedEventTest.class, BoatAddedEventTest.class })
public class Events {

}
