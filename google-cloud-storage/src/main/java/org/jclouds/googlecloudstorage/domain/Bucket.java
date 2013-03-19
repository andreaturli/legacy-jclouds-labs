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
package org.jclouds.googlecloudstorage.domain;

import com.google.common.annotations.Beta;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.beans.ConstructorProperties;
import java.net.URI;
import java.util.Date;
import java.util.List;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Optional.fromNullable;

/**
 * A persistent disk resource
 *
 * @see <a href="https://developers.google.com/storage/docs/json_api/v1/buckets"/>
 *
 * @author Andrea Turli
 *
 */
@Beta
public final class Bucket extends Resource {

   private final String projectId;
   private final Date timeCreated;
   private final Optional<List<BucketAccessControl>> acl;
   private final Optional<List<ObjectAccessControl>> defaultObjectAcl;
   private final Owner owner;
   private final String location;
   private final Optional<Website> website;

   @ConstructorProperties({"id", "selfLink", "projectId", "timeCreated", "acl", "defaultObjectAcl",
           "owner", "location", "website" })
   private Bucket(String id, URI selfLink, String projectId,  Date timeCreated, List<BucketAccessControl> acl,
                  List<ObjectAccessControl> defaultObjectAcl, Owner owner, String location, Website website) {
      super(Kind.BUCKET, id, selfLink);
      this.timeCreated = timeCreated;
      this.projectId = projectId;
      this.acl = fromNullable(acl);
      this.defaultObjectAcl = fromNullable(defaultObjectAcl);
      this.owner = owner;
      this.location = location;
      this.website = fromNullable(website);
   }

   /**
    * Retrieves the ID of the project the bucket belongs to.
    *
    * @return the ID of the project the bucket belongs to.
    */
   public String getProjectId() {
      return projectId;
   }

   /**
    * Retrieves the creation time of the bucket in RFC 3339 format.
    *
    * @return Creation time of the bucket in RFC 3339 format.
    */
   public Date getTimeCreated() {
      return timeCreated;
   }

   /**
    * Retrieves the access controls on the bucket.
    *
    * @return access controls on the bucket.
    */
   public List<BucketAccessControl> getACL() {
      if(!acl.isPresent()) {
         return Lists.newArrayList();
      }
      return acl.get();
   }

   /**
    * Retrieves the default access controls to apply to new objects when no ACL is provided.
    *
    * @return default access controls to apply to new objects when no ACL is provided.
    */
   public List<ObjectAccessControl> getDefaultObjectAcl() {
      if(!defaultObjectAcl.isPresent()) {
         return Lists.newArrayList();
      }
      return defaultObjectAcl.get();
   }

   /**
    *
    * Retrieves the owner of the bucket.
    *
    * @return the owner of the bucket. This will always be the project team's owner group
    */
   public Owner getOwner() {
      return owner;
   }

   /**
    *
    * Retrieves the location of the bucket.
    *
    * @return the location of the bucket. Object data for objects in the bucket resides in physical storage in this
    * location. Can be US or EU. Defaults to US.
    */
   public String getLocation() {
      return location;
   }

   /**
    *
    * Retrieves the the bucket's website configuration.
    *
    * @return the bucket's website configuration.
    */
   public Optional<Website> getWebsite() {
      return website;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(id);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Bucket that = Bucket.class.cast(obj);
      return equal(this.id, that.id) &&
              equal(this.timeCreated, that.timeCreated) &&
              equal(this.projectId, that.projectId) &&
              equal(this.owner, that.owner) &&
              equal(this.location, that.location);
   }

   @Override
   public String toString() {
      return string().toString();
   }

   protected Objects.ToStringHelper string() {
      return toStringHelper(this).add("id", id).add("timeCreated", timeCreated).add("projectId", projectId)
              .add("acl", acl.orNull()).add("defaultObjectAcl", defaultObjectAcl.orNull()).add("owner", owner)
              .add("location", location).add("website", website.orNull());
   }

   public static Builder builder() {
      return new Builder();
   }

   public Builder toBuilder() {
      return new Builder().fromBucket(this);
   }

   public static final class Builder extends Resource.Builder<Builder> {

      private String projectId;
      private Date timeCreated;
      private List<BucketAccessControl> acl;
      private List<ObjectAccessControl> defaultObjectAcl;
      private Owner owner;
      private String location;
      private Website website;

      public Builder projectId(String projectId) {
         this.projectId = projectId;
         return this;
      }

      public Builder timeCreated(Date timeCreated) {
         this.timeCreated = timeCreated;
         return this;
      }

      public Builder acl(List<BucketAccessControl> acl) {
         this.acl = acl;
         return this;
      }

      public Builder defaultObjectAcl(List<ObjectAccessControl> defaultObjectAcl) {
         this.defaultObjectAcl = defaultObjectAcl;
         return this;
      }

      public Builder owner(Owner owner) {
         this.owner = owner;
         return this;
      }

      public Builder location(String location) {
         this.location = location;
         return this;
      }

      public Builder website(Website website) {
         this.website = website;
         return this;
      }

      @Override
      protected Builder self() {
         return this;
      }

      public Bucket build() {
         return new Bucket(super.id, super.selfLink, projectId, timeCreated, acl, defaultObjectAcl, owner,
                 location, website);
      }

      public Builder fromBucket(Bucket in) {
         return super.fromResource(in)
                 .projectId(in.getProjectId())
                 .timeCreated(in.getTimeCreated())
                 .acl(in.getACL())
                 .defaultObjectAcl(in.getDefaultObjectAcl())
                 .owner(in.getOwner())
                 .location(in.getLocation())
                 .website(in.getWebsite().orNull());
      }
   }

}
