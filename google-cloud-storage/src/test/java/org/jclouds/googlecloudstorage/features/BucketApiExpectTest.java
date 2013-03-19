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

import org.jclouds.googlecloudstorage.internal.BaseGoogleCloudStorageApiExpectTest;
import org.jclouds.googlecloudstorage.parse.ParseBucketListTest;
import org.jclouds.googlecloudstorage.parse.ParseBucketTest;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpResponse;
import org.testng.annotations.Test;

import javax.ws.rs.core.MediaType;

import static org.jclouds.googlecloudstorage.GoogleCloudStorageConstants.STORAGE_SCOPE;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertNull;

/**
 * @author Andrea Turli
 */
@Test(groups = "unit")
public class BucketApiExpectTest extends BaseGoogleCloudStorageApiExpectTest {

   public void testGetBucketResponseIs2xx() throws Exception {
      HttpRequest get = HttpRequest
              .builder()
              .method("GET")
              .endpoint("https://www.googleapis.com/storage/v1beta1/b/test-bucket-1")
              .addHeader("Accept", "application/json")
              .addHeader("Authorization", "Bearer " + TOKEN).build();

      HttpResponse operationResponse = HttpResponse.builder().statusCode(200).payload(payloadFromResource("/bucket_get.json")).build();

      BucketApi api = requestsSendResponses(requestForScopes(STORAGE_SCOPE),
              TOKEN_RESPONSE, get, operationResponse).getBucketApi();

      assertEquals(api.get("test-bucket-1"), new ParseBucketTest().expected());
   }

   public void testGetBucketResponseIs4xx() throws Exception {
      HttpRequest get = HttpRequest
              .builder()
              .method("GET")
              .endpoint("https://www.googleapis.com/storage/v1beta1/b/test-bucket-1")
              .addHeader("Accept", "application/json")
              .addHeader("Authorization", "Bearer " + TOKEN).build();

      HttpResponse operationResponse = HttpResponse.builder().statusCode(404).build();

      BucketApi api = requestsSendResponses(requestForScopes(STORAGE_SCOPE),
              TOKEN_RESPONSE, get, operationResponse).getBucketApi();

      assertNull(api.get("test-bucket-1"));
   }

   public void testInsertBucketResponseIs2xx() {
      HttpRequest insert = HttpRequest
              .builder()
              .method("POST")
              .endpoint("https://www.googleapis.com/storage/v1beta1/b")
              .addHeader("Accept", "application/json")
              .addHeader("Authorization", "Bearer " + TOKEN)
              .payload(payloadFromResourceWithContentType("/bucket_insert.json", MediaType.APPLICATION_JSON))
              .build();

      HttpResponse insertBucketResponse = HttpResponse.builder().statusCode(200)
              .payload(payloadFromResource("/bucket.json")).build();

      BucketApi api = requestsSendResponses(requestForScopes(STORAGE_SCOPE),
              TOKEN_RESPONSE, insert,
              insertBucketResponse).getBucketApi();

      assertEquals(api.createInProject("test-bucket-1", "477990493411"), new ParseBucketTest().expected());
   }

   public void testDeleteBucketResponseIs2xx() {
      HttpRequest delete = HttpRequest
              .builder()
              .method("DELETE")
              .endpoint("https://www.googleapis.com/storage/v1beta1/b/test-bucket-1")
              .addHeader("Accept", "application/json")
              .addHeader("Authorization", "Bearer " + TOKEN).build();

      HttpResponse deleteResponse = HttpResponse.builder().statusCode(200).build();

      BucketApi api = requestsSendResponses(requestForScopes(STORAGE_SCOPE),
              TOKEN_RESPONSE, delete, deleteResponse).getBucketApi();

      api.delete("test-bucket-1");
   }

   public void testDeleteBucketResponseIs4xx() {
      HttpRequest delete = HttpRequest
              .builder()
              .method("DELETE")
              .endpoint("https://www.googleapis.com/storage/v1beta1/b/test-bucket-1")
              .addHeader("Accept", "application/json")
              .addHeader("Authorization", "Bearer " + TOKEN).build();

      HttpResponse deleteResponse = HttpResponse.builder().statusCode(404).build();

      BucketApi api = requestsSendResponses(requestForScopes(STORAGE_SCOPE),
              TOKEN_RESPONSE, delete, deleteResponse).getBucketApi();

      api.delete("test-bucket-1");
   }

   public void testListBucketsResponseIs2xx() {
      HttpRequest list = HttpRequest
              .builder()
              .method("GET")
              .endpoint("https://www.googleapis.com/storage/v1beta1/b?projectId=477990493411")
              .addHeader("Accept", "application/json")
              .addHeader("Authorization", "Bearer " + TOKEN).build();

      HttpResponse operationResponse = HttpResponse.builder().statusCode(200)
              .payload(payloadFromResource("/bucket_list.json")).build();

      BucketApi api = requestsSendResponses(requestForScopes(STORAGE_SCOPE),
              TOKEN_RESPONSE, list, operationResponse).getBucketApi();

      assertEquals(api.listFirstPage("477990493411"), new ParseBucketListTest().expected());
   }

   public void testListBucketsResponseIs4xx() {
      HttpRequest list = HttpRequest
              .builder()
              .method("GET")
              .endpoint("https://www.googleapis.com/storage/v1beta1/b?projectId=4779904934111")
              .addHeader("Accept", "application/json")
              .addHeader("Authorization", "Bearer " + TOKEN).build();

      HttpResponse operationResponse = HttpResponse.builder().statusCode(404).build();

      BucketApi api = requestsSendResponses(requestForScopes(STORAGE_SCOPE),
              TOKEN_RESPONSE, list, operationResponse).getBucketApi();

      assertTrue(api.list("4779904934111").concat().isEmpty());
   }

}
