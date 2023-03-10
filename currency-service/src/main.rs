use serde::{Serialize};
use rocket::response::content;
use serde_json::json;

#[macro_use] extern crate rocket;

#[derive(Serialize)]
struct ConversionRate {
  fromCurrency: String,
  toCurrency: String,
  conversionRate: f64, 
}

#[get("/conversion-rates?<fromCurrency>&<toCurrency>")]
fn conversionRates(fromCurrency: &str, toCurrency: &str) -> content::RawJson<String> {
    let mut rateValue = 0.8;

    let loweredFrom = fromCurrency.to_uppercase();
    let loweredTo = toCurrency.to_uppercase();

    if loweredFrom.eq(&loweredTo) {
        rateValue = 1.0;
    }

    let cRate = ConversionRate {
        fromCurrency: loweredFrom.to_string(),
        toCurrency: loweredTo.to_string(),
        conversionRate: rateValue,
    };

    let json = serde_json::to_string(&cRate).unwrap();

    content::RawJson(json)
}


#[get("/q/health")]
fn health() -> &'static str {
    "{\"status\": \"UP\"}"
}

#[launch]
fn rocket() -> _ {
    rocket::build().mount("/", routes![conversionRates, health])
}
