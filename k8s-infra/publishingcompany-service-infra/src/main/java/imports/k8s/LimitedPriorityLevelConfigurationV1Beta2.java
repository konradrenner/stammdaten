package imports.k8s;

/**
 * LimitedPriorityLevelConfiguration specifies how to handle requests that are subject to limits.
 * <p>
 * It addresses two issues:
 * <p>
 * <ul>
 * <li>How are requests for this priority level limited?</li>
 * <li>What should be done with requests that exceed the limit?</li>
 * </ul>
 */
@javax.annotation.Generated(value = "jsii-pacmak/1.74.0 (build 6d08790)", date = "2023-01-31T09:42:24.412Z")
@software.amazon.jsii.Jsii(module = imports.k8s.$Module.class, fqn = "k8s.LimitedPriorityLevelConfigurationV1Beta2")
@software.amazon.jsii.Jsii.Proxy(LimitedPriorityLevelConfigurationV1Beta2.Jsii$Proxy.class)
public interface LimitedPriorityLevelConfigurationV1Beta2 extends software.amazon.jsii.JsiiSerializable {

    /**
     * `assuredConcurrencyShares` (ACS) configures the execution limit, which is a limit on the number of requests of this priority level that may be exeucting at a given time.
     * <p>
     * ACS must be a positive number. The server's concurrency limit (SCL) is divided among the concurrency-controlled priority levels in proportion to their assured concurrency shares. This produces the assured concurrency value (ACV) --- the number of requests that may be executing at a time --- for each such priority level:
     * <p>
     * ACV(l) = ceil( SCL * ACS(l) / ( sum[priority levels k] ACS(k) ) )
     * <p>
     * bigger numbers of ACS mean more reserved concurrent requests (at the expense of every other PL). This field has a default value of 30.
     */
    default @org.jetbrains.annotations.Nullable java.lang.Number getAssuredConcurrencyShares() {
        return null;
    }

    /**
     * `limitResponse` indicates what to do with requests that can not be executed right now.
     */
    default @org.jetbrains.annotations.Nullable imports.k8s.LimitResponseV1Beta2 getLimitResponse() {
        return null;
    }

    /**
     * @return a {@link Builder} of {@link LimitedPriorityLevelConfigurationV1Beta2}
     */
    static Builder builder() {
        return new Builder();
    }
    /**
     * A builder for {@link LimitedPriorityLevelConfigurationV1Beta2}
     */
    public static final class Builder implements software.amazon.jsii.Builder<LimitedPriorityLevelConfigurationV1Beta2> {
        java.lang.Number assuredConcurrencyShares;
        imports.k8s.LimitResponseV1Beta2 limitResponse;

        /**
         * Sets the value of {@link LimitedPriorityLevelConfigurationV1Beta2#getAssuredConcurrencyShares}
         * @param assuredConcurrencyShares `assuredConcurrencyShares` (ACS) configures the execution limit, which is a limit on the number of requests of this priority level that may be exeucting at a given time.
         *                                 ACS must be a positive number. The server's concurrency limit (SCL) is divided among the concurrency-controlled priority levels in proportion to their assured concurrency shares. This produces the assured concurrency value (ACV) --- the number of requests that may be executing at a time --- for each such priority level:
         *                                 <p>
         *                                 ACV(l) = ceil( SCL * ACS(l) / ( sum[priority levels k] ACS(k) ) )
         *                                 <p>
         *                                 bigger numbers of ACS mean more reserved concurrent requests (at the expense of every other PL). This field has a default value of 30.
         * @return {@code this}
         */
        public Builder assuredConcurrencyShares(java.lang.Number assuredConcurrencyShares) {
            this.assuredConcurrencyShares = assuredConcurrencyShares;
            return this;
        }

        /**
         * Sets the value of {@link LimitedPriorityLevelConfigurationV1Beta2#getLimitResponse}
         * @param limitResponse `limitResponse` indicates what to do with requests that can not be executed right now.
         * @return {@code this}
         */
        public Builder limitResponse(imports.k8s.LimitResponseV1Beta2 limitResponse) {
            this.limitResponse = limitResponse;
            return this;
        }

