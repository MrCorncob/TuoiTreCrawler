/*
 * Copyright 2015 Corncob.
 *
 */
package com.fit.tuoitre.crawler.Controller;

import static java.lang.System.out;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Corncob
 */
public class GenderIdentifierTest {

    public GenderIdentifierTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getGender method, of class GenderIdentifier.
     */
    @Test
    public void testGetGender() {
        System.out.println("getGender");
        String name = "Yen";
        int expResult = 0;
        int result = GenderIdentifier.getGender(name);
        out.print(result);
    }

}
