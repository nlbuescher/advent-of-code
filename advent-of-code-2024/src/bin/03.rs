use advent_of_code_2024::*;
use code_timing_macros::*;
use std::fs::File;
use std::io::{BufRead, BufReader};
use regex::Regex;

const DAY: &str = "03";
const INPUT_FILE: &str = "input/03.txt";

const TEST: &str = "\
xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))
";

fn main() -> Result<()> {
	start_day(DAY);

	//region Part 1
	println!("=== Part 1 ===");

	fn read_input<R: BufRead>(reader: R) -> Vec<String> {
		reader.lines().flatten().collect()
	}
	
	fn part1<R: BufRead>(reader: R) -> Result<usize> {
		let input = read_input(reader);
		
		let answer = input.into_iter()
			.map(|line| {
				Regex::new(r"mul\((\d+),(\d+)\)").unwrap()
					.captures_iter(line.as_str())
					.map(|capture| {
						capture[1].parse::<usize>().unwrap() * capture[2].parse::<usize>().unwrap()
					})
					.sum::<usize>()
			}).sum();
		
		Ok(answer)
	}

	assert_eq!(161, part1(BufReader::new(TEST.as_bytes()))?);

	let input_file = BufReader::new(File::open(INPUT_FILE)?);
	let result = time_snippet!(part1(input_file)?);
	println!("Result = {result}");
	//endregion

	//region Part 2
	// println!("\n=== Part 2 ===");
	//
	// fn part2<R: BufRead>(reader: R) -> Result<usize> {
	//   Ok(0)
	// }
	//
	// assert_eq!(0, part2(BufReader::new(TEST.as_bytes()))?);
	//
	// let input_file = BufReader::new(File::open(INPUT_FILE)?);
	// let result = time_snippet!(part2(input_file)?);
	// println!("Result = {result}");
	//endregion

	Ok(())
}
