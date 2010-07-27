/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.impl.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TestRFC2617Scheme extends TestCase {


    // ------------------------------------------------------------ Constructor

    public TestRFC2617Scheme(String name) {
        super(name);
    }

    // ------------------------------------------------------- TestCase Methods

    public static Test suite() {
        return new TestSuite(TestRFC2617Scheme.class);
    }

    static class TestAuthScheme extends RFC2617Scheme {

        public Header authenticate(
                final Credentials credentials, 
                final HttpRequest request) throws AuthenticationException {
            return null;
        }

        public String getSchemeName() {
            return "test";
        }

        public boolean isComplete() {
            return false;
        }

        public boolean isConnectionBased() {
            return false;
        }
        
    }

    public void testProcessChallenge() throws Exception {
        TestAuthScheme authscheme = new TestAuthScheme();
        Header header = new BasicHeader(
                AUTH.WWW_AUTH,
                "Test realm=\"realm1\", test, test1 =  stuff, test2 =  \"stuff, stuff\", test3=\"crap");
        
        authscheme.processChallenge(header);
        
        assertEquals("test", authscheme.getSchemeName());
        assertEquals("realm1", authscheme.getParameter("realm"));
        assertEquals(null, authscheme.getParameter("test"));
        assertEquals("stuff", authscheme.getParameter("test1"));
        assertEquals("stuff, stuff", authscheme.getParameter("test2"));
        assertEquals("\"crap", authscheme.getParameter("test3"));
    }

    public void testProcessChallengeWithLotsOfBlanks() throws Exception {
        TestAuthScheme authscheme = new TestAuthScheme();
        CharArrayBuffer buffer = new CharArrayBuffer(32);
        buffer.append(" WWW-Authenticate:    Test       realm=\"realm1\"");
        Header header = new BufferedHeader(buffer);
        
        authscheme.processChallenge(header);
        
        assertEquals("test", authscheme.getSchemeName());
        assertEquals("realm1", authscheme.getParameter("realm"));
    }

    public void testInvalidHeader() throws Exception {
        TestAuthScheme authscheme = new TestAuthScheme();
        Header header = new BasicHeader("whatever", "Test realm=\"realm1\"");
        try {
            authscheme.processChallenge(header);
            fail("MalformedChallengeException should have been thrown");
        } catch (MalformedChallengeException ex) {
            //expected
        }
    }
    
    public void testEmptyHeader() throws Exception {
        TestAuthScheme authscheme = new TestAuthScheme();
        Header header = new BasicHeader(AUTH.WWW_AUTH, "Test    ");
        try {
            authscheme.processChallenge(header);
            fail("MalformedChallengeException should have been thrown");
        } catch (MalformedChallengeException ex) {
            //expected
        }
    }
    
    public void testInvalidHeaderValue() throws Exception {
        TestAuthScheme authscheme = new TestAuthScheme();
        Header header = new BasicHeader("whatever", "whatever");
        try {
            authscheme.processChallenge(header);
            fail("MalformedChallengeException should have been thrown");
        } catch (MalformedChallengeException ex) {
            //expected
        }
    }

}

