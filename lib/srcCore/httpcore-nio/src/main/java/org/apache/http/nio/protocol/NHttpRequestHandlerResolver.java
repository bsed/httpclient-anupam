/*
 * $HeadURL: https://svn.apache.org/repos/asf/httpcomponents/httpcore/tags/4.0.1/httpcore-nio/src/main/java/org/apache/http/nio/protocol/NHttpRequestHandlerResolver.java $
 * $Revision: 744543 $
 * $Date: 2009-02-14 18:30:41 +0100 (Sat, 14 Feb 2009) $
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

package org.apache.http.nio.protocol;

/**
 * HttpRequestHandlerResolver can be used to resolve an instance of 
 * {@link NHttpRequestHandler} matching a particular request URI. Usually the 
 * resolved request handler will be used to process the request with the 
 * specified request URI.
 *
 *
 * @version $Revision: 744543 $
 *
 * @since 4.0
 */
public interface NHttpRequestHandlerResolver {

    /**
     * Looks up a handler matching the given request URI.
     * 
     * @param requestURI the request URI
     * @return HTTP request handler or <code>null</code> if no match
     * is found.
     */
    NHttpRequestHandler lookup(String requestURI);

}
