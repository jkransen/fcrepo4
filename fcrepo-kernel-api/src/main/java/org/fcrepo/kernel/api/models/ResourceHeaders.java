/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fcrepo.kernel.api.models;

import java.net.URI;
import java.time.Instant;
import java.util.Collection;

/**
 * An object containing header information which describes a FedoraResource.
 *
 * @author bbpennel
 */
public interface ResourceHeaders {

    /**
     * Get the identifier for the described resource.
     *
     * @return identifier for the resource.
     */
    String getId();

    /**
     * Get the State Token value for the resource.
     *
     * @return state-token value
     */
    String getStateToken();

    /**
     * Get the rdf:type values for the resource
     *
     * @return types
     */
    Collection<String> getTypes();

    /**
     * Get the mimetype describing the content contained by this resource
     *
     * @return mimetype
     */
    String getMimeType();

    /**
     * Get the filename for the content of this resource
     *
     * @return filename
     */
    String getFilename();

    /**
     * Get the size in bytes of the content of this resource
     *
     * @return size
     */
    long getContentSize();

    /**
     * Get the list of all digest URIs recorded for this resource
     *
     * @return digest URIs
     */
    Collection<URI> getDigests();

    /**
     * Get the url of external content associated with this resource.
     *
     * @return external url
     */
    String getExternalUrl();

    /**
     * Get the handling type for external content associated with this resource.
     *
     * @return external handling value
     */
    String getExternalHandling();

    /**
     * Get the date this resource was created
     *
     * @return created date
     */
    Instant getCreatedDate();

    /**
     * Get the date this resource was last modified
     *
     * @return last modified date
     */
    Instant getLastModifiedDate();

    /**
     * Retrieve the mementoDatetime property and return it as an Instant
     *
     * @return the Instant for this resource
     */
    Instant getMementoDatetime();
}
