FROM rust:1.67 as builder

WORKDIR /usr/src/currency-service
COPY . .
RUN cargo install --path .

FROM debian:bullseye-slim

RUN apt-get update
#RUN apt-get install -y extra-runtime-dependencies
RUN rm -rf /var/lib/apt/lists/*

COPY --from=builder /usr/local/cargo/bin/currency-service /usr/local/bin/currency-service
CMD ["currency-service"]
