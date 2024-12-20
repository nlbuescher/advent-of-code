use advent_of_code_2024::*;
use code_timing_macros::*;
use std::fs::File;
use std::io::{BufRead, BufReader};
use std::str::FromStr;
use tap::Pipe;

const DAY: &str = "01";
const INPUT_FILE: &str = "input/01.txt";

const TEST: &str = "\
3   4
4   3
2   5
1   3
3   9
3   3
";

fn main() -> Result<()> {
	start_day(DAY);

	fn read_input<R: BufRead>(reader: R) -> (Vec<i64>, Vec<i64>) {
		reader
			.lines()
			.flatten()
			.map(|line| {
				line.split("   ")
					.flat_map(|string| i64::from_str(string))
					.pipe_borrow_mut(|list| (list.next().unwrap(), list.next().unwrap()))
			})
			.unzip()
	}

	//region Part 1
	println!("=== Part 1 ===");

	fn part1<R: BufRead>(reader: R) -> Result<i64> {
		let mut input: (Vec<_>, Vec<_>) = read_input(reader);

		let answer = input.pipe_borrow_mut(|(a, b)| {
			a.sort();
			b.sort();
			a.iter().zip(b.iter()).map(|(a, b)| (a - b).abs()).sum()
		});

		Ok(answer)
	}

	assert_eq!(11, part1(BufReader::new(TEST.as_bytes()))?);

	let input_file = BufReader::new(File::open(INPUT_FILE)?);
	let result = time_snippet!(part1(input_file)?);
	println!("Result = {result}");
	//endregion

	//region Part 2
	println!("\n=== Part 2 ===");

	fn part2<R: BufRead>(reader: R) -> Result<i64> {
		let input = read_input(reader);

		let answer = input
			.0
			.iter()
			.map(|target| *target * input.1.iter().filter(|it| *it == target).count() as i64)
			.sum();

		Ok(answer)
	}

	assert_eq!(31, part2(BufReader::new(TEST.as_bytes()))?);

	let input_file = BufReader::new(File::open(INPUT_FILE)?);
	let result = time_snippet!(part2(input_file)?);
	println!("Result = {result}");
	//endregion

	Ok(())
}
