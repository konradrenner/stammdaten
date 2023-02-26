#[macro_use] extern crate rocket;

#[get("/rust")]
fn greeting() -> &'static str {
    "Hello from Rust"
}

#[launch]
fn rocket() -> _ {
    rocket::build().mount("/", routes![greeting])
}
