/*
 * Copyright 2018, Yahoo! Inc. Licensed under the terms of the
 * Apache License 2.0. See LICENSE file at the project root for terms.
 */

package com.yahoo.memory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Implementation of {@link WritableBuffer} for heap-based, non-native byte order.
 *
 * @author Roman Leventov
 * @author Lee Rhodes
 */
final class HeapNonNativeWritableBufferImpl extends NonNativeWritableBufferImpl {
  private final Object unsafeObj;

  HeapNonNativeWritableBufferImpl(
      final Object unsafeObj,
      final long regionOffset,
      final long capacityBytes,
      final boolean readOnly,
      final BaseWritableMemoryImpl originMemory) {
    super(unsafeObj, 0L, regionOffset, capacityBytes, readOnly, originMemory);
    this.unsafeObj = unsafeObj;
  }

  @Override
  BaseWritableBufferImpl toWritableRegion(final long offsetBytes, final long capacityBytes,
      final boolean localReadOnly, final ByteOrder byteOrder) {
    return Util.isNativeOrder(byteOrder)
        ? new HeapWritableBufferImpl(
            unsafeObj, getRegionOffset(offsetBytes), capacityBytes, localReadOnly, originMemory)
        : new HeapNonNativeWritableBufferImpl(
            unsafeObj, getRegionOffset(offsetBytes), capacityBytes, localReadOnly, originMemory);
  }

  @Override
  BaseWritableBufferImpl toDuplicate(final boolean localReadOnly, final ByteOrder byteOrder) {
    return Util.isNativeOrder(byteOrder)
        ? new HeapWritableBufferImpl(
            unsafeObj, getRegionOffset(), getCapacity(), localReadOnly, originMemory)
        : new HeapNonNativeWritableBufferImpl(
            unsafeObj, getRegionOffset(), getCapacity(), localReadOnly, originMemory);
  }

  @Override
  public ByteBuffer getByteBuffer() {
    return null;
  }

  @Override
  public ByteOrder getByteOrder() {
    assertValid();
    return Util.nonNativeOrder;
  }

  @Override
  public MemoryRequestServer getMemoryRequestServer() {
    return null;
  }

  @Override
  long getNativeBaseOffset() {
    return 0;
  }

  @Override
  Object getUnsafeObject() {
    return unsafeObj;
  }

  @Override
  public boolean isValid() {
    return true;
  }

  @Override
  void setMemoryRequestServer(final MemoryRequestServer svr) {
    //do nothing
  }
}
