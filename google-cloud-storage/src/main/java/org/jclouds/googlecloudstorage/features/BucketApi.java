/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
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
 */
package org.jclouds.googlecloudstorage.features;

import org.jclouds.collect.IterableWithMarker;
import org.jclouds.collect.PagedIterable;
import org.jclouds.googlecloudstorage.options.OptionalParameters;
import org.jclouds.googlecloudstorage.domain.Bucket;
import org.jclouds.googlecloudstorage.domain.ListPage;

/**
 * Provides synchronous access to Buckets via their REST API.
 *
 * @see BucketAsyncApi
 * @see <a href="https://developers.google.com/storage/docs/json_api/v1/#Buckets"/>
 *
 * @author Andrea Turli
 *
 */
public interface BucketApi {

   /**
    * Returns metadata for the specified bucket.
    *
    * GET https://www.googleapis.com/storage/v1beta1/b/bucket
    */
   Bucket get(String bucketName, OptionalParameters... optionalParameters);

   /**
    * Creates a bucket in the specified project.
    *
    * @param bucketName the name of bucket.
    * @param projectId the id of project.
    *
    * @return a Bucket resource.
    */
   Bucket createInProject(String bucketName, String projectId);

   /**
    * Deletes the specified bucket.
    *
    * @param bucketName name of the bucket to be deleted.
    *
    */
   void delete(String bucketName);

   /**
    * Retrieves the first listPage of bucket resources contained within the specified project.
    */
   public ListPage<Bucket> listFirstPage(String projectId);

   /**
    * Retrieves the listPage of bucket resources contained within the specified project.
    * By default the listPage as a maximum size of 100, if no options are provided or ListOptions#getMaxResults() has
    * not been set.
    *
    * @param marker      marks the beginning of the next list page
    * @return a page of the listPage
    * @see org.jclouds.googlecloudstorage.domain.ListPage
    */
   IterableWithMarker<Bucket> listAtMarker(String marker);

   /**
    * Lists all the buckets on the specified project.
    *
    * @param projectId the id of project.
    * @return a Paged, Fluent Iterable (@see PagedIterable) that is able to fetch additional pages when required
    *
    */
    PagedIterable<Bucket> list(String projectId);
}
