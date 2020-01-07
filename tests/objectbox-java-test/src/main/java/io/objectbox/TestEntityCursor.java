/*
 * Copyright 2017 ObjectBox Ltd. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.objectbox;

import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

// NOTE: Copied from a plugin project (& removed some unused Properties).
// THIS CODE IS GENERATED BY ObjectBox, DO NOT EDIT.

/**
 * ObjectBox generated Cursor implementation for "TestEntity".
 * Note that this is a low-level class: usually you should stick to the Box class.
 */
public final class TestEntityCursor extends Cursor<TestEntity> {

    // For testing
    public static boolean INT_NULL_HACK;

    @Internal
    static final class Factory implements CursorFactory<TestEntity> {
        @Override
        public Cursor<TestEntity> createCursor(io.objectbox.Transaction tx, long cursorHandle, BoxStore boxStoreForEntities) {
            return new TestEntityCursor(tx, cursorHandle, boxStoreForEntities);
        }
    }

    private static final TestEntity_.TestEntityIdGetter ID_GETTER = TestEntity_.__ID_GETTER;


    private final static int __ID_simpleBoolean = TestEntity_.simpleBoolean.id;
    private final static int __ID_simpleByte = TestEntity_.simpleByte.id;
    private final static int __ID_simpleShort = TestEntity_.simpleShort.id;
    private final static int __ID_simpleInt = TestEntity_.simpleInt.id;
    private final static int __ID_simpleLong = TestEntity_.simpleLong.id;
    private final static int __ID_simpleFloat = TestEntity_.simpleFloat.id;
    private final static int __ID_simpleDouble = TestEntity_.simpleDouble.id;
    private final static int __ID_simpleString = TestEntity_.simpleString.id;
    private final static int __ID_simpleByteArray = TestEntity_.simpleByteArray.id;
    private final static int __ID_simpleShortU = TestEntity_.simpleShortU.id;
    private final static int __ID_simpleIntU = TestEntity_.simpleIntU.id;
    private final static int __ID_simpleLongU = TestEntity_.simpleLongU.id;

    public TestEntityCursor(io.objectbox.Transaction tx, long cursor, BoxStore boxStore) {
        super(tx, cursor, TestEntity_.__INSTANCE, boxStore);
    }

    @Override
    public final long getId(TestEntity entity) {
        return ID_GETTER.getId(entity);
    }

    /**
     * Puts an object into its box.
     *
     * @return The ID of the object within its box.
     */
    @Override
    public final long put(TestEntity entity) {
        String simpleString = entity.getSimpleString();
        int __id8 = simpleString != null ? __ID_simpleString : 0;
        byte[] simpleByteArray = entity.getSimpleByteArray();
        int __id9 = simpleByteArray != null ? __ID_simpleByteArray : 0;

        collect313311(cursor, 0, PUT_FLAG_FIRST,
                __id8, simpleString, 0, null,
                0, null, __id9, simpleByteArray,
                __ID_simpleLong, entity.getSimpleLong(), __ID_simpleLongU, entity.getSimpleLongU(),
                INT_NULL_HACK ? 0 : __ID_simpleInt, entity.getSimpleInt(), __ID_simpleIntU, entity.getSimpleIntU(),
                __ID_simpleShort, entity.getSimpleShort(), __ID_simpleShortU, entity.getSimpleShortU(),
                __ID_simpleFloat, entity.getSimpleFloat(), __ID_simpleDouble, entity.getSimpleDouble());

        long __assignedId = collect004000(cursor, entity.getId(), PUT_FLAG_COMPLETE,
                __ID_simpleByte, entity.getSimpleByte(), __ID_simpleBoolean, entity.getSimpleBoolean() ? 1 : 0,
                0, 0, 0, 0);

        entity.setId(__assignedId);

        return __assignedId;
    }

}
