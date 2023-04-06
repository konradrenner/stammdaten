use serde::{Serialize, Deserialize};
use std::net::SocketAddr;
use axum_tracing_opentelemetry::{opentelemetry_tracing_layer, response_with_trace_layer};
use axum::{
    extract::Query,
    routing::get,
    response::Json,
    BoxError,
    Router,
};

#[derive(Serialize)]
struct ConversionRate {
  fromCurrency: String,
  toCurrency: String,
  conversionRate: f64, 
}

#[derive(Deserialize)]
struct ConversionQuery {
  fromCurrency: String,
  toCurrency: String,
}

async fn conversionRates(Query(query_params): Query<ConversionQuery>) -> Json<ConversionRate> {
    let mut rateValue = 0.8;

    let loweredFrom = query_params.fromCurrency.to_uppercase();
    let loweredTo = query_params.toCurrency.to_uppercase();

    if loweredFrom.eq(&loweredTo) {
        rateValue = 1.0;
    }

    let cRate = ConversionRate {
        fromCurrency: loweredFrom.to_string(),
        toCurrency: loweredTo.to_string(),
        conversionRate: rateValue,
    };

    Json(cRate)
}


async fn health() -> Json<String> {
    Json("{\"status\": \"UP\"}".to_string())
}

#[tokio::main]
async fn main()-> Result<(), BoxError> {
    // https://github.com/davidB/axum-tracing-opentelemetry/blob/c2d62bc8bffb9282981d32c728d2c4ed2e64d735/examples/otlp/src/main.rs
    axum_tracing_opentelemetry::tracing_subscriber_ext::init_subscribers()?;

    let app = Router::new()
        .route("/conversion-rates", get(conversionRates))
        .layer(response_with_trace_layer())
        .layer(opentelemetry_tracing_layer())
        .route("/q/health", get(health));

    let addr = SocketAddr::from(([0, 0, 0, 0], 8000));
    println!("listening on {}", addr);
    axum::Server::bind(&addr)
        .serve(app.into_make_service())
        .with_graceful_shutdown(shutdown_signal())
        .await?;

    Ok(())
}


async fn shutdown_signal() {
    let ctrl_c = async {
        tokio::signal::ctrl_c()
            .await
            .expect("failed to install Ctrl+C handler");
    };

    #[cfg(unix)]
    let terminate = async {
        tokio::signal::unix::signal(tokio::signal::unix::SignalKind::terminate())
            .expect("failed to install signal handler")
            .recv()
            .await;
    };

    #[cfg(not(unix))]
    let terminate = std::future::pending::<()>();

    tokio::select! {
        _ = ctrl_c => {},
        _ = terminate => {},
    }

    tracing::warn!("signal received, starting graceful shutdown");
    opentelemetry::global::shutdown_tracer_provider();
}