        /**
         * Builds the configured instance.
         * @return a new instance of {@link LimitedPriorityLevelConfigurationV1Beta2}
         * @throws NullPointerException if any required attribute was not provided
         */
        @Override
        public LimitedPriorityLevelConfigurationV1Beta2 build() {
            return new Jsii$Proxy(this);
        }
    }

    /**
     * An implementation for {@link LimitedPriorityLevelConfigurationV1Beta2}
     */
    @software.amazon.jsii.Internal
    final class Jsii$Proxy extends software.amazon.jsii.JsiiObject implements LimitedPriorityLevelConfigurationV1Beta2 {
        private final java.lang.Number assuredConcurrencyShares;
        private final imports.k8s.LimitResponseV1Beta2 limitResponse;

        /**
         * Constructor that initializes the object based on values retrieved from the JsiiObject.
         * @param objRef Reference to the JSII managed object.
         */
        protected Jsii$Proxy(final software.amazon.jsii.JsiiObjectRef objRef) {
            super(objRef);
            this.assuredConcurrencyShares = software.amazon.jsii.Kernel.get(this, "assuredConcurrencyShares", software.amazon.jsii.NativeType.forClass(java.lang.Number.class));
            this.limitResponse = software.amazon.jsii.Kernel.get(this, "limitResponse", software.amazon.jsii.NativeType.forClass(imports.k8s.LimitResponseV1Beta2.class));
        }

        /**
         * Constructor that initializes the object based on literal property values passed by the {@link Builder}.
         */
        protected Jsii$Proxy(final Builder builder) {
            super(software.amazon.jsii.JsiiObject.InitializationMode.JSII);
            this.assuredConcurrencyShares = builder.assuredConcurrencyShares;
            this.limitResponse = builder.limitResponse;
        }

        @Override
        public final java.lang.Number getAssuredConcurrencyShares() {
            return this.assuredConcurrencyShares;
        }

        @Override
        public final imports.k8s.LimitResponseV1Beta2 getLimitResponse() {
            return this.limitResponse;
        }

        @Override
        @software.amazon.jsii.Internal
        public com.fasterxml.jackson.databind.JsonNode $jsii$toJson() {
            final com.fasterxml.jackson.databind.ObjectMapper om = software.amazon.jsii.JsiiObjectMapper.INSTANCE;
            final com.fasterxml.jackson.databind.node.ObjectNode data = com.fasterxml.jackson.databind.node.JsonNodeFactory.instance.objectNode();

            if (this.getAssuredConcurrencyShares() != null) {
                data.set("assuredConcurrencyShares", om.valueToTree(this.getAssuredConcurrencyShares()));
            }
            if (this.getLimitResponse() != null) {
                data.set("limitResponse", om.valueToTree(this.getLimitResponse()));
            }

            final com.fasterxml.jackson.databind.node.ObjectNode struct = com.fasterxml.jackson.databind.node.JsonNodeFactory.instance.objectNode();
            struct.set("fqn", om.valueToTree("k8s.LimitedPriorityLevelConfigurationV1Beta2"));
            struct.set("data", data);

            final com.fasterxml.jackson.databind.node.ObjectNode obj = com.fasterxml.jackson.databind.node.JsonNodeFactory.instance.objectNode();
            obj.set("$jsii.struct", struct);

            return obj;
        }

        @Override
        public final boolean equals(final Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            LimitedPriorityLevelConfigurationV1Beta2.Jsii$Proxy that = (LimitedPriorityLevelConfigurationV1Beta2.Jsii$Proxy) o;

            if (this.assuredConcurrencyShares != null ? !this.assuredConcurrencyShares.equals(that.assuredConcurrencyShares) : that.assuredConcurrencyShares != null) return false;
            return this.limitResponse != null ? this.limitResponse.equals(that.limitResponse) : that.limitResponse == null;
        }

        @Override
        public final int hashCode() {
            int result = this.assuredConcurrencyShares != null ? this.assuredConcurrencyShares.hashCode() : 0;
            result = 31 * result + (this.limitResponse != null ? this.limitResponse.hashCode() : 0);
            return result;
        }
    }
}
