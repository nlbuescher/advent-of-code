use advent_of_code_2024::*;
use code_timing_macros::*;
use std::fs::File;
use std::io::{BufRead, BufReader};
use std::str::FromStr;
use tap::Pipe;

const DAY: &str = "02";
const INPUT_FILE: &str = "input/02.txt";

const TEST: &str = "\
7 6 4 2 1
1 2 7 8 9
9 7 6 2 1
1 3 2 4 5
8 6 4 4 1
1 3 6 7 9
";

fn main() -> Result<()> {
	start_day(DAY);

	//region Part 1
	println!("=== Part 1 ===");

	fn read_input<R: BufRead>(reader: R) -> Vec<Vec<usize>> {
		reader.lines().flatten()
			.map(|line| {
				line.split(' ')
					.flat_map(usize::from_str)
					.collect::<Vec<_>>()
			})
			.collect::<Vec<_>>()
	}

	fn part1<R: BufRead>(reader: R) -> Result<usize> {
		let input = read_input(reader);

		let answer = input.iter()
			.filter(|report| {
				report.into_iter()
					.pipe(|iter| {
						iter.clone().zip(iter.clone().skip(1))
					})
					.pipe_borrow_mut(|list| {
						let is_ascending = list.clone().all(|(a, b)| *a < *b);
						let is_descending = list.clone().all(|(a, b)| *a > *b);
						let is_gradual = list.all(|(a, b)| a.abs_diff(*b) > 0 && a.abs_diff(*b) < 4);
						
						(is_ascending || is_descending) && is_gradual
					})
			})
			.count();

		Ok(answer)
	}

	assert_eq!(2, part1(BufReader::new(TEST.as_bytes()))?);

	let input_file = BufReader::new(File::open(INPUT_FILE)?);
	let result = time_snippet!(part1(input_file)?);
	println!("Result = {result}");
	//endregion

	//region Part 2
	// println!("\n=== Part 2 ===");
	// 
	// fn part2<R: BufRead>(reader: R) -> Result<usize> {
	//     Ok(0)
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
