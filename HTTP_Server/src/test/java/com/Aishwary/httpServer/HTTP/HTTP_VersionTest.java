package com.Aishwary.httpServer.HTTP;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HTTP_VersionTest {
    @Test
    void getBestCompatibleVersionExactMatch() {
        HTTP_Version version = null;
        try {
            version = HTTP_Version.getBestCompatibleVersion("HTTP/1.1");
        } catch (BadHTTPVersionException e) {
            fail();
        }
        assertNotNull(version);
        assertEquals(version, HTTP_Version.HTTP_1_1);
    }

    //test case for bad request
    @Test
    void getBestCompatibleVersionBadFormat() {
        HTTP_Version version = null;
        try {
            version = HTTP_Version.getBestCompatibleVersion("http/1.1");
            fail();
        } catch (BadHTTPVersionException e) {

        }
    }

    //test case for higher version
    @Test
    void getBestCompatibleVersionHigherVersion() {
        HTTP_Version version = null;
        try {
            version = HTTP_Version.getBestCompatibleVersion("HTTP/1.3");
            assertNotNull(version);
            assertEquals(version, HTTP_Version.HTTP_1_1);
        } catch (BadHTTPVersionException e) {
            fail();
        }

    }
}
