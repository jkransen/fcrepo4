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
package org.fcrepo.persistence.ocfl.impl;

import java.util.HashSet;
import java.util.Set;

import org.fcrepo.kernel.api.operations.ResourceOperation;
import org.fcrepo.kernel.api.operations.ResourceOperationType;
import org.fcrepo.persistence.ocfl.api.Persister;

/**
 * A base abstract persister class
 *
 * @author dbernstein
 * @since 6.0.0
 */
public abstract class AbstractPersister implements Persister {

    private final Set<ResourceOperationType> resourceOperationType;

    private final Set<Class<? extends ResourceOperation>> resourceOperations;

    AbstractPersister(final Set<Class<? extends ResourceOperation>> resourceOperations, final Set<ResourceOperationType> resourceOperationType) {
        this.resourceOperations = resourceOperations;
        this.resourceOperationType = resourceOperationType;
    }

    AbstractPersister(final Class<? extends ResourceOperation> resourceOperation, final ResourceOperationType resourceOperationType) {
        this();
        this.resourceOperations.add(resourceOperation);
        this.resourceOperationType.add(resourceOperationType);
    }

    AbstractPersister(final Class<? extends ResourceOperation> resourceOperation, final Set<ResourceOperationType> resourceOperationType) {
        this.resourceOperations = new HashSet<>();
        this.resourceOperations.add(resourceOperation);
        this.resourceOperationType = resourceOperationType;
    }

    private AbstractPersister() {
        this.resourceOperations = new HashSet<>();
        this.resourceOperationType = new HashSet<>();
    }


    @Override
    public boolean handle(final ResourceOperation operation) {
        //ensure that at least one of them match.
        for (final var c : this.resourceOperations) {
            if (c.isInstance(operation)) {
                //return true if the operation types match.
                return this.resourceOperationType.contains(operation.getType());
            }
        }
        return false;
    }
}
