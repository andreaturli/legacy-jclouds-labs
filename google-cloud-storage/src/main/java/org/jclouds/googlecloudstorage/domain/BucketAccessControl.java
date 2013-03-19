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

import com.google.common.base.Objects;

import java.beans.ConstructorProperties;
import java.net.URI;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * An access-control entry.
 *
 * @author Andrea Turli
 */
public final class BucketAccessControl extends Resource {

   private final String bucket;
   private final String entity;
   private final Role role;
   private final String email;
   private final String entityId;
   private final String domain;

   @ConstructorProperties({"id", "selfLink", "bucket", "entity", "role", "email", "entityId", "domain" })
   private BucketAccessControl(String id, URI selfLink, String bucket, String entity, Role role,
                                 String email, String entityId, String domain) {
      super(Kind.BUCKET_ACCESS_CONTROL, id, selfLink);
      this.bucket = checkNotNull(bucket, "bucket can't be null");
      this.entity = entity;
      this.role = role;
      this.email = email;
      this.entityId = entityId;
      this.domain = domain;
   }

   /**
    * Retrieves the name of the bucket.
    *
    * @return the name of the bucket.
    */
   public String getBucket() {
      return bucket;
   }

   /**
    * Retrieves the entity holding the permission.
    *
    * @return the entity holding the permission.
    */
   public String getEntity() {
      return entity;
   }

   /**
    * Retrieves the access permission for the entity.
    *
    * @return the access permission for the entity.
    */
   public Role getRole() {
      return role;
   }

   /**
    * Retrieves the email address associated with the entity.
    *
    * @return the email address associated with the entity.
    */
   public String getEmail() {
      return email;
   }

   /**
    * Retrieves the ID for the entity.
    *
    * @return the ID for the entity.
    */
   public String getEntityId() {
      return entityId;
   }

   /**
    * Retrieves the domain associated with the entity.
    *
    * @return the domain associated with the entity.
    */
   public String getDomain() {
      return domain;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(id);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      BucketAccessControl that = BucketAccessControl.class.cast(obj);
      return equal(this.id, that.id) &&
             equal(this.bucket, that.bucket) &&
             equal(this.entity, that.entity) &&
             equal(this.role, that.role) &&
             equal(this.email, that.email) &&
             equal(this.entityId, that.entityId) &&
             equal(this.domain, that.domain);
   }

   @Override
   public String toString() {
      return string().toString();
   }

   protected Objects.ToStringHelper string() {
      return toStringHelper(this).add("id", id)
                                 .add("bucket", bucket)
                                 .add("entity", entity)
                                 .add("role", role)
                                 .add("email", email)
                                 .add("entityId", entityId)
                                 .add("domain", domain);
   }

   public static Builder builder() {
      return new Builder();
   }

   public Builder toBuilder() {
      return new Builder().fromBucketAccessControl(this);
   }

   public static final class Builder extends Resource.Builder<Builder> {

      private String bucket;
      private String entity;
      private Role role;
      private String email;
      private String entityId;
      private String domain;

      public Builder bucket(String bucket) {
         this.bucket = bucket;
         return this;
      }

      public Builder entity(String entity) {
         this.entity = entity;
         return this;
      }

      public Builder role(Role role) {
         this.role = role;
         return this;
      }

      public Builder email(String email) {
         this.email = email;
         return this;
      }

      public Builder entityId(String entityId) {
         this.entityId = entityId;
         return this;
      }

      public Builder domain(String domain) {
         this.domain = domain;
         return this;
      }

      @Override
      protected Builder self() {
         return this;
      }

      public BucketAccessControl build() {
         return new BucketAccessControl(super.id, super.selfLink, bucket, entity, role, email, entityId, domain);
      }

      public Builder fromBucketAccessControl(BucketAccessControl in) {
         return super.fromResource(in)
                 .bucket(in.getBucket())
                 .entity(in.getEntity())
                 .email(in.getEmail())
                 .role(in.getRole())
                 .entityId(in.getEntityId())
                 .domain(in.getDomain());
      }
   }

}
