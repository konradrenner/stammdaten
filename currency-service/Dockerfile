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
EXPOSE 8000
CMD ["./currency-service"]
