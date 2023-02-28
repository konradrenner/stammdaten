use serde::{Serialize};

#[macro_use] extern crate rocket;

#[derive(Serialize)]
struct ConversionRate {
  fromCurrency: String,
  toCurrency: String,
  conversionRate: f64, 
}

#[get("/conversion-rates?<fromCurrency>&<toCurrency>")]
fn conversionRates(fromCurrency: &str, toCurrency: &str) -> String {
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

    serde_json::to_string(&cRate).unwrap()
}

#[launch]
fn rocket() -> _ {
    rocket::build().mount("/", routes![conversionRates])
}
