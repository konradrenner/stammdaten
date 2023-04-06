# FROM rust:1.67 as builder

# WORKDIR /usr/src/currency-service
# COPY . .
# RUN cargo install --path .

# FROM debian:bullseye-slim

# RUN apt-get update
# #RUN apt-get install -y extra-runtime-dependencies
# RUN rm -rf /var/lib/apt/lists/*

# COPY --from=builder /usr/local/cargo/bin/currency-service /usr/local/bin/currency-service

# EXPOSE 8000

# CMD ["currency-service"]

FROM rust as builder
WORKDIR /app
COPY . .
# required for otel
RUN apt-get update
RUN apt-get install -y protobuf-compiler
RUN cargo build --release

FROM rust as runtime
WORKDIR /app
COPY --from=builder /app/target/release/currency-service .
COPY --from=builder /app/Rocket.toml .
EXPOSE 8000
# ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://localhost:4317
ENV OTEL_RESOURCE_ATTRIBUTES=service.name=currency-service
CMD ["./currency-service"]
