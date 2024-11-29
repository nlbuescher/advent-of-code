pub fn start_day(day: &str) {
	println!("Advent of Code 2015 – Day {day:0>2}");
}

#[cfg(test)]
mod tests {
	use super::*;

	#[test]
	fn it_works() {
		start_day("00");
	}
}
