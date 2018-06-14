/*
 * Copyright 2018, Yahoo! Inc. Licensed under the terms of the
 * Apache License 2.0. See LICENSE file at the project root for terms.
 */

package com.yahoo.memory;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Implementation of {@link WritableMemory} for ByteBuffer, native byte order.
 *
 * @author Roman Leventov
 * @author Lee Rhodes
 */
class BBWritableMemoryImpl extends WritableMemoryImpl {
  private final Object unsafeObj;
  private final long nativeBaseOffset;
  private final ByteBuffer byteBuf;

  BBWritableMemoryImpl(
      final long unsafeObj,
      final long nativeBaseOffset,
      final long regionOffset,
      final long capacityBytes,
      final boolean readOnly,
      final ByteBuffer byteBuf) {
    super(unsafeObj, nativeBaseOffset, regionOffset, capacityBytes, readOnly, null, null);
    this.unsafeObj = unsafeObj;
    this.nativeBaseOffset = nativeBaseOffset;
    this.byteBuf = byteBuf;
  }

  @Override
  public ByteBuffer getByteBuffer() {
    return byteBuf;
  }

  @Override
  public ByteOrder getByteOrder() {
    return Util.nativeOrder;
  }

  @Override //TODO remove from baseWMemImpl NOTE WRITABLE ONLY
  public MemoryRequestServer getMemoryRequestServer() {
    return null;
  }

  @Override
  long getNativeBaseOffset() {
    return nativeBaseOffset;
  }

  @Override
  Object getUnsafeObject() {
    return unsafeObj;
  }

  @Override
  StepBoolean getValid() {
    return null;
  }

  @Override
  public boolean isValid() {
    return true;
  }

  @Override
  public void setMemoryRequestServer(final MemoryRequestServer svr) {

  }
}

