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
package org.jclouds.googlecloudstorage.functions.internal;

import com.google.common.base.Function;
import com.google.inject.TypeLiteral;
import org.jclouds.collect.IterableWithMarker;
import org.jclouds.googlecloudstorage.domain.ListPage;
import org.jclouds.googlecloudstorage.GoogleCloudStorageApi;
import org.jclouds.googlecloudstorage.domain.Bucket;
import org.jclouds.http.functions.ParseJson;
import org.jclouds.json.Json;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author Andrea Turli
 */
@Singleton
public class ParseBuckets extends ParseJson<ListPage<Bucket>> {

   @Inject
   public ParseBuckets(Json json) {
      super(json, new TypeLiteral<ListPage<Bucket>>() {});
   }

   public static class ToPagedIterable extends BaseToPagedIterable<Bucket, ToPagedIterable> {

      private final GoogleCloudStorageApi api;

      @Inject
      protected ToPagedIterable(GoogleCloudStorageApi api) {
         this.api = checkNotNull(api, "api");
      }

      @Override
      protected Function<Object, IterableWithMarker<Bucket>> fetchNextPage(final String projectId) {
         return new Function<Object, IterableWithMarker<Bucket>>() {

            @Override
            public IterableWithMarker<Bucket> apply(Object input) {
               return api.getBucketApi().listAtMarker(input.toString());
            }
         };
      }
   }
}
