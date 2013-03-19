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

import com.google.common.util.concurrent.ListenableFuture;
import org.jclouds.Fallbacks;
import org.jclouds.collect.PagedIterable;
import org.jclouds.googlecloudstorage.GoogleCloudStorageConstants;
import org.jclouds.googlecloudstorage.domain.Bucket;
import org.jclouds.googlecloudstorage.domain.ListPage;
import org.jclouds.googlecloudstorage.functions.internal.ParseBuckets;
import org.jclouds.googlecloudstorage.options.OptionalParameters;
import org.jclouds.javax.annotation.Nullable;
import org.jclouds.oauth.v2.config.OAuthScopes;
import org.jclouds.oauth.v2.filters.OAuthAuthenticator;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.MapBinder;
import org.jclouds.rest.annotations.PayloadParam;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.ResponseParser;
import org.jclouds.rest.annotations.SkipEncoding;
import org.jclouds.rest.annotations.Transform;
import org.jclouds.rest.binders.BindToJsonPayload;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Provides asynchronous access to Bucket via their REST API.
 *
 * @see BucketApi
 *
 * @author Andrea Turli
 *
 */
@SkipEncoding({'/', '='})
@RequestFilters(OAuthAuthenticator.class)
public interface BucketAsyncApi {

   /**
    * @see BucketApi#get(String, org.jclouds.googlecloudstorage.options.OptionalParameters...)
    */
   @Named("Buckets:get")
   @GET
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/b/{bucket}")
   @OAuthScopes(GoogleCloudStorageConstants.STORAGE_SCOPE)
   @Fallback(Fallbacks.NullOnNotFoundOr404.class)
   ListenableFuture<Bucket> get(@PathParam("bucket") String bucketName, OptionalParameters... optionalParameters);

   /**
    * @see BucketApi#createInProject(String, String)
    */
   @Named("Buckets:insert")
   @POST
   @Consumes(MediaType.APPLICATION_JSON)
   @Produces(MediaType.APPLICATION_JSON)
   @Path("/b")
   @OAuthScopes({GoogleCloudStorageConstants.STORAGE_SCOPE})
   @MapBinder(BindToJsonPayload.class)
   ListenableFuture<Bucket> createInProject(@PayloadParam("id") String bucketName,
                                            @PayloadParam("projectId") String projectId);

   /**
    * @see BucketApi#delete(String)
    */
   @Named("Buckets:delete")
   @DELETE
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/b/{bucket}")
   @OAuthScopes(GoogleCloudStorageConstants.STORAGE_SCOPE)
   @Fallback(Fallbacks.NullOnNotFoundOr404.class)
   ListenableFuture<Void> delete(@PathParam("bucket") String bucketName);

   /**
    * @see BucketApi#listFirstPage(String)
    */
   @Named("Buckets:list")
   @GET
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/b")
   @OAuthScopes(GoogleCloudStorageConstants.STORAGE_SCOPE)
   @ResponseParser(ParseBuckets.class)
   @Fallback(Fallbacks.EmptyIterableWithMarkerOnNotFoundOr404.class)
   ListenableFuture<ListPage<Bucket>> listFirstPage(@QueryParam("projectId") String projectId);

   /**
    * @see BucketApi#listAtMarker(String)
    */
   @Named("Buckets:list")
   @GET
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/b")
   @OAuthScopes(GoogleCloudStorageConstants.STORAGE_SCOPE)
   @ResponseParser(ParseBuckets.class)
   @Fallback(Fallbacks.EmptyIterableWithMarkerOnNotFoundOr404.class)
   ListenableFuture<ListPage<Bucket>> listAtMarker(@QueryParam("pageToken") @Nullable String marker);

   /**
    * @see BucketApi#list(String)
    */
   @Named("Buckets:list")
   @GET
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/b")
   @OAuthScopes(GoogleCloudStorageConstants.STORAGE_SCOPE)
   @ResponseParser(ParseBuckets.class)
   @Transform(ParseBuckets.ToPagedIterable.class)
   @Fallback(Fallbacks.EmptyPagedIterableOnNotFoundOr404.class)
   ListenableFuture<? extends PagedIterable<Bucket>> list(@QueryParam("projectId") String projectId);
}
