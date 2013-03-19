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

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.jclouds.collect.PagedIterable;
import org.jclouds.googlecloudstorage.domain.Bucket;
import org.jclouds.googlecloudstorage.internal.BaseGoogleCloudStorageApiLiveTest;
import org.jclouds.googlecloudstorage.options.OptionalParameters;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static org.testng.Assert.*;

/**
 * @author Andrea Turli
 */
public class BucketApiLiveTest extends BaseGoogleCloudStorageApiLiveTest {

   private Bucket bucket;
   private String bucketName;

   private BucketApi api() {
      return context.getApi().getBucketApi();
   }

   @BeforeClass
   void init() {
      bucketName = "bucket-api-live-test-" + new Random().nextInt(100);
   }

   @Test(groups = "live")
   public void testInsertBucket() {
      bucket = api().createInProject(bucketName, projectId);
   }

   @Test(groups = "live", dependsOnMethods = "testInsertBucket")
   public void testGetBucket() {
      Bucket foundBucket = api().get(bucketName);
      assertNotNull(foundBucket);
      assertEquals(foundBucket, bucket);
   }

   @Test(groups = "live", dependsOnMethods = "testGetBucket")
   public void testGetBucketWithOptionalParameters() {
      OptionalParameters optionalParameters = new OptionalParameters.Builder()
              .projection(OptionalParameters.Projection.FULL.toString());
      Bucket foundBucket = api().get(bucketName, optionalParameters);
      assertNotNull(foundBucket);
      assertEquals(foundBucket, bucket);
   }

   @Test(groups = "live", dependsOnMethods = "testGetBucketWithOptionalParameters")
   public void testListBuckets() {
      PagedIterable<Bucket> buckets = api().list(projectId);
      List<Bucket> bucketsAsList = Lists.newArrayList(buckets.concat());
      assertTrue(Iterables.contains(bucketsAsList, bucket));
   }

   @Test(groups = "live", dependsOnMethods = "testListBuckets")
   public void testDeleteBucket() {
      api().delete(bucketName);
   }

}
