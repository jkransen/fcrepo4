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
package org.fcrepo.kernel.impl.operations;

import org.fcrepo.kernel.api.RdfStream;
import org.fcrepo.kernel.api.operations.ResourceOperation;


/**
 * Abstract operation for interacting with a resource
 *
 * @author bbpennel
 */
public abstract class AbstractResourceOperation implements ResourceOperation {

    /**
     * The internal Fedora ID.
     */
    private final String rescId;

    /**
     * The server managed triples.
     */
    private RdfStream serverManagedProperties;

    /**
     * The interaction model, null if not used (ie. an update operation)
     */
    private String interactionModel;

    protected AbstractResourceOperation(final String rescId, final String interactionModel) {
        this.rescId = rescId;
        this.interactionModel = interactionModel;
    }

    @Override
    public String getResourceId() {
        return rescId;
    }

    @Override
    public RdfStream getServerManagedProperties() {
        return serverManagedProperties;
    }

    /**
     * Set the server managed properties for the resource
     *
     * @param serverManagedProperties stream of properties
     */
    public void setServerManagedProperties(final RdfStream serverManagedProperties) {
        this.serverManagedProperties = serverManagedProperties;
    }

}
