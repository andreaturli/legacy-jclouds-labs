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
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;
import org.jclouds.collect.IterableWithMarker;

import java.beans.ConstructorProperties;
import java.util.Iterator;

import static com.google.common.base.Objects.equal;
import static com.google.common.base.Objects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The collection returned from any <code>listFirstPage()</code> method.
 *
 * @author Andrea Turli
 *
 */
public class ListPage<T> extends IterableWithMarker<T> {

   private final Resource.Kind kind;
   private final String nextPageToken;
   private final Iterable<T> items;

   @ConstructorProperties({"kind", "nextPageToken", "items" })
   protected ListPage(Resource.Kind kind, String nextPageToken, Iterable<T> items) {
      this.kind = checkNotNull(kind, "kind");
      this.nextPageToken = nextPageToken;
      this.items = items != null ? ImmutableSet.copyOf(items) : ImmutableSet.<T>of();
   }

   public Resource.Kind getKind() {
      return kind;
   }

   @Override
   public Optional<Object> nextMarker() {
      return Optional.<Object>fromNullable(nextPageToken);
   }

   @Override
   public Iterator<T> iterator() {
      return checkNotNull(items, "items").iterator();
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(kind);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      ListPage<?> that = ListPage.class.cast(obj);
      return equal(this.kind, that.kind);
   }

   /**
    * {@inheritDoc}
    */
   protected Objects.ToStringHelper string() {
      return toStringHelper(this)
              .omitNullValues()
              .add("kind", kind)
              .add("nextPageToken", nextPageToken)
              .add("items", items);
   }

   /**
    * {@inheritDoc}
    */
   @Override
   public String toString() {
      return string().toString();
   }

   public static <T> Builder<T> builder() {
      return new Builder<T>();
   }

   public Builder<T> toBuilder() {
      return new Builder<T>().fromPagedList(this);
   }

   public static final class Builder<T> {

      private Resource.Kind kind;
      private String nextPageToken;
      private ImmutableSet.Builder<T> items = ImmutableSet.builder();

      public Builder<T> kind(Resource.Kind kind) {
         this.kind = kind;
         return this;
      }

      public Builder<T> addItem(T item) {
         this.items.add(item);
         return this;
      }

      public Builder<T> items(Iterable<T> items) {
         this.items.addAll(items);
         return this;
      }

      public Builder<T> nextPageToken(String nextPageToken) {
         this.nextPageToken = nextPageToken;
         return this;
      }

      public ListPage<T> build() {
         return new ListPage<T>(kind, nextPageToken, items.build());
      }

      public Builder<T> fromPagedList(ListPage<T> in) {
         return this
                 .kind(in.getKind())
                 .nextPageToken((String) in.nextMarker().orNull())
                 .items(in);

      }
   }
}
