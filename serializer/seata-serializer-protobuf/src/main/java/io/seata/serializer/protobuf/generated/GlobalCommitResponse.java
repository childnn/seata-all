// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: globalCommitResponse.proto

package io.seata.serializer.protobuf.generated;

public final class GlobalCommitResponse {
  private GlobalCommitResponse() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_io_seata_protocol_protobuf_GlobalCommitResponseProto_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_io_seata_protocol_protobuf_GlobalCommitResponseProto_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\032globalCommitResponse.proto\022\032io.seata.p" +
      "rotocol.protobuf\032\037abstractGlobalEndRespo" +
      "nse.proto\"z\n\031GlobalCommitResponseProto\022]" +
      "\n\031abstractGlobalEndResponse\030\001 \001(\0132:.io.s" +
      "eata.protocol.protobuf.AbstractGlobalEnd" +
      "ResponseProtoB@\n&io.seata.serializer.pro" +
      "tobuf.generatedB\024GlobalCommitResponseP\001b" +
      "\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          io.seata.serializer.protobuf.generated.AbstractGlobalEndResponse.getDescriptor(),
        });
    internal_static_io_seata_protocol_protobuf_GlobalCommitResponseProto_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_io_seata_protocol_protobuf_GlobalCommitResponseProto_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_io_seata_protocol_protobuf_GlobalCommitResponseProto_descriptor,
        new java.lang.String[] { "AbstractGlobalEndResponse", });
    io.seata.serializer.protobuf.generated.AbstractGlobalEndResponse.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
