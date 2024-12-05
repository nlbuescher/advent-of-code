use advent_of_code_2024::*;
use code_timing_macros::*;
use regex::Regex;
use std::fs::File;
use std::io::{BufRead, BufReader};
use tap::Pipe;

const DAY: &str = "03";
const INPUT_FILE: &str = "input/03.txt";

const TEST: &str = "\
xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))
";

fn main() -> Result<()> {
	start_day(DAY);

	fn read_input<R: BufRead>(reader: R) -> String {
		reader.bytes().flatten().map(|b| b as char).collect()
	}

	//region Part 1
	println!("=== Part 1 ===");

	fn part1<R: BufRead>(reader: R) -> Result<usize> {
		let input = read_input(reader);

		let answer = Regex::new(r"mul\((?<a>\d+),(?<b>\d+)\)")
			.unwrap()
			.captures_iter(input.as_str())
			.map(|capture| {
				capture["a"].parse::<usize>().unwrap() * capture["b"].parse::<usize>().unwrap()
			})
			.sum::<usize>();

		Ok(answer)
	}

	assert_eq!(161, part1(BufReader::new(TEST.as_bytes()))?);

	let input_file = BufReader::new(File::open(INPUT_FILE)?);
	let result = time_snippet!(part1(input_file)?);
	println!("Result = {result}");
	//endregion

	//region Part 2
	println!("\n=== Part 2 ===");

	fn part2<R: BufRead>(reader: R) -> Result<usize> {
		let input = read_input(reader);

		let answer = Regex::new(r"(do\(\)|don't\(\)|mul\((?<a>\d+),(?<b>\d+)\))")
			.unwrap()
			.captures_iter(input.as_str())
			.fold((true, 0usize), |(doing, acc), capture| {
				let string = capture[1].to_string();

				if string == "do()" {
					(true, acc)
				} else if string == "don't()" {
					(false, acc)
				} else {
					(
						doing,
						if doing {
							acc + capture["a"].parse::<usize>().unwrap()
								* capture["b"].parse::<usize>().unwrap()
						} else {
							acc
						},
					)
				}
			})
			.pipe(|(_, result)| result);

		Ok(answer)
	}

	assert_eq!(48, part2(BufReader::new(TEST.as_bytes()))?);

	let input_file = BufReader::new(File::open(INPUT_FILE)?);
	let result = time_snippet!(part2(input_file)?);
	println!("Result = {result}");
	//endregion

	Ok(())
}
