package com.kuleuven.swop.group17.CoolGameWorld;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.ApplicationLayer;
import com.kuleuven.swop.group17.CoolGameWorld.domainLayer.DomainLayer;
import com.kuleuven.swop.group17.CoolGameWorld.events.Events;
import com.kuleuven.swop.group17.CoolGameWorld.guiLayer.GuiLayer;
import com.kuleuven.swop.group17.CoolGameWorld.types.Types;

@RunWith(Suite.class)
@SuiteClasses({Types.class,ApplicationLayer.class,DomainLayer.class,Events.class,GuiLayer.class})
public class AllTests {

}
