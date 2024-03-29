/*
 * $HeadURL: https://svn.apache.org/repos/asf/httpcomponents/httpcore/tags/4.0.1/httpcore-nio/src/test/java/org/apache/http/mockup/MockupEncoder.java $
 * $Revision: 617652 $
 * $Date: 2008-02-01 22:18:00 +0100 (Fri, 01 Feb 2008) $
 *
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

package org.apache.http.mockup;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

import org.apache.http.impl.io.HttpTransportMetricsImpl;
import org.apache.http.impl.nio.codecs.AbstractContentEncoder;
import org.apache.http.nio.reactor.SessionOutputBuffer;

public class MockupEncoder extends AbstractContentEncoder {
    
    // TODO? remove this field and the complete() and isCompleted() methods
    private boolean completed;
    
    public MockupEncoder(
            final WritableByteChannel channel, 
            final SessionOutputBuffer buffer,
            final HttpTransportMetricsImpl metrics) {
        super(channel, buffer, metrics);
    }

    @Override
    public boolean isCompleted() {
        return this.completed;
    }
    
    @Override
    public void complete() throws IOException {
        this.completed = true;
    }
    
    public int write(final ByteBuffer src) throws IOException {
        if (src == null) {
            return 0;
        }
        if (this.completed) {
            throw new IllegalStateException("Decoding process already completed");
        }
        return this.channel.write(src);
    }
    
}
