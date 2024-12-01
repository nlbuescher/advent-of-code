use std::fmt;
use std::io;

pub fn start_day(day: &str) {
	println!("Advent of Code 2015 – Day {day:0>2}");
}

#[derive(Debug)]
pub struct Error {
	message: String,
}

impl fmt::Display for Error {
	fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
		write!(f, "{}", self.message)
	}
}

impl From<io::Error> for Error {
	fn from(value: io::Error) -> Self {
		Error {
			message: value.to_string(),
		}
	}
}

impl From<std::num::ParseIntError> for Error {
	fn from(value: std::num::ParseIntError) -> Self {
		Error {
			message: value.to_string(),
		}
	}
}

pub type Result<T> = std::result::Result<T, Error>;

#[cfg(test)]
mod tests {
	use super::*;

	#[test]
	fn it_works() {
		start_day("00");
	}
}
