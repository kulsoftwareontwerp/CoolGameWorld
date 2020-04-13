package com.kuleuven.swop.group17.CoolGameWorld;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.kuleuven.swop.group17.CoolGameWorld.applicationLayer.ApplicationLayer;
import com.kuleuven.swop.group17.CoolGameWorld.types.Types;

@RunWith(Suite.class)
@SuiteClasses({Types.class,ApplicationLayer.class})
public class AllTests {

}
