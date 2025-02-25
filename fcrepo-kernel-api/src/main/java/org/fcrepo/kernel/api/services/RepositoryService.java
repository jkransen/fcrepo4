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
package org.fcrepo.kernel.api.services;

import java.io.File;
import java.util.Collection;

import org.fcrepo.kernel.api.Transaction;

/**
 * @author bbpennel
 * @since Feb 20, 2014
 */
public interface RepositoryService {

    /**
     * Calculate the total size of all the binary properties in the repository
     *
     * @return size in bytes
     */
    Long getRepositorySize();

    /**
     * Calculate the number of objects in the repository
     *
     * @return number of objects in the repository
     */
    Long getRepositoryObjectCount();

    /**
     * This method backups up a running repository
     *
     * @param transaction the transaction
     * @param backupDirectory the backup directory
     * @return problems
     */
    Collection<Throwable> backupRepository(Transaction transaction, File backupDirectory);

    /**
     * This methods restores the repository from a backup
     *
     * @param transaction the transaction
     * @param backupDirectory the backup directory
     * @return problems
     */
    Collection<Throwable> restoreRepository(Transaction transaction, File backupDirectory);

}
