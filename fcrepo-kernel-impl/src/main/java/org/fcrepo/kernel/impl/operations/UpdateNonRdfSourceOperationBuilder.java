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

import org.fcrepo.kernel.api.operations.NonRdfSourceOperation;
import org.fcrepo.kernel.api.operations.NonRdfSourceOperationBuilder;

import java.io.InputStream;
import java.net.URI;
import java.util.Collection;


/**
 * Builder for operations to update non-rdf sources
 *
 * @author bbpennel
 */
public class UpdateNonRdfSourceOperationBuilder implements NonRdfSourceOperationBuilder {

    private final String resourceId;

    private InputStream contentStream;

    private String externalType;

    private URI externalUri;

    protected UpdateNonRdfSourceOperationBuilder(final String rescId, final InputStream stream) {
        this(rescId);
        this.contentStream = stream;
    }

    protected UpdateNonRdfSourceOperationBuilder(final String rescId, final String handling, final URI contentUri) {
        this(rescId);
        this.externalType = handling;
        this.externalUri = contentUri;
    }

    private UpdateNonRdfSourceOperationBuilder(final String rescId) {
        this.resourceId = rescId;
    }

    @Override
    public NonRdfSourceOperationBuilder mimeType(final String mimetype) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NonRdfSourceOperationBuilder filename(final String filename) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NonRdfSourceOperationBuilder contentDigests(final Collection<URI> digests) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NonRdfSourceOperationBuilder contentSize(final long size) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NonRdfSourceOperation build() {
        // TODO Auto-generated method stub
        return null;
    }

}
