use std::cmp::min;
use std::fs::File;
use std::io::{BufRead, BufReader};

use advent_of_code_2015::*;
use code_timing_macros::*;

const DAY: &str = "01";
const INPUT_FILE: &str = "advent-of-code-2015/input/01.txt";

const TEST: &str = "\
)())())
";

fn main() -> Result<()> {
	start_day(DAY);

	//region Part 1
	println!("=== Part 1 ===");

	fn part1<R: BufRead>(reader: R) -> Result<i64> {
		let input = reader.lines().next().unwrap_or(Ok(String::new()))?;

		let answer = input.chars().fold(0, |floor, it| {
			if it == '(' {
				floor + 1
			}
			else if it == ')' {
				floor - 1
			}
			else {
				floor
			}
		});

		Ok(answer)
	}

	assert_eq!(-3, part1(BufReader::new(TEST.as_bytes()))?);

	let input_file = BufReader::new(File::open(INPUT_FILE)?);
	let result = time_snippet!(part1(input_file)?);
	println!("Result = {}", result);
	//endregion

	//region Part 2
	println!("\n=== Part 2 ===");

	fn part2<R: BufRead>(reader: R) -> Result<usize> {
		let input = reader.lines().next().unwrap_or(Ok(String::new()))?;

		let mut answer = usize::MAX;
		input.chars().enumerate().fold(0, |floor, (index, it)| {
			if it == '(' {
				floor + 1
			}
			else if it == ')' {
				if floor - 1 < 0 {
					answer = min(answer, index + 1);
				}
				floor - 1
			}
			else {
				floor
			}
		});

		Ok(answer)
	}

	assert_eq!(1, part2(BufReader::new(TEST.as_bytes()))?);

	let input_file = BufReader::new(File::open(INPUT_FILE)?);
	let result = time_snippet!(part2(input_file)?);
	println!("Result = {}", result);
	//endregion

	Ok(())
}
