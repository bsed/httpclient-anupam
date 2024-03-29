/*
 * $HeadURL: https://svn.apache.org/repos/asf/httpcomponents/httpcore/tags/4.0.1/httpcore-nio/src/main/java/org/apache/http/nio/reactor/ListeningIOReactor.java $
 * $Revision: 744545 $
 * $Date: 2009-02-14 18:34:58 +0100 (Sat, 14 Feb 2009) $
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

package org.apache.http.nio.reactor;

import java.net.SocketAddress;
import java.util.Set;
import java.io.IOException;

/**
 * ListeningIOReactor represents an I/O reactor capable of listening for 
 * incoming connections on one or several ports.
 *  
 *
 * @version $Revision: 744545 $
 * 
 * @since 4.0
 */
public interface ListeningIOReactor extends IOReactor {

    /**
     * Opens a new listener endpoint with the given socket address. Once 
     * the endpoint is fully initialized it starts accepting incoming 
     * connections and propagates I/O activity notifications to the I/O event
     * dispatcher. 
     * 
     * @param address the socket address to listen on.
     * @return listener endpoint.
     */
    ListenerEndpoint listen(SocketAddress address);

    /**
     * Suspends the I/O reactor preventing it from accepting new connections on
     * all active endpoints.
     * 
     * @throws IOException in case of an I/O error.
     */
    void pause()
        throws IOException;

    /**
     * Resumes the I/O reactor restoring its ability to accept incoming 
     * connections on all active endpoints.
     * 
     * @throws IOException in case of an I/O error.
     */
    void resume()
        throws IOException;

    /**
     * Returns a set of endpoints for this I/O reactor.
     * 
     * @return set of endpoints. 
     */
    Set<ListenerEndpoint> getEndpoints();
    
}
