use advent_of_code_2024::*;
use code_timing_macros::*;
use std::fs::File;
use std::io::{BufRead, BufReader};
use std::str::FromStr;
use tap::Pipe;

const DAY: &str = "01"; // TODO: Fill the day
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

    //region Part 1
    println!("=== Part 1 ===");

    fn part1<R: BufRead>(reader: R) -> Result<i64> {
        let mut input: (Vec<_>, Vec<_>) = reader.lines().flatten()
          .map(|line| {
              line.split("   ")
                .flat_map(|string| i64::from_str(string))
                .pipe_borrow_mut(|list| (list.next().unwrap(), list.next().unwrap()))
          })
          .unzip();

        let answer = input.pipe_borrow_mut(|(a, b)| {
            a.sort(); b.sort();
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
