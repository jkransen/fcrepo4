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

import org.fcrepo.kernel.api.operations.ResourceOperationBuilder;


/**
 * Builder for operations to delete a resource
 *
 * @author bbpennel
 */
public class DeleteResourceOperationBuilder implements ResourceOperationBuilder {

    private String rescId;

    /**
     * Construct the builder
     *
     * @param rescId identifier of the resource to delete
     */
    public DeleteResourceOperationBuilder(final String rescId) {
        this.rescId = rescId;
    }

    @Override
    public DeleteResourceOperation build() {
        return new DeleteResourceOperation(rescId);
    }
}
