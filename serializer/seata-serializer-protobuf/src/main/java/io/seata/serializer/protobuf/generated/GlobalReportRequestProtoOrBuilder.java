// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: globalReportRequest.proto

package io.seata.serializer.protobuf.generated;

public interface GlobalReportRequestProtoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:io.seata.protocol.protobuf.GlobalReportRequestProto)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.io.seata.protocol.protobuf.AbstractGlobalEndRequestProto abstractGlobalEndRequest = 1;</code>
   * @return Whether the abstractGlobalEndRequest field is set.
   */
  boolean hasAbstractGlobalEndRequest();
  /**
   * <code>.io.seata.protocol.protobuf.AbstractGlobalEndRequestProto abstractGlobalEndRequest = 1;</code>
   * @return The abstractGlobalEndRequest.
   */
  io.seata.serializer.protobuf.generated.AbstractGlobalEndRequestProto getAbstractGlobalEndRequest();
  /**
   * <code>.io.seata.protocol.protobuf.AbstractGlobalEndRequestProto abstractGlobalEndRequest = 1;</code>
   */
  io.seata.serializer.protobuf.generated.AbstractGlobalEndRequestProtoOrBuilder getAbstractGlobalEndRequestOrBuilder();

  /**
   * <code>.io.seata.protocol.protobuf.GlobalStatusProto globalStatus = 2;</code>
   * @return The enum numeric value on the wire for globalStatus.
   */
  int getGlobalStatusValue();
  /**
   * <code>.io.seata.protocol.protobuf.GlobalStatusProto globalStatus = 2;</code>
   * @return The globalStatus.
   */
  io.seata.serializer.protobuf.generated.GlobalStatusProto getGlobalStatus();
}