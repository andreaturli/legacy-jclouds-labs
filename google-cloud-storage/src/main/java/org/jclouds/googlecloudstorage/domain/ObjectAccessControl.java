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

/**
 * @author Andrea Turli
 */
public final class ObjectAccessControl extends Resource {

   private final String bucket;
   private final String object;
   private final Role role;
   private final String email;
   private final String entity;
   private final String entityId;
   private final String domain;

   @ConstructorProperties({"id", "selfLink", "bucket", "object", "entity", "role", "email", "entityId", "domain" })
   protected ObjectAccessControl
           (String id, URI selfLink, String bucket, String object, String entity, Role role, String email,
            String entityId, String domain) {
      super(Kind.OBJECT_ACCESS_CONTROL, id, selfLink);
      this.bucket = bucket;
      this.object = object;
      this.entity = entity;
      this.role = role;
      this.email = email;
      this.entityId = entityId;
      this.domain = domain;
   }

   /**
    *
    * @return the name of the bucket.
    */
   public String getBucket() {
      return bucket;
   }

   /**
    *
    * @return the name of the object.
    */
   public String getObject() {
      return object;
   }

   /**
    *
    * @return the entity holding the permission
    */
   public String getEntity() {
      return entity;
   }

   /**
    *
    * @return the domain associated with the entity.
    */
   public String getEmail() {
      return email;
   }

   /**
    *
    * @return the ID for the entity.
    */
   public String getEntityId() {
      return entityId;
   }

   /**
    *
    * @return the domain associated with the entity.
    */
   public String getDomain() {
      return domain;
   }

   /**
    *
    * @return the access permission for the entity.
    */
   public Role getRole() {
      return role;
   }

   /**
    * {@inheritDoc}
    */
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
      ObjectAccessControl that = ObjectAccessControl.class.cast(obj);
      return equal(this.id, that.id) && equal(this.bucket, that.bucket) && equal(this.object,
              that.object) && equal(this.entity, that.entity)
              && equal(this.role, that.role) && equal(this.email, that.email) && equal(this.entityId,
              that.entityId) && equal(this.domain, that.domain);
   }

   @Override
   public String toString() {
      return string().toString();
   }

   protected Objects.ToStringHelper string() {
      return toStringHelper(this).add("id", id)
              .add("bucket", bucket)
              .add("object", object)
              .add("role", role)
              .add("email", email)
              .add("entity", entity)
              .add("entityId", entityId)
              .add("domain", domain);
   }

   public static Builder builder() {
      return new Builder();
   }

   public Builder toBuilder() {
      return new Builder().fromObjectAccessControl(this);
   }

   public static final class Builder extends Resource.Builder<Builder> {

      private String bucket;
      private String object;
      private String entity;
      private Role role;
      private String email;
      private String entityId;
      private String domain;

      /**
       * @see ObjectAccessControl#getBucket()
       */
      public Builder bucket(String bucket) {
         this.bucket = bucket;
         return this;
      }

      /**
       * @see ObjectAccessControl#getObject()
       */
      public Builder object(String object) {
         this.object = object;
         return this;
      }

      /**
       * @see ObjectAccessControl#getRole
       */
      public Builder role(Role role) {
         this.role = role;
         return this;
      }

      /**
       * @see ObjectAccessControl#getEmail()
       */
      public Builder email(String email) {
         this.email = email;
         return this;
      }

      /**
       * @see ObjectAccessControl#getEntity()
       */
      public Builder entity(String entity) {
         this.entity = entity;
         return this;
      }

      /**
       * @see ObjectAccessControl#getEntityId()
       */
      public Builder entityId(String entityId) {
         this.entityId = entityId;
         return this;
      }

      /**
       * @see BucketAccessControl#getDomain()
       */
      public Builder domain(String domain) {
         this.domain = domain;
         return this;
      }

      @Override
      protected Builder self() {
         return this;
      }

      public ObjectAccessControl build() {
         return new ObjectAccessControl(super.id, super.selfLink, bucket, object, entity, role, email, entityId,
                 domain);
      }

      public Builder fromObjectAccessControl(ObjectAccessControl in) {
         return super.fromResource(in)
                 .bucket(in.getBucket())
                 .object(in.getObject())
                 .entity(in.getEntity())
                 .role(in.getRole())
                 .email(in.getEmail())
                 .entityId(in.getEntityId())
                 .domain(in.getDomain());
      }

   }

}
