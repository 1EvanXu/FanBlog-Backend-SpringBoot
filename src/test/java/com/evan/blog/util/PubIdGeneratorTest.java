package com.evan.blog.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PubIdGeneratorTest {

    @Test
    public void generatePubId() {
        System.out.println("pubId: " + PubIdGenerator.generatePubId());
    }
}